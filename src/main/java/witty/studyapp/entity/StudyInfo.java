package witty.studyapp.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class StudyInfo {

    @Id
    @GeneratedValue
    @Column(name = "STUDY_INFO_ID")
    private Long id;

    @ManyToOne
    private StudyPolicy studyPolicy;
}
