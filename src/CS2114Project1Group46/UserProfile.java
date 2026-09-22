//Project 1
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
import java.util.HashMap;
import java.util.Map;

//-------------------------------------------------------------------------
/**
* The UserProfile class holds a user's monthly salary, fixed (recurring)
* expenses, and the current surplus or deficit against that salary.
* 
* @author Liam McKee (liamkmckee)
* @version 2026.09.22
*/
public class UserProfile {
 // Current monthly salary
 private double salary;

 /**
  * This map holds the user's fixed expenses, keyed by category name
  */
 private Map<String, Double> fixedExpenses;

 // Current surplus (positive) or deficit (negative)
 private double surplusOrDeficit;

 // ----------------------------------------------------------
 /**
  * Creates an empty UserProfile with no salary, no fixed expenses,
  * and a surplus/deficit of zero.
  */
 public UserProfile() {
     salary = 0.0;
     fixedExpenses = new HashMap<>();
     surplusOrDeficit = 0.0;
 }


 // ----------------------------------------------------------
 /**
  * Updates the user's monthly income.
  * 
  * @param salary
  *            the new monthly salary
  * @precondition salary is non negative
  * @postcondition salary is unchanged if the precondition is violated
  */
 public void setSalary(double salary) {
     if (salary < 0 || Double.isNaN(salary)) {
         return;
     }
     this.salary = salary;
 }


 // ----------------------------------------------------------
 /**
  * A basic getter method for the private salary variable.
  * 
  * @return the stored salary
  */
 public double getSalary() {
     return salary;
 }


 // ----------------------------------------------------------
 /**
  * Adds a new fixed expense category or updates the amount of an
  * existing one.
  * 
  * @param category
  *            the name of the fixed expense category
  * @param amount
  *            the monthly amount for that category
  * @precondition category is not null or empty, amount is non negative
  * @postcondition fixedExpenses is unchanged if the precondition is
  *                violated
  */
 public void addFixedExpense(String category, double amount) {
     if (category == null || category.isEmpty() || amount < 0
         || Double.isNaN(amount)) {
         return;
     }
     fixedExpenses.put(category, amount);
 }


 // ----------------------------------------------------------
 /**
  * Creates a copy of the user's fixed expenses.
  * 
  * @return a map of category name to monthly amount
  */
 public Map<String, Double> getFixedExpenses() {
     return new HashMap<>(fixedExpenses);
 }


 // ----------------------------------------------------------
 /**
  * Sets the current surplus or deficit value.
  * 
  * @param newBalance
  *            the new surplusor deficit value
  * @precondition newBalance is not NaN
  * @postcondition surplusOrDeficit is unchanged if the precondition is
  *                violated
  */
 public void updateSurplusDeficit(double newBalance) {
     if (Double.isNaN(newBalance)) {
         // NaN represents a corrupted upstream calculation
         return;
     }
     surplusOrDeficit = newBalance;
 }


 // ----------------------------------------------------------
 /**
  * A basic getter method for the private surplusOrDeficit variable.
  * 
  * @return a positive surplus or a negative deficit
  */
 public double getSurplusDeficit() {
     return surplusOrDeficit;
 }
}
