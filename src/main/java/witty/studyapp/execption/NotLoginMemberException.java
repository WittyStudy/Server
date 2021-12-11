package witty.studyapp.execption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NotLoginMemberException extends SecurityException{
    public NotLoginMemberException(String s) {
        super(s);
    }
}
