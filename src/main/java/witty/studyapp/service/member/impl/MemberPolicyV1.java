package witty.studyapp.service.member.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import witty.studyapp.dto.member.MemberRegisterDTO;
import witty.studyapp.service.member.MemberPolicy;

@Slf4j
@Component
public class MemberPolicyV1 implements MemberPolicy {

    private final int MAX_ID_LENGTH = 16;
    private final int MIN_ID_LENGTH = 4;

    private final int MAX_EMAIL_LENGTH = 64;
    private final int MIN_EMAIL_LENGTH = 4;

    @Override
    public boolean verifyRegister(MemberRegisterDTO memberRegisterDTO) {
        return (
                isValidIdent(memberRegisterDTO.getIdent()) &&
                        isValidPassword(memberRegisterDTO.getPassword()) &&
                        isValidEmail(memberRegisterDTO.getEmail()) &&
                        isValidName(memberRegisterDTO.getName())
        );
    }

    /**
     * ID 정책 : 영문자, 숫자만 조합 가능
     * MIN_ID_LENGTH <= {length} <= MAX_ID_LENGTH
     */
    private boolean isValidIdent(String ident) {
        return isValid("[^0-9a-zA-Z]", ident, MIN_ID_LENGTH, MAX_ID_LENGTH);
    }

    /**
     * PW 정책 : 영문자, 숫자, 일부 특수문자만 조합 가능.
     * 허용 특수문자 : ! @ # $ % & * ~ , .
     * MIN_ID_LENGTH <= {length} <= MAX_ID_LENGTH
     */
    private boolean isValidPassword(String password) {
        return isValid("[^0-9a-zA-Z!@#$%&*~,.]", password, MIN_ID_LENGTH, MAX_ID_LENGTH);
    }

    /**
     * Email 정책 : '@' 포함 && 아이디 조건 && 이메일 조건
     * 아이디 조건 : MIN_ID_LENGTH <= {length} <= MAX_ID_LENGTH
     * 이메일 조건 : MIN_EMAIL_LENGTH <= {length} <= MAX_EMAIL_LENGTH
     */
    private boolean isValidEmail(String email) {
        if (email.split("@").length != 2)
            return false;
        return isValid("", email.split("@")[0], MIN_ID_LENGTH, MAX_ID_LENGTH)
                && isValid("", email.split("@")[1], MIN_EMAIL_LENGTH, MAX_EMAIL_LENGTH)
                && isValid("[^!]", email.split("@")[1], 1, 1);
    }

    /**
     * Name 정책 :
     */
    private boolean isValidName(String name) {
        return true;
    }

    private boolean isValid(String regex, String string, int min, int max) {
        String modified = string.replaceAll(regex, "");
        log.debug("string =      '{}'", string);
        log.debug("modified  =   '{}'", modified);
        return string.length() == modified.length() &&
                string.length() >= min &&
                string.length() <= max;
    }
}
