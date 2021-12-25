package witty.studyapp.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MemberRelation {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "relation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // TODO : target member 조회 테스트 필요
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Member target;
}
