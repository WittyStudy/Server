package witty.studyapp.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import witty.studyapp.dto.NoticeDTO;
import witty.studyapp.entity.Notice;
import witty.studyapp.repository.BoardRepository;

import java.util.*;

@Slf4j
@Repository
public class BoardMemoryRepository implements BoardRepository {

    Map<Long, Notice> noticeMap;
    Long count;

    public BoardMemoryRepository() {
        noticeMap = new HashMap<>();
        count = 0L;
    }

    @Override
    public Optional<Notice> findById(Long id) {
        return Optional.ofNullable(noticeMap.get(id));
    }

    @Override
    public List<Notice> findAll() {
        return new ArrayList<>(noticeMap.values());
    }

    @Override
    public void deleteById(Long id) {
        noticeMap.remove(id);
        log.debug("Deleted ID: {id}",id);
    }

    @Override
    public void updateById(Long id, NoticeDTO noticeDTO) {
        Notice notice = Notice.getByDTO(noticeDTO);
        notice.setId(id);
        noticeMap.put(id,notice);
        log.debug("Updated ID: {id}, Title: {title}",id,noticeDTO.getTitle());
    }

    @Override
    public Long save(NoticeDTO noticeDTO) {
        Notice notice = Notice.getByDTO(noticeDTO);
        notice.setId(++count);
        noticeMap.put(count,notice);
        log.debug("Saved Id: {id}, Title: {title}",count,notice.getTitle());
        return notice.getId();
    }
}
