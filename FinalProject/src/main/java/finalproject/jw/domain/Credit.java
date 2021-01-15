package finalproject.jw.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "credit_card")
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "credit_card_number")
    private String creditCardNumber;

    @Column(name = "expiring_date")
    private LocalDate expiringDate;

    @Column(name = "card_holder_name")
    private String cardHolderName;

    @Column(name = "balance")
    private double balance;

    @EqualsAndHashCode.Exclude
    @ManyToMany
    private Set<User> users;

}
