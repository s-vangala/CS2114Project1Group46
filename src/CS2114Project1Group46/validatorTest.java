package CS2114Project1Group46;

import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.ArrayList;

import student.TestCase;

public class ValidatorTest
    extends student.TestCase
{
    private Validator validator;


    public void setUp()
    {
        validator = new Validator();
    }


    /*
     * tests a valid transaction
     */
    @Test
    public void validTransactionTest()
    {
        Transaction purchase =
            new Transaction(
                15.0,
                "Groceries",
                LocalDate.now(),
                "Food purchase");

        ArrayList<Transaction> recentPurchases =
            new ArrayList<Transaction>();

        Validator.ValidationResult result =
            validator.validateTransaction(
                purchase,
                500.0,
                recentPurchases);

        assertTrue(result.isValid());
    }


    /*
     * tests a negative transaction amount
     */
    @Test
    public void negativeAmountTest()
    {
        Transaction purchase =
            new Transaction(
                -20.0,
                "Groceries",
                LocalDate.now(),
                "Invalid purchase");

        ArrayList<Transaction> recentPurchases =
            new ArrayList<Transaction>();

        Validator.ValidationResult result =
            validator.validateTransaction(
                purchase,
                500.0,
                recentPurchases);

        assertFalse(result.isValid());
    }


    /*
     * tests a transaction that exceeds the balance
     */
    @Test
    public void overspendingTest()
    {
        Transaction purchase =
            new Transaction(
                600.0,
                "Shopping",
                LocalDate.now(),
                "Too expensive");

        ArrayList<Transaction> recentPurchases =
            new ArrayList<Transaction>();

        Validator.ValidationResult result =
            validator.validateTransaction(
                purchase,
                500.0,
                recentPurchases);

        assertFalse(result.isValid());
    }


    /*
     * tests a duplicate transaction
     */
    @Test
    public void duplicateTransactionTest()
    {
        Transaction purchase =
            new Transaction(
                20.0,
                "Groceries",
                LocalDate.now(),
                "Groceries");

        ArrayList<Transaction> recentPurchases =
            new ArrayList<Transaction>();

        Transaction recentPurchase =
            new Transaction(
                20.0,
                "Groceries",
                LocalDate.now(),
                "Groceries");

        recentPurchases.add(recentPurchase);

        Validator.ValidationResult result =
            validator.validateTransaction(
                purchase,
                500.0,
                recentPurchases);

        assertFalse(result.isValid());
    }
}
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