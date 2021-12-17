package witty.studyapp.service.relation.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import witty.studyapp.entity.Member;
import witty.studyapp.entity.MemberRelation;
import witty.studyapp.execption.MemberRelationException;
import witty.studyapp.execption.NotFoundUserException;
import witty.studyapp.execption.NotLoginMemberException;
import witty.studyapp.repository.member.MemberRepository;
import witty.studyapp.repository.relation.MemberRelationRepository;
import witty.studyapp.service.relation.MemberRelationService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MemberRelationServiceImpl implements MemberRelationService {

    private final MemberRelationRepository memberRelationRepository;
    private final MemberRepository memberRepository;

    @Override
    public Long addRelation(Long id, Long targetId) {

        Member member = memberRepository.findById(id).orElseThrow(() -> new NotLoginMemberException("사용자를 식별할 수 없습니다. 다시 로그인해야 합니다."));
        Member target = memberRepository.findById(targetId).orElseThrow(() -> new NotFoundUserException("존재하지 않는 사용자입니다."));

        if (memberRelationRepository.findByMember(member).stream().anyMatch(tar -> tar.getTarget().getId().equals(targetId))) {
            throw new MemberRelationException("이미 친구인 사용자입니다.");
        }
        if (id.equals(targetId)) {
            throw new MemberRelationException("자기 자신을 친구로 등록할 수 없습니다.");
        }

        MemberRelation memberRelation = new MemberRelation();
        memberRelation.setMember(member);
        memberRelation.setTarget(target);
        memberRelationRepository.save(memberRelation);
        return targetId;
    }

    @Override
    public List<Member> getFriendsList(Long id) {
        return memberRelationRepository.findByMember(
                        memberRepository
                                .findById(id)
                                .orElseThrow(() -> new NotLoginMemberException("사용자를 식별할 수 없습니다. 다시 로그인해야 합니다."))
                ).stream()
                .map(MemberRelation::getTarget)
                .collect(Collectors.toList());
    }

    @Override
    public Long deleteRelation(Long id, Long targetId) {
        memberRepository.findById(id).orElseThrow(() -> new NotLoginMemberException("사용자를 식별할 수 없습니다. 다시 로그인해야 합니다."));
        memberRepository.findById(targetId).orElseThrow(() -> new NotFoundUserException("존재하지 않는 사용자입니다."));

        memberRelationRepository.deleteById(
                memberRelationRepository.findByMemberAndTarget(id, targetId)
                        .map(MemberRelation::getId)
                        .orElseThrow(() -> new MemberRelationException("친구 관계가 아닙니다.")));
        return targetId;
    }
}
