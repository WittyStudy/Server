package witty.studyapp.service.member.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import witty.studyapp.entity.Member;
import witty.studyapp.service.member.MemberPolicy;

import java.util.regex.Pattern;

import static witty.studyapp.constant.member.MemberConstant.*;

@Slf4j
@Component
public class MemberPolicyV1 implements MemberPolicy {

    @Override
    public boolean verifyMember(Member member) {
        return (
                isValidPassword(member.getPassword()) &&
                        isValidEmail(member.getEmail()) &&
                        isValidName(member.getName())
        );
    }


    /**
     * PW 정책 : 영문자, 숫자, 일부 특수문자만 조합 가능.
     * 허용 특수문자 : ! @ # $ % & * ~ , .
     * MIN_PASSWORD_LENGTH <= {length} <= MAX_PASSWORD_LENGTH
     */
    @Override
    public boolean isValidPassword(String password) {
        return isValid("[0-9a-zA-Z!@#$%&*~,.]*$", password, MIN_PASSWORD_LENGTH, MAX_PASSWORD_LENGTH);
    }

    /**
     * Email 정책 : '@' 포함 && 아이디 조건 && 이메일 조건
     * 이메일 조건 : Patters Class 참조
     */
    @Override
    public boolean isValidEmail(String email) {
        boolean isEmail;
        if (email == null) return false;
        isEmail = Pattern.matches(
                "[\\w~\\-.]+@[\\w~\\-]+(\\.[\\w~\\-]+)+",
                email.trim());
        boolean valid = email.length() >= MIN_EMAIL_LENGTH && email.length() <= MAX_EMAIL_LENGTH;
        return isEmail && valid;
    }

    /**
     * Name 정책 : 영문 + 숫자
     * MIN_ID_LENGTH <= {length} <= MAX_ID_LENGTH
     */

    @Override
    public boolean isValidName(String name) {
        return isValid("[a-zA-Z0-9]*$", name, MIN_NAME_LENGTH, MAX_NAME_LENGTH);
    }

    public boolean isValid(String regex, String string, int min, int max) {
        return string.matches(regex) &&
                string.length() >= min &&
                string.length() <= max;
    }
}
