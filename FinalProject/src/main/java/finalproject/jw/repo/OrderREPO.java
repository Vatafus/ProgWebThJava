package finalproject.jw.repo;

import finalproject.jw.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Repository
public interface OrderREPO extends JpaRepository<Order, Long> {
    public Set<Order> findOrdersByUserId(Long id);
}
