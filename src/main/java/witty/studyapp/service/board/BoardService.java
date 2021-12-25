package witty.studyapp.service.board;

import witty.studyapp.dto.board.NoticeUpdateDTO;
import witty.studyapp.entity.Notice;

import java.util.List;
import java.util.Optional;

public interface BoardService {
    Long createNotice(Notice notice);
    List<Notice> getNotices();
    Optional<Notice> getById(Long id);
    Optional<Notice> viewNoticeDetailAndGet(Long id);
    Long updateNotice(Long memberId, Long noticeId, NoticeUpdateDTO noticeDTO);
    Long deleteNotice(Long noticeId);
    List<Notice> getNoticesByTitle(String title);
}
