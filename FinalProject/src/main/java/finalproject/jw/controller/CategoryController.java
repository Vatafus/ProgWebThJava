package finalproject.jw.controller;


import finalproject.jw.domain.Category;
import finalproject.jw.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @GetMapping("/all-categories")
    public List<Category> getAllCategories() {
        return this.categoryService.getAllCategories();
    }
}
