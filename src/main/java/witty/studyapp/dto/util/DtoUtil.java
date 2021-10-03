package witty.studyapp.dto.util;

import witty.studyapp.dto.board.NoticeDTO;
import witty.studyapp.dto.comment.CommentDTO;
import witty.studyapp.dto.member.MemberRegisterDTO;
import witty.studyapp.entity.Comment;
import witty.studyapp.entity.Member;
import witty.studyapp.entity.Notice;

public interface DtoUtil {
    Member getByDTO(MemberRegisterDTO memberRegisterDTO);
    Comment getByDTO(CommentDTO commentDTO);
    Notice getByDTO(NoticeDTO noticeDTO);


}
