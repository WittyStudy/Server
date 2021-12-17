package witty.studyapp.execption.result;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.i18n.LocaleContextHolder;

@Getter
@Setter
public class ErrorResult {
    String code;
    String message;

    public ErrorResult(Exception e, String message) {
        this.code = e.getClass().getSimpleName();
        this.message = message;
    }
}
