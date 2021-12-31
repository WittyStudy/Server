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

    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer;

    @OneToOne(fetch = FetchType.LAZY)
    private Message message;
}
