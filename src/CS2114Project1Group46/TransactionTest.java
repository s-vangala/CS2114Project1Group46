package CS2114Project1Group46;

import static org.junit.Assert.*;
import java.util.NoSuchElementException;
import org.junit.Test;
import student.TestCase;

public class TransactionTest
    extends student.TestCase
{

    private Transaction transaction;
    private double amount;
    private String category;
    private LocalDate date;
    private String note;

    /**
     * Test the creation of a Transaction object.
     */
    public void setUp()
    {

        LocalDate date = LocalDate.now();
        Transaction transaction = new Transaction(
            100.0,
            "Groceries",
            date,
            "Weekly grocery shopping");
    }


    /**
     * Test the amount of a Transaction object.
     */
    public void testTransactionAmount()
    {
        LocalDate date = LocalDate.now();
        Transaction transaction = new Transaction(
            100.0,
            "Groceries",
            date,
            "Weekly grocery shopping");

        assertEquals(100.0, transaction.getAmount());
        assertEquals("Groceries", transaction.getCategory());
    }


    /**
     * Test the date of a Transaction object.
     */
    public void testTransactionDate()
    {
        LocalDate date = LocalDate.now();
        Transaction transaction = new Transaction(
            100.0,
            "Groceries",
            date,
            "Weekly grocery shopping");

        assertEquals(date, transaction.getDate());
        assertEquals("Weekly grocery shopping", transaction.getNote());
    }


    /**
     * Test the category of a Transaction object.
     */
    public void testTransactionCategory()
    {
        LocalDate date = LocalDate.now();
        Transaction transaction = new Transaction(
            100.0,
            "Groceries",
            date,
            "Weekly grocery shopping");

        assertEquals(category, transaction.getCategory());
        assertEquals("Groceries", transaction.getCategory());
    }
}
