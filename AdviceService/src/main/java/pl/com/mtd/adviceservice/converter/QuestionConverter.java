package pl.com.mtd.adviceservice.converter;

import org.springframework.stereotype.Component;
import pl.com.mtd.adviceservice.dto.QuestionDto;
import pl.com.mtd.adviceservice.model.Question;
import pl.com.mtd.adviceservice.model.User;
import pl.com.mtd.adviceservice.repository.CategoryRepository;
import pl.com.mtd.adviceservice.service.CategoryService;
import pl.com.mtd.adviceservice.service.UserService;

import java.time.LocalDate;

@Component
public class QuestionConverter {

    private UserService userService;
    private CategoryConverter categoryConverter;
    private CategoryService categoryService;
    private CategoryRepository categoryRepository;


    public QuestionConverter(UserService userService, CategoryConverter categoryConverter, CategoryService categoryService,
                             CategoryRepository categoryRepository) {
        this.userService = userService;
        this.categoryConverter = categoryConverter;
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    public Question convertQuestionDtoToEntity(QuestionDto questionDto) {
        Question question = new Question();
        question.setUser(userService.getUserByNickname(userService.getLoggedUserName()));
        question.setQuestionDate(LocalDate.now());
        question.setQuestionSubject(questionDto.getQuestionSubject());
        question.setQuestionDetails(questionDto.getQuestionDetails());
        if(questionDto.getCategoryId() == null) {
            question.setCategory(categoryService.addNewCategory(questionDto));
        } else {
            question.setCategory(categoryRepository.findById(questionDto.getCategoryId()).orElse(null));
        }
        return question;
    }

    public QuestionDto convertEntityToQuestionDto(){
        QuestionDto dto = new QuestionDto();
        String loggedUserName = userService.getLoggedUserName();
        User user = userService.getUserByNickname(loggedUserName);
        dto.setUserNickname(user.getNickname());
        dto.setUserEmail(user.getEmail());
        dto.setCategories(categoryConverter.convertCategoryListToCategoryDtoList(categoryService.getAllCategories()));
        return dto;
    }

}
