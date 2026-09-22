// Project 1
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who 
// do.
// -- Liam McKee (liamkmckee)
// LLM Statement:
// I have not used any assistance for the assignment beyond course resources and
// staff.
package CS2114Project1Group46;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

// -------------------------------------------------------------------------
/**
 * The BudgetCalculatorTest class contains unit tests for the
 * BudgetCalculator class.
 * 
 * @author Liam McKee (liamkmckee)
 * @version 2026.09.22
 */
public class BudgetCalculatorTest {
    // The BudgetCalculator instance under test
    private BudgetCalculator calculator;

    // A UserProfile with a known salary and fixed expenses
    private UserProfile profile;

    // ----------------------------------------------------------
    /**
     * Creates a fresh BudgetCalculator and a UserProfile with a known
     * salary and fixed expenses before each test.
     */
    @Before
    public void setUp() {
        calculator = new BudgetCalculator();
        profile = new UserProfile();
        profile.setSalary(5000.00);
        profile.addFixedExpense("Rent", 1500.00);
        profile.addFixedExpense("Utilities", 500.00);
    }


    // ----------------------------------------------------------
    /**
     * Salary minus fixed expenses minus logged transactions should
     * equal the net remaining budget.
     */
    @Test
    public void computeSurplusDeficit_normal_returnsNetRemaining() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(
            new Transaction(500.00, "Groceries", LocalDate.now(), "", "t1"));

        double result = calculator.computeSurplusDeficit(
            profile, transactions);
        assertEquals(2500.00, result, 0.001);
    }


    // ----------------------------------------------------------
    /**
     * An empty transaction list should not crash the calculation, it
     * should just return salary minus fixed expenses.
     */
    @Test
    public void computeSurplusDeficit_emptyTransactions_returnsFixedExpensesOnly() {
        double result = calculator.computeSurplusDeficit(
            profile, new ArrayList<>());
        assertEquals(3000.00, result, 0.001);
    }


    // ----------------------------------------------------------
    /**
     * Each category's spending should be expressed as its own
     * percentage of salary, not combined with other categories.
     */
    @Test
    public void computeCategoryPercentage_normal_returnsPerCategoryPercentages() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(
            new Transaction(500.00, "Groceries", LocalDate.now(), "", "t1"));
        transactions.add(
            new Transaction(250.00, "Groceries", LocalDate.now(), "", "t2"));
        transactions.add(
            new Transaction(1500.00, "Rent", LocalDate.now(), "", "t3"));

        Map<String, Double> result = calculator.computeCategoryPercentage(
            profile, transactions);

        assertEquals(15.0, result.get("Groceries"), 0.001);
        assertEquals(30.0, result.get("Rent"), 0.001);
    }


    // ----------------------------------------------------------
    /**
     * An empty transaction list should return an empty map, not null
     * and not an exception.
     */
    @Test
    public void computeCategoryPercentage_emptyTransactions_returnsEmptyMap() {
        Map<String, Double> result = calculator.computeCategoryPercentage(
            profile, new ArrayList<>());
        assertTrue(result.isEmpty());
    }


    // ----------------------------------------------------------
    /**
     * Flexible remaining should be flexible budget minus discretionary
     * spending logged so far.
     */
    @Test
    public void computeFlexibleRemaining_normal_returnsRemainingAmount() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(
            800.00, "Entertainment", LocalDate.now(), "", "t1"));

        double result = calculator.computeFlexibleRemaining(
            profile, transactions);
        assertEquals(2200.00, result, 0.001);
    }


    // ----------------------------------------------------------
    /**
     * Overspending the flexible budget should return a defined
     * negative value rather than throwing an exception.
     */
    @Test
    public void computeFlexibleRemaining_overspend_returnsNegativeValue() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(
            3500.00, "Entertainment", LocalDate.now(), "", "t1"));

        double result = calculator.computeFlexibleRemaining(
            profile, transactions);
        assertEquals(-500.00, result, 0.001);
    }
}