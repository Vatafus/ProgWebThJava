package finalproject.jw.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart_product")
public class CartProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity")
    private int quantity;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
