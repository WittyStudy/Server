package witty.studyapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import witty.studyapp.dto.NoticeDTO;
import witty.studyapp.entity.Notice;
import witty.studyapp.repository.BoardRepository;
import witty.studyapp.service.BoardService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public Long createNotice(NoticeDTO notice) {
        return boardRepository.save(notice);
    }

    @Override
    public List<Notice> getNotices() {
        return boardRepository.findAll();
    }

    @Override
    public void updateNotice(Long id, NoticeDTO notice) {
        boardRepository.updateById(id,notice);
    }

    @Override
    public void deleteNotice(Long noticeId) {
        boardRepository.deleteById(noticeId);
    }
}
