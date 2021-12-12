package witty.studyapp.controller.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import witty.studyapp.annotation.Login;
import witty.studyapp.dto.board.NoticeDTO;
import witty.studyapp.entity.Member;
import witty.studyapp.entity.Notice;
import witty.studyapp.execption.NoAuthorizationException;
import witty.studyapp.execption.NoSuchBoardException;
import witty.studyapp.execption.NotLoginMemberException;
import witty.studyapp.service.board.BoardService;

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

    @GetMapping
    public List<Notice> getBoards(){
        log.debug("Method getBoards called");
        return boardService.getNotices();
    }

    @PostMapping
    public Long createBoard(@Login Member loginMember, @RequestBody NoticeDTO noticeDTO){
        if(loginMember == null){
            throw new NotLoginMemberException("세션이 없습니다. 로그인을 해야 합니다.");
        }

        log.debug("Method createBoard called");
        Notice notice = new Notice();
        notice.setTitle(noticeDTO.getTitle());
        notice.setWriter(loginMember);
        notice.setContent(noticeDTO.getContent());
        Date date = new Date(System.currentTimeMillis());
        notice.setDate(date.toString());
        return boardService.createNotice(notice);
    }

    @PatchMapping("/{noticeId}")
    public Long updateBoard(@Login Member loginMember, @PathVariable long noticeId, @RequestBody NoticeDTO noticeDTO){
        log.debug("Method updateBoard called");
        Notice notice = new Notice();
        notice.setTitle(noticeDTO.getTitle());
        notice.setContent(noticeDTO.getContent());
        boardService.updateNotice(noticeId, notice);
        return boardService.getById(noticeId).map((n) -> {
            if(n.getWriter() == loginMember){
                return loginMember.getId();
            }else{
                throw new NoAuthorizationException("작성자만 수정이 가능합니다.");
            }
        }).orElseThrow(()->{
            throw new NoSuchBoardException("존재하지 않는 게시물입니다.");
        });
    }

    @DeleteMapping("/{noticeId}")
    public Long deleteBoard(@PathVariable long noticeId){
        log.debug("Method deleteBoard called");
        boardService.deleteNotice(noticeId);
        return noticeId;
    }
}
