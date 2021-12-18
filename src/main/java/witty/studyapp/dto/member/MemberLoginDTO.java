package witty.studyapp.dto.member;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class MemberLoginDTO {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
