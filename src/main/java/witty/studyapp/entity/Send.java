package witty.studyapp.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Send {

    @Id
    @GeneratedValue
    @Column(name = "SEND_ID")
    private Long id;

    @ManyToOne
    private Member writer;

    @ManyToOne
    private Message message;
}
