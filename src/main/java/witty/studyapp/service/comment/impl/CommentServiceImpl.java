package witty.studyapp.service.comment.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import witty.studyapp.entity.Comment;
import witty.studyapp.execption.NoSuchBoardException;
import witty.studyapp.execption.NotFoundUserException;
import witty.studyapp.repository.board.BoardRepository;
import witty.studyapp.repository.comment.CommentRepository;
import witty.studyapp.repository.member.MemberRepository;
import witty.studyapp.service.comment.CommentService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Override
    public List<Comment> getCommentsByBoardId(Long boardId) {
        return boardRepository.findById(boardId).map(commentRepository::findByBoard).orElseThrow(() -> new NoSuchBoardException("게시글이 존재하지 않습니다."));
    }

    @Override
    public List<Comment> getCommentsByMemberId(Long memberId) throws NotFoundUserException {
        return memberRepository.findById(memberId).map(commentRepository::findByMember).orElseThrow(() -> new NotFoundUserException("해당 사용자를 찾을 수 없습니다"));
    }

    @Override
    public Long createComment(Comment comment, Long memberId, Long boardId) {
        try {
            return memberRepository.findById(memberId).map(member -> {
                comment.setWriter(member);
                return boardRepository.findById(boardId).map(notice -> {
                    comment.setNotice(notice);
                    return comment.getId();
                }).orElse(0L);
            }).orElse(0L);
        } catch (Exception e) {    // Exception 정의 필요.
            return 0L;
        }
    }

    @Override
    public Long deleteComment(long commentId) {
        try {
            commentRepository.deleteById(commentId);
            return commentId;
        } catch (Exception e) {    // Exception 정의 필요.
            return 0L;
        }
    }

    @Override
    public Long updateComment(Comment comment, Long commentId) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        try {
            return commentOptional.map(c -> {
                c.setContent(comment.getContent());
                return commentId;
            }).orElse(0L);
        } catch (Exception e) {
            return 0L;
        }
    }
}
