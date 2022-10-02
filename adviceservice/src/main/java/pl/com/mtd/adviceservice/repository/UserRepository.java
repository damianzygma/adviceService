package pl.com.mtd.adviceservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.mtd.adviceservice.model.User;
@Repository

public interface UserRepository extends JpaRepository<User,Long> {

    User findUserByNickname(String nickname);
}
