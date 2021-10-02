package witty.studyapp.repository.comment;

import witty.studyapp.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Long save(Comment comment);
    List<Comment> findByBoardId(Long boardId);
    List<Comment> findByMemberId(Long memberId);
    Optional<Comment> findById(Long commentId);
    Long deleteById(Long commentId);
}
