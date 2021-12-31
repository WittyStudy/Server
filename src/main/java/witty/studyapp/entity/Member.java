package witty.studyapp.entity;

import lombok.*;
import witty.studyapp.dto.member.MemberRegisterDTO;

import javax.persistence.*;

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

    @OneToOne
    @JoinColumn(name = "MEMBER_INFO_ID")
    private MemberInfo memberInfo;

    @Column(name="email")
    private String email;

    @Column(name="name")
    private String name;

    @Column(name="password")
    private String password;

}
