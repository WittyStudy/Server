package witty.studyapp.service.member;

import witty.studyapp.entity.Member;

import java.util.List;

public interface MemberService {
    Long register(Member member);
    Object login(Long memberId, Member member);
    Long updateMemberName(Long memberId, String name);
    Long updateMemberPassword(Long memberId, String password);
    List<Member> getAllMembers();
    Long deleteMember(Long memberId);
    Member getMemberById(Long id);
}
