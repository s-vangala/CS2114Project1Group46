package CS2114Project1Group46;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Test class for History
 * 
 * @author Shresta Vangala (svangala)
 * @version 2026.09.21
 */
public class HistoryTest
    extends student.TestCase
{
    private History history;
    private Transaction t1;
    private Transaction t2;
    private Transaction t3;
    private Transaction t4;

    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    public void setUp()
    {
        history = new History();
        t1 = new Transaction(
            50.0,
            "Groceries",
            LocalDate.of(2026, 5, 12),
            "Weekly Groceries");
        t2 = new Transaction(
            25.0,
            "Entertainment",
            LocalDate.of(2026, 7, 25),
            "Movies");
        t3 = new Transaction(
            100.0,
            "Rent",
            LocalDate.of(2026, 11, 11),
            "December Rent");
        t4 = new Transaction(
            25.0,
            "Entertainment",
            LocalDate.of(2026, 12, 30),
            "Movies");

    }


    // ----------------------------------------------------------
    /**
     * tests the addTransaction method
     */
    public void testHistory()
    {
        assertNotNull(history);
    }


    // ----------------------------------------------------------
    /**
     * tests the queryByDateRange method
     */
    public void testAddTransaction()
    {
        history.addTransaction(t1);
        ArrayList<Transaction> results = history.queryByDateRange(
            LocalDate.of(2026, 5, 12),
            LocalDate.of(2026, 5, 12));
        assertEquals(1, results.size());
        assertEquals(t1, results.get(0));
    }


    /**
     * 
     */
    public void testAddTransactionNull()
    {
        Exception exception = null;
        try
        {
            history.addTransaction(null);
        }
        catch (IllegalArgumentException e)
        {
            exception = e;
        }
        assertNotNull(exception);
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    public void testQueryByDateRange()
    {
        history.addTransaction(t1);
        history.addTransaction(t2);
        history.addTransaction(t3);

        ArrayList<Transaction> results = history.queryByDateRange(
            LocalDate.of(2026, 5, 1),
            LocalDate.of(2026, 7, 31));
        assertEquals(2, results.size());
        assertEquals(t1, results.get(0));
        assertEquals(t2, results.get(1));
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    public void testQueryByDateRangeStartDate()
    {
        history.addTransaction(t1);

        ArrayList<Transaction> results = history.queryByDateRange(
            LocalDate.of(2026, 5, 1),
            LocalDate.of(2026, 5, 25));

        assertEquals(1, results.size());
        assertEquals(t1, results.get(0));
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    public void testQueryByDateRangeEndDate()
    {
        history.addTransaction(t2);

        ArrayList<Transaction> results = history.queryByDateRange(
            LocalDate.of(2026, 7, 1),
            LocalDate.of(2026, 7, 25));

        assertEquals(1, results.size());
        assertEquals(t2, results.get(0));
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    public void testQueryByDateRangeInvalid()
    {
        history.addTransaction(t1);
        history.addTransaction(t2);

        ArrayList<Transaction> results = history.queryByDateRange(
            LocalDate.of(2026, 4, 1),
            LocalDate.of(2026, 3, 1));

        assertTrue(results.isEmpty());
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    public void testGetByCategory()
    {
        history.addTransaction(t1);
        history.addTransaction(t2);
        history.addTransaction(t3);
        history.addTransaction(t4);

        ArrayList<Transaction> results = history.getByCategory("Entertainment");

        assertEquals(2, results.size());
        assertEquals(t2, results.get(0));
        assertEquals(t4, results.get(1));
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    public void testGetByCategoryNoMatch()
    {
        history.addTransaction(t1);
        history.addTransaction(t2);

        ArrayList<Transaction> results = history.getByCategory("Rent");

        assertTrue(results.isEmpty());
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    public void testGetTotal()
    {
        history.addTransaction(t1);
        history.addTransaction(t2);
        history.addTransaction(t3);

        ArrayList<Transaction> transactions = history.queryByDateRange(
            LocalDate.of(2026, 5, 1),
            LocalDate.of(2026, 11, 30));

        assertEquals(175.0, history.getTotal(transactions), 0.01);
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    public void testGetTotalEmpty()
    {
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();

        assertEquals(0.0, history.getTotal(transactions), 0.01);
    }
}
