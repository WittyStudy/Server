package witty.studyapp.service;

import org.springframework.stereotype.Service;
import witty.studyapp.dto.NoticeDTO;
import witty.studyapp.entity.Notice;

import java.util.List;

public interface BoardService {
    Long createNotice(NoticeDTO noticeDTO);
    List<Notice> getNotices();
    void updateNotice(Long id, NoticeDTO notice);
    void deleteNotice(Long noticeId);
}
