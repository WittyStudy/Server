package witty.studyapp.service.board;

import witty.studyapp.dto.board.NoticeDTO;
import witty.studyapp.entity.Notice;

import java.util.List;

public interface BoardService {
    Long createNotice(NoticeDTO noticeDTO);
    List<Notice> getNotices();
    Long updateNotice(Long id, NoticeDTO notice);
    Long deleteNotice(Long noticeId);
}
