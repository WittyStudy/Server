package witty.studyapp.service.comment.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import witty.studyapp.dto.comment.CommentCreateDTO;
import witty.studyapp.dto.comment.CommentUpdateDTO;
import witty.studyapp.entity.Comment;
import witty.studyapp.entity.Member;
import witty.studyapp.entity.Notice;
import witty.studyapp.service.board.BoardService;
import witty.studyapp.service.comment.CommentService;
import witty.studyapp.service.member.MemberService;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class CommentServiceImplTest {
    // TODO : TEST 3개 PASS 하기

    @Autowired
    MemberService memberService;

    @Autowired
    BoardService noticeService;

    @Autowired
    CommentService commentService;

    Member createDemoMember(int number) {
        Member member = new Member();
        char c = (char) (('a') + number);
        member.setName("testName"+ c);
        member.setPassword("secret!secret");
        member.setEmail("test"+c+"@naver.com");
        return member;
    }

    Notice createNotice(Member writer) {
        Notice notice = new Notice();
        notice.setWriter(writer);
        notice.setTitle("Title");
        notice.setContent("Content");
        return notice;
    }

    Comment createComment(Member writer, Notice notice, String content) {
        Comment comment = new Comment();
        comment.setNotice(notice);
        comment.setWriter(writer);
        comment.setContent(content);
        return comment;
    }

    @Test
    @DisplayName("게시판 ID로 댓글(Comments) 조회 서비스 테스트")
    void getCommentsByBoardId() {
        Member member = createDemoMember(1);
        Long memberId = memberService.register(member);
        assertThat(memberId).isEqualTo(member.getId());

        Member member2 = createDemoMember(2);
        Long memberId2 = memberService.register(member2);
        assertThat(memberId2).isEqualTo(member2.getId());

        Notice notice = createNotice(member);
        Long noticeId = noticeService.createNotice(notice);
        assertThat(noticeId).isEqualTo(notice.getId());

        int prev = commentService.getCommentsByBoardId(notice.getId()).size();

        for (int i = 0; i < 5; i++) {
            Comment comment = createComment(member, notice, "COMMENTs content #" + (i + 1));
            Comment comment2 = createComment(member2, notice, "COMMENTs content #" + (i + 1));
            commentService.createComment(member.getId(), new CommentCreateDTO(notice.getId(), comment.getContent()));
            commentService.createComment(member2.getId(), new CommentCreateDTO(notice.getId(), comment2.getContent()));
        }

        List<Comment> commentsByBoardId = commentService.getCommentsByBoardId(notice.getId());
        assertThat(commentsByBoardId.size()).isEqualTo(prev + 10);
    }

    @Test
    @DisplayName("Member Id로 해당 사용자가 작성한 댓글 목록 조회 서비스 테스트")
    void getCommentsByMemberId() {
        Member member = createDemoMember(1);
        Long memberId = memberService.register(member);
        assertThat(memberId).isEqualTo(member.getId());

        Member member2 = createDemoMember(2);
        Long memberId2 = memberService.register(member2);
        assertThat(memberId2).isEqualTo(member2.getId());

        Notice notice = createNotice(member);
        Long noticeId = noticeService.createNotice(notice);
        assertThat(noticeId).isEqualTo(notice.getId());

        int prev = commentService.getCommentsByBoardId(notice.getId()).size();

        for (int i = 0; i < 5; i++) {
            Comment comment = createComment(member, notice, "COMMENTs content #" + (i + 1));
            Comment comment2 = createComment(member2, notice, "COMMENTs content #" + (i + 1));
            commentService.createComment(member.getId(), new CommentCreateDTO(notice.getId(), comment.getContent()));
            commentService.createComment(member2.getId(), new CommentCreateDTO(notice.getId(), comment2.getContent()));
        }

        List<Comment> commentsByBoardId = commentService.getCommentsByMemberId(member.getId());
        assertThat(commentsByBoardId.size()).isEqualTo(prev + 5);
    }

    @Test
    @DisplayName("특정 댓글(Comment) ID로 해당 댓글 삭제 서비스 테스트")
    void deleteComment() {
        Member member = createDemoMember(1);
        Long memberId = memberService.register(member);
        assertThat(memberId).isEqualTo(member.getId());

        Notice notice = createNotice(member);
        Long noticeId = noticeService.createNotice(notice);
        assertThat(noticeId).isEqualTo(notice.getId());

        int prev = commentService.getCommentsByBoardId(notice.getId()).size();

        for (int i = 0; i < 5; i++) {
            Comment comment = createComment(member, notice, "COMMENTs content #" + (i + 1));
            commentService.createComment(member.getId(), new CommentCreateDTO(notice.getId(), comment.getContent()));
        }

        List<Comment> commentsByBoardId = commentService.getCommentsByBoardId(notice.getId());
        for (Comment comment : commentsByBoardId) {
            commentService.deleteComment(member.getId(), comment.getId());
        }
        assertThat(commentService.getCommentsByBoardId(notice.getId()).size()).isEqualTo(prev);
    }

    @Test
    @DisplayName("특정 댓글 내용 수정 테스트")
    void updateComment() {
        Member member = createDemoMember(1);
        Long memberId = memberService.register(member);
        assertThat(memberId).isEqualTo(member.getId());

        Notice notice = createNotice(member);
        Long noticeId = noticeService.createNotice(notice);
        assertThat(noticeId).isEqualTo(notice.getId());

        Comment comment = createComment(member, notice, "CONTENT");
        String new_content = "NEW_CONTENT";
        Comment comment2 = createComment(member, notice, new_content);

        Long commentId = commentService.createComment(member.getId(), new CommentCreateDTO(notice.getId(), comment.getContent()));
        assertThat(commentId).isNotNull();
        comment2.setId(comment.getId());

        commentService.updateComment(member.getId(), new CommentUpdateDTO(commentId, comment2.getContent()));
        Comment updatedComment = commentService.getAllComments().stream().filter(c -> c.getId().equals(commentId)).findAny().get();
        assertThat(updatedComment.getContent()).isEqualTo(new_content);
    }
}