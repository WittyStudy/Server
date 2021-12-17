package witty.studyapp.execption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MemberRelationException extends IllegalArgumentException{
    public MemberRelationException(String s) {
        super(s);
    }
}
