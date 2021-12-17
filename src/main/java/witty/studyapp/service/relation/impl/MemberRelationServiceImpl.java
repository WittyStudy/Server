package witty.studyapp.service.relation.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import witty.studyapp.entity.Member;
import witty.studyapp.entity.MemberRelation;
import witty.studyapp.execption.custom.MemberRelationException;
import witty.studyapp.execption.custom.NotFoundUserException;
import witty.studyapp.execption.custom.NotLoginMemberException;
import witty.studyapp.repository.member.MemberRepository;
import witty.studyapp.repository.relation.MemberRelationRepository;
import witty.studyapp.service.relation.MemberRelationService;

import java.util.List;
import java.util.stream.Collectors;

import static witty.studyapp.constant.exception.ExceptionConstant.*;

@Service
@AllArgsConstructor
public class MemberRelationServiceImpl implements MemberRelationService {

    private final MemberRelationRepository memberRelationRepository;
    private final MemberRepository memberRepository;

    @Override
    public Long addRelation(Long id, Long targetId) {

        Member member = memberRepository.findById(id).orElseThrow(NotLoginMemberException::new);
        Member target = memberRepository.findById(targetId).orElseThrow(NotFoundUserException::new);

        if (memberRelationRepository.findByMember(member).stream().anyMatch(tar -> tar.getTarget().getId().equals(targetId))) {
            throw new MemberRelationException(MEMBER_RELATION_ALREADY);
        }
        if (id.equals(targetId)) {
            throw new MemberRelationException(MEMBER_RELATION_MYSELF);
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
                                .orElseThrow(NotLoginMemberException::new)
                ).stream()
                .map(MemberRelation::getTarget)
                .collect(Collectors.toList());
    }

    @Override
    public Long deleteRelation(Long id, Long targetId) {
        memberRepository.findById(id).orElseThrow(NotLoginMemberException::new);
        memberRepository.findById(targetId).orElseThrow(NotFoundUserException::new);

        memberRelationRepository.deleteById(
                memberRelationRepository.findByMemberAndTarget(id, targetId)
                        .map(MemberRelation::getId)
                        .orElseThrow(() -> new MemberRelationException(MEMBER_RELATION_NO_RELATION)));
        return targetId;
    }
}
