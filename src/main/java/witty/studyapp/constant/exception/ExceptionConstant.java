package witty.studyapp.constant.exception;

public interface ExceptionConstant {
    String LOGIN_ARGUMENT_CONSTRAINTS = "로그인 형태가 올바르지 않습니다.";
    String REGISTER_ARGUMENT_CONSTRAINTS = "회원가입 형태가 올바르지 않습니다.";
    String ARGUMENT_CONSTRAINTS = "입력 값이 올바르지 않습니다.";

    String WRONG_PASSWORD="비밀번호가 일치하지 않습니다.";
    String MEMBER_RELATION_ALREADY = "이미 친구 관계인 사용자입니다.";
    String MEMBER_RELATION_MYSELF = "자기 자신을 친구로 등록할 수 없습니다.";
    String MEMBER_RELATION_NO_RELATION = "친구 관계가 아닙니다.";
    String MEMBER_RELATION = "알 수 없는 관계 오류입니다.";
    String NO_AUTHORIZATION_WRITER = "작성자만 수행이 가능합니다.";
    String NO_SUCH_BOARD = "존재하지 않는 게시글입니다.";
    String NO_SUCH_COMMENT = "존재하지 않는 댓글입니다.";
    String NOT_FOUND_MEMBER = "존재하지 않는 사용자입니다.";
    String NOT_FOUND_LOGIN = "먼저 로그인을 해야 합니다.";

    String UNKNOWN = "알 수 없는 서버 내부 오류입니다.";
}
