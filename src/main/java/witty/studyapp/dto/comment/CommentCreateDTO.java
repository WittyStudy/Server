package witty.studyapp.dto.comment;

import lombok.Data;
import witty.studyapp.constant.comment.CommentConstant;

import javax.validation.constraints.Size;

@Data
public class CommentCreateDTO {
    private Long boardId;

    @Size(min = CommentConstant.MIN_CONTENT_LENGTH, max = CommentConstant.MAX_CONTENT_LENGTH)
    private String content;
}
