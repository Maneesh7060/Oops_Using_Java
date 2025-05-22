import java.util.ArrayList;
import java.util.Scanner;
public class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    public BankAccount(String accountNumber, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = 0.0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited ₹" + amount + ". New balance: ₹" + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew ₹" + amount + ". New balance: ₹" + balance);
        } else {
            System.out.println("Invalid or insufficient funds for withdrawal.");
        }
    }

    public void displayDetails() {
        System.out.println("\n--- Account Details ---");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: ₹" + balance);
    }
}


 class BankManagement {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<BankAccount> accounts = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Bank Management System =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Display Account Details");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();  // consume newline

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> depositMoney();
                case 3 -> withdrawMoney();
                case 4 -> displayAccount();
                case 5 -> {
                    System.out.println("Thank you for using the Bank Management System!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    static void createAccount() {
        System.out.print("Enter Account Number: ");
        String accNo = sc.nextLine();
        System.out.print("Enter Account Holder Name: ");
        String name = sc.nextLine();
        BankAccount account = new BankAccount(accNo, name);
        accounts.add(account);
        System.out.println("Account created successfully.");
    }

    static BankAccount findAccount(String accNo) {
        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber().equals(accNo)) {
                return acc;
            }
        }
        return null;
    }

    static void depositMoney() {
        System.out.print("Enter Account Number: ");
        String accNo = sc.nextLine();
        BankAccount account = findAccount(accNo);
        if (account != null) {
            System.out.print("Enter amount to deposit: ");
            double amount = sc.nextDouble();
            sc.nextLine();  // consume newline
            account.deposit(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    static void withdrawMoney() {
        System.out.print("Enter Account Number: ");
        String accNo = sc.nextLine();
        BankAccount account = findAccount(accNo);
        if (account != null) {
            System.out.print("Enter amount to withdraw: ");
            double amount = sc.nextDouble();
            sc.nextLine();  // consume newline
            account.withdraw(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    static void displayAccount() {
        System.out.print("Enter Account Number: ");
        String accNo = sc.nextLine();
        BankAccount account = findAccount(accNo);
        if (account != null) {
            account.displayDetails();
        } else {
            System.out.println("Account not found.");
        }
    }
}
