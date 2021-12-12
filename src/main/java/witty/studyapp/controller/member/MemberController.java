package witty.studyapp.controller.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import witty.studyapp.annotation.Login;
import witty.studyapp.dto.member.MemberLoginDTO;
import witty.studyapp.dto.member.MemberRegisterDTO;
import witty.studyapp.entity.Member;
import witty.studyapp.execption.LoginArgumentException;
import witty.studyapp.execption.NotLoginMemberException;
import witty.studyapp.execption.RegisterArgumentException;
import witty.studyapp.service.member.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static witty.studyapp.constant.session.SessionConstant.LOGIN_MEMBER;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    /**
     * TEST CODE
     *
     * @return All members
     */
    @GetMapping("/test")
    public List<Member> test() {
        return memberService.getAllMembers();
    }

    @PostMapping("/register")
    public Long register(@RequestBody @Validated MemberRegisterDTO memberRegisterDTO, BindingResult bindingResult) {
        log.info("/register called.");

        if(bindingResult.hasErrors()){
            log.info("binding result error. [/members/register]");
            throw new RegisterArgumentException("회원가입 형태가 올바르지 않습니다.");
        }

        Member member = new Member();
        member.setName(memberRegisterDTO.getName());
        member.setPassword(memberRegisterDTO.getPassword());
        member.setEmail(memberRegisterDTO.getEmail());
        return memberService.register(member);
    }

    @PostMapping("/login")
    public Object login(@RequestBody @Validated MemberLoginDTO memberLoginDTO, BindingResult bindingResult,
                        HttpServletRequest request) {
        log.info("/login called.");

        if (bindingResult.hasErrors()) {
            log.info("binding result error. [/members/login]");
            throw new LoginArgumentException("로그인 형태가 올바르지 않습니다.");
        }

        Member member = new Member();
        member.setEmail(memberLoginDTO.getEmail());
        member.setPassword(memberLoginDTO.getPassword());

        return memberService.login(member).map((m) -> {
            HttpSession session = request.getSession(true);
            session.setAttribute(LOGIN_MEMBER, m);
            return m;
        }).orElseThrow(()-> {
            log.info("login fail.");
            return new LoginArgumentException("이메일과 비밀번호가 올바르지 않습니다.");
        });
    }

    @PostMapping("/logout")
    public String logout(@Login Member loginMember, HttpServletRequest request){
        if(loginMember == null){
            throw new NotLoginMemberException("세션이 유효하지 않습니다.");
        }

        request.getSession().setMaxInactiveInterval(0);
        return "OK";
    }

    @PatchMapping("/name")
    public Long patchMemberName(@Login Member loginMember, @RequestBody String name) {
        return memberService.updateMemberName(loginMember.getId(), name);
    }

    @PatchMapping("/password")
    public Long patchMemberPassword(@Login Member loginMember, @RequestBody String password) {
        return memberService.updateMemberPassword(loginMember.getId(), password);
    }

    @DeleteMapping
    public Long deleteMemberById(@Login Member loginMember) {
        return memberService.deleteMember(loginMember.getId());
    }
}
