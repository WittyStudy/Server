package witty.studyapp.execption.custom;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
public class RegisterArgumentException extends IllegalArgumentException{
    public RegisterArgumentException() {
    }

    public RegisterArgumentException(String message) {
        super(message);
    }
}
