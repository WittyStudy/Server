package witty.studyapp.service.board.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import witty.studyapp.entity.Notice;
import witty.studyapp.execption.custom.NoSuchBoardException;
import witty.studyapp.repository.board.BoardRepository;
import witty.studyapp.service.board.BoardService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
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
    public Optional<Notice> viewNoticeDetailAndGet(Long id) {
        boardRepository.incrementView(id);
        return boardRepository.findById(id);
    }

    @Override
    public Long updateNotice(Long id, Notice notice) {
        boardRepository.findById(id).orElseThrow(NoSuchBoardException::new);
        boardRepository.updateTitle(notice.getTitle(), id);
        boardRepository.updateContent(notice.getContent(), id);
        boardRepository.updateDate(new Date(System.currentTimeMillis()).toString(), id);
        return id;
    }

    @Override
    public Long deleteNotice(Long noticeId) {
        return boardRepository.findById(noticeId).map(notice -> {
            boardRepository.deleteById(notice.getId());
            return notice.getId();
        }).orElseThrow(NoSuchBoardException::new);
    }
}
