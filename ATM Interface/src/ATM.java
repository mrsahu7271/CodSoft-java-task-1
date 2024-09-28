import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATM extends JFrame {
    private BankAccount account;
    private JLabel balanceLabel;
    private JTextField inputField;

    public ATM(BankAccount account) {
        this.account = account;

        // Setting up the GUI
        setTitle("ATM Machine");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        // Balance Display
        balanceLabel = new JLabel("Current Balance: $" + account.getBalance());
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(balanceLabel);

        // Input field
        inputField = new JTextField();
        add(inputField);

        // Withdraw Button
        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });
        add(withdrawButton);

        // Deposit Button
        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });
        add(depositButton);

        // Check Balance Button
        JButton checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });
        add(checkBalanceButton);
    }

    // Withdraw method
    private void withdraw() {
        try {
            double amount = Double.parseDouble(inputField.getText());
            if (account.withdraw(amount)) {
                JOptionPane.showMessageDialog(this, "Withdrawal successful. Amount: $" + amount);
            } else {
                JOptionPane.showMessageDialog(this, "Insufficient balance.");
            }
            updateBalance();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid amount.");
        }
        inputField.setText("");
    }

    // Deposit method
    private void deposit() {
        try {
            double amount = Double.parseDouble(inputField.getText());
            if (amount > 0) {
                account.deposit(amount);
                JOptionPane.showMessageDialog(this, "Deposit successful. Amount: $" + amount);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid deposit amount.");
            }
            updateBalance();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid amount.");
        }
        inputField.setText("");
    }

    // Check balance method
    private void checkBalance() {
        JOptionPane.showMessageDialog(this, "Current Balance: $" + account.getBalance());
    }

    // Update the balance label
    private void updateBalance() {
        balanceLabel.setText("Current Balance: $" + account.getBalance());
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(500.0); // Initial balance
        ATM atm = new ATM(account);
        atm.setVisible(true);
    }
}
