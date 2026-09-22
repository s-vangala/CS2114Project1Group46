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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// -------------------------------------------------------------------------
/**
 * Tests for the BudgetCalculator class, covering every method and both
 * sides of every branch.
 *
 * @author Liam McKee (liamkmckee)
 * @version 2026.09.22
 */
public class BudgetCalculatorTest
    extends student.TestCase {

    private static final double DELTA = 0.001;

    private BudgetCalculator calculator;
    private ArrayList<Transaction> transactions;

    // ----------------------------------------------------------
    /**
     * Creates a fresh calculator and empty transaction list before
     * each test.
     */
    public void setUp() {
        calculator = new BudgetCalculator();
        transactions = new ArrayList<>();
    }


    // ----------------------------------------------------------
    /**
     * Builds a UserProfile with the given salary and fixed expenses.
     * ADJUST THIS if your UserProfile constructor is different.
     *
     * @param salary
     *            the user's salary
     * @param fixed
     *            the user's fixed expenses
     * @return the new profile
     */
    private UserProfile makeProfile(
        double salary,
        Map<String, Double> fixed) {
        return new UserProfile(salary, fixed);
    }


    // ----------------------------------------------------------
    /**
     * Builds a Transaction with the given category and amount.
     * ADJUST THIS if your Transaction constructor is different.
     *
     * @param category
     *            the spending category
     * @param amount
     *            the amount spent
     * @return the new transaction
     */
    private Transaction makeTransaction(String category, double amount) {
        return new Transaction(category, amount);
    }


    // ----------------------------------------------------------
    /**
     * Builds a fixed-expense map with rent 1000 and utilities 200.
     *
     * @return the map
     */
    private Map<String, Double> standardFixed() {
        Map<String, Double> fixed = new HashMap<>();
        fixed.put("Rent", 1000.0);
        fixed.put("Utilities", 200.0);
        return fixed;
    }


    // ----------------------------------------------------------
    /**
     * Tests computeSurplusDeficit with fixed expenses and transactions,
     * giving a surplus.
     */
    public void testComputeSurplusDeficit() {
        UserProfile profile = makeProfile(3000.0, standardFixed());
        transactions.add(makeTransaction("Food", 50.0));
        transactions.add(makeTransaction("Fun", 100.0));

        // 3000 - 1200 - 150
        assertEquals(1650.0,
            calculator.computeSurplusDeficit(profile, transactions), DELTA);
    }


    // ----------------------------------------------------------
    /**
     * Tests computeSurplusDeficit when spending exceeds the budget,
     * giving a deficit.
     */
    public void testComputeSurplusDeficitNegative() {
        UserProfile profile = makeProfile(1000.0, standardFixed());
        transactions.add(makeTransaction("Food", 300.0));

        // 1000 - 1200 - 300
        assertEquals(-500.0,
            calculator.computeSurplusDeficit(profile, transactions), DELTA);
    }


    // ----------------------------------------------------------
    /**
     * Tests computeSurplusDeficit with no fixed expenses and no
     * transactions.
     */
    public void testComputeSurplusDeficitEmpty() {
        UserProfile profile = makeProfile(2000.0, new HashMap<>());

        assertEquals(2000.0,
            calculator.computeSurplusDeficit(profile, transactions), DELTA);
    }


    // ----------------------------------------------------------
    /**
     * Tests computeCategoryPercentage with a nonzero salary, including
     * two transactions in the same category that must be summed.
     */
    public void testComputeCategoryPercentage() {
        UserProfile profile = makeProfile(1000.0, standardFixed());
        transactions.add(makeTransaction("Food", 50.0));
        transactions.add(makeTransaction("Food", 25.0));
        transactions.add(makeTransaction("Gas", 100.0));

        Map<String, Double> result =
            calculator.computeCategoryPercentage(profile, transactions);

        assertEquals(2, result.size());
        assertEquals(7.5, result.get("Food"), DELTA);
        assertEquals(10.0, result.get("Gas"), DELTA);
    }


    // ----------------------------------------------------------
    /**
     * Tests computeCategoryPercentage when salary is zero, which
     * should give 0 percent instead of dividing by zero.
     */
    public void testComputeCategoryPercentageZeroSalary() {
        UserProfile profile = makeProfile(0.0, new HashMap<>());
        transactions.add(makeTransaction("Food", 50.0));

        Map<String, Double> result =
            calculator.computeCategoryPercentage(profile, transactions);

        assertEquals(1, result.size());
        assertEquals(0.0, result.get("Food"), DELTA);
    }


    // ----------------------------------------------------------
    /**
     * Tests computeCategoryPercentage with no transactions.
     */
    public void testComputeCategoryPercentageEmpty() {
        UserProfile profile = makeProfile(1000.0, standardFixed());

        Map<String, Double> result =
            calculator.computeCategoryPercentage(profile, transactions);

        assertTrue(result.isEmpty());
    }


    // ----------------------------------------------------------
    /**
     * Tests computeFlexibleRemaining with money left over.
     */
    public void testComputeFlexibleRemaining() {
        UserProfile profile = makeProfile(3000.0, standardFixed());
        transactions.add(makeTransaction("Food", 50.0));
        transactions.add(makeTransaction("Fun", 100.0));

        // (3000 - 1200) - 150
        assertEquals(1650.0,
            calculator.computeFlexibleRemaining(profile, transactions),
            DELTA);
    }


    // ----------------------------------------------------------
    /**
     * Tests computeFlexibleRemaining when the user has overspent.
     */
    public void testComputeFlexibleRemainingOverspent() {
        Map<String, Double> fixed = new HashMap<>();
        fixed.put("Rent", 900.0);
        UserProfile profile = makeProfile(1000.0, fixed);
        transactions.add(makeTransaction("Fun", 200.0));

        // (1000 - 900) - 200
        assertEquals(-100.0,
            calculator.computeFlexibleRemaining(profile, transactions),
            DELTA);
    }
}