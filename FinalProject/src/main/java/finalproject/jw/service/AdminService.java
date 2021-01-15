package finalproject.jw.service;

import finalproject.jw.domain.Category;
import finalproject.jw.dto.AddCategoryDTO;
import finalproject.jw.repo.CategoryREPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    
    private final CategoryREPO categoryREPO;

    @Autowired
    public AdminService(CategoryREPO categoryREPO) {
        this.categoryREPO = categoryREPO;
    }

    public Long addCategory(AddCategoryDTO category) {

        Category categoryFromDB = categoryREPO.findCategoryByName(category.getCategoryName());
        categoryFromDB = new Category();
        categoryFromDB.setName(category.getCategoryName());
        Long newCategoryId = categoryREPO.save(categoryFromDB).getId();
        return newCategoryId;
    }

}
