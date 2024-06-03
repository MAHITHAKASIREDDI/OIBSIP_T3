import java.util.Scanner;

class BankAccount {
    String name, userName, password, accountNO;
    private float balance = 900000f;
    private int transactions = 0;
    private String transactionHistory = "";

    Scanner sc = new Scanner(System.in);

    public void register() {
        System.out.print("Enter your name: ");
        this.name = sc.nextLine();
        System.out.print("Enter your username: ");
        this.userName = sc.nextLine();
        System.out.print("Enter your password: ");
        this.password = sc.nextLine();
        System.out.print("Enter your account number: ");
        this.accountNO = sc.nextLine();
        System.out.println("\nRegistration successful! Please log in to your bank account.");
    }

    public boolean login() {
        boolean isLogin = false;
        while (!isLogin) {
            System.out.print("Enter your username: ");
            String inputUserName = sc.nextLine();
            if (inputUserName.equals(userName)) {
                while (!isLogin) {
                    System.out.print("Enter your password: ");
                    String inputPassword = sc.nextLine();
                    if (inputPassword.equals(password)) {
                        System.out.println("Login successful.");
                        isLogin = true;
                    } else {
                        System.out.println("Incorrect password. Please try again.");
                    }
                }
            } else {
                System.out.println("Username not found. Please try again.");
            }
        }
        return isLogin;
    }

    public void withdraw() {
        System.out.print("Enter the amount to withdraw: ");
        float amount = sc.nextFloat();
        if (balance >= amount) {
            transactions++;
            balance -= amount;
            System.out.println("Withdrawal successful.");
            transactionHistory += amount + " Rs withdrawn\n";
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void deposit() {
        System.out.print("Enter the amount to deposit: ");
        float amount = sc.nextFloat();
        if (amount <= 900000f) {
            transactions++;
            balance += amount;
            System.out.println("Deposit successful.");
            transactionHistory += amount + " Rs deposited\n";
        } else {
            System.out.println("Sorry, you have crossed the limit. The limit is 900000.");
        }
    }

    public void transfer() {
        sc.nextLine(); // Consume newline left-over
        System.out.print("Enter recipient's name: ");
        String recipient = sc.nextLine();
        System.out.print("Enter the amount to transfer: ");
        float amount = sc.nextFloat();
        if (balance >= amount) {
            if (amount <= 50000f) {
                transactions++;
                balance -= amount;
                System.out.println("Successfully transferred to " + recipient);
                transactionHistory += amount + " Rs transferred to " + recipient + "\n";
            } else {
                System.out.println("Sorry, you have crossed the limit. The limit is 50000.");
            }
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void checkBalance() {
        System.out.println("Your balance amount is: " + balance + " Rs");
    }

    public void transactionHistory() {
        if (transactions == 0) {
            System.out.println("No transactions happened.");
        } else {
            System.out.println("\nTransaction History:");
            System.out.println(transactionHistory);
        }
    }
}

public class ATMINTERFACE {
    private static int getIntegerInput(int limit) {
        Scanner sc = new Scanner(System.in);
        int input = 0;
        boolean isValid = false;
        while (!isValid) {
            try {
                input = sc.nextInt();
                if (input >= 1 && input <= limit) {
                    isValid = true;
                } else {
                    System.out.println("Choose a number between 1 and " + limit + ": ");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter an integer value.");
                sc.next(); // clear the invalid input
            }
        }
        return input;
    }

    public static void main(String[] args) {
        System.out.println("\n*Welcome to ATM Interface*");
        System.out.println("1. Register\n2. Exit");
        System.out.print("Choose one option from above: ");
        int choice = getIntegerInput(2);

        if (choice == 1) {
            BankAccount account = new BankAccount();
            account.register();
            while (true) {
                System.out.println("\n1. Login\n2. Exit");
                System.out.print("Enter your choice: ");
                int loginChoice = getIntegerInput(2);
                if (loginChoice == 1) {
                    if (account.login()) {
                        System.out.println("\n*Welcome back, " + account.name + "!*");
                        boolean session = true;
                        while (session) {
                            System.out.println("\n1. Withdraw\n2. Deposit\n3. Transfer\n4. Check Balance\n5. Transaction History\n6. Exit");
                            System.out.print("Enter your choice: ");
                            int action = getIntegerInput(6);
                            switch (action) {
                                case 1:
                                    account.withdraw();
                                    break;
                                case 2:
                                    account.deposit();
                                    break;
                                case 3:
                                    account.transfer();
                                    break;
                                case 4:
                                    account.checkBalance();
                                    break;
                                case 5:
                                    account.transactionHistory();
                                    break;
                                case 6:
                                    session = false;
                                    break;
                            }
                        }
                    }
                } else {
                    System.exit(0);
                }
            }
        } else {
            System.exit(0);
        }
    }
}