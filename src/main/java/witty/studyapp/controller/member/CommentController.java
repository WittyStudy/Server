package witty.studyapp.controller.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import witty.studyapp.annotation.Login;
import witty.studyapp.constant.comment.CommentOption;
import witty.studyapp.dto.comment.CommentDTO;
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

    @PostMapping("/members/{memberId}/boards/{boardId}")
    public Long createComment(@RequestBody CommentDTO commentDTO, @PathVariable Long memberId, @PathVariable Long boardId){
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        return commentService.createComment(comment, memberId, boardId);
    }

    @PatchMapping("/{commentId}")
    public Long updateCommentContext(@RequestBody Comment comment, @PathVariable Long commentId){
        return commentService.updateComment(comment,commentId);
    }

    @DeleteMapping("/{commentId}")
    public Long deleteCommentById(@PathVariable Long commentId){
        return commentService.deleteComment(commentId);
    }
}
