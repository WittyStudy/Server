package witty.studyapp.dto.member;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberResponseDTO {

    private String email;
    private String name;

}
