package pl.com.mtd.adviceservice.converter;

import org.springframework.stereotype.Component;
import pl.com.mtd.adviceservice.dto.QuestionDto;
import pl.com.mtd.adviceservice.model.Question;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;


@Component
public class QuestionConverter {

    public Question convertQuestionDtoToEntity(QuestionDto questionDto) {
        Question question = new Question();
        question.setQuestionDate(Date.from(Instant.from(LocalDate.now())));
        question.setQuestionSubject(questionDto.getQuestionSubject());
        question.setQuestionDetails(questionDto.getQuestionDetails());
        question.getCategory().setName(questionDto.getCategoryName());
        return question;
    }

    public QuestionDto convertQuestionEntityToQuestionDto(Question question){
        QuestionDto dto = new QuestionDto();
        dto.setQuestionDate(question.getQuestionDate());
        dto.setQuestionSubject(question.getQuestionSubject());
        dto.setQuestionDetails(question.getQuestionDetails());
        dto.setCategoryName(question.getCategory().getName());
        dto.setUserNickname(question.getUser().getNickname());
        return dto;
    }

}
