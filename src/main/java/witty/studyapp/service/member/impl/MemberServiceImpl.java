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
        if (memberPolicy.verifyMember(member)) {
            if (isAlreadyExistEmail(member.getEmail())) {
                throw new RegisterArgumentException(REGISTER_ALREADY_EXIST);
            }
            member.setPassword(passwordEncoder.encode(member.getPassword()));
            return memberRepository.save(member).getId();
        } else {
            throw new RegisterArgumentException();
        }
    }

    @Override
    public Optional<Member> login(Member member) {
        checkPresentEmail(member.getEmail());
        return Optional.of(verifyMemberLogin(member));
    }

    @Override
    @Transactional
    public Long updateMemberName(Long memberId, String name) {
        if (memberPolicy.isValidName(name)) {
            memberRepository.findById(memberId)
                    .orElseThrow(NotFoundUserException::new)
                    .setName(name);
        } else {
            throw new IllegalArgumentException();
        }
        return memberId;
    }

    @Override
    @Transactional
    public Long updateMemberPassword(Long memberId, String password) {
        if(memberPolicy.isValidPassword(password)){
            memberRepository.findById(memberId)
                    .orElseThrow(NotFoundUserException::new)
                    .setPassword(password);
        }else{
            throw new IllegalArgumentException();
        }
        return memberId;
    }

    @Override
    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
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
        return memberRepository.findById(memberId)
                .orElseThrow(NotFoundUserException::new)
                .getId();
    }

    private void checkPresentEmail(String email) {
        memberRepository.findByEmail(email)
                .orElseThrow(NotFoundUserException::new);
    }

    private Member verifyMemberLogin(Member member) {
        return memberRepository.findByEmail(member.getEmail())
                .filter(m -> passwordEncoder.matches(member.getPassword(), m.getPassword()))
                .orElseThrow(PasswordWrongException::new);
    }

    private boolean isAlreadyExistEmail(String email) {
        return memberRepository.findByEmail(email).isPresent();
    }
}
