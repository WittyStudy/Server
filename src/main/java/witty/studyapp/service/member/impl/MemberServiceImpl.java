package witty.studyapp.service.member.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import witty.studyapp.dto.member.MemberLoginDTO;
import witty.studyapp.dto.member.MemberRegisterDTO;
import witty.studyapp.dto.util.DtoUtil;
import witty.studyapp.entity.Member;
import witty.studyapp.repository.member.MemberRepository;
import witty.studyapp.service.member.MemberPolicy;
import witty.studyapp.service.member.MemberService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberPolicy memberPolicy;
    private final DtoUtil dtoUtil;

    @Override
    public Long register(MemberRegisterDTO memberRegisterDTO) {
        if(memberPolicy.verifyRegisterDTO(memberRegisterDTO) && !isAlreadyExistIdent(memberRegisterDTO.getIdent())){
            try {
                return memberRepository.save(dtoUtil.getByDTO(memberRegisterDTO)).getId();
            }catch(Exception e){    // Exception 처리 필요.
                return 0L;
            }
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

    @Override
    public Long updateMemberName(Long memberId, String name) {
        return memberRepository.findById(memberId).map(member -> {
            if(memberPolicy.isValidName(name)) {
                memberRepository.updateName(name);
                return member.getId();
            }else{
                log.debug("memberId:'{}' is failed to update name:'{}'",memberId,name);
                return 0L;
            }
        }).orElse(0L);
    }

    @Override
    public Long updateMemberPassword(Long memberId, String password) {
        return memberRepository.findById(memberId).map(member -> {
            if(memberPolicy.isValidPassword(password)) {
                memberRepository.updatePassword(password);
                return member.getId();
            }else{
                log.debug("memberId:'{}' is failed to update password:'{}'",memberId,password);
                return 0L;
            }
        }).orElse(0L);
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Long deleteMember(long memberId) {
        try{
            memberRepository.deleteById(memberId);
            return memberId;
        }catch(Exception e) {
            return 0L;
        }
    }

    private boolean verifyMemberLogin(Long memberId, MemberLoginDTO memberLoginDTO){
        return memberRepository.findByIdent(memberLoginDTO.getIdent())
                .map(member -> member.getPassword().equals(memberLoginDTO.getPassword()) && member.getId().equals(memberId))
                .orElse(false);
    }

    private boolean isAlreadyExistIdent(String ident){
        return memberRepository.findByIdent(ident).isPresent();
    }
}
