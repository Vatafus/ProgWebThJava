package finalproject.jw.controller;


import finalproject.jw.dto.ProductDTO;
import finalproject.jw.exception.NoProductException;
import finalproject.jw.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable Long id) throws NoProductException  {
        return productService.findProductById(id);
    }
}
