package pl.com.mtd.adviceservice.service;

import org.springframework.stereotype.Service;
import pl.com.mtd.adviceservice.converter.CategoryConverter;
import pl.com.mtd.adviceservice.dto.CategoryDto;
import pl.com.mtd.adviceservice.dto.QuestionDto;
import pl.com.mtd.adviceservice.model.Category;
import pl.com.mtd.adviceservice.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    private CategoryConverter categoryConverter;

    public CategoryService(CategoryRepository categoryRepository, CategoryConverter categoryConverter) {
        this.categoryRepository = categoryRepository;
        this.categoryConverter = categoryConverter;
    }

    public Category addNewCategory(QuestionDto questionDto) {
        Category category = new Category();
        if (questionDto.getCategoryName() != null) {
            category.setName(questionDto.getCategoryName());
            categoryRepository.save(category);
        }
        return category;
    }

    public List<CategoryDto> getAllCategories() {
        return categoryConverter.convertCategoryListToCategoryDtoList(categoryRepository.findAll());
    }


}
