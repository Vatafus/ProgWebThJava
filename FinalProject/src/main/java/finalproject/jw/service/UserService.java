package finalproject.jw.service;

import finalproject.jw.domain.BankACC;
import finalproject.jw.domain.Cart;
import finalproject.jw.domain.Seller;
import finalproject.jw.domain.User;
import finalproject.jw.dto.BankACCRegistrationDTO;
import finalproject.jw.dto.LoginDTO;
import finalproject.jw.dto.SellerRegistrationDTO;
import finalproject.jw.dto.UserRegistrationDTO;
import finalproject.jw.exception.*;
import finalproject.jw.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;

@Service
public class UserService {

    private final UserREPO userREPO;
    private final CartREPO cartREPO;
    private final OrderREPO orderREPO;
    private final SellerREPO sellerREPO;
    private final BankACCREPO bankACCREPO;
    @Autowired
    public UserService(UserREPO userREPO, CartREPO cartREPO, OrderREPO orderREPO, SellerREPO sellerREPO, BankACCREPO bankACCREPO) {
        this.userREPO = userREPO;
        this.cartREPO = cartREPO;
        this.orderREPO = orderREPO;
        this.sellerREPO = sellerREPO;
        this.bankACCREPO = bankACCREPO;
    }

    public static String stringToSha1(String string) throws UserException {
        String sha1 = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(string.getBytes("utf8"));
            sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e){
            throw new UserException("A problem with the password!", e);
        }
        return sha1;
    }

    public boolean isThereAlreadySuchEmail(String email) {
        User user = userREPO.findUserByEmail(email);

        return (user != null);
    }

    @Transactional
    public Long register(UserRegistrationDTO u) throws UserException {
        if(this.isThereAlreadySuchEmail(u.getEmail())){
            throw new UserException("Already such email!");
        }

        User user = new User(u);
        userREPO.save(user);

        Long newUserId = userREPO.save(user).getId();

        Cart cart = new Cart(user);
        cartREPO.save(cart);

        return newUserId;
    }

    public User login(LoginDTO user) throws InvalidPasswordException, NoEmailException, UserException {

        User u = userREPO.findUserByEmail(user.getEmail());
        if(u == null){
            throw new NoEmailException("Invalid email!");
        }
        u.setOrders(orderREPO.findOrdersByUserId(u.getId()));
        u.setSellers(sellerREPO.findSellersByUserId(u.getId()));
        u.setCart(cartREPO.findBasketByUserId(u.getId()));

        if(!this.stringToSha1(user.getPassword()).equals(u.getPassword())){
            throw new InvalidPasswordException("Invalid password!");
        }

        return u;
    }

    public boolean isBasketEmpty(Cart basket){
        Cart c = cartREPO.findBasketByUserId(basket.getUser().getId());

        return (c.getCartProducts().isEmpty());
    }

    public boolean isBankAccountAlready(BankACCRegistrationDTO bankAccount) {
        BankACC account = bankACCREPO.findBankACCByIban(bankAccount.getIban());
        return (account != null);
    }

    public boolean isBusinessNameAvailable(String businessName) {
        Seller seller = sellerREPO.findSellerByBusinessName(businessName);
        return (seller == null);
    }

    public Long registerSeller(User user, SellerRegistrationDTO seller) throws BankException, SellerException {

        if(this.isBankAccountAlready(seller.getAccount())) {
            throw new BankException("Already such bank account");
        }
        if(!this.isBusinessNameAvailable(seller.getBusinessName())) {
            throw new SellerException("Business name already taken");
        }

        BankACC bankAccount = new BankACC(seller.getAccount());

        Seller newSeller = new Seller(seller, user);

        HashSet<BankACC> newSellerBankAccounts = new HashSet<>();
        newSellerBankAccounts.add(bankAccount);
        bankAccount.setSeller(newSeller);
        newSeller.setBankAccounts(newSellerBankAccounts);

        Long sellerId = sellerREPO.save(newSeller).getId();
        bankACCREPO.save(bankAccount);

        return sellerId;
    }
}
