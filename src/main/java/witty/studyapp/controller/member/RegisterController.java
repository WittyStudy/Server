package witty.studyapp.controller.member;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import witty.studyapp.dto.member.MemberRegisterDTO;
import witty.studyapp.entity.Member;
import witty.studyapp.execption.RegisterArgumentException;
import witty.studyapp.service.member.MemberService;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/members")
public class RegisterController {

    private final MemberService memberService;

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
}
