package witty.studyapp.dto.comment;

import lombok.Data;

@Data
public class CommentUpdateDTO {
    Long commentId;
    String content;
}
