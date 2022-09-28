package pl.com.mtd.adviceservice.service;

import org.springframework.stereotype.Service;
import pl.com.mtd.adviceservice.model.Question;
import pl.com.mtd.adviceservice.repository.QuestionRepository;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public void addQuestion(Question question){
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
