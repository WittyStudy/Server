package witty.studyapp.service.member;

import witty.studyapp.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    Long register(Member member);
    Optional<Member> login(Member member);
    Long updateMemberName(Long memberId, String name);
    Long updateMemberPassword(Long memberId, String password);
    List<Member> getAllMembers();
    Long deleteMember(Long memberId);
    Optional<Member> getMemberById(Long id);
}
