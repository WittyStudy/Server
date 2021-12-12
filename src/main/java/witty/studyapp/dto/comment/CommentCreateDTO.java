package witty.studyapp.dto.comment;

import lombok.Data;

@Data
public class CommentCreateDTO {
    private Long boardId;
    private String content;
}
