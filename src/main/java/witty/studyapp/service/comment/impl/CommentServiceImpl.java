package witty.studyapp.service.comment.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import witty.studyapp.entity.Comment;
import witty.studyapp.entity.Member;
import witty.studyapp.execption.custom.NoAuthorizationException;
import witty.studyapp.execption.custom.NoSuchBoardException;
import witty.studyapp.execption.custom.NoSuchCommentException;
import witty.studyapp.execption.custom.NotFoundUserException;
import witty.studyapp.repository.board.BoardRepository;
import witty.studyapp.repository.comment.CommentRepository;
import witty.studyapp.repository.member.MemberRepository;
import witty.studyapp.service.comment.CommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> getCommentsByBoardId(Long boardId) {
        return boardRepository.findById(boardId).map(commentRepository::findByBoard)
                .orElseThrow(() -> {
                    throw new NoSuchBoardException();
                });
    }

    @Override
    public List<Comment> getCommentsByMemberId(Long memberId) {
        return memberRepository.findById(memberId).map(commentRepository::findByMember)
                .orElseThrow(NotFoundUserException::new);
    }

    @Override
    public Long createComment(Comment comment, Long memberId, Long boardId) {
        return memberRepository.findById(memberId).map(member -> {
            comment.setWriter(member);
            return boardRepository.findById(boardId).map(notice -> {
                comment.setNotice(notice);
                commentRepository.save(comment);
                return comment.getId();
            }).orElseThrow(NoSuchBoardException::new);
        }).orElseThrow(NotFoundUserException::new);
    }

    @Override
    public Long deleteComment(Member member, long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(NoSuchCommentException::new);
        verifyWriterOnComment(member, comment);
        commentRepository.deleteById(commentId);
        return commentId;
    }

    @Override
    public Long updateComment(Member member, Comment newComment) {
        Comment comment = commentRepository.findById(newComment.getId()).orElseThrow(NoSuchCommentException::new);
        verifyWriterOnComment(member, comment);
        commentRepository.updateComment(newComment.getId(), newComment.getContent());
        return comment.getId();
    }

    private void verifyWriterOnComment(Member member, Comment comment){
        if(!comment.getWriter().getId().equals(member.getId())){
            throw new NoAuthorizationException();
        }
    }

}
