package finalproject.jw.domain;

import finalproject.jw.dto.BankACCRegistrationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bank_account")
public class BankACC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "iban")
    private String iban;

    @Column(name = "balance")
    private double balance;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    public BankACC(BankACCRegistrationDTO bankAccountRegistrationDTO){
        this.iban = bankAccountRegistrationDTO.getIban();
        this.balance = bankAccountRegistrationDTO.getBalance();
    }
}
