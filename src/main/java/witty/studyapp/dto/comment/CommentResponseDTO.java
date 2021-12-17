package witty.studyapp.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentResponseDTO {
    private Long id;
    private String content;
    private String writerName;
    private String boardTitle;
}
