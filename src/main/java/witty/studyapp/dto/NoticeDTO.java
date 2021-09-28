package witty.studyapp.dto;

import lombok.Data;

@Data
public class NoticeDTO {
    private String title;
    private Long writerId;
    private Long views;
    private String date;
    private String content;
}
