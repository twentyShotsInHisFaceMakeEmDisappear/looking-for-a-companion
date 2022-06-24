package by.grsu.lookingforacompanion.service.impl;

import by.grsu.lookingforacompanion.dto.DefaultSubCategoryDto;
import by.grsu.lookingforacompanion.dto.InformationResponseDto;
import by.grsu.lookingforacompanion.dto.SubCategoryAttributesDto;
import by.grsu.lookingforacompanion.dto.TruncatedSubCategoryDto;
import by.grsu.lookingforacompanion.entity.SubCategory;
import by.grsu.lookingforacompanion.exception.category.InvalidEntityDataException;
import by.grsu.lookingforacompanion.exception.subcategory.NoSuchDataByRequestException;
import by.grsu.lookingforacompanion.repository.SubCategoryRepository;
import by.grsu.lookingforacompanion.service.SubCategoryServiceInterface;
import by.grsu.lookingforacompanion.util.filter.category.DtoFilter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubCategoryService implements SubCategoryServiceInterface {

    private final ModelMapper mapper;

    private final SubCategoryRepository subCategoryRepository;

    @Override
    public List<TruncatedSubCategoryDto> getMostPublishedSubCategories(Integer count) {
        List<SubCategory> mostPublishedSubCategories = subCategoryRepository.getSubCategoriesByNodesCount(count);

        return mostPublishedSubCategories.stream()
                .map((entity) -> mapper.map(entity, TruncatedSubCategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<DefaultSubCategoryDto> getTopSubCategoriesByCategoryOwner(Long categoryOwnerId) {
        List<SubCategory> mostPopularSubCategories = subCategoryRepository
                .getTopMostPublishedSubCategoriesByCategoryOwnerId(categoryOwnerId);

        return mostPopularSubCategories.stream()
                .map((entity) -> mapper.map(entity, DefaultSubCategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<DefaultSubCategoryDto> getSubCategoriesByAttributes(SubCategoryAttributesDto subCategoryAttributesDto) {
        List<SubCategory> subCategoriesFiltered;

        if (!Objects.isNull(subCategoryAttributesDto.getTitleMention()))
            subCategoriesFiltered = subCategoryRepository
                    .getSubCategoriesByTitleContainingIgnoreCase(subCategoryAttributesDto.getTitleMention());
        else if (!Objects.isNull(subCategoryAttributesDto.getDescriptionMention()))
            subCategoriesFiltered = subCategoryRepository
                    .getSubCategoriesByDescriptionContainingIgnoreCase(subCategoryAttributesDto.getDescriptionMention());
        else if (!Objects.isNull(subCategoryAttributesDto.getShortDescriptionMention()))
            subCategoriesFiltered = subCategoryRepository
                    .getSubCategoriesByShortDescriptionContainingIgnoreCase(subCategoryAttributesDto
                            .getShortDescriptionMention());
        else
            throw new NoSuchDataByRequestException("No such data by your request.");

        return subCategoriesFiltered.stream()
                .map((entity) -> mapper.map(entity, DefaultSubCategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public DefaultSubCategoryDto createAtSubCategory(DefaultSubCategoryDto defaultSubCategoryDto) {
        if (!DtoFilter.isSubCategoryReadyToSave(defaultSubCategoryDto))
            throw new InvalidEntityDataException("Please input correct data.");

        SubCategory currentNewSubCategory = subCategoryRepository.save(mapper.map(defaultSubCategoryDto, SubCategory.class));

        return mapper.map(currentNewSubCategory, DefaultSubCategoryDto.class);
    }

}
