package CS2114Project1Group46;

import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.ArrayList;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 * 
 * @author Monya
 * @version Sep 22, 2026
 */
public class ValidatorTest
    extends student.TestCase
{
    private Validator validator;
    private Transaction purchase;

    public void setUp()
    {
        validator = new Validator();
        purchase = new Transaction(
            15.0,
            "Groceries",
            LocalDate.now(),
            "Weekly Groceries");
    }


    /**
     * tests a valid transaction
     */
    public void validTransactionTest()
    {
        ArrayList<Transaction> recentPurchases = new ArrayList<Transaction>();

        Validator.ValidationResult result =
            validator.validateTransaction(purchase, 500.0, recentPurchases);

        assertTrue(result.isValid());
    }


    /**
     * tests a negative transaction amount
     */
    public void negativeAmountTest()
    {
        Transaction purchase2 = new Transaction(
            -20.0,
            "Groceries",
            LocalDate.now(),
            "Invalid purchase");

        ArrayList<Transaction> recentPurchases = new ArrayList<Transaction>();

        Validator.ValidationResult result =
            validator.validateTransaction(purchase2, 500.0, recentPurchases);

        assertFalse(result.isValid());
    }


    // ----------------------------------------------------------
    /**
     * tests a transaction that exceeds the balance
     */
    public void overspendingTest()
    {
        Transaction purchase3 = new Transaction(
            600.0,
            "Shopping",
            LocalDate.now(),
            "Too expensive");

        ArrayList<Transaction> recentPurchases = new ArrayList<Transaction>();

        Validator.ValidationResult result =
            validator.validateTransaction(purchase3, 500.0, recentPurchases);

        assertFalse(result.isValid());
    }


    // ----------------------------------------------------------
    /**
     * tests a duplicate transaction
     */
    public void duplicateTransactionTest()
    {
        Transaction purchase4 =
            new Transaction(20.0, "Groceries", LocalDate.now(), "Groceries");

        ArrayList<Transaction> recentPurchases = new ArrayList<Transaction>();

        Transaction recentPurchase =
            new Transaction(20.0, "Groceries", LocalDate.now(), "Groceries");

        recentPurchases.add(recentPurchase);

        Validator.ValidationResult result =
            validator.validateTransaction(purchase4, 500.0, recentPurchases);

        assertFalse(result.isValid());
    }


    // ----------------------------------------------------------
    /**
     * Tests a purchase that is too expensive
     */
    public void testOverspending()
    {
        ArrayList<Transaction> recentPurchases = new ArrayList<Transaction>();

        Validator.ValidationResult result =
            validator.validateTransaction(purchase, 500.0, recentPurchases);

        assertTrue(result.isValid());
    }


    // ----------------------------------------------------------
    /**
     * Tests a duplicate purchase
     */
    public void testDuplicatePurchase()
    {
        ArrayList<Transaction> recentPurchases = new ArrayList<Transaction>();

        recentPurchases.add(
            new Transaction(20.0, "Groceries", LocalDate.now(), "Groceries"));

        Validator.ValidationResult result =
            validator.validateTransaction(purchase, 500.0, recentPurchases);

        assertTrue(result.isValid());
    }
}
