package finalproject.jw.repo;

import finalproject.jw.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartREPO extends JpaRepository<Cart, Long> {

    public Cart findBasketByUserId(Long id);
}
