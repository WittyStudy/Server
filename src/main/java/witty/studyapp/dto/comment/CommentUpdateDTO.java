package witty.studyapp.dto.comment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentUpdateDTO {
    Long commentId;
    String content;
}
