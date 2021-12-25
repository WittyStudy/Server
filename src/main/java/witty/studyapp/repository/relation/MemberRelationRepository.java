package witty.studyapp.repository.relation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import witty.studyapp.entity.Member;
import witty.studyapp.entity.MemberRelation;

import java.util.List;
import java.util.Optional;

public interface MemberRelationRepository extends JpaRepository<MemberRelation, Long> {

    @Query("SELECT r FROM MemberRelation r WHERE r.member=:member")
    List<MemberRelation> findByMember(@Param("member") Member member);

    @Query("SELECT r FROM MemberRelation r WHERE r.member.id=:id and r.target.id=:targetId")
    Optional<MemberRelation> findByMemberAndTarget(@Param("id") Long id, @Param("targetId") Long targetId);
}
