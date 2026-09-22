// Project 1
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Rohit Rachapudi
// LLM Statement:
// I have not used any assistance for the assignment beyond course resources and
// staff.

package CS2114Project1Group46;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * Unit and integration tests for Main class.
 * 
 * @author Rohit Rachapudi
 * @version 2026.09.22
 */
public class Main
{

    // ~ Fields ................................................................
    private UserProfile profile;
    private History history;
    private Validator validator;
    private BudgetCalculator calculator;

    // ~ Constructors ..........................................................
    /**
     * Initializes a new Main integration instance with clean model classes.
     */
    public Main()
    {
        this.profile = new UserProfile();
        this.history = new History();
        this.validator = new Validator();
        this.calculator = new BudgetCalculator();
    }


    // ~ Public Methods ........................................................
    /**
     * Entry point for running the Budget Diva program flow.
     * 
     * @param args
     *            Command line arguments
     */
    public static void main(String[] args)
    {
        Main runner = new Main();
        runner.runInteractiveConsole();
    }


    /**
     * Runs an interactive console workflow allowing the user to enter salary,
     * fixed expenses, and pending transactions dynamically.
     */
    public void runInteractiveConsole()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=========================================");
        System.out.println("      WELCOME TO BUDGET DIVA            ");
        System.out.println("=========================================");

        // 1. Interactive Setup: Profile Salary
        System.out.print("\nEnter your monthly salary ($): ");
        double salary = readDoubleInput(scanner);
        profile.setSalary(salary);

        // 2. Interactive Setup: Fixed Expenses
        System.out.print("How many fixed expenses do you have? ");
        int numExpenses = readIntInput(scanner);

        for (int i = 1; i <= numExpenses; i++)
        {
            System.out.println("\n--- Fixed Expense #" + i + " ---");
            System.out.print("Expense Name (e.g., Rent, Utilities): ");
            String expenseName = scanner.nextLine().trim();

            System.out.print("Monthly Amount ($): ");
            double expenseAmount = readDoubleInput(scanner);

            profile.addFixedExpense(expenseName, expenseAmount);
        }

        System.out.println("\n--- Initial Profile Setup Complete ---");
        System.out.println("Monthly Salary: $" + profile.getSalary());
        System.out.println("Fixed Expenses: " + profile.getFixedExpenses());

        // 3. Interactive Setup: Pending Transactions
        System.out.print("\nHow many transactions would you like to process? ");
        int numTransactions = readIntInput(scanner);

        ArrayList<Transaction> pendingTransactions = new ArrayList<>();

        for (int i = 1; i <= numTransactions; i++)
        {
            System.out.println("\n--- Transaction #" + i + " ---");
            System.out.print("Category (e.g., Groceries, Entertainment): ");
            String category = scanner.nextLine().trim();

            System.out.print("Amount ($): ");
            double amount = readDoubleInput(scanner);

            System.out.print("Note / Description: ");
            String note = scanner.nextLine().trim();

            pendingTransactions
                .add(new Transaction(amount, category, LocalDate.now(), note));
        }

        // 4. Process Transactions via Validator
        System.out.println("\n=========================================");
        System.out.println("        PROCESSING TRANSACTIONS          ");
        System.out.println("=========================================");

        for (Transaction t : pendingTransactions)
        {
            double currentFlexible = calculator
                .computeFlexibleRemaining(profile, queryLoggedTransactions());
            Validator.ValidationResult result = validator.validateTransaction(
                t,
                currentFlexible,
                queryLoggedTransactions());

            if (result.isValid())
            {
                history.addTransaction(t);
                System.out.println(
                    "[ACCEPTED] " + t.getCategory() + ": $" + t.getAmount()
                        + " (" + t.getNote() + ")");
            }
            else
            {
                System.out.println(
                    "[REJECTED] " + t.getCategory() + ": $" + t.getAmount()
                        + " -> Reason: " + result.getMessage());
            }
        }

        // 5. Compute Final Budget Metrics
        ArrayList<Transaction> logged = queryLoggedTransactions();
        double remainingSurplus =
            calculator.computeSurplusDeficit(profile, logged);
        double flexibleRemaining =
            calculator.computeFlexibleRemaining(profile, logged);

        profile.updateSurplusDeficit(remainingSurplus);

        System.out.println("\n=========================================");
        System.out.println("          FINAL BUDGET SUMMARY           ");
        System.out.println("=========================================");
        System.out
            .println("Total Accepted Transactions Logged: " + logged.size());
        System.out.println(
            "Current Surplus/Deficit: $" + profile.getSurplusDeficit());
        System.out.println("Remaining Flexible Budget: $" + flexibleRemaining);

        // 6. Output Spending Percentages relative to total Salary
        Map<String, Double> percentages =
            calculator.computeCategoryPercentage(profile, logged);
        System.out.println("\n--- Category Breakdown (% of Salary) ---");
        for (Map.Entry<String, Double> entry : percentages.entrySet())
        {
            System.out.printf("%s: %.2f%%\n", entry.getKey(), entry.getValue());
        }

        scanner.close();
    }


    /**
     * Helper to retrieve all current transactions logged in History.
     * 
     * @return ArrayList of recorded transactions
     */
    public ArrayList<Transaction> queryLoggedTransactions()
    {
        return history.queryByDateRange(LocalDate.MIN, LocalDate.MAX);
    }


    // ~ Helper Input Methods ..................................................
    /**
     * Safely reads a double value from the console to prevent
     * InputMismatchException.
     */
    private double readDoubleInput(Scanner scanner)
    {
        while (true)
        {
            try
            {
                double val = Double.parseDouble(scanner.nextLine().trim());
                return val;
            }
            catch (NumberFormatException e)
            {
                System.out
                    .print("Invalid amount. Please enter a valid number: ");
            }
        }
    }


    /**
     * Safely reads an integer value from the console to prevent
     * InputMismatchException.
     */
    private int readIntInput(Scanner scanner)
    {
        while (true)
        {
            try
            {
                int val = Integer.parseInt(scanner.nextLine().trim());
                return val;
            }
            catch (NumberFormatException e)
            {
                System.out
                    .print("Invalid integer. Please enter a valid number: ");
            }
        }
    }


    // ~ Getters for Unit Testing ..............................................
    public UserProfile getProfile()
    {
        return profile;
    }


    public History getHistory()
    {
        return history;
    }


    public BudgetCalculator getCalculator()
    {
        return calculator;
    }


    public Validator getValidator()
    {
        return validator;
    }
}
