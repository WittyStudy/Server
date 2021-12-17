package witty.studyapp.dto.board;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static witty.studyapp.constant.board.NoticeConstant.*;

@Data
public class NoticeCreateDTO {

    @NotBlank
    @Size(min = MIN_TITLE_LENGTH, max = MAX_TITLE_LENGTH)
    private String title;

    @NotBlank
    @Size(min = MIN_CONTENT_LENGTH, max = MAX_CONTENT_LENGTH)
    private String content;
}
