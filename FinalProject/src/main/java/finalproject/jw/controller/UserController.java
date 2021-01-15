package finalproject.jw.controller;

import finalproject.jw.domain.User;
import finalproject.jw.dto.*;
import finalproject.jw.exception.*;
import finalproject.jw.service.UserService;
import finalproject.jw.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.SQLException;


@RestController
@RequestMapping("users")
public class UserController {
    private static final String USER_SESSION_ATTRIBUTE = "user";
    private static final int SESSION_DURATION = 30;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<CreateDTO> register(@RequestBody @Valid UserRegistrationDTO user) throws UserException {
        Validation.validateUserPassword(user.getPassword(), user.getReEnteredPassword());
        Long newUserId = userService.register(user);
        return new ResponseEntity<CreateDTO>(new CreateDTO(HttpStatus.CREATED.value(), "User registered successfully!", newUserId), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> getUser(@RequestBody @Valid LoginDTO user , HttpServletRequest request) throws UserException,InvalidPasswordException, NoEmailException {
        User u = userService.login(user);
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(SESSION_DURATION);
        session.setAttribute(USER_SESSION_ATTRIBUTE, u);
        return new ResponseEntity<ResponseDTO>(new ResponseDTO(HttpStatus.OK.value(), "Log in successful!"), HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ProfileDTO getUserProfile(HttpServletRequest request) throws NotLoggedInException {
        Validation.validateLogIn(request);
        User user = (User) request.getSession().getAttribute(USER_SESSION_ATTRIBUTE);
        ProfileDTO u = new ProfileDTO(user.getName(), user.getEmail());
        return u;
    }

    @PostMapping("/registerSeller")
    public ResponseEntity<CreateDTO> registerSeller(@RequestBody @Valid SellerRegistrationDTO seller, HttpServletRequest request) throws NotLoggedInException, BankException, SellerException {
        Validation.validateLogIn(request);
        Long newSellerId = userService.registerSeller((User) request.getSession().getAttribute(USER_SESSION_ATTRIBUTE), seller);
        return new ResponseEntity<CreateDTO>(new CreateDTO(HttpStatus.CREATED.value(), "Seller created successfully!", newSellerId), HttpStatus.CREATED);
    }
}
