package witty.studyapp.dto.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static witty.studyapp.constant.board.NoticeConstant.*;
import static witty.studyapp.constant.board.NoticeConstant.MAX_CONTENT_LENGTH;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeUpdateDTO {

    @NotBlank
    @Size(min = MIN_TITLE_LENGTH, max = MAX_TITLE_LENGTH)
    private String title;

    @NotBlank
    @Size(min = MIN_CONTENT_LENGTH, max = MAX_CONTENT_LENGTH)
    private String content;
}