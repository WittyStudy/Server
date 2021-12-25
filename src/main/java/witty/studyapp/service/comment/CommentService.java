package witty.studyapp.service.comment;

import witty.studyapp.dto.comment.CommentCreateDTO;
import witty.studyapp.dto.comment.CommentUpdateDTO;
import witty.studyapp.entity.Comment;
import witty.studyapp.entity.Member;

import java.util.List;

public interface CommentService {
    List<Comment> getAllComments();
    List<Comment> getCommentsByBoardId(Long boardId);
    List<Comment> getCommentsByMemberId(Long memberId);
    Long createComment(Long memberId, CommentCreateDTO commentCreateDTO);
    Long deleteComment(Long memberId, long commentId);
    Long updateComment(Long memberId, CommentUpdateDTO commentUpdateDTO);
}
