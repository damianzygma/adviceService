package pl.com.mtd.adviceservice.converter.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.com.mtd.adviceservice.dto.CategoryDto;
import pl.com.mtd.adviceservice.dto.QuestionDto;
import pl.com.mtd.adviceservice.service.CategoryService;

import java.util.List;

@Component
public class QuestionValidator implements Validator {

    private CategoryService categoryService;

    public QuestionValidator(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return QuestionDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        QuestionDto questionDto = (QuestionDto) target;
        List<CategoryDto> allCategories = categoryService.getAllCategories();
        if(questionDto.getCategoryId() == null){
            if(questionDto.getCategoryName() == null){
                errors.rejectValue("categoryName", "category name can't be empty");
            }
        }
        for (int i = 0; i < allCategories.size(); i++) {
            if(allCategories.get(i).getCategoryName().equals(questionDto.getCategoryName())){
                errors.rejectValue("categoryName", "this category already exists");
            }
        }
        if(questionDto.getCategoryName().length() < 2 && questionDto.getCategoryName().length() > 20){
            errors.rejectValue("categoryName", "category name should have size between 2 and 20");
        }
    }
}
