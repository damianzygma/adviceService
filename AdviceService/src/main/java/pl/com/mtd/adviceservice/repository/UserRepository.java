package pl.com.mtd.adviceservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.mtd.adviceservice.user.User;


public interface UserRepository extends JpaRepository<User,Long> {
}
