package witty.studyapp.repository.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import witty.studyapp.entity.Member;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    void saveTest() {
        Member member = new Member();
        String email = "qw12zx@naver.com";
        member.setEmail(email);
        member.setName("userName");
        member.setPassword("secret!");
        memberRepository.save(member);

        Optional<Member> byEmail = memberRepository.findByEmail(email);
        assertThat(byEmail.get()).isEqualTo(member);
        
    }
}