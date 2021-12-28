import com.sun.deploy.util.Waiter;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BankTest extends TestCase
{
    Bank testBank;

    @Override
    protected void setUp() throws Exception {
        testBank = new Bank();
        testBank.addAccount(new Account(1000, "1"));
        testBank.addAccount(new Account(1500, "2"));
    }

    public void testGetBalance(){
        long actual = testBank.getBalance("1");
        long expected = 1000;
        assertEquals(expected, actual);
    }

    public void testGetSumAllAccounts(){
        long actual = testBank.getSumAllAccounts();
        long expected = 2500;
        assertEquals(expected, actual);
    }

    public void testTransfer(){
        testBank.transfer("1", "2", 500);
        long actualValue1 = testBank.getBalance("1");
        long actualValue2 = testBank.getBalance("2");

        long expectedValue1 = 500;
        long expectedValue2 = 2000;
        assertEquals(expectedValue1, actualValue1);
        assertEquals(expectedValue2, actualValue2);

    }

}
