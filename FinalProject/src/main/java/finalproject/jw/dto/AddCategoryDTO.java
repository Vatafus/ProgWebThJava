package finalproject.jw.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCategoryDTO {

    @NotBlank(message = "Category name can't be blank or null!")
    private String categoryName;

}
