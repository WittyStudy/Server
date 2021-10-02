package witty.studyapp.entity;

import lombok.Data;
import witty.studyapp.dto.comment.CommentDTO;

@Data
public class Comment {
    private Long id;
    private String context;
    private Long memberId;
    private Long boardId;

    public static Comment getByDTO(CommentDTO commentDTO, Long memberId, Long boardId){
        Comment comment = new Comment();
        comment.setContext(commentDTO.getContent());
        comment.setMemberId(memberId);
        comment.setBoardId(boardId);

        return comment;
    }
}
