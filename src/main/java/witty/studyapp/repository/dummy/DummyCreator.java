package witty.studyapp.repository.dummy;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import witty.studyapp.entity.Comment;
import witty.studyapp.entity.Member;
import witty.studyapp.entity.Notice;
import witty.studyapp.service.board.BoardService;
import witty.studyapp.service.comment.CommentService;
import witty.studyapp.service.member.MemberService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class DummyCreator {
    private final MemberService memberService;
    private final BoardService boardService;
    private final CommentService commentService;

    public void create(int number) {
        List<Member> members = getMembers(number);
        for (int i = 0; i < number; i++) {
            members.get(i).setId(memberService.register(members.get(i)));
            log.warn("name =[{}], id =[{}]", members.get(i).getName(), members.get(i).getId());
        }


        List<Notice> boards = getNotices(members);
        for (int i = 0; i < number; i++) {
            boards.get(i).setId(boardService.createNotice(boards.get(i)));
        }

        List<Comment> comments = getComments(members, boards);
        for (Comment comment : comments) {
            commentService.createComment(comment, comment.getWriter().getId(), comment.getNotice().getId());
        }

    }

    public String clear() {
        String result;
        List<Member> members = memberService.getAllMembers();
        List<Notice> notices = boardService.getNotices();
        List<Comment> comments = commentService.getAllComments();

        result = "[" + members.size() + "][" + notices.size() + "][" + comments.size() + "]";
        for (Comment comment : comments) {
            commentService.deleteComment(memberService.getMemberById(comment.getWriter().getId()), comment.getId());
        }
        for (Notice notice : notices) {
            boardService.deleteNotice(notice.getId());
        }
        for (Member member : members) {
            memberService.deleteMember(member.getId());
        }
        return result;
    }

    public List<Comment> getComments(List<Member> members, List<Notice> notices) {
        List<Comment> result = new ArrayList<>();
        for (int i = 0; i < members.size(); i++) {
            for (int j = 0; j < notices.size(); j++) {
                Comment comment = new Comment();
                comment.setContent("this is comment. on notice[#" + notices.get(j).getId() + "] created by " + members.get(i).getName());
                comment.setWriter(memberService.getMemberById(members.get(i).getId()));
                comment.setNotice(boardService.getById(notices.get(j).getId()).get());
                result.add(comment);
            }
        }
        return result;
    }

    public List<Notice> getNotices(List<Member> members) {
        List<Notice> result = new ArrayList<>();
        for (int i = 0; i < members.size(); i++) {
            Notice notice = new Notice();
            Date date = new Date(System.currentTimeMillis());
            notice.setDate(date.toString());
            notice.setWriter(memberService.getMemberById(members.get(i).getId()));
            notice.setTitle("title " + (i + 1) + " created by " + members.get(i).getName());
            notice.setViews(1L + i);
            notice.setContent("content " + (i + 1) + " created by " + members.get(i).getName());
            result.add(notice);
        }
        return result;
    }


    public List<Member> getMembers(int number) {
        List<Member> result = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            Member member = new Member();
            member.setEmail("test" + (i + 1) + "@naver.com");
            member.setName("test" + (i + 1) + "user");
            member.setPassword("password" + (i + 1));
            result.add(member);
        }
        return result;
    }

}
