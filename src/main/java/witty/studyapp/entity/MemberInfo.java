package witty.studyapp.entity;

import lombok.*;
import witty.studyapp.util.FileInfo;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MemberInfo {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_INFO_ID")
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "firebase_token")
    private String firebase_token;

    @Embedded
    @Column(name = "profile_picture")
    private FileInfo profile_picture;
}
