package witty.studyapp.execption.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import witty.studyapp.execption.*;
import witty.studyapp.execption.result.ErrorResult;

@Slf4j
@RestControllerAdvice(basePackages = "witty.studyapp")
public class GlobalRestControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LoginArgumentException.class)
    public ErrorResult exceptionHandler(LoginArgumentException e){
        return new ErrorResult(e);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(NoAuthorizationException.class)
    public ErrorResult exceptionHandler(NoAuthorizationException e){
        return new ErrorResult(e);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchBoardException.class)
    public ErrorResult exceptionHandler(NoSuchBoardException e){
        return new ErrorResult(e);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NotFoundUserException.class)
    public ErrorResult exceptionHandler(NotFoundUserException e){
        return new ErrorResult(e);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(NotLoginMemberException.class)
    public ErrorResult exceptionHandler(NotLoginMemberException e){
        log.info("return ErrorResult");
        return new ErrorResult(e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotSupportedOptionException.class)
    public ErrorResult exceptionHandler(NotSupportedOptionException e){
        return new ErrorResult(e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RegisterArgumentException.class)
    public ErrorResult exceptionHandler(RegisterArgumentException e){
        return new ErrorResult(e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RequiredAdditionalInformationException.class)
    public ErrorResult exceptionHandler(RequiredAdditionalInformationException e){
        return new ErrorResult(e);
    }

}
