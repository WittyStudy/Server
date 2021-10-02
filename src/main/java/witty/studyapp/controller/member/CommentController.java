package witty.studyapp.controller.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import witty.studyapp.dto.comment.CommentDTO;
import witty.studyapp.entity.Comment;
import witty.studyapp.service.comment.CommentService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public String test(){
        return "OK";
    }

    @GetMapping("/members/{memberId}")
    public List<Comment> getCommentsByMemberId(@PathVariable Long memberId){
        return commentService.getCommentsByMemberId(memberId);
    }

    @GetMapping("/boards/{boardId}")
    public List<Comment> getCommentsByBoardId(@PathVariable Long boardId){
        return commentService.getCommentsByBoardId(boardId);
    }

    @PostMapping("/members/{memberId}/boards/{boardId}")
    public Long createComment(CommentDTO commentDTO, @PathVariable Long memberId, @PathVariable Long boardId){
        return commentService.createComment(commentDTO, memberId, boardId);
    }

    @PatchMapping("/{commentId}")
    public Long updateCommentContext(CommentDTO commentDTO, @PathVariable Long commentId){
        return commentService.updateComment(commentDTO,commentId);
    }

    @DeleteMapping("/{commentId}")
    public Long deleteCommentById(@PathVariable Long commentId){
        return commentService.deleteComment(commentId);
    }
}
