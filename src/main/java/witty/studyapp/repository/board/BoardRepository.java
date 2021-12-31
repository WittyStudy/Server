package witty.studyapp.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;
import witty.studyapp.entity.Notice;

public interface BoardRepository extends JpaRepository<Notice, Long> {
}
