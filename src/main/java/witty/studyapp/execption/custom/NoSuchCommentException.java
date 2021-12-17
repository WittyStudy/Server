package witty.studyapp.execption.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchCommentException extends NoSuchElementException {
    public NoSuchCommentException(String s) {
        super(s);
    }
}
