package finalproject.jw.domain;

import finalproject.jw.dto.ProductSellDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "product")
    private Set<CartProduct> productInCart;

    public Product(ProductSellDTO product, Category category, Seller seller){

        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.category = category;
        this.seller = seller;
    }

}
