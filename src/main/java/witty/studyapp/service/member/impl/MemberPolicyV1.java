package witty.studyapp.service.member.impl;

import org.springframework.stereotype.Component;
import witty.studyapp.dto.member.MemberRegisterDTO;
import witty.studyapp.service.member.MemberPolicy;

@Component
public class MemberPolicyV1 implements MemberPolicy {
    @Override
    public boolean verifyRegister(MemberRegisterDTO memberRegisterDTO) {
        return(
                isValidIdent(memberRegisterDTO.getIdent()) &&
                isValidPassword(memberRegisterDTO.getPassword()) &&
                isValidEmail(memberRegisterDTO.getEmail()) &&
                isValidName(memberRegisterDTO.getName())
        );
    }

    private boolean isValidIdent(String ident){
        return true;
    }

    private boolean isValidPassword(String password){
        return true;
    }

    private boolean isValidEmail(String email){
        return true;
    }

    private boolean isValidName(String name){
        return true;
    }
}
