package finalproject.jw.domain;

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
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @EqualsAndHashCode.Exclude
    @OneToOne
    private User user;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "cart")
    private Set<CartProduct> cartProducts;

    public Cart(User user){
        this.user = user;
    }
}
