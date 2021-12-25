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
import witty.studyapp.dto.board.NoticeUpdateDTO;
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

    @PostMapping
    public Long createBoard(@Login Member loginMember, @RequestBody @Validated NoticeCreateDTO noticeDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException();
        }

        log.debug("Method createBoard called");
        return boardService.createNotice(Notice.builder()
                .title(noticeDTO.getTitle())
                .writer(loginMember)
                .content(noticeDTO.getContent())
                .date(new Date(System.currentTimeMillis()).toString())
                .views(0L)
                .build());
    }

    @PatchMapping("/{noticeId}")
    public Long updateBoard(@Login Member loginMember, @PathVariable long noticeId, @RequestBody @Validated NoticeUpdateDTO noticeDTO) {
        log.debug("Method updateBoard called");
        return boardService.updateNotice(loginMember.getId(), noticeId, noticeDTO);
    }

    @DeleteMapping("/{noticeId}")
    public Long deleteBoard(@PathVariable long noticeId) {
        log.debug("Method deleteBoard called");
        boardService.deleteNotice(noticeId);
        return noticeId;
    }

    private List<NoticeResponseDTO> getNoticeResponseDTOs(List<Notice> notices) {
        List<NoticeResponseDTO> result = new ArrayList<>();
        for (Notice notice : notices) {
            result.add(NoticeResponseDTO.builder()
                    .id(notice.getId())
                    .title(notice.getTitle())
                    .writerName(notice.getWriter().getName())
                    .views(notice.getViews())
                    .date(notice.getDate())
                    .build());
        }
        return result;
    }

    private NoticeDetailDTO getNoticeDetailDTO(Notice notice) {
        return NoticeDetailDTO.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .writerName(notice.getWriter().getName())
                .views(notice.getViews())
                .date(notice.getDate())
                .content(notice.getContent())
                .build();
    }
}
