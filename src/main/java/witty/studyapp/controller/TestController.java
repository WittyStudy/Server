package witty.studyapp.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import witty.studyapp.repository.dummy.DummyCreator;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/test")
public class TestController {

    private final DummyCreator dummyCreator;

    @GetMapping
    public String test() {
        log.info("TEST RECEIVE.");
        return "OK";
    }

    @GetMapping("/create/{number}")
    public String testCreate(@PathVariable int number) {
        try {
            dummyCreator.create(number);
            return "CREATE [" + number + "]";
        }catch(Exception e){
            return "FAIL. already exists or Server Error. [use : /clear]";
        }
    }

    @GetMapping("/clear")
    public String clear() {
        return "CLEAR " + dummyCreator.clear();
    }
}
