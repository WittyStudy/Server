package witty.studyapp.service.board.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

    @Override
    public Long createNotice(Notice notice) {
        Optional<Member> optionalMember = memberRepository.findById(notice.getWriter().getId());
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
    public Long updateNotice(Long id, Notice notice) {
        try {
            boardRepository.updateTitle(notice.getTitle(),id);
            boardRepository.updateContent(notice.getContent(),id);
            boardRepository.updateDate(notice.getDate(),id);

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
