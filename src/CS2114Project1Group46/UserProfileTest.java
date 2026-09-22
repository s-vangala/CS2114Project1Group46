// Project 1
//Virginia Tech Honor Code Pledge:
//
//As a Hokie, I will conduct myself with honor and integrity at all times.
//I will not lie, cheat, or steal, nor will I accept the actions of those who 
//do.
//-- Liam McKee (liamkmckee)
//LLM Statement:
//I have not used any assistance for the assignment beyond course resources and
//staff.
package CS2114Project1Group46;
import static org.junit.Assert.*;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

//-------------------------------------------------------------------------
/**
* The UserProfileTest class contains unit tests for the UserProfile
* class.
* 
* @author Liam McKee (liamkmckee)
* @version 2026.09.22
*/
public class UserProfileTest {

    private UserProfile profile;

    // ----------------------------------------------------------
    /**
     * Sets up the test fixture before each test execution.
     * Creates a new {@link UserProfile} instance and initializes it 
     * with a standard starting salary for predictable testing state.
     */
    @Before
    public void setUp() {
        profile = new UserProfile();
        profile.setSalary(3000.00);
    }

    // ----------------------------------------------------------
    /**
     * Test constructor defaults to ensure fresh state.
     */
    @Test
    public void testUserProfileConstructor() {
        UserProfile newProfile = new UserProfile();
        assertEquals(0.0, newProfile.getSalary(), 0.001);
        assertEquals(0.0, newProfile.getSurplusDeficit(), 0.001);
        assertTrue(newProfile.getFixedExpenses().isEmpty());
    }

    // ----------------------------------------------------------
    /**
     * Normal case: Valid salary should update salary successfully.
     */
    @Test
    public void testSetSalaryNormal() {
        profile.setSalary(4000.00);
        assertEquals(4000.00, profile.getSalary(), 0.001);
    }

    // ----------------------------------------------------------
    /**
     * Bad input case: Negative salary should be rejected.
     */
    @Test
    public void testSetSalaryNegative() {
        double salaryBefore = profile.getSalary();
        profile.setSalary(-500.00);
        assertEquals(salaryBefore, profile.getSalary(), 0.001);
    }

    // ----------------------------------------------------------
    /**
     * Bad input case: Double.NaN salary should be rejected.
     */
    @Test
    public void testSetSalaryNaN() {
        double salaryBefore = profile.getSalary();
        profile.setSalary(Double.NaN);
        assertEquals(salaryBefore, profile.getSalary(), 0.001);
    }

    // ----------------------------------------------------------
    /**
     * Normal case: Adding and updating valid fixed expenses.
     */
    @Test
    public void testAddFixedExpenseNormal() {
        profile.addFixedExpense("Rent", 1200.00);
        Map<String, Double> expenses = profile.getFixedExpenses();
        assertEquals(1, expenses.size());
        assertEquals(1200.00, expenses.get("Rent"), 0.001);

        // Updating existing expense key
        profile.addFixedExpense("Rent", 1250.00);
        assertEquals(1250.00, profile.getFixedExpenses().get("Rent"), 0.001);
    }

    // ----------------------------------------------------------
    /**
     * Bad input case: Null, empty, negative, and NaN expense parameters.
     */
    @Test
    public void testAddFixedExpenseBadInput() {
        profile.addFixedExpense("Rent", 1200.00);

        // Null category
        profile.addFixedExpense(null, 500.00);
        // Empty string category
        profile.addFixedExpense("", 500.00);
        // Negative amount
        profile.addFixedExpense("Utilities", -150.00);
        // NaN amount
        profile.addFixedExpense("Utilities", Double.NaN);

        Map<String, Double> expenses = profile.getFixedExpenses();
        assertEquals(1, expenses.size());
        assertTrue(expenses.containsKey("Rent"));
        assertFalse(expenses.containsKey("Utilities"));
    }

    // ----------------------------------------------------------
    /**
     * Defensive copy check: External modifications must not corrupt internal state.
     */
    @Test
    public void testGetFixedExpensesDefensiveCopy() {
        profile.addFixedExpense("Rent", 1200.00);
        Map<String, Double> copy = profile.getFixedExpenses();
        copy.put("Groceries", 300.00);

        assertFalse(profile.getFixedExpenses().containsKey("Groceries"));
        assertEquals(1, profile.getFixedExpenses().size());
    }

    // ----------------------------------------------------------
    /**
     * Normal case: Valid positive or negative balance updates surplus/deficit.
     */
    @Test
    public void testUpdateSurplusDeficitNormal() {
        profile.updateSurplusDeficit(500.00);
        assertEquals(500.00, profile.getSurplusDeficit(), 0.001);

        profile.updateSurplusDeficit(-250.00);
        assertEquals(-250.00, profile.getSurplusDeficit(), 0.001);
    }

    // ----------------------------------------------------------
    /**
     * Bad input case: Double.NaN input should be rejected.
     */
    @Test
    public void testUpdateSurplusDeficitNaN() {
        profile.updateSurplusDeficit(100.00);
        double before = profile.getSurplusDeficit();
        profile.updateSurplusDeficit(Double.NaN);
        assertEquals(before, profile.getSurplusDeficit(), 0.001);
    }
}