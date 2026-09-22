package CS2114Project1Group46;

import java.time.LocalDate;
import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 * 
 * @author shres
 * @version Sep 21, 2026
 */
public class History
{
    // ~ Fields ................................................................
    private ArrayList<Transaction> acceptedTransactions;

    // ~ Constructors ..........................................................
    // ----------------------------------------------------------
    /**
     * Create a new History object.
     */
    public History() {
        acceptedTransactions = new ArrayList<>();
    }


    // ~Public Methods ........................................................
    /**
     * appends an accepted transaction to the history
     * @param transaction the transaction to add
     * @throws IllegalArgumentException if the transaction is null
     */
    public void addTransaction(Transaction transaction) {
        if (transaction == null) 
        {
            throw new IllegalArgumentException("Transaction cannot be null");
        }
        acceptedTransactions.add(transaction);
        
    }


    /**
     * retrieves transactions within a specific date range
     * @param start the start date of the range
     * @param end the end date of the range
     * @return transactions within the specified date range
     */
    public ArrayList<Transaction> queryByDateRange(LocalDate start, LocalDate end) 
    {
        ArrayList<Transaction> results = new ArrayList<>();
        if (start.isAfter(end))
        {
            return results; // Return empty list if start date is after end date
        }
        for (Transaction t : acceptedTransactions) {
            if ((t.getDate().isEqual(start) || t.getDate().isAfter(start)) &&
                (t.getDate().isEqual(end) || t.getDate().isBefore(end))) 
            {
                results.add(t);
            }
        }
        return results;
    }


    /**
     * Retrieves transactions by category.
     * @param category the category to filter transactions
     * @return a list of transactions in the specified category
     */
    public ArrayList<Transaction> getByCategory(String category) 
    {
        ArrayList<Transaction> results = new ArrayList<>();
        for (Transaction t : acceptedTransactions) {
            if (t.getCategory().equals(category)) {
                results.add(t);
            }
        }
        return results;
    }


    /**
     * calculates and returns the total amount of the transactions in the list
     * @param transactions
     * @return the total amount of the transactions
     */
    public double getTotal(ArrayList<Transaction> transactions) {
        double total = 0;
        for (Transaction t : transactions) {
            total += t.getAmount();
        }
        return total;
    }


//    /**
//     * Edits a transaction record in the history.
//     * 
//     * @param index
//     *            the index of the transaction to edit
//     * @param newTransaction
//     *            the new transaction data
//     * @throws IllegalArgumentException
//     *             if the newTransaction is null
//     */
//    public void editRecord(int index, Transaction newTransaction)
//    {
//        if (newTransaction == null)
//        {
//            throw new IllegalArgumentException("Transaction cannot be null");
//        }
//        acceptedTransactions.set(index, newTransaction);
//    }
}