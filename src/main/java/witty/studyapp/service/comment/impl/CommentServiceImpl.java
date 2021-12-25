package witty.studyapp.service.comment.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import witty.studyapp.dto.comment.CommentCreateDTO;
import witty.studyapp.dto.comment.CommentUpdateDTO;
import witty.studyapp.entity.Comment;
import witty.studyapp.entity.Member;
import witty.studyapp.entity.Notice;
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
@Transactional(readOnly = true)
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
                .orElseThrow(NoSuchBoardException::new);
    }

    @Override
    public List<Comment> getCommentsByMemberId(Long memberId) {
        return memberRepository.findById(memberId).map(commentRepository::findByMember)
                .orElseThrow(NotFoundUserException::new);
    }

    @Override
    @Transactional
    public Long createComment(Long memberId, CommentCreateDTO commentCreateDTO) {
        Member member = memberRepository.findById(memberId).orElseThrow(NotFoundUserException::new);
        Notice notice = boardRepository.findById(commentCreateDTO.getBoardId()).orElseThrow(NoSuchBoardException::new);

        Comment comment = new Comment();
        comment.setContent(commentCreateDTO.getContent());
        comment.setNotice(notice);
        comment.setWriter(member);
        commentRepository.save(comment);
        return comment.getId();
    }

    @Override
    @Transactional
    public Long deleteComment(Long memberId, long commentId) {
        Member member = memberRepository.findById(memberId).orElseThrow(NotFoundUserException::new);
        Comment comment = commentRepository.findById(commentId).orElseThrow(NoSuchCommentException::new);
        verifyWriterOnComment(member, comment);
        commentRepository.deleteById(commentId);
        return commentId;
    }

    @Override
    @Transactional
    public Long updateComment(Long memberId, CommentUpdateDTO commentUpdateDTO) {
        Member member = memberRepository.findById(memberId).orElseThrow(NotFoundUserException::new);
        Comment comment = commentRepository.findById(commentUpdateDTO.getCommentId()).orElseThrow(NoSuchCommentException::new);
        verifyWriterOnComment(member, comment);
        commentRepository.updateComment(commentUpdateDTO.getCommentId(), commentUpdateDTO.getContent());
        return comment.getId();
    }

    private void verifyWriterOnComment(Member member, Comment comment){
        if(!comment.getWriter().getId().equals(member.getId())){
            throw new NoAuthorizationException();
        }
    }

}
