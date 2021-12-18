package witty.studyapp.dto.comment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentResponseDTO {
    private Long id;
    private String content;
    private String writerName;
}
