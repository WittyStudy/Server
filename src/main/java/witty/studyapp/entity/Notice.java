package witty.studyapp.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Notice {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "NOTICE_ID")
    private Long id;

    @Column(name="title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member writer;

    @Column(name="views")
    private Long views;

    @Column(name="date")
    private String date;

    @Column(name="content")
    private String content;
}
