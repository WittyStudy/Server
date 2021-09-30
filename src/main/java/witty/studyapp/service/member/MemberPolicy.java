package witty.studyapp.service.member;

import witty.studyapp.dto.member.MemberRegisterDTO;

public interface MemberPolicy {
    boolean verifyRegisterDTO(MemberRegisterDTO memberRegisterDTO);
    boolean isValidIdent(String ident);
    boolean isValidPassword(String password);
    boolean isValidEmail(String email);
    boolean isValidName(String name);
}
