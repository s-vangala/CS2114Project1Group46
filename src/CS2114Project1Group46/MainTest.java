package CS2114Project1Group46;

import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.Test;

public class MainTest
    extends student.TestCase
{
// ~ Fields ................................................................
    private Main mainApp;

// ~ Public Methods ........................................................
    /**
     * Sets up the test fixture before each test case runs.
     */
    @Override
    public void setUp()
    {
        mainApp = new Main();
    }


// ----------------------------------------------------------
    /**
     * Tests class constructor and getters to verify initial model instances.
     */
    public void testMainConstructorAndGetters()
    {
        assertNotNull(mainApp.getProfile());
        assertNotNull(mainApp.getHistory());
        assertNotNull(mainApp.getCalculator());
        assertNotNull(mainApp.getValidator());
    }


// ----------------------------------------------------------
    /**
     * Tests queryLoggedTransactions method with empty state and added state.
     */
    public void testQueryLoggedTransactions()
    {
        assertTrue(mainApp.queryLoggedTransactions().isEmpty());

        Transaction t = new Transaction(
            45.0,
            "Groceries",
            LocalDate.now(),
            "Weekly Groceries");

        mainApp.getHistory().addTransaction(t);

        ArrayList<Transaction> logged = mainApp.queryLoggedTransactions();
        assertEquals(1, logged.size());
        assertEquals(t, logged.get(0));
    }


// ----------------------------------------------------------
    /**
     * Tests the interactive console workflow using student.TestCase's
     * setSystemIn to test salary, fixed expenses, accepted and rejected
     * transactions.
     */
    public void testRunInteractiveConsoleNormalFlow()
    {
        // Supply console lines directly via student.TestCase setSystemIn
        setSystemIn(
            "5000.0",          // Salary
            "2",               // 2 Fixed expenses
            "Rent",            // Expense 1 name
            "1500.0",          // Expense 1 amount
            "Utilities",       // Expense 2 name
            "500.0",           // Expense 2 amount
            "2",               // 2 Transactions
            "Groceries",       // Transaction 1 category
            "200.0",           // Transaction 1 amount
            "Food",            // Transaction 1 note (Accepted)
            "Entertainment",   // Transaction 2 category
            "4000.0",          // Transaction 2 amount
            "Party"            // Transaction 2 note (Rejected)
        );

        mainApp.runInteractiveConsole();

        assertEquals(5000.0, mainApp.getProfile().getSalary(), 0.001);
        assertEquals(1, mainApp.queryLoggedTransactions().size());
        assertEquals(2800.0, mainApp.getProfile().getSurplusDeficit(), 0.001);
    }


// ----------------------------------------------------------
    /**
     * Tests edge cases in helper input methods by feeding non-numeric inputs to
     * trigger try-catch blocks and prompt retries.
     */
    public void testRunInteractiveConsoleInvalidInputHandling()
    {
        // Feeds invalid inputs followed by valid corrections
        setSystemIn(
            "invalid_salary", // Non-numeric salary
            "3000.0",         // Valid salary
            "not_an_int",     // Non-numeric expense count
            "0",              // Valid expense count
            "bad_count",      // Non-numeric transaction count
            "1",              // Valid transaction count
            "Groceries",      // Category
            "not_a_number",   // Non-numeric amount
            "100.0",          // Valid amount
            "Snacks"          // Note
        );

        mainApp.runInteractiveConsole();

        assertEquals(3000.0, mainApp.getProfile().getSalary(), 0.001);
        assertEquals(1, mainApp.queryLoggedTransactions().size());
    }


// ----------------------------------------------------------
    /**
     * Tests the main method entry point using student.TestCase setSystemIn.
     */
    public void testMainEntryPoint()
    {
        setSystemIn(
            "2000.0", // Salary
            "0",      // Fixed expenses count
            "0"       // Transactions count
        );

        Main.main(new String[] {});

        assertNotNull(mainApp);
    }
}
