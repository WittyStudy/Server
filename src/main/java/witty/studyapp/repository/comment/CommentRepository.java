package witty.studyapp.repository.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import witty.studyapp.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
