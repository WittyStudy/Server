package witty.studyapp.repository.member;

import witty.studyapp.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Long save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByIdent(String ident);
    List<Member> findAll();
}
