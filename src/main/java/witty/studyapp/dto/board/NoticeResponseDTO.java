package witty.studyapp.dto.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NoticeResponseDTO {
    private Long id;
    private String title;
    private String writerName;
    private Long views;
    private String date;
}
