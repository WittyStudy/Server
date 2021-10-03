package witty.studyapp.service.board.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import witty.studyapp.dto.board.NoticeDTO;
import witty.studyapp.dto.util.DtoUtil;
import witty.studyapp.entity.Member;
import witty.studyapp.entity.Notice;
import witty.studyapp.repository.board.BoardRepository;
import witty.studyapp.repository.member.MemberRepository;
import witty.studyapp.service.board.BoardService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final DtoUtil dtoUtil;

    @Override
    public Long createNotice(NoticeDTO noticeDTO) {
        Notice notice = dtoUtil.getByDTO(noticeDTO);
        Optional<Member> optionalMember = memberRepository.findById(noticeDTO.getWriterId());
        return optionalMember.map(member -> {
            notice.setWriter(member);
            return boardRepository.save(notice).getId();
        }).orElse(0L);
    }

    @Override
    public List<Notice> getNotices() {
        return boardRepository.findAll();
    }

    @Override
    public Long updateNotice(Long id, NoticeDTO noticeDTO) {
        try {
            boardRepository.updateTitle(noticeDTO.getTitle(),id);
            boardRepository.updateContent(noticeDTO.getContent(),id);
            boardRepository.updateDate(noticeDTO.getDate(),id);

            return id;
        }catch(Exception e){    // Exception 정의 필요.
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
