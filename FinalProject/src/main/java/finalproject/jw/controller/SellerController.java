package finalproject.jw.controller;

import finalproject.jw.domain.User;
import finalproject.jw.dto.CreateDTO;
import finalproject.jw.dto.DeleteDTO;
import finalproject.jw.dto.ProductSellDTO;
import finalproject.jw.dto.ResponseDTO;
import finalproject.jw.exception.NoProductException;
import finalproject.jw.exception.NotLoggedInException;
import finalproject.jw.exception.SellerException;
import finalproject.jw.service.SellerService;
import finalproject.jw.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @PostMapping("/addProduct")
    public ResponseEntity<CreateDTO> addProductToSell(@RequestBody @Valid ProductSellDTO product, HttpServletRequest request) throws NotLoggedInException, SellerException {
        Validation.validateLogIn(request);
        User user = (User) request.getSession().getAttribute("user");
        Long newProductId = sellerService.addProductForSell(user, product);
        return new ResponseEntity<CreateDTO>(new CreateDTO(HttpStatus.CREATED.value(), "New product successfully created!", newProductId), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteProduct")
    public ResponseEntity<ResponseDTO> deleteProductToSell(@RequestBody @Valid DeleteDTO product, HttpServletRequest request) throws SellerException, NotLoggedInException, NoProductException {
        Validation.validateLogIn(request);
        User user = (User) request.getSession().getAttribute("user");
        sellerService.deleteProduct(user, product);
        return new ResponseEntity<ResponseDTO>(new ResponseDTO(HttpStatus.OK.value(), "Product deleted successfully!"), HttpStatus.OK);
    }
}
