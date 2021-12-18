package witty.studyapp.service.member.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import witty.studyapp.entity.Member;
import witty.studyapp.repository.member.MemberRepository;
import witty.studyapp.service.member.MemberService;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberServiceImplTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    private final String EMAIL = "testtest@naver.com";

    @Test
    @DisplayName("사용자 회원가입 서비스 테스트")
    void register() {
        Member member = createDemoUser(EMAIL);
        memberService.register(member);
        assertThat(memberRepository.findByEmail(EMAIL).isPresent()).isTrue();
    }

    private Member createDemoUser(String email) {
        Member member = new Member();
        member.setEmail(email);
        member.setPassword("secret!secret");
        member.setName("testName");
        return member;
    }

    @Test
    @DisplayName("사용자 로그인 서비스 테스트")
    void login() {
        Member member = createDemoUser(EMAIL);
        memberService.register(member);
        Optional<Member> login = memberService.login(member);
        assertThat(login.isPresent()).isTrue();
    }

    @Test
    @DisplayName("사용자 이름 변경 서비스 테스트")
    void updateMemberName() {
        Member member = createDemoUser(EMAIL);
        Long memberId = memberService.register(member);
        String newName = "newTestName";
        Long result = memberService.updateMemberName(member.getId(), newName);
        assertThat(result).isNotEqualTo(0L);
        assertThat(memberId).isEqualTo(member.getId());
        assertThat(memberService.getMemberById(member.getId()).get().getName()).isEqualTo(newName);
    }

    @Test
    @DisplayName("사용자 비밀번호 변경 서비스 테스트")
    void updateMemberPassword() {
        Member member = createDemoUser(EMAIL);
        Long memberId = memberService.register(member);
        String newPassword = "new!Password";
        assertThat(memberId).isEqualTo(member.getId());
        Long result = memberService.updateMemberPassword(member.getId(), newPassword);
        assertThat(result).isNotEqualTo(0L);
        assertThat(memberService.getMemberById(member.getId()).get().getPassword()).isEqualTo(newPassword);
    }

    @Test
    @DisplayName("사용자 목록 조회 서비스 테스트")
    void getAllMembers() {
        int prev = memberService.getAllMembers().size();
        for (int i = 0; i < 5; i++) {
            Member member = createDemoUser(EMAIL+(i+1));
            memberService.register(member);
        }
        assertThat(memberService.getAllMembers().size()).isEqualTo(prev+5);
    }

    @Test
    @DisplayName("회원 탈퇴 서비스 테스트")
    void deleteMember() {
        Member member = createDemoUser(EMAIL);
        memberService.register(member);
        int prev = memberService.getAllMembers().size();
        memberService.deleteMember(member.getId());
        assertThat(memberService.getAllMembers().size()).isEqualTo(prev-1);
    }
}