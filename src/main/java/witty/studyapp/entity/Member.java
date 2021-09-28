package witty.studyapp.entity;

import lombok.Data;
import witty.studyapp.dto.member.MemberRegisterDTO;

@Data
public class Member {
    private Long id;
    private String ident;
    private String name;
    private String password;
    private String email;

    public static Member getByRegisterDTO(MemberRegisterDTO memberRegisterDTO){
        Member member = new Member();
        member.setIdent(memberRegisterDTO.getIdent());
        member.setName(memberRegisterDTO.getName());
        member.setPassword(memberRegisterDTO.getPassword());
        member.setEmail(memberRegisterDTO.getEmail());
        return member;
    }
}
