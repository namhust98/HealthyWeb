package vn.edu.hust.pthtwat.pthtwat.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hust.pthtwat.pthtwat.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    Page<User> findByDeleted(Boolean deleted, Pageable pageable);
//    List<User> findByDeletedFalse();
    Page<User> findAll(Pageable pageable);

    User findByEmail(String email);

//    User findByEmailAndEnabelTrue(String email);

    User findByEmailAndTypeAndEnabelTrue(String type, String email);

    User findByEmailAndEnabelTrue(String email);

    User findByAccessToken(String accessToken);

    User getByAccessTokenAndEnabelTrue(String accessToken);

    User findById(Long userId);
}
