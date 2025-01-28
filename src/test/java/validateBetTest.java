import org.junitpractice.ValidateBet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class validateBetTest {
    @Test
    public void testValidateWithInsufficientBalance(){
        ValidateBet bet= new ValidateBet(100);
        int accountBalance =50;
        assertFalse(bet.validate(accountBalance),"Bet should not be allowed with insufficient balance");
    }

    @Test
    public void testValidateWithSufficientBalance(){
        ValidateBet bet= new ValidateBet(100);
        int accountBalance =150;
        assertTrue(bet.validate(accountBalance),"Bet should  be allowed with sufficient balance");
    }

    @Test
    public void testValidateWithExactBalance(){
        ValidateBet bet= new ValidateBet(100);
        int accountBalance =100;
        assertTrue(bet.validate(accountBalance),"Bet should  be allowed with sufficient balance");
    }


}
