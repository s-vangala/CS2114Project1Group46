package CS2114Project1Group46;

import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 * Validates transactions before they are added to the budget history.
 * Checks for invalid amounts, overspending, and duplicate purchases.
 *
 * @author shres
 * @version Sep 22, 2026
 */
public class Validator
{
    // ~ Fields ................................................................
    

    // ~ Constructors ..........................................................
    

    // ~Public Methods ........................................................
    /**
     * Checks whether a transaction is valid.
     *
     * @param purchase the transaction being checked
     * @param currentBalance the amount of money currently available
     * @param recentPurchases the list of recent transactions
     * @return a ValidationResult containing whether the transaction is valid
     *         and a message explaining the result
     */
    public ValidationResult validateTransaction(
        Transaction purchase,
        double currentBalance,
        ArrayList<Transaction> recentPurchases)
    {
        // Check for a negative amount
        if (purchase.getAmount() < 0)
        {
            return new ValidationResult(
                false,
                "Amount cannot be negative");
        }

        // Check if the purchase is over the available balance
        if (purchase.getAmount() > currentBalance)
        {
            return new ValidationResult(
                false,
                "Purchase exceeds current balance");
        }

        // Check for duplicate purchases
        for (Transaction recentPurchase : recentPurchases)
        {
            if (recentPurchase.getAmount() == purchase.getAmount()
                && recentPurchase.getCategory().equals(
                    purchase.getCategory()))
            {
                return new ValidationResult(
                    false,
                    "Duplicate purchase");
            }
        }

        // If all checks pass, the purchase is valid
        return new ValidationResult(
            true,
            "Transaction is valid");
    }


    // ~ Inner Classes ........................................................
    /**
     * Stores the result of a transaction validation.
     */
    public static class ValidationResult
    {
        // ~ Fields ............................................................
        private boolean valid;
        private String message;


        // ~ Constructors ......................................................
        /**
         * Creates a validation result.
         *
         * @param valid whether the transaction is valid
         * @param message explanation of the validation result
         */
        public ValidationResult(boolean valid, String message)
        {
            this.valid = valid;
            this.message = message;
        }


        // ~Public Methods ....................................................
        /**
         * Returns whether the transaction is valid.
         *
         * @return true if the transaction is valid
         */
        public boolean isValid()
        {
            return valid;
        }


        /**
         * Returns the message explaining the validation result.
         *
         * @return the validation message
         */
        public String getMessage()
        {
            return message;
        }
    }
}