package pl.com.mtd.adviceservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.mtd.adviceservice.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
