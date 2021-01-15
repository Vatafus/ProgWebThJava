package finalproject.jw.validation;

import finalproject.jw.domain.User;
import finalproject.jw.exception.NotAdminException;
import finalproject.jw.exception.NotLoggedInException;
import finalproject.jw.exception.UserException;

import javax.servlet.http.HttpServletRequest;

public class Validation {
    private static final String USER_ATTRIBUTE = "user";

    public static void validateUserPassword(String password,String reEnteredPassword) throws UserException {
        if(!password.equals(password)) {
            throw new UserException("Invalid password!");
        }
    }

    public static void validateLogIn(HttpServletRequest request) throws NotLoggedInException {
        if(request.getSession().getAttribute(USER_ATTRIBUTE) == null) {
            throw new NotLoggedInException("Please log in!");
        }
    }

    public static void validateAdminUser(HttpServletRequest request) throws NotLoggedInException, NotAdminException {
        Validation.validateLogIn(request);
        if(!((User)request.getSession().getAttribute(USER_ATTRIBUTE)).isAdmin()) {
            throw new NotAdminException("You are not admin!");
        }
    }
}
