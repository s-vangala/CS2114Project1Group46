package CS2114Project1Group46;

import java.time.LocalDate;

// -------------------------------------------------------------------------
/**
 * Test class that tests the methods in the Transaction class.
 * 
 * @author Shresta Vangala (svangala)
 * @version 2026.09.21
 */
public class TransactionTest
    extends student.TestCase
{

    private Transaction t1;
    private double amount;
    private String category;
    private LocalDate date1;
    private String note;

    // ----------------------------------------------------------
    /**
     * Set up for all test methods. Runs before every test.
     */
    public void setUp()
    {

        date1 = LocalDate.now();
        t1 = new Transaction(
            100.0,
            "Groceries",
            date1,
            "Weekly grocery shopping");
    }

    // ----------------------------------------------------------
    /**
     * tests the amount of a Transaction object
     */
    public void testTransactionAmount()
    {
        assertEquals(100.0, t1.getAmount(), 0.01);
        assertEquals("Groceries", t1.getCategory());
    }

    // ----------------------------------------------------------
    /**
     * Test the date of a Transaction object.=
     */
    public void testTransactionDate()
    {
        assertEquals(date1, t1.getDate());
    }

    // ----------------------------------------------------------
    /**
     * tests the category of a Transaction object
     */
    public void testTransactionCategory()
    {
        assertEquals("Groceries", t1.getCategory());
    }


    // ----------------------------------------------------------
    /**
     * tests the note of a Transaction object
     */
    public void testTransactionNote()
    {
        assertEquals("Weekly grocery shopping", t1.getNote());
    }
}
