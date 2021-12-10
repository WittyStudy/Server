package witty.studyapp.service.comment;

import witty.studyapp.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentsByBoardId(Long boardId);
    List<Comment> getCommentsByMemberId(Long memberId);
    Long createComment(Comment comment, Long memberId, Long boardId);
    Long deleteComment(long commentId);
    Long updateComment(Comment comment, Long commentId);
}
