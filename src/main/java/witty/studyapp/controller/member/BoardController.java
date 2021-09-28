package witty.studyapp.controller.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import witty.studyapp.dto.board.NoticeDTO;
import witty.studyapp.entity.Notice;
import witty.studyapp.service.board.BoardService;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/members/{memberId}/boards")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @PostConstruct
    public void init(){
        NoticeDTO notice1 = new NoticeDTO();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        String currentDate = dateFormat.format(new Date());
        notice1.setDate(currentDate);

        notice1.setContent("Content1");
        notice1.setTitle("Title1");
        notice1.setWriterId(1L);
        notice1.setViews(30L);

        NoticeDTO notice2 = new NoticeDTO();

        notice2.setDate(currentDate);
        notice2.setContent("Content2");
        notice2.setTitle("Title2");
        notice2.setWriterId(2L);
        notice2.setViews(15L);

        boardService.createNotice(notice1);
        boardService.createNotice(notice2);

        log.debug("Init board controller for test.");
    }

    @GetMapping
    public List<Notice> getBoards(@PathVariable String memberId){
        log.debug("Method getBoards called");
        return boardService.getNotices();
    }

    @PostMapping
    public Long createBoard(@PathVariable String memberId, @RequestBody NoticeDTO noticeDTO){
        log.debug("Method createBoard called");
        return boardService.createNotice(noticeDTO);
    }

    @PutMapping("/{noticeId}")
    public Long updateBoard(@PathVariable String memberId, @PathVariable long noticeId, @RequestBody NoticeDTO noticeDTO){
        log.debug("Method updateBoard called");
        boardService.updateNotice(noticeId,noticeDTO);
        return noticeId;
    }

    @DeleteMapping("/{noticeId}")
    public Long deleteBoard(@PathVariable String memberId, @PathVariable long noticeId, @RequestBody NoticeDTO noticeDTO){
        log.debug("Method deleteBoard called");
        boardService.deleteNotice(noticeId);
        return noticeId;
    }
}
