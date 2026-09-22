package CS2114Project1Group46;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

public class ValidatorTest {

    // Tests a valid purchase
    @Test
    public void testValidPurchase() {
        Validator validator = new Validator();

        Transaction purchase =
            new Transaction(15.0, "Groceries");

        ArrayList<Transaction> recentPurchases =
            new ArrayList<Transaction>();

        Validator.ValidationResult result =
            validator.validateTransaction(
                purchase, 500.0, recentPurchases);

        assertTrue(result.isValid());
    }

    // Tests a negative purchase amount
    @Test
    public void testNegativeAmount() {
        Validator validator = new Validator();

        Transaction purchase =
            new Transaction(-20.0, "Groceries");

        ArrayList<Transaction> recentPurchases =
            new ArrayList<Transaction>();

        Validator.ValidationResult result =
            validator.validateTransaction(
                purchase, 500.0, recentPurchases);

        assertFalse(result.isValid());
    }

    // Tests a purchase that is too expensive
    @Test
    public void testOverspending() {
        Validator validator = new Validator();

        Transaction purchase =
            new Transaction(600.0, "Shopping");

        ArrayList<Transaction> recentPurchases =
            new ArrayList<Transaction>();

        Validator.ValidationResult result =
            validator.validateTransaction(
                purchase, 500.0, recentPurchases);

        assertFalse(result.isValid());
    }

    // Tests a duplicate purchase
    @Test
    public void testDuplicatePurchase() {
        Validator validator = new Validator();

        Transaction purchase =
            new Transaction(20.0, "Groceries");

        ArrayList<Transaction> recentPurchases =
            new ArrayList<Transaction>();

        recentPurchases.add(
            new Transaction(20.0, "Groceries"));

        Validator.ValidationResult result =
            validator.validateTransaction(
                purchase, 500.0, recentPurchases);

        assertFalse(result.isValid());
    }
}