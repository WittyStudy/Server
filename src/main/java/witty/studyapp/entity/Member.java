package witty.studyapp.entity;

import lombok.*;
import witty.studyapp.dto.member.MemberRegisterDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class Member {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_INFO_ID")
    private MemberInfo memberInfo;

    @Column(name="email")
    private String email;

    @Column(name="name")
    private String name;

    @Column(name="password")
    private String password;

    @OneToMany(mappedBy = "writer")
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "writer")
    private List<Notice> noticeList = new ArrayList<>();
}
