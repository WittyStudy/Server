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
public class Member {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name="email")
    private String email;

    @Column(name="name")
    private String name;

    @Column(name="password")
    private String password;

    // TODO : member에서 commentList 목록 출력 테스트 및 이용 필요
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="writer")
    private List<Comment> commentList = new ArrayList<>();

    // TODO : member에서 boardList 목록 출력 테스트 및 이용 필요
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="writer")
    private List<Notice> noticeList = new ArrayList<>();
}
