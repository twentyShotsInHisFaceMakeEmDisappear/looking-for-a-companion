package by.grsu.lookingforacompanion.controller;

import by.grsu.lookingforacompanion.dto.TruncatedCategoryDto;
import by.grsu.lookingforacompanion.dto.TruncatedSubCategoryDto;
import by.grsu.lookingforacompanion.entity.Category;
import by.grsu.lookingforacompanion.entity.SubCategory;
import by.grsu.lookingforacompanion.repository.CategoryRepository;
import by.grsu.lookingforacompanion.repository.SubCategoryRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/category")
public class CategoryController {

    private final ModelMapper mapper;
    private final SubCategoryRepository categoryRepository;

    @GetMapping("/gt")
    public List<TruncatedSubCategoryDto> getTopCategories(@RequestParam("c") Integer count) {

        return categoryRepository.getSubCategoriesByNodesCount(count).stream()
                .map((entity) -> mapper.map(entity, TruncatedSubCategoryDto.class))
                .collect(Collectors.toList());
    }

}
