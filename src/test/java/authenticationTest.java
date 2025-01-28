
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junitpractice.Authentication;
import org.junitpractice.UserClass;

import static org.junit.jupiter.api.Assertions.*;

public class authenticationTest {
    private Authentication auth;
    private UserClass user;

    @BeforeEach
    public  void setup(){
        user =Authentication.registerUser(1,"anmol","anmol");
        auth=new Authentication();
    }
    @Test
    public void testAuthenticateSuccess(){
        boolean isAuthenticated=auth.authenticate(user,"anmol","anmol");
        assertTrue(isAuthenticated,"User should be authenticated successfully.");
    }
    @Test
    public void testAuthenticateIncorrectUsername() {
        boolean isAuthenticated = auth.authenticate(user, "wrongusername", "anmol");
        assertFalse(isAuthenticated, "User should not be authenticated with incorrect username.");
    }
    @Test
    public void testAuthenticateIncorrectPassword() {
        boolean isAuthenticated = auth.authenticate(user, "anmol", "wrongPassword");
        assertFalse(isAuthenticated, "User should not be authenticated with incorrect password.");
    }
    @Test
    public void testRegisterUser() {
        UserClass newUser = Authentication.registerUser(2, "anmol", "Password");
        assertNotNull(newUser, "The new user should not be null.");
        assertEquals(2, newUser.getUserId(), "The user ID should be 2.");
        assertEquals("anmol", newUser.getUserName(), "The username should be 'janeDoe'.");
        assertEquals("Password", newUser.getPassword(), "The password should be 'securePass'.");
    }
}
