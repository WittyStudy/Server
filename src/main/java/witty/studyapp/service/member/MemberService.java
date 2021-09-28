package witty.studyapp.service.member;

import witty.studyapp.dto.member.MemberLoginDTO;
import witty.studyapp.dto.member.MemberRegisterDTO;

import java.util.Map;

public interface MemberService {
    Long register(MemberRegisterDTO member);
    Object login(Long memberId, MemberLoginDTO memberDTO);
    Long updateMemberName(Long memberId, String name);
    Long updateMemberPassword(Long memberId, String password);
}
