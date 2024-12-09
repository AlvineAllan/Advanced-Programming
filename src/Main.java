import Lecture1_adt.*; // Import all classes from Lecture1_adt package to be used in this client code

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.List;

/*
* Client Code for accessing the Lecture1_adt.TransactionInterface.java module
 */
public class Main {

    public static void testTransaction1() {
        Calendar d1 = new GregorianCalendar(); // d1 is an Object [Objects are Reference types]
        Lecture1_adt.Transaction1 t1 = new Lecture1_adt.Transaction1(1000, d1); // amount and d1 are arguments

        System.out.println(t1.toString());
        System.out.println("Lecture1_adt.TransactionInterface Amount: \t " + t1.amount);
        System.out.println("Lecture1_adt.TransactionInterface Date: \t " + t1.date);
    }

    public static Transaction2 makeNextPayment(Transaction2 t) {
        Calendar d =  t.getDate();
        d.add(Calendar.MONTH, 1);
        return new Transaction2(t.getAmount(), d);
    }

    public static void testTransaction2() {
        Calendar d1 = new GregorianCalendar();
        Lecture1_adt.Transaction2 t = new Lecture1_adt.Transaction2(1000, d1);

        Lecture1_adt.Transaction2 modified_t = makeNextPayment(t);

        System.out.println("\n\nState of the Object T1 After Client Code Tried to Change the Amount");
        System.out.println("Lecture1_adt.TransactionInterface Amount: \t "+modified_t.getAmount());
        System.out.println("Lecture1_adt.TransactionInterface Date: \t "+modified_t.getDate().getTime());

        System.out.println("\n\nHow does T2 Look Like?????");
        System.out.println("Lecture1_adt.TransactionInterface Amount: \t "+modified_t.getAmount());
        System.out.println("Lecture1_adt.TransactionInterface Date: \t "+modified_t.getDate().getTime());
    }

    public static List<Transaction3> makeYearOfPayments (int amount) throws NullPointerException {
        List<Transaction3> listOfTransaction3s = new ArrayList<Transaction3>();
        Calendar date = new GregorianCalendar(2024, Calendar.JANUARY, 3);

        for (int i = 0; i < 12; i++) {
            listOfTransaction3s.add(new Transaction3(amount, date));
            date.add(Calendar.MONTH, 1);
        }
        return listOfTransaction3s;
    }

    public static void testTransaction3() {
        List<Transaction3> allPaymentsIn2024 = makeYearOfPayments(1000);

        for (Transaction3 t3 : allPaymentsIn2024) {
            System.out.println("\n\n  ::::::::::::::::::::::::::::::::::::::::::::\n");
            System.out.println("Lecture1_adt.TransactionInterface Amount: \t "+t3.getAmount());
            System.out.println("Lecture1_adt.TransactionInterface Date: \t "+t3.getDate().getTime());
        }
    }

    public static List<Transaction4> makeYearOfPaymentsFinal (int amount) throws NullPointerException {
        List<Transaction4> listOfTransaction4s = new ArrayList<Transaction4>();
        Calendar date = new GregorianCalendar(2024, Calendar.JANUARY, 3);

        for (int i = 0; i < 12; i++) {
            listOfTransaction4s.add(new Transaction4(amount, date));
            date.add(Calendar.MONTH, 1);
        }
        return listOfTransaction4s;
    }

    public static void testTransaction4() {
        List<Transaction4> transactionsIn2024 = makeYearOfPaymentsFinal(1200);

        for (Transaction4 transact : transactionsIn2024) {
            System.out.println("\n\n  ::::::::::::::::::::::::::::::::::::::::::::\n");
            System.out.println("Lecture1_adt.TransactionInterface Amount: \t "+transact.getAmount());
            System.out.println("Lecture1_adt.TransactionInterface Date: \t "+transact.getDate().getTime());
        }
    }

    // Polymorphism test 
    public static void testPolymorphism(BankAccount account) {
        Calendar depositDate = new GregorianCalendar(2024, Calendar.FEBRUARY, 1);
        BaseTransaction deposit = new DepositTransaction(300, depositDate); // BaseTransaction reference

        Calendar withdrawalDate = new GregorianCalendar(2024, Calendar.FEBRUARY, 2);
        BaseTransaction withdrawal = new WithdrawalTransaction(1000, withdrawalDate); // BaseTransaction reference

        System.out.println("\n=== Testing Polymorphism ===");
        try {
            deposit.apply(account);
            System.out.println("Deposit applied via BaseTransaction. New balance: $" + account.getBalance());

            withdrawal.apply(account); // Should throw InsufficientFundsException
        } catch (InsufficientFundsException e) {
            System.out.println("Exception caught during polymorphic withdrawal: " + e.getMessage());
        }
    }

    // Reversal logic test
    public static void testReversalLogic(BankAccount account) {
        System.out.println("\n=== Testing Reversal Logic ===");

        Calendar withdrawalDate = new GregorianCalendar(2024, Calendar.MARCH, 1);
        WithdrawalTransaction withdrawal = new WithdrawalTransaction(200, withdrawalDate);

        try {
            withdrawal.apply(account); // Apply withdrawal
            System.out.println("Withdrawal applied. New balance: $" + account.getBalance());

            // Reversal operation
            withdrawal.reverse(account);
            System.out.println("Reversal completed. Current balance: $" + account.getBalance());
        } catch (InsufficientFundsException e) {
            System.out.println("Exception caught during withdrawal: " + e.getMessage());
        }
    }

    // Exception handling test - 
    public static void testExceptionHandling(BankAccount account) {
        System.out.println("\n=== Testing Exception Handling ===");

        Calendar withdrawalDate = new GregorianCalendar(2024, Calendar.APRIL, 1);
        WithdrawalTransaction withdrawal = new WithdrawalTransaction(1000, withdrawalDate);

        try {
            withdrawal.apply(account); // Should throw InsufficientFundsException
        } catch (InsufficientFundsException e) {
            System.out.println("Exception caught: " + e.getMessage());
        } finally {
            System.out.println("Final account balance: $" + account.getBalance());
        }
    }

    public static void main(String[] args) {
        // Create a BankAccount with an initial balance
        BankAccount account = new BankAccount(500);

        // Uncomment the following lines to test each component

        // testTransaction1()
        // testTransaction2()
        // testTransaction3()
        // testTransaction4()

        // Test Polymorphism
        testPolymorphism(account);

        // Test Reversal Logic
        testReversalLogic(account);

        // Test Exception Handling
        testExceptionHandling(account);
    }
}
