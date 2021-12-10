package witty.studyapp.service.member;

import witty.studyapp.entity.Member;

public interface MemberPolicy {
    boolean verifyMember(Member member);
    boolean isValidEmail(String email);
    boolean isValidName(String name);
    boolean isValidPassword(String password);
}
