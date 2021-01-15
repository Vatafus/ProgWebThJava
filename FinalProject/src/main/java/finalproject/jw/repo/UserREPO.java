package finalproject.jw.repo;

import finalproject.jw.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserREPO extends JpaRepository<User, Long> {
    public User findUserByEmail(String email);
}