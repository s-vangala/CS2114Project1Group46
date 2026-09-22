package CS2114Project1Group46;

import java.time.LocalDate;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 * 
 * @author Shresta Vangala (svangala)
 * @version 2026.09.21
 */
public class Transaction
{

    // ~ Fields ................................................................
    private double amount;
    private String category;
    private LocalDate date;
    private String note;

    // ~ Constructors ..........................................................
    // ----------------------------------------------------------
    /**
     * Create a new Transaction object.
     * 
     * @param amount
     * @param category
     * @param date
     * @param note
     */
    public Transaction(
        double amount,
        String category,
        LocalDate date,
        String note)
    {
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.note = note;
    }


    // ~Public Methods ........................................................
    /**
     * returns the amount of the transaction
     * 
     * @return the amount of the transaction
     */
    public double getAmount()
    {
        return amount;
    }


    /**
     * returns the category of the transaction
     * 
     * @return the category of the transaction
     */
    public String getCategory()
    {
        return category;
    }


    /**
     * returns the date of the transaction
     * 
     * @return the date of the transaction
     */
    public LocalDate getDate()
    {
        return date;
    }


    /**
     * returns the note of the transaction
     * 
     * @return the note of the transaction
     */
    public String getNote()
    {
        return note;
    }
}
