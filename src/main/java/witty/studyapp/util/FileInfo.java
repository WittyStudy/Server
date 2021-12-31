package witty.studyapp.util;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.FIELD)
@Getter
@Setter
public class FileInfo {
    private String inputPath;
    private String absolutePath;
}
