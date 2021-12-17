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
import witty.studyapp.execption.NotFoundUserException;
import witty.studyapp.execption.NotSupportedOptionException;
import witty.studyapp.execption.RequiredAdditionalInformationException;
import witty.studyapp.service.comment.CommentService;

import java.util.ArrayList;
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
    public List<CommentResponseDTO> getCommentsByMemberId(@Login Member loginMember, @PathVariable String option, @PathVariable(required = false) Long id) throws NotFoundUserException {
        if(option.equals(BY_USER_ID)){
            return getCommentResponseDTOs(commentService.getCommentsByMemberId(loginMember.getId()));
        }else if(option.equals(BY_BOARD_ID)){
            if(id == null) {
                throw new RequiredAdditionalInformationException("BOARD ID 값이 필요합니다.");
            }
            return getCommentResponseDTOs(commentService.getCommentsByBoardId(id));
        }else{
            throw new NotSupportedOptionException("존재하지 않는 option 입니다.");
        }
    }

    private List<CommentResponseDTO> getCommentResponseDTOs(List<Comment> comments) {
        List<CommentResponseDTO> result = new ArrayList<>();
        for (Comment comment : comments) {
            CommentResponseDTO commentResponseDTO = new CommentResponseDTO(comment.getId(), comment.getContent(), comment.getWriter().getName(), comment.getNotice().getTitle());
            result.add(commentResponseDTO);
        }
        return result;
    }

    @PostMapping
    public Long createComment(@Login Member loginMember, @RequestBody CommentCreateDTO commentCreateDTO){
        Comment comment = new Comment();
        comment.setContent(commentCreateDTO.getContent());
        return commentService.createComment(comment, loginMember.getId(), commentCreateDTO.getBoardId());
    }

    @PatchMapping
    public Long updateCommentContext(@Login Member loginMember, @RequestBody CommentUpdateDTO commentUpdateDTO){
        Comment comment = new Comment();
        comment.setContent(commentUpdateDTO.getContent());
        comment.setId(commentUpdateDTO.getCommentId());
        return commentService.updateComment(loginMember, comment);
    }

    @DeleteMapping("/{commentId}")
    public Long deleteCommentById(@Login Member loginMember, @PathVariable Long commentId){
        return commentService.deleteComment(loginMember, commentId);
    }
}
