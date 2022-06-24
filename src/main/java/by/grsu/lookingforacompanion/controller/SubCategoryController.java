package by.grsu.lookingforacompanion.controller;

import by.grsu.lookingforacompanion.dto.DefaultSubCategoryDto;
import by.grsu.lookingforacompanion.dto.SubCategoryAttributesDto;
import by.grsu.lookingforacompanion.dto.TruncatedSubCategoryDto;
import by.grsu.lookingforacompanion.service.SubCategoryServiceInterface;
import by.grsu.lookingforacompanion.util.logger.ProcessTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/sub-category")
public class SubCategoryController {

    private final SubCategoryServiceInterface subCategoryService;

    @ProcessTrace
    @GetMapping("/gt")
    public List<TruncatedSubCategoryDto> getTopCategories(@RequestParam("c") Integer count) {

        return subCategoryService.getMostPublishedSubCategories(count);
    }

    @ProcessTrace
    @GetMapping("/at")
    public List<DefaultSubCategoryDto> getSubCategoriesByAttributes
            (@RequestBody SubCategoryAttributesDto attributesDto) {

        return subCategoryService.getSubCategoriesByAttributes(attributesDto);
    }

    @ProcessTrace
    @GetMapping("/bo/{categoryOwnerId}")
    private List<DefaultSubCategoryDto> getTopSubCategoriesByOwnerId(@PathVariable Long categoryOwnerId) {

        return subCategoryService.getTopSubCategoriesByCategoryOwner(categoryOwnerId);
    }

}
