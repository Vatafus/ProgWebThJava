package finalproject.jw.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindeProductDTO {
    @Positive
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @Positive
    private Double price;
    @NotBlank
    private String sellerName;
}
