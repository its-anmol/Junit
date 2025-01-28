
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junitpractice.ValidateBet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class parameterizedValidateBet {

    @ParameterizedTest
    @CsvSource({
            "100, 50, false",   // bet amount, account balance, expected result
            "100, 150, true",   // bet amount, account balance, expected result
            "100, 100, true"    // bet amount, account balance, expected result
    })
    public void testValidateWithDifferentBalances(int betAmount, int accountBalance, boolean expectedResult) {

        ValidateBet bet = new ValidateBet(betAmount);

        assertEquals(expectedResult, bet.validate(accountBalance),
                "Bet validation failed for bet amount: " + betAmount + " and account balance: " + accountBalance);
    }
}

