package witty.studyapp.service.board.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import witty.studyapp.dto.board.NoticeDTO;
import witty.studyapp.entity.Notice;
import witty.studyapp.repository.board.BoardRepository;
import witty.studyapp.service.board.BoardService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public Long createNotice(NoticeDTO noticeDTO) {
        Notice notice = Notice.getByDTO(noticeDTO);
        return boardRepository.save(notice);
    }

    @Override
    public List<Notice> getNotices() {
        return boardRepository.findAll();
    }

    @Override
    public void updateNotice(Long id, NoticeDTO noticeDTO) {
        Notice notice = Notice.getByDTO(noticeDTO);
        boardRepository.updateById(id,notice);
    }

    @Override
    public void deleteNotice(Long noticeId) {
        boardRepository.deleteById(noticeId);
    }
}
