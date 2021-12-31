package witty.studyapp.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Study {

    @Id
    @GeneratedValue
    @Column(name = "STUDY_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member master;

    @OneToOne(fetch = FetchType.LAZY)
    private StudyInfo studyInfo;
}
