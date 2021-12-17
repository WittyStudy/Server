package witty.studyapp.service.relation.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import witty.studyapp.entity.Member;
import witty.studyapp.service.member.MemberService;
import witty.studyapp.service.relation.MemberRelationService;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberRelationServiceImplTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRelationService memberRelationService;

    @Test
    @DisplayName("사용자 친구 관계 추가 및 조회 서비스 테스트")
    void addRelationAndGetTest(){
        Member member1 = saveMember(1);
        Member member2 = saveMember(2);
        Member member3 = saveMember(3);
        memberRelationService.addRelation(member1.getId(),member2.getId());
        memberRelationService.addRelation(member1.getId(),member3.getId());

        List<Member> friendsList = memberRelationService.getFriendsList(member1.getId());
        // Add Member Relation Test
        assertThat(friendsList.size()).isEqualTo(2);

        // Get Added (Relation)Members
        assertThat(friendsList).contains(member2);
        assertThat(friendsList).contains(member3);
    }

    @Test
    @DisplayName("사용자 친구 관계 삭제 서비스 테스트")
    void deleteRelationTest(){
        Member member1 = saveMember(1);
        Member member2 = saveMember(2);
        Member member3 = saveMember(3);
        memberRelationService.addRelation(member1.getId(),member2.getId());
        memberRelationService.addRelation(member1.getId(),member3.getId());

        memberRelationService.deleteRelation(member1.getId(),member3.getId());
        List<Member> friendsList = memberRelationService.getFriendsList(member1.getId());

        assertThat(friendsList.size()).isEqualTo(1);
        assertThat(friendsList).contains(member2);
    }

    Member saveMember(int number){
        Member member = new Member();
        member.setEmail("test"+number+"@naver.com");
        member.setName("testname"+number);
        member.setPassword("password"+number);
        memberService.register(member);
        return member;
    }
}