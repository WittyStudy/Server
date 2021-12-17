package witty.studyapp.controller.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import witty.studyapp.annotation.Login;
import witty.studyapp.dto.member.MemberRegisterDTO;
import witty.studyapp.entity.Member;
import witty.studyapp.execption.RegisterArgumentException;
import witty.studyapp.service.member.MemberService;

import java.util.List;

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
