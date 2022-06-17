package by.grsu.lookingforacompanion.service;

import by.grsu.lookingforacompanion.dto.CategoryAttributesDto;
import by.grsu.lookingforacompanion.dto.DefaultCategoryDto;
import by.grsu.lookingforacompanion.dto.TruncatedCategoryDto;

import java.util.List;
import java.util.Set;

public interface CategoryServiceInterface {

    Set<TruncatedCategoryDto> getAllCategories();

    List<DefaultCategoryDto> getCategoriesByAttributes(CategoryAttributesDto categoryAttributesDto);

}
