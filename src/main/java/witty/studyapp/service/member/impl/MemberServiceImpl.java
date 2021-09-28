package witty.studyapp.service.member.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import witty.studyapp.dto.member.MemberLoginDTO;
import witty.studyapp.dto.member.MemberRegisterDTO;
import witty.studyapp.entity.Member;
import witty.studyapp.repository.member.MemberRepository;
import witty.studyapp.service.member.MemberPolicy;
import witty.studyapp.service.member.MemberService;

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
    public Object login(MemberLoginDTO memberLoginDTO) {
        if(verifyMemberLogin(memberLoginDTO)){
            return "TOKEN";
        }else {
            return "FALSE";
        }
    }

    private boolean verifyMemberLogin(MemberLoginDTO memberLoginDTO){
        return memberRepository.findByIdent(memberLoginDTO.getIdent())
                .map(member -> member.getPassword().equals(memberLoginDTO.getPassword()))
                .orElse(false);
    }
}
