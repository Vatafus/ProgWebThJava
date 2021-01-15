package finalproject.jw.service;

import finalproject.jw.domain.Cart;
import finalproject.jw.domain.CartProduct;
import finalproject.jw.domain.Product;
import finalproject.jw.domain.User;
import finalproject.jw.exception.NoProductException;
import finalproject.jw.exception.NoQuantityException;
import finalproject.jw.repo.CartProductREPO;
import finalproject.jw.repo.ProductREPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final ProductREPO productREPO;
    private final CartProductREPO cartProductREPO;

    @Autowired
    public CartService(ProductREPO productREPO, CartProductREPO cartProductREPO) {
        this.productREPO = productREPO;
        this.cartProductREPO = cartProductREPO;
    }

    public void addProductToCart(User user, Integer quantity, Long productId) throws NoProductException, NoQuantityException {
        Product product = productREPO.findProductById(productId);
        if(product == null){
            throw new NoProductException("There is no product !!!");
        }
        if(product.getQuantity() < quantity){
            throw new NoQuantityException("There is not enough quantity of this product!");
        }
        CartProduct cartProduct = new CartProduct();
        cartProduct.setCart(user.getCart());
        cartProduct.setProduct(product);
        cartProduct.setQuantity(quantity);

        cartProductREPO.save(cartProduct);
    }

    public void deleteProductFromCart(Long productId, Cart cart) throws NoProductException {

        CartProduct cartProduct = cartProductREPO.findCartProductByCart_IdAndAndProduct_Id(cart.getId(), productId);
        if(cartProduct == null){
            throw new NoProductException("No such product in user basket !!!");
        }
        cartProductREPO.delete(cartProduct);
    }
}
