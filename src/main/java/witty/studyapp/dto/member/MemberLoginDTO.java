package witty.studyapp.dto.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberLoginDTO {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
