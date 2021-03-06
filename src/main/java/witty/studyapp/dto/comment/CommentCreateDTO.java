package witty.studyapp.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import witty.studyapp.constant.comment.CommentConstant;

import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentCreateDTO {
    private Long boardId;

    @Size(min = CommentConstant.MIN_CONTENT_LENGTH, max = CommentConstant.MAX_CONTENT_LENGTH)
    private String content;
}
