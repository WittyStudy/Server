package witty.studyapp.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import witty.studyapp.entity.Member;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    @Query("SELECT m FROM Member m WHERE m.ident = :ident")
    Optional<Member> findByIdent(@Param("ident") String ident);

    @Transactional
    @Modifying
    @Query("UPDATE Member m SET m.name=:name")
    int updateName(@Param("name") String name);

    @Transactional
    @Modifying
    @Query("UPDATE Member m SET m.password=:password")
    int updatePassword(@Param("password") String password);
}
