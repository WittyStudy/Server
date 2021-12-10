package witty.studyapp.service.comment.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import witty.studyapp.entity.Comment;
import witty.studyapp.repository.board.BoardRepository;
import witty.studyapp.repository.comment.CommentRepository;
import witty.studyapp.repository.member.MemberRepository;
import witty.studyapp.service.comment.CommentService;

import java.util.ArrayList;
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
        try {
            return boardRepository.findById(boardId).map(commentRepository::findByBoard)
                    .orElse(new ArrayList<>());
        }catch(Exception e){    // Exception 처리 필요
            return new ArrayList<>();
        }
    }

    @Override
    public List<Comment> getCommentsByMemberId(Long memberId) {
        try {
            return memberRepository.findById(memberId).map(commentRepository::findByMember)
                    .orElse(new ArrayList<>());
        }catch(Exception e){        // Exception 처리 필요.
            return new ArrayList<>();
        }
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
        }catch(Exception e){    // Exception 정의 필요.
            return 0L;
        }
    }

    @Override
    public Long deleteComment(long commentId) {
        try {
            commentRepository.deleteById(commentId);
            return commentId;
        }catch(Exception e){    // Exception 정의 필요.
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
        }catch(Exception e){
            return 0L;
        }
    }
}
