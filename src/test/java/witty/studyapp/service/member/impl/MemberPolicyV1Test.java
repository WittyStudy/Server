package witty.studyapp.service.member.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import witty.studyapp.entity.Member;

import static org.assertj.core.api.Assertions.assertThat;

class MemberPolicyV1Test {

    Member member;

    @BeforeEach
    void beforeEach(){
        member = new Member();
        member.setEmail("minsoo@hello.com");
        member.setName("MyName");
        member.setPassword("password");
    }

    @Test
    @DisplayName("테스트 기본 설정 확인")
    void identTest0(){
        MemberPolicyV1 memberPolicyV1 = new MemberPolicyV1();
        assertThat(memberPolicyV1.verifyMember(member)).isEqualTo(true);
    }

    @Test
    @DisplayName("영문 한글 혼합 비정상 이름 확인")
    void identTest1(){
        member.setName("김minz");

        MemberPolicyV1 memberPolicyV1 = new MemberPolicyV1();
        assertThat(memberPolicyV1.verifyMember(member)).isEqualTo(false);
    }
}