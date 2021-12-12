package witty.studyapp.controller.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import witty.studyapp.annotation.Login;
import witty.studyapp.dto.comment.CommentCreateDTO;
import witty.studyapp.dto.comment.CommentUpdateDTO;
import witty.studyapp.entity.Comment;
import witty.studyapp.entity.Member;
import witty.studyapp.execption.NotSupportedOptionException;
import witty.studyapp.execption.RequiredAdditionalInformationException;
import witty.studyapp.service.comment.CommentService;

import java.util.List;

import static witty.studyapp.constant.comment.CommentOption.*;

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

    @GetMapping("/{option}/{id}")
    public List<Comment> getCommentsByMemberId(@Login Member loginMember, @PathVariable String option, @PathVariable(required = false) Long id){
        if(option.equals(BY_USER_ID)){
            return commentService.getCommentsByMemberId(loginMember.getId());
        }else if(option.equals(BY_BOARD_ID)){
            if(id == null) {
                throw new RequiredAdditionalInformationException("BOARD ID값이 필요합니다.");
            }
            return commentService.getCommentsByBoardId(id);
        }else{
            throw new NotSupportedOptionException("존재하지 않는 option 입니다.");
        }
    }

    @PostMapping
    public Long createComment(@Login Member loginMember, @RequestBody CommentCreateDTO commentCreateDTO){
        Comment comment = new Comment();
        comment.setContent(commentCreateDTO.getContent());
        return commentService.createComment(comment, loginMember.getId(), commentCreateDTO.getBoardId());
    }

    @PatchMapping
    public Long updateCommentContext(@RequestBody CommentUpdateDTO commentUpdateDTO){
        Comment comment = new Comment();
        comment.setContent(commentUpdateDTO.getContent());
        return commentService.updateComment(comment, commentUpdateDTO.getCommentId());
    }

    @DeleteMapping("/{commentId}")
    public Long deleteCommentById(@PathVariable Long commentId){
        return commentService.deleteComment(commentId);
    }
}
