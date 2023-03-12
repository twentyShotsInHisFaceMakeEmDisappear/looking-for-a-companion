package by.grsu.lookingforacompanion.controller;

import by.grsu.lookingforacompanion.controller.documentation.CategoryControllerDocumentation;
import by.grsu.lookingforacompanion.dto.CategoryAttributesDto;
import by.grsu.lookingforacompanion.dto.DefaultCategoryDto;
import by.grsu.lookingforacompanion.dto.TruncatedCategoryDto;
import by.grsu.lookingforacompanion.service.CategoryServiceInterface;
import by.grsu.lookingforacompanion.util.logger.ProcessTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController implements CategoryControllerDocumentation {

    private final CategoryServiceInterface categoryService;

    @Override
    @GetMapping
    @ProcessTrace
    public Set<TruncatedCategoryDto> getAllCategories() {

        return categoryService.getAllCategories();
    }

    @Override
    @ProcessTrace
    @GetMapping("/bo")
    public List<DefaultCategoryDto> getCategoriesByAttributes(CategoryAttributesDto categoryAttributesDto) {

        return categoryService.getCategoriesByAttributes(categoryAttributesDto);
    }

}
