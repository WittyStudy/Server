package witty.studyapp.service.member.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import witty.studyapp.dto.member.MemberRegisterDTO;
import witty.studyapp.service.member.MemberPolicy;

import java.util.StringTokenizer;
import java.util.regex.Pattern;

@Slf4j
@Component
public class MemberPolicyV1 implements MemberPolicy {

    private final int MAX_ID_LENGTH = 16;
    private final int MIN_ID_LENGTH = 4;

    private final int MAX_NAME_LENGTH = 16;
    private final int MIN_NAME_LENGTH = 4;

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
        return isValid("[0-9a-zA-Z]*$", ident, MIN_ID_LENGTH, MAX_ID_LENGTH);
    }

    /**
     * PW 정책 : 영문자, 숫자, 일부 특수문자만 조합 가능.
     * 허용 특수문자 : ! @ # $ % & * ~ , .
     * MIN_ID_LENGTH <= {length} <= MAX_ID_LENGTH
     */
    private boolean isValidPassword(String password) {
        return isValid("[0-9a-zA-Z!@#$%&*~,.]*$", password, MIN_ID_LENGTH, MAX_ID_LENGTH);
    }

    /**
     * Email 정책 : '@' 포함 && 아이디 조건 && 이메일 조건
     * 이메일 조건 : Patters Class 참조
     */
    private boolean isValidEmail(String email) {
        boolean isEmail;
        if (email == null) return false;
        isEmail = Pattern.matches(
                "[\\w~\\-.]+@[\\w~\\-]+(\\.[\\w~\\-]+)+",
                email.trim());
        return isEmail;
    }

    /**
     * Name 정책 : Only 영문
     * MIN_ID_LENGTH <= {length} <= MAX_ID_LENGTH
     */
    private boolean isValidName(String name) {
        return isValid("[a-zA-Z]*$",name,MIN_NAME_LENGTH,MAX_NAME_LENGTH);
    }

    private boolean isValid(String regex, String string, int min, int max) {
        return string.matches(regex) &&
                string.length() >= min &&
                string.length() <= max;
    }
}
