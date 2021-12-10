package witty.studyapp.controller.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import witty.studyapp.dto.board.NoticeDTO;
import witty.studyapp.entity.Notice;
import witty.studyapp.service.board.BoardService;
import witty.studyapp.service.member.MemberService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    // TODO : member Session & Cookie 이용 로그인 세션 구현 필요.
    //   (member login, register 외엔 인터셉터를 적용하고, member 정보를 받아 와야 함)

    private final BoardService boardService;
    private final MemberService memberService;

    @GetMapping
    public List<Notice> getBoards(){
        log.debug("Method getBoards called");
        return boardService.getNotices();
    }

    @PostMapping
    public Long createBoard(@RequestBody NoticeDTO noticeDTO){
        log.debug("Method createBoard called");
        Notice notice = new Notice();
        notice.setTitle(noticeDTO.getTitle());
        // TODO : memberID (로그인 세션에서 정보 불러와야 함)
//        notice.setWriter(memberService.getMemberById(memberId));
        notice.setViews(noticeDTO.getViews());
        Date date = new Date(System.currentTimeMillis());
        notice.setDate(date.toString());
        return boardService.createNotice(notice);
    }

    @PutMapping("/{noticeId}")
    public Long updateBoard(@PathVariable long noticeId, @RequestBody NoticeDTO noticeDTO){
        log.debug("Method updateBoard called");
        // TODO : noticeDTO -> notice -> PUT to DB(repo)
        return noticeId;
    }

    @DeleteMapping("/{noticeId}")
    public Long deleteBoard(@PathVariable long noticeId){
        log.debug("Method deleteBoard called");
        boardService.deleteNotice(noticeId);
        return noticeId;
    }
}
