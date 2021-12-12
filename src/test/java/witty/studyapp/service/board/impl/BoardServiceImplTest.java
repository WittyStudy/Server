package witty.studyapp.service.board.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import witty.studyapp.entity.Member;
import witty.studyapp.entity.Notice;
import witty.studyapp.service.board.BoardService;
import witty.studyapp.service.member.MemberService;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class BoardServiceImplTest {

    @Autowired
    BoardService boardService;

    @Autowired
    MemberService memberService;

    Member addTestUser(){
        Member member = new Member();
        member.setEmail("test@naver.com");
        member.setPassword("secret!secret");
        member.setName("testName");
        memberService.register(member);
        return member;
    }

    @Test
    void createNoticeAndGetById() {
        Member member = addTestUser();
        Notice notice = new Notice();
        notice.setContent("CONTENT");
        notice.setTitle("TITLE");
        notice.setWriter(member);
        notice.setDate(new Date(System.currentTimeMillis()).toString());
        boardService.createNotice(notice);
        Optional<Notice> byId = boardService.getById(notice.getId());
        assertThat(byId.isPresent()).isTrue();
        Notice find = byId.get();
        assertThat(find.getId()).isEqualTo(notice.getId());
    }

    @Test
    void getNotices() {
        Member member = addTestUser();
        int prev = boardService.getNotices().size();
        for (int i = 0; i < 5; i++) {
            Notice notice = new Notice();
            notice.setContent("CONTENT#"+(i+1));
            notice.setTitle("TITLE#"+(i+1));
            notice.setWriter(member);
            notice.setDate(new Date(System.currentTimeMillis()).toString());
            boardService.createNotice(notice);
        }
        List<Notice> notices = boardService.getNotices();
        assertThat(notices.size()).isEqualTo(prev + 5);
    }

    @Test
    void updateNotice() {
        Member member = addTestUser();
        Notice notice = new Notice();
        notice.setContent("CONTENT");
        notice.setTitle("TITLE");
        notice.setWriter(member);
        notice.setDate(new Date(System.currentTimeMillis()).toString());
        boardService.createNotice(notice);

        Notice to = new Notice();
        to.setContent("newc");
        to.setTitle("newt");

        boardService.updateNotice(notice.getId(), to);
        Optional<Notice> byId = boardService.getById(notice.getId());
        assertThat(byId.isPresent()).isTrue();
        String title = byId.get().getTitle();
        String content = byId.get().getContent();
        assertThat(title).isLessThanOrEqualTo("newc");
        assertThat(content).isLessThanOrEqualTo("newt");
    }

    @Test
    void deleteNotice() {
        int prev = boardService.getNotices().size();

        Member member = addTestUser();
        Notice notice = new Notice();
        notice.setContent("CONTENT");
        notice.setTitle("TITLE");
        notice.setWriter(member);
        notice.setDate(new Date(System.currentTimeMillis()).toString());
        boardService.createNotice(notice);
        boardService.deleteNotice(notice.getId());
        List<Notice> notices = boardService.getNotices();
        assertThat(notices.size()).isEqualTo(prev);
    }
}