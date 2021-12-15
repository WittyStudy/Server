package witty.studyapp.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import witty.studyapp.entity.Notice;

import javax.transaction.Transactional;

public interface BoardRepository extends JpaRepository<Notice, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Notice n Set n.title = :title WHERE n.id = :id")
    void updateTitle(@Param("title") String title, @Param("id") Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Notice n Set n.content = :content WHERE n.id = :id")
    void updateContent(@Param("content") String content, @Param("id") Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Notice n Set n.date = :date WHERE n.id = :id")
    void updateDate(@Param("date") String date, @Param("id") Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Notice n Set n.views = n.views+1 WHERE n.id = :id")
    void incrementView(@Param("id") Long id);
}
