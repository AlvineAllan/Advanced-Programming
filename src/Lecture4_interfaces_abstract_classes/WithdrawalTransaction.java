package Lecture4_interfaces_abstract_classes;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

public class WithdrawalTransaction extends BaseTransaction {

    public WithdrawalTransaction(int amount, @NotNull Calendar date) {
        super(amount, date);
    }

    /**
     * Checks if the transaction amount is valid (greater than 0).
     * @param amt the amount to check
     * @return true if the amount is valid, false if negative
     */
    private boolean checkTransactionAmount(int amt) {
        return amt >= 0;
    }

    /**
     * Reverses the withdrawal transaction (adds the amount back to the account).
     * @return true if the reversal was successful
     */
    public boolean reverse(BankAccount ba) {
        ba.setBalance(ba.getBalance() + getAmount()); // Re-credit the amount
        return true; // Assuming reversal is always successful
    }

    /**
     * Prints the transaction details for the withdrawal.
     */
    public void printTransactionDetails() {
        System.out.println("Withdrawal Transaction: " + this.toString());
    }

    /**
     * Applies the withdrawal to the bank account by decreasing the balance.
     * Throws InsufficientFundsException if the balance is insufficient.
     * @param ba the BankAccount to apply the transaction to
     * @throws InsufficientFundsException if the account balance is insufficient
     */
    @Override
    public void apply(BankAccount ba) throws InsufficientFundsException {
        double curr_balance = ba.getBalance();
        if (curr_balance < getAmount()) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal of " + getAmount());
        }
        ba.setBalance(curr_balance - getAmount());
    }
}