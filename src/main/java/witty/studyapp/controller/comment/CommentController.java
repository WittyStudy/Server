package witty.studyapp.controller.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import witty.studyapp.annotation.Login;
import witty.studyapp.dto.comment.CommentCreateDTO;
import witty.studyapp.dto.comment.CommentResponseDTO;
import witty.studyapp.dto.comment.CommentUpdateDTO;
import witty.studyapp.entity.Comment;
import witty.studyapp.entity.Member;
import witty.studyapp.execption.custom.NoSuchBoardException;
import witty.studyapp.execption.custom.NotFoundUserException;
import witty.studyapp.service.comment.CommentService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public String test() {
        return "OK";
    }

    @GetMapping("/{boardId}")
    public List<CommentResponseDTO> getCommentsByBoardId(@PathVariable Long boardId) throws NotFoundUserException {
        if (boardId == null || boardId == 0L) {
            throw new NoSuchBoardException();
        }
        return getCommentResponseDTOs(commentService.getCommentsByBoardId(boardId));
    }

    @PostMapping
    public Long createComment(@Login Member loginMember, @RequestBody CommentCreateDTO commentCreateDTO) {
        return commentService.createComment(Comment.builder()
                .content(commentCreateDTO.getContent())
                .build(),
                loginMember.getId(), commentCreateDTO.getBoardId());
    }

    @PatchMapping
    public Long updateCommentContext(@Login Member loginMember, @RequestBody CommentUpdateDTO commentUpdateDTO) {
        return commentService.updateComment(loginMember, Comment.builder()
                .content(commentUpdateDTO.getContent())
                .id(commentUpdateDTO.getCommentId())
                .build());
    }

    @DeleteMapping("/{commentId}")
    public Long deleteCommentById(@Login Member loginMember, @PathVariable Long commentId) {
        return commentService.deleteComment(loginMember, commentId);
    }

    private List<CommentResponseDTO> getCommentResponseDTOs(List<Comment> comments) {
        List<CommentResponseDTO> result = new ArrayList<>();
        for (Comment comment : comments) {
            result.add(CommentResponseDTO.builder()
                    .id(comment.getId())
                    .content(comment.getContent())
                    .writerName(comment.getWriter().getName())
                    .build());
        }
        return result;
    }
}
