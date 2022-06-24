package by.grsu.lookingforacompanion.service;

import by.grsu.lookingforacompanion.dto.CategoryAttributesDto;
import by.grsu.lookingforacompanion.dto.DefaultCategoryDto;
import by.grsu.lookingforacompanion.dto.InformationResponseDto;
import by.grsu.lookingforacompanion.dto.TruncatedCategoryDto;

import java.util.List;
import java.util.Set;

public interface CategoryServiceInterface {

    Set<TruncatedCategoryDto> getAllCategories();

    DefaultCategoryDto createAnCategory(DefaultCategoryDto defaultCategoryDto);

    List<DefaultCategoryDto> getCategoriesByAttributes(CategoryAttributesDto categoryAttributesDto);

    InformationResponseDto editAnCategoryInformation(Long categoryId, DefaultCategoryDto categoryDto);

}
