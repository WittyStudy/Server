package witty.studyapp.entity;

import lombok.Data;
import witty.studyapp.dto.NoticeDTO;

@Data
public class Notice {
    private Long id;
    private String title;
    private Long writerId;
    private Long views;
    private String date;
    private String content;

    public static Notice getByDTO(NoticeDTO noticeDTO){
        Notice notice = new Notice();
        notice.setTitle(noticeDTO.getTitle());
        notice.setWriterId(noticeDTO.getWriterId());
        notice.setViews(noticeDTO.getViews());
        notice.setDate(noticeDTO.getDate());
        notice.setContent(noticeDTO.getContent());
        return notice;
    }
}
