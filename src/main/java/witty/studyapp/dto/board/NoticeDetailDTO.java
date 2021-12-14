package witty.studyapp.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NoticeDetailDTO {
    private Long id;
    private String title;
    private String writerName;
    private Long views;
    private String date;
    private String content;
}
