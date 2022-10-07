package pl.com.mtd.adviceservice.service;

import org.springframework.stereotype.Service;
import pl.com.mtd.adviceservice.converter.QuestionConverter;
import pl.com.mtd.adviceservice.dto.QuestionDto;
import pl.com.mtd.adviceservice.model.Question;
import pl.com.mtd.adviceservice.repository.QuestionRepository;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionConverter questionConverter;

    public QuestionService(QuestionRepository questionRepository, QuestionConverter questionConverter) {
        this.questionRepository = questionRepository;
        this.questionConverter = questionConverter;
    }

    public void addQuestion(QuestionDto questionDto){
        Question question = questionConverter.convertQuestionDtoToEntity(questionDto);
        questionRepository.save(question);
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
