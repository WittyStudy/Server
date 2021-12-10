package witty.studyapp.dto.member;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static witty.studyapp.constant.member.MemberConstant.*;

@Data
public class MemberRegisterDTO {

    @NotBlank
    @Email
    @Size(min = MIN_EMAIL_LENGTH, max = MAX_EMAIL_LENGTH)
    private String email;

    @NotBlank
    @Size(min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH)
    private String name;

    @NotBlank
    @Size(min = MIN_PASSWORD_LENGTH, max = MAX_PASSWORD_LENGTH)
    private String password;

}
