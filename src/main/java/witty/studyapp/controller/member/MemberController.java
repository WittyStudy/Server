package witty.studyapp.controller.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import witty.studyapp.dto.member.MemberLoginDTO;
import witty.studyapp.dto.member.MemberRegisterDTO;
import witty.studyapp.entity.Member;
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
     * @return All members
     */
    @GetMapping
    public List<Member> test(){
        return memberService.getAllMembers();
    }

    @PostMapping
    public Long register(@RequestBody MemberRegisterDTO memberRegisterDTO){
        return memberService.register(memberRegisterDTO);
    }

    @PostMapping("/{memberId}/login")
    public Object login(@PathVariable long memberId, @RequestBody MemberLoginDTO memberLoginDTO){
        return memberService.login(memberId, memberLoginDTO);
    }

    @PatchMapping("/{memberId}/update/name")
    public Long patchMemberName(@PathVariable long memberId, @RequestBody String name){
        return memberService.updateMemberName(memberId, name);
    }

    @PatchMapping("/{memberId}/update/password")
    public Long patchMemberPassword(@PathVariable long memberId, @RequestBody String password){
        return memberService.updateMemberPassword(memberId,password);
    }

    @DeleteMapping("/{memberId}")
    public Long deleteMemberById(@PathVariable long memberId){
        return memberService.deleteMember(memberId);
    }
}
