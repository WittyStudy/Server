package witty.studyapp.service.member.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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

    @Override
    public Long register(Member member) {
        if(memberPolicy.verifyMember(member) && !isAlreadyExistEmail(member.getEmail())){
            try {
                return memberRepository.save(member).getId();
            }catch(Exception e){    // Exception 처리 필요.
                return 0L;
            }
        }else {
            return 0L;
        }
    }

    @Override
    public Object login(Long memberId, Member member) {
        if(verifyMemberLogin(member)){
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
    public Member getMemberById(Long id) {
        return memberRepository.getById(id);
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Long deleteMember(Long memberId) {
        try{
            memberRepository.deleteById(memberId);
            return memberId;
        }catch(Exception e) {
            return 0L;
        }
    }

    private boolean verifyMemberLogin(Member member){
        return memberRepository.findById(member.getId())
                .map(m -> m.getPassword().equals(member.getPassword()))
                .orElse(false);
    }

    private boolean isAlreadyExistEmail(String email){
        return memberRepository.findByEmail(email).isPresent();
    }
}
