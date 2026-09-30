package PE;

public class SavingsAccount {
    // Shared static variable for all account holders
    private static double annualInterestRate = 0.0;
    
    // Private instance variable for individual account balance
    private double savingsBalance;

    public SavingsAccount(double savingsBalance) {
        this.savingsBalance = (savingsBalance >= 0) ? savingsBalance : 0.0;
    }

    public double getSavingsBalance() {
        return savingsBalance;
    }

    // Calculates monthly interest and adds it to the balance
    public void calculateMonthlyInterest() {
        double monthlyInterest = (savingsBalance * annualInterestRate) / 12.0;
        savingsBalance += monthlyInterest;
    }

    // Static method to update the shared interest rate
    public static void modifyInterestRate(double newRate) {
        if (newRate >= 0.0) {
            annualInterestRate = newRate;
        } else {
            System.out.println("Error: Interest rate cannot be negative.");
        }
    }
}