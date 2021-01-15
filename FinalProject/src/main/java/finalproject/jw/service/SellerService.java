package finalproject.jw.service;

import finalproject.jw.domain.Category;
import finalproject.jw.domain.Product;
import finalproject.jw.domain.Seller;
import finalproject.jw.domain.User;
import finalproject.jw.dto.DeleteDTO;
import finalproject.jw.dto.ProductSellDTO;
import finalproject.jw.dto.ResponseDTO;
import finalproject.jw.exception.NoProductException;
import finalproject.jw.exception.NotLoggedInException;
import finalproject.jw.exception.SellerException;
import finalproject.jw.repo.CategoryREPO;
import finalproject.jw.repo.ProductREPO;
import finalproject.jw.repo.SellerREPO;
import finalproject.jw.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;

@Service
public class SellerService {
    private final SellerREPO sellerREPO;
    private final ProductREPO productREPO;
    private final CategoryREPO categoryREPO;

    @Autowired
    public SellerService(SellerREPO sellerREPO, ProductREPO productREPO, CategoryREPO categoryREPO) {
        this.sellerREPO = sellerREPO;
        this.productREPO = productREPO;
        this.categoryREPO = categoryREPO;
    }

    public Long addProductForSell(User user, ProductSellDTO product) {

        Seller seller = sellerREPO.findSellerById(product.getSellerId());


        Product sellerProduct = productREPO.findProductBySeller_Id(product.getSellerId());


        Category category = categoryREPO.findCategoryById(product.getCategoryId());
        Product newProductForSell = new Product(product, category, seller);

        Long newProductId = productREPO.save(newProductForSell).getId();

        return newProductId;

    }
    public void validateSeller(User user, Seller seller) throws SellerException {
        if (seller == null) {
            throw new SellerException("No such Seller profile");
        }
        Set<Seller> userSellerProfiles = user.getSellers();
        if (userSellerProfiles == null || !userSellerProfiles.contains(seller)) {
            throw new SellerException("Seller profile doesnt match user !!!");
        }
    }

    @Transactional
    public void deleteProduct(User user, DeleteDTO product) throws SellerException, NoProductException {

        Seller seller = sellerREPO.findSellerById(product.getSellerId());
        this.validateSeller(user, seller);
        if(productREPO.deleteByIdAndSeller_Id(product.getProductId(), product.getSellerId()) == 0){
            throw new NoProductException("There is no product");
        }
    }
}