package witty.studyapp.service.member.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import witty.studyapp.dto.member.MemberRegisterDTO;

import static org.assertj.core.api.Assertions.assertThat;

class MemberPolicyV1Test {

    MemberRegisterDTO dto;

    @BeforeEach
    void beforeEach(){
        dto = new MemberRegisterDTO();
        dto.setIdent("Ident");
        dto.setName("MyName");
        dto.setPassword("password");
        dto.setEmail("minsoo@hello.com");
    }

    @Test
    @DisplayName("테스트 기본 설정 확인")
    void identTest0(){
        MemberPolicyV1 memberPolicyV1 = new MemberPolicyV1();
        assertThat(memberPolicyV1.verifyRegister(dto)).isEqualTo(true);
    }

    @Test
    @DisplayName("정상 아이디 확인")
    void identTest1(){
        dto.setIdent("Ident");

        MemberPolicyV1 memberPolicyV1 = new MemberPolicyV1();
        assertThat(memberPolicyV1.verifyRegister(dto)).isEqualTo(true);
    }

    @Test
    @DisplayName("공백 포함 비정상 아이디 확인")
    void identTest2(){
        dto.setIdent("Ide  ");

        MemberPolicyV1 memberPolicyV1 = new MemberPolicyV1();
        assertThat(memberPolicyV1.verifyRegister(dto)).isEqualTo(false);
    }

    @Test
    @DisplayName("탭 포함 비정상 아이디 확인")
    void identTest3(){
        dto.setIdent("Ide\t");

        MemberPolicyV1 memberPolicyV1 = new MemberPolicyV1();
        assertThat(memberPolicyV1.verifyRegister(dto)).isEqualTo(false);
    }

    @Test
    @DisplayName("중간 공백 포함 비정상 아이디 확인")
    void identTest4(){
        dto.setIdent("Ide d");

        MemberPolicyV1 memberPolicyV1 = new MemberPolicyV1();
        assertThat(memberPolicyV1.verifyRegister(dto)).isEqualTo(false);
    }

    @Test
    @DisplayName("영문 한글 혼합 비정상 이름 확인")
    void identTest5(){
        dto.setName("김minz");

        MemberPolicyV1 memberPolicyV1 = new MemberPolicyV1();
        assertThat(memberPolicyV1.verifyRegister(dto)).isEqualTo(false);
    }

    @Test
    void testTest(){
        String string = "Hello2";
        System.out.println(string.matches("[a-zA-Z]*$"));
    }

}