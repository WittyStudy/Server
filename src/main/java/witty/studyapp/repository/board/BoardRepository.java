package witty.studyapp.repository.board;

import witty.studyapp.dto.board.NoticeDTO;
import witty.studyapp.entity.Notice;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    Optional<Notice> findById(Long id);
    List<Notice> findAll();
    void deleteById(Long id);
    void updateById(Long id, Notice notice);
    Long save(Notice notice);
}
