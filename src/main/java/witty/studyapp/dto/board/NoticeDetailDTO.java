package witty.studyapp.dto.board;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NoticeDetailDTO {
    private Long id;
    private String title;
    private String writerName;
    private Long views;
    private String date;
    private String content;
}
