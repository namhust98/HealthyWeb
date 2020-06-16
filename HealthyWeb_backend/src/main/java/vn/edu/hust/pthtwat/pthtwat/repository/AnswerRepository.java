package vn.edu.hust.pthtwat.pthtwat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hust.pthtwat.pthtwat.entity.Answer;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByPostId(Long postId);
}
