package finalproject.jw.dto;

import finalproject.jw.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String sellername;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.sellername = product.getSeller().getLegalName();
    }
}
