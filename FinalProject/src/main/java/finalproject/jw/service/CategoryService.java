package finalproject.jw.service;

import finalproject.jw.domain.Category;
import finalproject.jw.repo.CategoryREPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryREPO categoryREPO;

    @Autowired
    public CategoryService(CategoryREPO categoryREPO) {
        this.categoryREPO = categoryREPO;
    }

    public List<Category> getAllCategories() {
        return categoryREPO.findAll();
    }
}
