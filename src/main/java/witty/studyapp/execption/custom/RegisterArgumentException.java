package witty.studyapp.execption.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RegisterArgumentException extends IllegalArgumentException{
    public RegisterArgumentException(String s) {
        super(s);
    }
}
