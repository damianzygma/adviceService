package pl.com.mtd.adviceservice.service;

import org.springframework.stereotype.Service;
import pl.com.mtd.adviceservice.converter.QuestionConverter;
import pl.com.mtd.adviceservice.converter.UserProfileConverter;
import pl.com.mtd.adviceservice.dto.QuestionDto;
import pl.com.mtd.adviceservice.model.Category;
import pl.com.mtd.adviceservice.model.Question;
import pl.com.mtd.adviceservice.repository.CategoryRepository;
import pl.com.mtd.adviceservice.repository.QuestionRepository;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    private final CategoryRepository categoryRepository;
    private final UserProfileConverter userProfileConverter;
    private final QuestionConverter questionConverter;

    public QuestionService(QuestionRepository questionRepository, CategoryRepository categoryRepository, UserProfileConverter userProfileConverter,
                           QuestionConverter questionConverter) {
        this.questionRepository = questionRepository;
        this.categoryRepository = categoryRepository;
        this.userProfileConverter = userProfileConverter;
        this.questionConverter = questionConverter;
    }

    public void addQuestion(QuestionDto questionDto){
        Question question = questionConverter.convertQuestionDtoToEntity(questionDto);
        if(questionDto.getCategoryId() == null){
            if(questionDto.getCategoryName() != null){
                Category category = new Category();
                category.setName(questionDto.getCategoryName());
                category.getQuestions().add(question);
                categoryRepository.save(category);
            }
        }
        questionRepository.save(question);
        System.out.println("adding a new question: " + question.getId());
    }

    public List<Question> getQuestions(){
        return questionRepository.findAll();
    }

    public Question getQuestion(Long id){
        return questionRepository.findById(id).orElse(null);
    }

    public void editQuestion(Question question){
        questionRepository.save(question);
    }

    public void deleteQuestion(Long id){
        questionRepository.deleteById(id);
    }

}
