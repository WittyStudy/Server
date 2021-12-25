package witty.studyapp.service.member.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import witty.studyapp.entity.Member;
import witty.studyapp.execption.custom.NotFoundUserException;
import witty.studyapp.execption.custom.PasswordWrongException;
import witty.studyapp.execption.custom.RegisterArgumentException;
import witty.studyapp.repository.member.MemberRepository;
import witty.studyapp.service.member.MemberPolicy;
import witty.studyapp.service.member.MemberService;

import java.util.List;
import java.util.Optional;

import static witty.studyapp.constant.exception.ExceptionConstant.REGISTER_ALREADY_EXIST;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberPolicy memberPolicy;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Long register(Member member) {
        if(memberPolicy.verifyMember(member)){
            if(isAlreadyExistEmail(member.getEmail())){
                throw new RegisterArgumentException(REGISTER_ALREADY_EXIST);
            }
            member.setPassword(passwordEncoder.encode(member.getPassword()));
            System.out.println("member.getPassword() = " + member.getPassword());
            return memberRepository.save(member).getId();
        }else {
            throw new RegisterArgumentException();
        }
    }

    @Override
    public Optional<Member> login(Member member) {
        // TODO : 비밀번호 salt + hash 필요
        checkPresentEmail(member.getEmail());
        return Optional.of(verifyMemberLogin(member));
    }

    @Override
    @Transactional
    public Long updateMemberName(Long memberId, String name) {
        if(memberPolicy.isValidName(name)){
            memberRepository.updateName(checkPresentId(memberId), name);
        }else{
            throw new IllegalArgumentException();
        }
        return memberId;
    }

    @Override
    @Transactional
    public Long updateMemberPassword(Long memberId, String password) {
        return memberRepository.findById(memberId).map(member -> {
            if(memberPolicy.isValidPassword(password)) {
                memberRepository.updatePassword(member.getId(), password);
                return member.getId();
            }else{
                log.debug("memberId:'{}' is failed to update password:'{}'",memberId,password);
                throw new IllegalArgumentException();
            }
        }).orElseThrow(NotFoundUserException::new);
    }

    @Override
    public Optional<Member> getMemberById(Long id) {
        return Optional.of(memberRepository.getById(id));
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    @Transactional
    public Long deleteMember(Long memberId) {
        memberRepository.deleteById(checkPresentId(memberId));
        return memberId;
    }

    private Long checkPresentId(Long memberId) {
        memberRepository.findById(memberId).orElseThrow(NotFoundUserException::new);
        return memberId;
    }

    private void checkPresentEmail(String email){
        memberRepository.findByEmail(email).orElseThrow(NotFoundUserException::new);
    }

    private Member verifyMemberLogin(Member member){
        return memberRepository.findByEmail(member.getEmail())
                .filter(m -> passwordEncoder.matches(member.getPassword(), m.getPassword()))
                .orElseThrow(PasswordWrongException::new);
    }

    private boolean isAlreadyExistEmail(String email){
        return memberRepository.findByEmail(email).isPresent();
    }
}
