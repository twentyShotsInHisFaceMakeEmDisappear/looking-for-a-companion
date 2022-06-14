package by.grsu.lookingforacompanion.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@JsonAutoDetect
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TruncatedSubCategoryDto {

    private Long id;

    private String title;

    private String imageUrl;

    private String shortDescription;

}
