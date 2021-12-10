package witty.studyapp.service.board;

import witty.studyapp.entity.Notice;

import java.util.List;

public interface BoardService {
    Long createNotice(Notice notice);
    List<Notice> getNotices();
    Long updateNotice(Long id, Notice notice);
    Long deleteNotice(Long noticeId);
}
