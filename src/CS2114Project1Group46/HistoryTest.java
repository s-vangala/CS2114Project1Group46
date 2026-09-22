package CS2114Project1Group46;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Test class for History
 * 
 * @author shresta vangala (svangala)
 * @version 2026.09.22
 */
public class HistoryTest
    extends student.TestCase
{
    private History history;
    private Transaction t1;
    private Transaction t2;
    private Transaction t3;

    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    public void setUp()
    {
        history = new History();
        Transaction t1 = new Transaction(
            50.0,
            "Groceries",
            LocalDate.of(2026, 5, 12),
            "Weekly Groceries");
        Transaction t2 = new Transaction(
            25.0,
            "Entertainment",
            LocalDate.of(2026, 7, 25),
            "Movies");
        Transaction t3 = new Transaction(
            100.0,
            "Rent",
            LocalDate.of(2026, 11, 11),
            "December Rent");

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
        ArrayList<Transaction> results = history.queryByDateRange(LocalDate.of(2026, 5, 12), LocalDate.of(2026, 5, 12));
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
        
        ArrayList<Transaction> results = history.queryByDateRange(LocalDate.of(2026, 5, 1), LocalDate.of(2026, 7, 31));
        assertEquals(2, results.size());
        assertEquals(t1, results.get(0));
        assertEquals(t2, results.get(1));
    }
}
