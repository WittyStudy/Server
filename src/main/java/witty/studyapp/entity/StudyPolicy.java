package witty.studyapp.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class StudyPolicy {

    @Id
    @GeneratedValue
    @Column(name = "STUDY_POLICY_ID")
    private Long id;
}
