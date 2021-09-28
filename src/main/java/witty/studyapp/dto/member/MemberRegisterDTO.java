package witty.studyapp.dto.member;

import lombok.Data;

@Data
public class MemberRegisterDTO {
    private String ident;
    private String name;
    private String password;
    private String email;
}
