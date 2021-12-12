package witty.studyapp.execption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class NotFoundUserException extends NoSuchElementException {
    public NotFoundUserException(String message) {
        super(message);
    }
}
