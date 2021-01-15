package finalproject.jw.controller;

import finalproject.jw.domain.User;
import finalproject.jw.dto.ResponseDTO;
import finalproject.jw.exception.NoProductException;
import finalproject.jw.exception.NoQuantityException;
import finalproject.jw.exception.NotLoggedInException;
import finalproject.jw.service.CartService;
import finalproject.jw.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("cart")
public class CartController {

    private static final String USER_SESSION_ATTRIBUTE = "user";

    @Autowired
    private CartService cartService;

    @GetMapping("/add-to-cart")
    public ResponseEntity<ResponseDTO> addProductToCart(@RequestParam(name="productId") Long productId, @RequestParam(name="quantity") Integer quantity, HttpServletRequest request) throws NotLoggedInException, NoQuantityException, NoProductException {
        Validation.validateLogIn(request);
        User user = (User) request.getSession().getAttribute(USER_SESSION_ATTRIBUTE);
        this.cartService.addProductToCart(user, quantity, productId);
        return new ResponseEntity<ResponseDTO>(new ResponseDTO(HttpStatus.OK.value(), "Product added to cart!"), HttpStatus.OK);
    }

    @DeleteMapping("/delete-from-cart")
    public ResponseEntity<ResponseDTO> deleteProductFromCart(@RequestParam(name="productId", required=true) Long productId, HttpServletRequest request) throws NoProductException, NotLoggedInException {
        Validation.validateLogIn(request);
        User user = (User) request.getSession().getAttribute(USER_SESSION_ATTRIBUTE);
        this.cartService.deleteProductFromCart(productId, user.getCart());
        return new ResponseEntity<ResponseDTO>(new ResponseDTO(HttpStatus.OK.value(), "Product removed from basket successfully!"), HttpStatus.OK);
    }

}
