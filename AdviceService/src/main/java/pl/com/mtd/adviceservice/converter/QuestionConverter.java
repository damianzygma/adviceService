package pl.com.mtd.adviceservice.converter;

import org.springframework.stereotype.Component;
import pl.com.mtd.adviceservice.dto.QuestionDto;
import pl.com.mtd.adviceservice.model.Question;


@Component
public class QuestionConverter {

    public Question convertQuestionAddDtoToEntity(QuestionDto addQuestionDto) {
        Question question = new Question();
        question.setQuestionDate(addQuestionDto.getQuestionDate());
        question.setQuestionDetails(addQuestionDto.getQuestionDetails());
        question.getUser().setId(addQuestionDto.getUserId());
        return question;
    }

    public QuestionDto convertQuestionEntityToAddQuestionDto(Question question){
        QuestionDto dto = new QuestionDto();
        dto.setQuestionDate(question.getQuestionDate());
        dto.setQuestionDetails(question.getQuestionDetails());
        dto.setCategoryId(question.getCategory().getId());
        dto.setCategoryName(question.getCategory().getName());
        dto.setUserId(question.getUser().getId());
        dto.setSubcategoryId(question.getSubcategory().getId());
        dto.setSubcategoryName(question.getSubcategory().getName());
        return dto;
    }
}
