package witty.studyapp.service.relation;

import witty.studyapp.entity.Member;

import java.util.List;

public interface MemberRelationService {
    Long addRelation(Long id, Long targetId);
    List<Member> getFriendsList(Long id);
    Long deleteRelation(Long id, Long targetId);
}
