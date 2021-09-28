package witty.studyapp.service.member.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import witty.studyapp.dto.member.MemberLoginDTO;
import witty.studyapp.dto.member.MemberRegisterDTO;
import witty.studyapp.entity.Member;
import witty.studyapp.repository.member.MemberRepository;
import witty.studyapp.service.member.MemberPolicy;
import witty.studyapp.service.member.MemberService;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberPolicy memberPolicy;

    @Override
    public Long register(MemberRegisterDTO memberRegisterDTO) {
        if(memberPolicy.verifyRegister(memberRegisterDTO)){
            return memberRepository.save(Member.getByRegisterDTO(memberRegisterDTO));
        }else {
            return 0L;
        }
    }

    @Override
    public Object login(Long memberId, MemberLoginDTO memberLoginDTO) {
        if(verifyMemberLogin(memberId, memberLoginDTO)){
            return "TOKEN";
        }else {
            return "FALSE";
        }
    }

    private boolean verifyMemberLogin(Long memberId, MemberLoginDTO memberLoginDTO){
        return memberRepository.findByIdent(memberLoginDTO.getIdent())
                .map(member -> member.getPassword().equals(memberLoginDTO.getPassword()) && member.getId() == memberId)
                .orElse(false);
    }

    @Override
    public Long updateMemberName(Long memberId, String name) {
        return memberRepository.findById(memberId).map(member -> {
            member.setName(name);
            return member.getId();
        }).orElse(0L);
    }

    @Override
    public Long updateMemberPassword(Long memberId, String password) {
        return memberRepository.findById(memberId).map(member -> {
            member.setPassword(password);
            return member.getId();
        }).orElse(0L);
    }
}
