package finalproject.jw.service;

import finalproject.jw.domain.Product;
import finalproject.jw.dto.ProductDTO;
import finalproject.jw.exception.NoProductException;
import finalproject.jw.repo.CategoryREPO;
import finalproject.jw.repo.ProductREPO;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductREPO productREPO;
    private final CategoryREPO categoryREPO;

    public ProductService(ProductREPO productREPO, CategoryREPO categoryREPO) {
        this.productREPO = productREPO;
        this.categoryREPO = categoryREPO;
    }
    public ProductDTO findProductById(Long id) throws NoProductException {
        Product product = productREPO.findProductById(id);
        if(product == null){
            throw new NoProductException("No product!");
        }
        ProductDTO productDetailsDTO = new ProductDTO(product);
        return productDetailsDTO;
    }
}
