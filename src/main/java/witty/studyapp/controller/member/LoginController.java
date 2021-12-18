package witty.studyapp.controller.member;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import witty.studyapp.dto.member.MemberLoginDTO;
import witty.studyapp.entity.Member;
import witty.studyapp.execption.custom.LoginArgumentException;
import witty.studyapp.execption.custom.UnknownException;
import witty.studyapp.service.member.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static witty.studyapp.constant.session.SessionConstant.LOGIN_MEMBER;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/members")
public class LoginController {

    private final MemberService memberService;

    @PostMapping("/login")
    public Object login(@RequestBody @Validated MemberLoginDTO memberLoginDTO, BindingResult bindingResult,
                        HttpServletRequest request) {
        log.info("/login called.");

        if (bindingResult.hasErrors()) {
            log.info("binding result error. [/members/login]");
            throw new LoginArgumentException();
        }

        return memberService.login(Member.builder()
                .email(memberLoginDTO.getEmail())
                .password(memberLoginDTO.getPassword())
                .build())
                .map((member) -> {
            HttpSession session = request.getSession(true);
            session.setAttribute(LOGIN_MEMBER, member);
            return member;
        }).orElseThrow(()-> {
            log.info("login fail.");
            return new UnknownException();
        });
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession(false).invalidate();
        return "OK";
    }
}
