package vn.edu.hust.pthtwat.pthtwat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hust.pthtwat.pthtwat.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
