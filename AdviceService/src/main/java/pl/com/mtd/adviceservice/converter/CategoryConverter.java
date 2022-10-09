package pl.com.mtd.adviceservice.converter;

import org.springframework.stereotype.Component;
import pl.com.mtd.adviceservice.dto.CategoryDto;
import pl.com.mtd.adviceservice.model.Category;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryConverter {

    public Category convertCategoryDtoToEntity(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getCategoryName());
        return category;
    }

    public CategoryDto convertEntityToCategoryDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setCategoryId(category.getId());
        dto.setCategoryName(category.getName());
        return dto;
    }

    public List<CategoryDto> convertCategoryListToCategoryDtoList(List<Category> categories) {
        List<CategoryDto> categoriesDto = categories
                .stream()
                .map(this::convertEntityToCategoryDto)
                .collect(Collectors.toList());
        return categoriesDto;
    }


}
