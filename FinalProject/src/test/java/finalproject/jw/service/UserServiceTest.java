package finalproject.jw.service;

import finalproject.jw.domain.User;
import finalproject.jw.dto.LoginDTO;
import finalproject.jw.exception.InvalidPasswordException;
import finalproject.jw.exception.NoEmailException;
import finalproject.jw.exception.UserException;
import finalproject.jw.repo.*;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    public static final String INVALID_EMAIL = "Apple@gmail.com";
    public static final String VALID_PASSWORD = "12456789";
    public static final int RANDOM_ID = 1;

    @Mock
    private UserREPO userREPO;
    @Mock
    private OrderREPO orderREPO;
    @Mock
    private SellerREPO sellerREPO;
    @Mock
    private CartREPO cartREPO;
    @Mock
    private BankACCREPO bankACCREPO;

    @InjectMocks
    private UserService userService;

    private User user;

    @Before
    public void setUp(){
        initMocks(this);
        this.user = new User();
        user.setId(Long.valueOf(RANDOM_ID));
    }

    @Test
    public void login_InvalidEmail() throws NoEmailException, InvalidPasswordException, UserException {

        LoginDTO loginDto = new LoginDTO(INVALID_EMAIL, VALID_PASSWORD);

        when(userREPO.findUserByEmail(INVALID_EMAIL)).thenReturn(null);
        userService.login(loginDto);
    }

}
