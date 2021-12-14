package witty.studyapp.execption.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResult {
    String code;
    String message;

    public ErrorResult(Exception e) {
        this.code = e.getClass().getSimpleName();
        this.message = e.getMessage();
    }
}
