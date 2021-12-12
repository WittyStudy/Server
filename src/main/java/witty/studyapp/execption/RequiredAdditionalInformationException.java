package witty.studyapp.execption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredAdditionalInformationException extends IllegalArgumentException {
    public RequiredAdditionalInformationException(String message) {
        super(message);
    }
}
