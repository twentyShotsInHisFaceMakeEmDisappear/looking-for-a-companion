package by.grsu.lookingforacompanion.service;

import by.grsu.lookingforacompanion.dto.DefaultSubCategoryDto;
import by.grsu.lookingforacompanion.dto.SubCategoryAttributesDto;
import by.grsu.lookingforacompanion.dto.TruncatedSubCategoryDto;

import java.util.List;

public interface SubCategoryServiceInterface {

    List<TruncatedSubCategoryDto> getMostPublishedSubCategories(Integer count);

    List<DefaultSubCategoryDto> getTopSubCategoriesByCategoryOwner(Long categoryOwnerId);

    DefaultSubCategoryDto createAtSubCategory(DefaultSubCategoryDto defaultSubCategoryDto);

    List<DefaultSubCategoryDto> getSubCategoriesByAttributes(SubCategoryAttributesDto subCategoryAttributesDto);

}
