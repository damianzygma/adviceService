package pl.com.mtd.adviceservice.converter;

import pl.com.mtd.adviceservice.dto.CategoryDto;
import pl.com.mtd.adviceservice.model.Category;

public class CategoryConverter {

    public Category convertCategoryDtoToEntity(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getCategoryName());
        category.getQuestions().forEach(question -> question.setId(categoryDto.getQuestionId()));
        category.getQuestions().forEach(question -> question.getUser().setId(categoryDto.getUserId()));
        category.getQuestions().forEach(question -> question.getUser().setNickname(categoryDto.getUserNickname()));
        category.getQuestions().forEach(question -> question.setQuestionDate(categoryDto.getQuestionDate()));
        category.getQuestions().forEach(question -> question.setQuestionSubject(categoryDto.getQuestionSubject()));
        category.getQuestions().forEach(question -> question.setQuestionDetails(categoryDto.getQuestionDetails()));
        return category;
    }

    public CategoryDto convertEntityToCategoryDto(Category category){
        CategoryDto dto = new CategoryDto();
        dto.setCategoryId(category.getId());
        dto.setCategoryName(category.getName());
        return dto;
    }


}
