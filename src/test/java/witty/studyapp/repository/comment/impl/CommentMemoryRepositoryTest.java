package witty.studyapp.repository.comment.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentMemoryRepositoryTest {

    @Test
    void simpleEqualTest(){
        Long a = 3L;
        Long b = 3L;
        b--;
        b++;
        Assertions.assertThat(a.equals(b)).isEqualTo(true);
    }
}