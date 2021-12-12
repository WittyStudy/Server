package witty.studyapp.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import witty.studyapp.entity.Member;

import javax.transaction.Transactional;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m FROM Member m WHERE m.id=:id")
    Optional<Member> findById(@Param("id") Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Member m SET m.name=:name WHERE m.id=:id")
    void updateName(@Param("id") Long id, @Param("name") String name);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Member m SET m.password=:password WHERE m.id=:id")
    void updatePassword(@Param("id") Long id, @Param("password") String password);

    @Query("SELECT m FROM Member m WHERE m.email=:email")
    Optional<Member> findByEmail(@Param("email") String email);
}
