package finalproject.jw.repo;

import finalproject.jw.domain.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CartProductREPO extends JpaRepository<CartProduct,Long> {

    public List<CartProduct> findCartProductByCartUserId(Long id);

    public CartProduct findCartProductByCart_IdAndAndProduct_Id(Long cartId, Long productId);
}
