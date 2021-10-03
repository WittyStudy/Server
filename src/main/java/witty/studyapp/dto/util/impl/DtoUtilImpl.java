package witty.studyapp.dto.util.impl;

import org.springframework.stereotype.Component;
import witty.studyapp.dto.board.NoticeDTO;
import witty.studyapp.dto.comment.CommentDTO;
import witty.studyapp.dto.member.MemberRegisterDTO;
import witty.studyapp.dto.util.DtoUtil;
import witty.studyapp.entity.Comment;
import witty.studyapp.entity.Member;
import witty.studyapp.entity.Notice;

@Component
public class DtoUtilImpl implements DtoUtil {
    @Override
    public Comment getByDTO(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());

        return comment;
    }

    @Override
    public Member getByDTO(MemberRegisterDTO memberRegisterDTO) {
        Member member = new Member();
        member.setIdent(memberRegisterDTO.getIdent());
        member.setName(memberRegisterDTO.getName());
        member.setPassword(memberRegisterDTO.getPassword());
        member.setEmail(memberRegisterDTO.getEmail());
        return member;
    }

    @Override
    public Notice getByDTO(NoticeDTO noticeDTO) {
        Notice notice = new Notice();
        notice.setTitle(noticeDTO.getTitle());
        notice.setViews(noticeDTO.getViews());
        notice.setDate(noticeDTO.getDate());
        notice.setContent(noticeDTO.getContent());
        return notice;
    }
}
