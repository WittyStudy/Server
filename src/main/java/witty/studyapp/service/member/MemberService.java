package witty.studyapp.service.member;

import witty.studyapp.dto.member.MemberLoginDTO;
import witty.studyapp.dto.member.MemberRegisterDTO;

public interface MemberService {
    Long register(MemberRegisterDTO member);
    Object login(MemberLoginDTO memberDTO);
}
