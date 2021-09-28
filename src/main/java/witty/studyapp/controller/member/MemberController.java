package witty.studyapp.controller.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import witty.studyapp.dto.member.MemberLoginDTO;
import witty.studyapp.dto.member.MemberRegisterDTO;
import witty.studyapp.service.member.MemberService;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public Long register(@RequestBody MemberRegisterDTO memberRegisterDTO){
        return memberService.register(memberRegisterDTO);
    }

    @PostMapping("/{memberId}/login")
    public Object login(@RequestBody MemberLoginDTO memberLoginDTO){
        return memberService.login(memberLoginDTO);
    }
}
