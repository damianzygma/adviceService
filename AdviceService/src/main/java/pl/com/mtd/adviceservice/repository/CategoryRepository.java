package pl.com.mtd.adviceservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.mtd.adviceservice.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
