package finalproject.jw.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSellDTO {
    @NotBlank(message = "Product name can't be blank!")
    private String name;

    @NotBlank(message = "Product description can't be blank!")
    private String description;

    @NotNull(message = "Product price can't be null!")
    @PositiveOrZero(message = "Product price can't be negative number!")
    private Double price;

    @NotNull(message = "Product quantity can't be null!")
    private Integer quantity;

    @NotNull(message = "Product category id can't be null!")
    private Long categoryId;

    @NotNull(message = "Product seller id can't be null!")
    private Long sellerId;
}
