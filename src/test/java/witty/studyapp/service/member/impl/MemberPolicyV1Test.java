package witty.studyapp.service.member.impl;

import org.junit.jupiter.api.Test;
import witty.studyapp.dto.member.MemberRegisterDTO;

import static org.assertj.core.api.Assertions.assertThat;

class MemberPolicyV1Test {

    @Test
    void validationTest1(){
        MemberRegisterDTO dto = new MemberRegisterDTO();
        dto.setIdent("Ident");
        dto.setName("");
        dto.setPassword("");
        dto.setEmail("");


        MemberPolicyV1 memberPolicyV1 = new MemberPolicyV1();
        assertThat(memberPolicyV1.verifyRegister(dto)).isEqualTo(true);
    }

    @Test
    void validationTest2(){
        MemberRegisterDTO dto = new MemberRegisterDTO();
        dto.setIdent("Ide  ");
        dto.setName("");
        dto.setPassword("");
        dto.setEmail("");


        MemberPolicyV1 memberPolicyV1 = new MemberPolicyV1();
        assertThat(memberPolicyV1.verifyRegister(dto)).isEqualTo(false);
    }

    @Test
    void validationTest3(){
        MemberRegisterDTO dto = new MemberRegisterDTO();
        dto.setIdent("Ide   ");
        dto.setName("");
        dto.setPassword("");
        dto.setEmail("");


        MemberPolicyV1 memberPolicyV1 = new MemberPolicyV1();
        assertThat(memberPolicyV1.verifyRegister(dto)).isEqualTo(false);
    }

    @Test
    void validationTest4(){
        MemberRegisterDTO dto = new MemberRegisterDTO();
        dto.setIdent("Ide d");
        dto.setName("");
        dto.setPassword("");
        dto.setEmail("");


        MemberPolicyV1 memberPolicyV1 = new MemberPolicyV1();
        assertThat(memberPolicyV1.verifyRegister(dto)).isEqualTo(false);
    }
}