package by.grsu.lookingforacompanion.util.filter.category;

import by.grsu.lookingforacompanion.dto.DefaultCategoryDto;
import by.grsu.lookingforacompanion.dto.DefaultSubCategoryDto;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class DtoFilter {

    public Boolean isCategoryDtoReadyToSave(DefaultCategoryDto defaultCategoryDto) {
        return !Objects.isNull(defaultCategoryDto.getTitle()) && !Objects.isNull(defaultCategoryDto.getDescription()) &&
                !Objects.isNull(defaultCategoryDto.getImageUrl()) && !Objects.isNull(defaultCategoryDto.getShortDescription());
    }

    public Boolean isSubCategoryReadyToSave(DefaultSubCategoryDto defaultSubCategoryDto) {
        return !Objects.isNull(defaultSubCategoryDto.getTitle()) && !Objects.isNull(defaultSubCategoryDto.getDescription())
                && !Objects.isNull(defaultSubCategoryDto.getImageUrl()) && !Objects.isNull(defaultSubCategoryDto.getShortDescription());
    }

}
