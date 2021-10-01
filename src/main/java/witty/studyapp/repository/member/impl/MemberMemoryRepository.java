package witty.studyapp.repository.member.impl;

import org.springframework.stereotype.Repository;
import witty.studyapp.entity.Member;
import witty.studyapp.repository.member.MemberRepository;

import java.util.*;

@Repository
public class MemberMemoryRepository implements MemberRepository {

    private final Map<Long, Member> members;
    private Long count;

    public MemberMemoryRepository() {
        members = new HashMap<>();
        count = 0L;
    }

    @Override
    public Long save(Member member) {
        members.put(++count,member);
        member.setId(count);
        return count;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(members.get(id));
    }

    @Override
    public Optional<Member> findByIdent(String ident) {
        for (Long id : members.keySet()) {
            if(members.get(id).getIdent().equals(ident))
                return Optional.of(members.get(id));
        }
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(members.values());
    }

    @Override
    public boolean deleteById(Long id) {
        if(members.get(id) != null){
            members.remove(id);
            return true;
        }else{
            return false;
        }
    }
}
