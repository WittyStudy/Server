package witty.studyapp.controller.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import witty.studyapp.annotation.Login;
import witty.studyapp.dto.board.NoticeCreateDTO;
import witty.studyapp.dto.board.NoticeDetailDTO;
import witty.studyapp.dto.board.NoticeResponseDTO;
import witty.studyapp.entity.Member;
import witty.studyapp.entity.Notice;
import witty.studyapp.execption.custom.NoAuthorizationException;
import witty.studyapp.execption.custom.NoSuchBoardException;
import witty.studyapp.service.board.BoardService;

import java.util.ArrayList;
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
    public List<NoticeResponseDTO> getBoards() {
        log.debug("Method getBoards called");
        return getNoticeResponseDTOs(boardService.getNotices());
    }

    @GetMapping("/{noticeId}")
    public NoticeDetailDTO getBoardDetail(@PathVariable Long noticeId) {
        return getNoticeDetailDTO(
                boardService.viewNoticeDetailAndGet(noticeId)
                        .orElseThrow(NoSuchBoardException::new)
        );
    }

    @GetMapping("/title/{query}")
    public List<NoticeResponseDTO> getBoardsByTitleName(@PathVariable String query) {
        return getNoticeResponseDTOs(boardService.getNoticesByTitle(query));
    }

    private List<NoticeResponseDTO> getNoticeResponseDTOs(List<Notice> notices) {
        List<NoticeResponseDTO> result = new ArrayList<>();
        for (Notice notice : notices) {
            NoticeResponseDTO noticeResponseDTO = new NoticeResponseDTO(notice.getId(), notice.getTitle(), notice.getWriter().getName(), notice.getViews(), notice.getDate());
            result.add(noticeResponseDTO);
        }
        return result;
    }

    private NoticeDetailDTO getNoticeDetailDTO(Notice notice) {
        return new NoticeDetailDTO(notice.getId(), notice.getTitle(), notice.getWriter().getName(), notice.getViews(), notice.getDate(), notice.getContent());
    }

    @PostMapping
    public Long createBoard(@Login Member loginMember, @RequestBody @Validated NoticeCreateDTO noticeDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException();
        }

        log.debug("Method createBoard called");
        Notice notice = new Notice();
        notice.setTitle(noticeDTO.getTitle());
        notice.setWriter(loginMember);
        notice.setContent(noticeDTO.getContent());
        notice.setDate(new Date(System.currentTimeMillis()).toString());
        notice.setViews(0L);
        return boardService.createNotice(notice);
    }

    @PatchMapping("/{noticeId}")
    public Long updateBoard(@Login Member loginMember, @PathVariable long noticeId, @RequestBody @Validated NoticeCreateDTO noticeDTO) {
        log.debug("Method updateBoard called");
        Notice notice = new Notice();
        notice.setTitle(noticeDTO.getTitle());
        notice.setContent(noticeDTO.getContent());
        boardService.updateNotice(noticeId, notice);
        return boardService.getById(noticeId).map((n) -> {
            if (n.getWriter() == loginMember) {
                return loginMember.getId();
            } else {
                throw new NoAuthorizationException();
            }
        }).orElseThrow(NoSuchBoardException::new);
    }

    @DeleteMapping("/{noticeId}")
    public Long deleteBoard(@PathVariable long noticeId) {
        log.debug("Method deleteBoard called");
        boardService.deleteNotice(noticeId);
        return noticeId;
    }
}
