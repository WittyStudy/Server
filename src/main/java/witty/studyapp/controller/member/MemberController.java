package witty.studyapp.controller.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import witty.studyapp.dto.member.MemberLoginDTO;
import witty.studyapp.dto.member.MemberRegisterDTO;
import witty.studyapp.entity.Member;
import witty.studyapp.execption.LoginArgumentException;
import witty.studyapp.service.member.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static witty.studyapp.constant.session.SessionConstant.LOGIN_MEMBER;
import static witty.studyapp.constant.session.SessionConstant.LOGIN_UUID;

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
    @GetMapping
    public List<Member> test() {
        return memberService.getAllMembers();
    }

    @PostMapping("/register")
    public Long register(@RequestBody @Validated MemberRegisterDTO memberRegisterDTO) {
        Member member = new Member();
        member.setName(memberRegisterDTO.getName());
        member.setPassword(memberRegisterDTO.getPassword());
        member.setEmail(memberRegisterDTO.getEmail());
        return memberService.register(member);
    }

    @PostMapping("/login")
    public Object login(@RequestBody @Validated MemberLoginDTO memberLoginDTO, BindingResult bindingResult,
                        HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            log.info("binding result error. [{}]", request.getAttribute(LOGIN_UUID));
            throw new LoginArgumentException("로그인 형태가 올바르지 않습니다");
        }

        Member member = new Member();
        member.setEmail(memberLoginDTO.getEmail());
        member.setPassword(memberLoginDTO.getPassword());

        return memberService.login(member).map((m) -> {
            HttpSession session = request.getSession(true);
            session.setAttribute(LOGIN_MEMBER, m);
            return m;
        }).orElseThrow(()-> new LoginArgumentException("이메일과 비밀번호가 올바르지 않습니다."));
    }

    @PatchMapping("/{memberId}/name")
    public Long patchMemberName(@PathVariable long memberId, @RequestBody String name) {
        return memberService.updateMemberName(memberId, name);
    }

    @PatchMapping("/{memberId}/password")
    public Long patchMemberPassword(@PathVariable long memberId, @RequestBody String password) {
        return memberService.updateMemberPassword(memberId, password);
    }

    @DeleteMapping("/{memberId}")
    public Long deleteMemberById(@PathVariable long memberId) {
        return memberService.deleteMember(memberId);
    }
}
