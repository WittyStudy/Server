package witty.studyapp.service.comment;

import witty.studyapp.entity.Comment;
import witty.studyapp.entity.Member;

import java.util.List;

public interface CommentService {
    List<Comment> getAllComments();
    List<Comment> getCommentsByBoardId(Long boardId);
    List<Comment> getCommentsByMemberId(Long memberId);
    Long createComment(Comment comment, Long memberId, Long boardId);
    Long deleteComment(Member member, long commentId);
    Long updateComment(Member member, Comment comment);
}
