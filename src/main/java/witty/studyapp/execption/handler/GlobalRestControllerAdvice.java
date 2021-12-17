package witty.studyapp.execption.handler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import witty.studyapp.execption.custom.*;
import witty.studyapp.execption.result.ErrorResult;

import java.util.Objects;

import static witty.studyapp.constant.exception.ExceptionConstant.*;

@Slf4j
@RestControllerAdvice(basePackages = "witty.studyapp")
@AllArgsConstructor
public class GlobalRestControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LoginArgumentException.class)
    public ErrorResult exceptionHandler(LoginArgumentException e) {
        return new ErrorResult(e, LOGIN_ARGUMENT_CONSTRAINTS);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(NoAuthorizationException.class)
    public ErrorResult exceptionHandler(NoAuthorizationException e) {
        return new ErrorResult(e, NO_AUTHORIZATION_WRITER);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchBoardException.class)
    public ErrorResult exceptionHandler(NoSuchBoardException e) {
        return new ErrorResult(e, NO_SUCH_BOARD);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NotFoundUserException.class)
    public ErrorResult exceptionHandler(NotFoundUserException e) {
        return new ErrorResult(e, NOT_FOUND_MEMBER);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(NotLoginMemberException.class)
    public ErrorResult exceptionHandler(NotLoginMemberException e) {
        return new ErrorResult(e,NOT_FOUND_LOGIN);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RegisterArgumentException.class)
    public ErrorResult exceptionHandler(RegisterArgumentException e) {
        return new ErrorResult(e, REGISTER_ARGUMENT_CONSTRAINTS);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MemberRelationException.class)
    public ErrorResult exceptionHandler(MemberRelationException e) {
        String message = e.getMessage();
        return new ErrorResult(e, Objects.requireNonNullElse(message, MEMBER_RELATION));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoSuchCommentException.class)
    public ErrorResult exceptionHandler(NoSuchCommentException e) {
        return new ErrorResult(e, NO_SUCH_COMMENT);
    }
}
