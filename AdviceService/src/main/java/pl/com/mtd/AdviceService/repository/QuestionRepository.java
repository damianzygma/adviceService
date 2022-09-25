package pl.com.mtd.AdviceService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.mtd.AdviceService.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
