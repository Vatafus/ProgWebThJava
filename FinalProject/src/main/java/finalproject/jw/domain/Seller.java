package finalproject.jw.domain;

import finalproject.jw.dto.SellerRegistrationDTO;
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
@Table(name = "seller")
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "legal_name")
    private String legalName;

    @Column(name = "business")
    private String businessName;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "seller")
    private Set<BankACC> bankAccounts;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "seller")
    private Set<Product> products;

    public Seller(SellerRegistrationDTO seller, User user){
        this.legalName = seller.getLegalName();
        this.businessName = seller.getBusinessName();
        this.user = user;
    }
}
