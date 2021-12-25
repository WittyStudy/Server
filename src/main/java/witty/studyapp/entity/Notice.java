package witty.studyapp.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Notice {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    @Column(name="title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member writer;

    @Column(name="views")
    private Long views;

    @Column(name="date")
    private String date;

    @Column(name="content")
    private String content;

    // TODO : notice에서 commentList 목록 출력 테스트 및 이용 필요
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="notice")
    private List<Comment> commentList = new ArrayList<>();
}
