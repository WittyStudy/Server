package witty.studyapp.repository.comment.impl;

import org.springframework.stereotype.Repository;
import witty.studyapp.entity.Comment;
import witty.studyapp.repository.comment.CommentRepository;

import java.util.*;

@Repository
public class CommentMemoryRepository implements CommentRepository {

    private final Map<Long, Comment> commentMap;
    private Long count;

    public CommentMemoryRepository() {
        commentMap = new HashMap<>();
        count = 0L;
    }

    @Override
    public Long save(Comment comment) {
        comment.setId(++count);
        commentMap.put(count,comment);
        return count;
    }

    @Override
    public List<Comment> findByBoardId(Long boardId) {
        ArrayList<Comment> list = new ArrayList<>();
        for (Comment value : commentMap.values()) {
            if(value.getBoardId().equals(boardId))
                list.add(value);
        }

        return list;
    }

    @Override
    public List<Comment> findByMemberId(Long memberId) {
        ArrayList<Comment> list = new ArrayList<>();
        for (Comment value : commentMap.values()) {
            if(value.getMemberId().equals(memberId))
                list.add(value);
        }
        return list;
    }

    @Override
    public Optional<Comment> findById(Long commentId) {
        return Optional.ofNullable(commentMap.get(commentId));
    }

    @Override
    public Long deleteById(Long commentId) {
        if(commentMap.get(commentId) != null){
            commentMap.remove(commentId);
            return commentId;
        }else{
            return 0L;
        }
    }
}
