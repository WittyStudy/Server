package witty.studyapp.repository;

import witty.studyapp.dto.NoticeDTO;
import witty.studyapp.entity.Notice;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    Optional<Notice> findById(Long id);
    List<Notice> findAll();
    void deleteById(Long id);
    void updateById(Long id, NoticeDTO noticeDTO);
    Long save(NoticeDTO noticeDTO);
}
