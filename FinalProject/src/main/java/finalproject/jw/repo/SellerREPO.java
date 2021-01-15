package finalproject.jw.repo;


import finalproject.jw.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SellerREPO extends JpaRepository<Seller, Long> {
    public Set<Seller> findSellersByUserId(Long id);

    public Seller findSellerById(long id);

    public Seller findSellerByBusinessName(String businessName);
}
