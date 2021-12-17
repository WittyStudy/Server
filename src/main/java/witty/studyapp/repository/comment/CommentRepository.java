package witty.studyapp.repository.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import witty.studyapp.entity.Comment;
import witty.studyapp.entity.Member;
import witty.studyapp.entity.Notice;

import javax.transaction.Transactional;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Query("SELECT c FROM Comment c WHERE c.notice = :notice")
    List<Comment> findByBoard(@Param("notice") Notice notice);

    @Query("SELECT c FROM Comment c WHERE c.writer = :member")
    List<Comment> findByMember(@Param("member") Member member);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Comment c Set c.content =:content WHERE c.id = :id")
    void updateComment(@Param("id") Long id, @Param("content") String content);
}
