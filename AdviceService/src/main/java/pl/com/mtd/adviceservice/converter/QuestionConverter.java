package pl.com.mtd.adviceservice.converter;

import org.springframework.stereotype.Component;
import pl.com.mtd.adviceservice.dto.QuestionDto;
import pl.com.mtd.adviceservice.model.Question;
import pl.com.mtd.adviceservice.repository.CategoryRepository;
import pl.com.mtd.adviceservice.service.CategoryService;
import pl.com.mtd.adviceservice.service.UserService;

import java.time.LocalDate;

@Component
public class QuestionConverter {

    private UserService userService;
    private CategoryService categoryService;
    private CategoryRepository categoryRepository;


    public QuestionConverter(UserService userService, CategoryService categoryService,
                             CategoryRepository categoryRepository) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    public Question convertQuestionDtoToEntity(QuestionDto questionDto) {
        Question question = new Question();
        question.setQuestionDate(LocalDate.now());
        question.setQuestionSubject(questionDto.getQuestionSubject());
        question.setQuestionDetails(questionDto.getQuestionDetails());
        if (questionDto.getCategoryId() == null) {
            question.setCategory(categoryService.addNewCategory(questionDto));
        } else {
            categoryRepository.findById(questionDto.getCategoryId()).ifPresent(question::setCategory);
        }
        return question;
    }

}
