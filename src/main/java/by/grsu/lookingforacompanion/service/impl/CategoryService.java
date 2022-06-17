package by.grsu.lookingforacompanion.service.impl;

import by.grsu.lookingforacompanion.dto.*;
import by.grsu.lookingforacompanion.entity.Category;
import by.grsu.lookingforacompanion.entity.SubCategory;
import by.grsu.lookingforacompanion.exception.subcategory.NoSuchDataByRequestException;
import by.grsu.lookingforacompanion.repository.CategoryRepository;
import by.grsu.lookingforacompanion.service.CategoryServiceInterface;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService implements CategoryServiceInterface {

    private final ModelMapper mapper;

    private final CategoryRepository categoryRepository;

    @Override
    public Set<TruncatedCategoryDto> getAllCategories() {
        Set<Category> categories = categoryRepository.getAll();

        return categories.stream()
                .map((entity) -> mapper.map(entity, TruncatedCategoryDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public List<DefaultCategoryDto> getCategoriesByAttributes(CategoryAttributesDto categoryAttributesDto) {
        List<Category> categoriesFiltered;

        if (!Objects.isNull(categoryAttributesDto.getTitleMention()))
            categoriesFiltered = categoryRepository
                    .getCategoriesByTitleContainingIgnoreCase(categoryAttributesDto.getTitleMention());
        else if (!Objects.isNull(categoryAttributesDto.getDescriptionMention()))
            categoriesFiltered = categoryRepository
                    .getCategoriesByDescriptionContainingIgnoreCase(categoryAttributesDto.getDescriptionMention());
        else if (!Objects.isNull(categoryAttributesDto.getShortDescriptionMention()))
            categoriesFiltered = categoryRepository
                    .getCategoriesByShortDescriptionContainingIgnoreCase(categoryAttributesDto
                            .getShortDescriptionMention());
        else
            throw new NoSuchDataByRequestException("No such data by your request.");

        return categoriesFiltered.stream()
                .map((entity) -> mapper.map(entity, DefaultCategoryDto.class))
                .collect(Collectors.toList());

    }

}
