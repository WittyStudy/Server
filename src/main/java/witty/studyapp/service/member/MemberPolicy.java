package witty.studyapp.service.member;

import witty.studyapp.dto.member.MemberRegisterDTO;

public interface MemberPolicy {
    boolean verifyRegister(MemberRegisterDTO memberRegisterDTO);
}
