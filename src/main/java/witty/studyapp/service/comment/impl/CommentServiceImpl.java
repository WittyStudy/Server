package witty.studyapp.service.comment.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import witty.studyapp.dto.comment.CommentDTO;
import witty.studyapp.entity.Comment;
import witty.studyapp.repository.comment.CommentRepository;
import witty.studyapp.service.comment.CommentService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public List<Comment> getCommentsByBoardId(Long boardId) {
        return commentRepository.findByBoardId(boardId);
    }

    @Override
    public List<Comment> getCommentsByMemberId(Long memberId) {
        return commentRepository.findByMemberId(memberId);
    }

    @Override
    public Long createComment(CommentDTO commentDTO, Long memberId, Long boardId) {
        Comment comment = Comment.getByDTO(commentDTO, memberId, boardId);
        return commentRepository.save(comment);
    }

    @Override
    public Long deleteComment(long commentId) {
        return commentRepository.deleteById(commentId);
    }

    @Override
    public Long updateComment(CommentDTO commentDTO, Long commentId) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        return commentOptional.map(comment -> {
            comment.setContext(commentDTO.getContent());
            return commentId;
        }).orElse(0L);
    }
}
