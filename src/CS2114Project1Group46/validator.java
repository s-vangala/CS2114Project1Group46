package CS2114Project1Group46;

import java.util.ArrayList;

public class Validator {

    // Stores the result of checking a purchase
    public static class ValidationResult {

        private boolean valid;
        private String message;

        // Creates a validation result
        public ValidationResult(boolean valid, String message) {
            this.valid = valid;
            this.message = message;
        }

        // Returns true if the purchase is valid
        public boolean isValid() {
            return valid;
        }

        // Returns the reason for the result
        public String getMessage() {
            return message;
        }
    }

    // Checks if a purchase is valid
    public ValidationResult validateTransaction(
        Transaction purchase,
        double currentBalance,
        ArrayList<Transaction> recentPurchases) {

        // Check for a negative amount
        if (purchase.getAmount() < 0) {
            return new ValidationResult(false,"Amount cannot be negative");
        }

        // Check if the purchase is over the available balance
        if (purchase.getAmount() > currentBalance) {
            return new ValidationResult(false,"Purchase exceeds current balance");
        }

        // Check for duplicate purchases
        for (Transaction recentPurchase : recentPurchases) {
            if (recentPurchase.getAmount() == purchase.getAmount()
                && recentPurchase.getCategory().equals(purchase.getCategory())) {

                    return new ValidationResult(false,"Duplicate purchase");
            
            }
        }

        // If all checks pass, the purchase is valid
        return new ValidationResult(true, "Transaction is valid");
    }
}