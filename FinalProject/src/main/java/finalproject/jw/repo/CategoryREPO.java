package finalproject.jw.repo;

import finalproject.jw.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryREPO extends JpaRepository<Category,Long> {

    public Category findCategoryByName(String name);

    public Category findCategoryById(Long id);
}
