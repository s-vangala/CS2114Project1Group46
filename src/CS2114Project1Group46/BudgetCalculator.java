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
 * The BudgetCalculator class computes the surplus or deficit, percategory
 * spending percentages, and remaining flexible budget for a given
 * UserProfile and list of Transaction records.
 * 
 * @author Liam McKee (liamkmckee)
 * @version 2026.09.22
 */
public class BudgetCalculator {
    // ----------------------------------------------------------
    /**
     * Calculates the net remaining budget: salary minus fixed expenses
     * minus the total of all logged transactions.
     * 
     * @param profile
     *            the user's salary and fixed expenses
     * @param transactions
     *            the transactions to subtract from the budget
     * @return the resulting surplus (positive) or deficit (negative)
     */
    public double computeSurplusDeficit(
        UserProfile profile,
        ArrayList<Transaction> transactions) {
        double totalFixed = 0.0;
        for (double amount : profile.getFixedExpenses().values()) {
            totalFixed += amount;
        }

        double totalSpent = 0.0;
        for (Transaction t : transactions) {
            totalSpent += t.getAmount();
        }

        return profile.getSalary() - totalFixed - totalSpent;
    }


    // ----------------------------------------------------------
    /**
     * Computes each spending category's total as a percentage of the
     * user's salary.
     * 
     * @param profile
     *            the user's salary
     * @param transactions
     *            the transactions to group and total by category
     * @return a map of category name to that category's percentage of
     *         salary
     */
    public Map<String, Double> computeCategoryPercentage(
        UserProfile profile,
        ArrayList<Transaction> transactions) {
        Map<String, Double> categoryTotals = new HashMap<>();
        for (Transaction t : transactions) {
            categoryTotals.merge(t.getCategory(), t.getAmount(), Double::sum);
        }

        Map<String, Double> percentages = new HashMap<>();
        double salary = profile.getSalary();
        for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) {
            double percentage = (salary == 0.0)
                ? 0.0
                : (entry.getValue() / salary) * 100.0;
            percentages.put(entry.getKey(), percentage);
        }

        return percentages;
    }


    // ----------------------------------------------------------
    /**
     * Computes the flexible budget remaining after discretionary
     * spending logged so far this period.
     * 
     * @param profile
     *            the user's salary and fixed expenses, used to derive
     *            the starting flexible budget
     * @param transactions
     *            the discretionary transactions logged so far
     * @return the flexible budget remaining; may be negative if the
     *         user has overspent
     */
    public double computeFlexibleRemaining(
        UserProfile profile,
        ArrayList<Transaction> transactions) {
        double totalFixed = 0.0;
        for (double amount : profile.getFixedExpenses().values()) {
            totalFixed += amount;
        }
        double flexibleBudget = profile.getSalary() - totalFixed;

        double totalSpent = 0.0;
        for (Transaction t : transactions) {
            totalSpent += t.getAmount();
        }

        return flexibleBudget - totalSpent;
    }
}