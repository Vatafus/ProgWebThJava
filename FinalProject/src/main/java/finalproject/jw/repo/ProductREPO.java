package finalproject.jw.repo;

import finalproject.jw.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductREPO extends JpaRepository<Product, Long> {

    public Product findProductById(Long id);

    public List<Product> findAll();

    public Product findProductBySeller_Id(Long sellerId);

    public Long deleteByIdAndSeller_Id(Long productId, Long sellerId);

}
