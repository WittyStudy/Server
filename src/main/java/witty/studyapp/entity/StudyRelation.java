package witty.studyapp.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class StudyRelation {

    @Id
    @GeneratedValue
    @Column(name = "STUDY_RELATION_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member participant;

    @ManyToOne(fetch = FetchType.LAZY)
    private Study study;
}
