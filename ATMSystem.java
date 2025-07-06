import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public double getBalance() {
        return balance;
    }
}

public class ATMSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account = new BankAccount(5000.00); // Starting balance
        boolean running = true;

        System.out.println("🏦 Welcome to Simple ATM!");

        while (running) {
            System.out.println("\n----- ATM Menu -----");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            String input = scanner.nextLine();
            int option;

            try {
                option = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a number.");
                continue;
            }

            switch (option) {
                case 1 -> System.out.printf("✅ Current Balance: ₹%.2f%n", account.getBalance());
                case 2 -> {
                    System.out.print("Enter deposit amount: ₹");
                    double depositAmount = getValidAmount(scanner);
                    if (account.deposit(depositAmount)) {
                        System.out.printf("✅ Deposited ₹%.2f successfully.%n", depositAmount);
                    } else {
                        System.out.println("❌ Deposit failed. Amount must be positive.");
                    }
                }
                case 3 -> {
                    System.out.print("Enter withdrawal amount: ₹");
                    double withdrawalAmount = getValidAmount(scanner);
                    if (account.withdraw(withdrawalAmount)) {
                        System.out.printf("✅ Withdrawn ₹%.2f successfully.%n", withdrawalAmount);
                    } else {
                        System.out.println("❌ Withdrawal failed. Insufficient balance or invalid amount.");
                    }
                }
                case 4 -> {
                    System.out.println("👋 Thank you for using the ATM. Goodbye!");
                    running = false;
                }
                default -> System.out.println("❌ Invalid option. Please select between 1-4.");
            }
        }

        scanner.close();
    }

    // Helper method to read valid numeric amount
    private static double getValidAmount(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine();
            try {
                double amount = Double.parseDouble(input);
                if (amount > 0) {
                    return amount;
                } else {
                    System.out.print("Amount must be greater than 0. Try again: ₹");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid number format. Try again: ₹");
            }
        }
    }
}
