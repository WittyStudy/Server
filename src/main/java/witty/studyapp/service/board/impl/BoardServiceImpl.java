package witty.studyapp.service.board.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import witty.studyapp.entity.Notice;
import witty.studyapp.repository.board.BoardRepository;
import witty.studyapp.service.board.BoardService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public Optional<Notice> getById(Long id){
        return boardRepository.findById(id);
    }

    @Override
    public Long updateNotice(Long id, Notice notice) {
        try {
            boardRepository.updateTitle(notice.getTitle(),id);
            boardRepository.updateContent(notice.getContent(),id);
            boardRepository.updateDate(new Date(System.currentTimeMillis()).toString(),id);
            return id;
        }catch(Exception e){    // TODO : Exception 정의 필요.
            return 0L;
        }
    }

    @Override
    public Long deleteNotice(Long noticeId) {
        try {
            boardRepository.deleteById(noticeId);
            return noticeId;
        }catch(Exception e){    // Exception 정의 필요.
            return 0L;
        }
    }
}
