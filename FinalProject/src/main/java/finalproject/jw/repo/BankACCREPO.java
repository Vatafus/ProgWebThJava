package finalproject.jw.repo;

import finalproject.jw.domain.BankACC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankACCREPO extends JpaRepository<BankACC,Long> {
    public BankACC findBankACCByIban(String iban);
}
