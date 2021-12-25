package witty.studyapp.controller.error;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import witty.studyapp.execption.custom.NotFoundPageException;

@RestController
public class ErrorController {

    @GetMapping("/error")
    public String showError() throws NotFoundPageException {
        throw new NotFoundPageException();
    }
}
