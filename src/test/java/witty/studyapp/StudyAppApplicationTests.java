package witty.studyapp;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import witty.studyapp.dto.board.NoticeDTO;
import witty.studyapp.entity.Notice;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
class StudyAppApplicationTests {

    @Test
    void contextLoads() {
    }

    @PostConstruct
    void setMockData() {
        // TODO : MOCK DATA 생성 필요.
//        Notice notice1 = new Notice();
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
//        String currentDate = dateFormat.format(new Date());
//        notice1.setDate(currentDate);
//
//        notice1.setContent("Content1");
//        notice1.setTitle("Title1");
//        notice1.setWriter(1L);
//        notice1.setViews(30L);
//
//        NoticeDTO notice2 = new NoticeDTO();
//
//        notice2.setDate(currentDate);
//        notice2.setContent("Content2");
//        notice2.setTitle("Title2");
//        notice2.setWriterId(2L);
//        notice2.setViews(15L);
//
//        boardService.createNotice(notice1);
//        boardService.createNotice(notice2);
//
//        log.debug("Init board controller for test.");
    }

}
