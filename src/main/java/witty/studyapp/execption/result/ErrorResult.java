package witty.studyapp.execption.result;

public class ErrorResult {
    String code;
    String message;

    public ErrorResult(Exception e) {
        this.code = e.getClass().getSimpleName();
        this.message = e.getMessage();
    }
}
