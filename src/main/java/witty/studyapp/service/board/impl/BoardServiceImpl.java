package witty.studyapp.service.board.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import witty.studyapp.dto.board.NoticeCreateDTO;
import witty.studyapp.dto.board.NoticeUpdateDTO;
import witty.studyapp.entity.Notice;
import witty.studyapp.execption.custom.NoAuthorizationException;
import witty.studyapp.execption.custom.NoSuchBoardException;
import witty.studyapp.repository.board.BoardRepository;
import witty.studyapp.service.board.BoardService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    @Transactional
    public Long createNotice(Notice notice) {
        return boardRepository.save(notice).getId();
    }

    @Override
    public List<Notice> getNotices() {
        return boardRepository.findAll();
    }

    @Override
    public List<Notice> getNoticesByTitle(String title) {
        return boardRepository.findAll()
                .stream()
                .filter(board -> board.getTitle().contains(title))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Notice> getById(Long id) {
        return boardRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Notice> viewNoticeDetailAndGet(Long id) {
        return Optional.of(
                boardRepository.findById(id)
                        .orElseThrow(NoSuchBoardException::new)
                        .incrementView());
    }

    @Override
    @Transactional
    public Long updateNotice(Long memberId, Long noticeId, NoticeUpdateDTO noticeDTO) {
        Notice notice = boardRepository.findById(noticeId).orElseThrow(NoSuchBoardException::new);
        if(!notice.getWriter().getId().equals(memberId)){
            throw new NoAuthorizationException();
        }
        notice.setContent(noticeDTO.getContent());
        notice.setTitle(noticeDTO.getTitle());
        notice.setDate(new Date(System.currentTimeMillis()).toString());
        return noticeId;
    }

    @Override
    @Transactional
    public Long deleteNotice(Long noticeId) {
        return boardRepository.findById(noticeId).map(notice -> {
            boardRepository.deleteById(notice.getId());
            return notice.getId();
        }).orElseThrow(NoSuchBoardException::new);
    }
}
