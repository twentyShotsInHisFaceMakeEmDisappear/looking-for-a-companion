package by.grsu.lookingforacompanion.controller.documentation;

import by.grsu.lookingforacompanion.dto.CategoryAttributesDto;
import by.grsu.lookingforacompanion.dto.DefaultCategoryDto;
import by.grsu.lookingforacompanion.dto.TruncatedCategoryDto;
import by.grsu.lookingforacompanion.exception.subcategory.NoSuchDataByRequestException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Set;

@Tag(name = "Category Controller",
        description = "Get everything about categories")
public interface CategoryControllerDocumentation {

    @Operation(summary = "All categories",
            description = "Get all truncated categories")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful pull all categories"
            )
    })
    Set<TruncatedCategoryDto> getAllCategories();

    @Operation(summary = "Get categories by attributes",
            description = "User should provide at least one searching attributes")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful pull categories by attributes"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request. No one of the attributes was found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = NoSuchDataByRequestException.class)
                            )
                    }
            )
    })
    List<DefaultCategoryDto> getCategoriesByAttributes(CategoryAttributesDto categoryAttributesDto);

}
