import javax.swing.*;
import java.awt.*;

public class BankAppGUI {
    private Bank bank = new Bank();
    private CardLayout layout = new CardLayout();
    private JPanel mainPanel = new JPanel(layout);
    private JFrame frame;

    public void start() {
        frame = new JFrame("Bank Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        mainPanel.add(menuPage(), "menu");
        mainPanel.add(createAccountPage(), "create");
        mainPanel.add(depositPage(), "deposit");
        mainPanel.add(withdrawPage(), "withdraw");
        mainPanel.add(viewPage(), "view");

        frame.add(mainPanel);
        layout.show(mainPanel, "menu");
        frame.setVisible(true);
    }

    private JPanel menuPage() {
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        JButton create = new JButton("Create Account");
        JButton deposit = new JButton("Deposit");
        JButton withdraw = new JButton("Withdraw");
        JButton view = new JButton("View Account");
        JButton exit = new JButton("Exit");

        panel.add(create);
        panel.add(deposit);
        panel.add(withdraw);
        panel.add(view);
        panel.add(exit);

        create.addActionListener(e -> layout.show(mainPanel, "create"));
        deposit.addActionListener(e -> layout.show(mainPanel, "deposit"));
        withdraw.addActionListener(e -> layout.show(mainPanel, "withdraw"));
        view.addActionListener(e -> layout.show(mainPanel, "view"));
        exit.addActionListener(e -> System.exit(0));

        return panel;
    }

    private JPanel createAccountPage() {
        JPanel panel = new JPanel(new GridLayout(6, 1));
        JTextField nameField = new JTextField();
        JTextField depositField = new JTextField();
        JButton submit = new JButton("Create");
        JButton back = new JButton("Back");

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Initial Deposit:"));
        panel.add(depositField);
        panel.add(submit);
        panel.add(back);

        submit.addActionListener(e -> {
            try {
                String name = nameField.getText();
                double deposit = Double.parseDouble(depositField.getText());
                Account acc = bank.createAccount(name, deposit);
                JOptionPane.showMessageDialog(frame, "Created: " + acc);
                nameField.setText("");
                depositField.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input!");
            }
        });

        back.addActionListener(e -> layout.show(mainPanel, "menu"));
        return panel;
    }

    private JPanel depositPage() {
        JPanel panel = new JPanel(new GridLayout(6, 1));
        JTextField accNo = new JTextField();
        JTextField amount = new JTextField();
        JButton submit = new JButton("Deposit");
        JButton back = new JButton("Back");

        panel.add(new JLabel("Account No:"));
        panel.add(accNo);
        panel.add(new JLabel("Amount:"));
        panel.add(amount);
        panel.add(submit);
        panel.add(back);

        submit.addActionListener(e -> {
            try {
                Account acc = bank.getAccount(Integer.parseInt(accNo.getText()));
                if (acc != null) {
                    acc.deposit(Double.parseDouble(amount.getText()));
                    JOptionPane.showMessageDialog(frame, "New Balance: " + acc.getBalance());
                    accNo.setText("");
                    amount.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Account not found.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input!");
            }
        });

        back.addActionListener(e -> layout.show(mainPanel, "menu"));
        return panel;
    }

    private JPanel withdrawPage() {
        JPanel panel = new JPanel(new GridLayout(6, 1));
        JTextField accNo = new JTextField();
        JTextField amount = new JTextField();
        JButton submit = new JButton("Withdraw");
        JButton back = new JButton("Back");

        panel.add(new JLabel("Account No:"));
        panel.add(accNo);
        panel.add(new JLabel("Amount:"));
        panel.add(amount);
        panel.add(submit);
        panel.add(back);

        submit.addActionListener(e -> {
            try {
                Account acc = bank.getAccount(Integer.parseInt(accNo.getText()));
                if (acc != null && acc.withdraw(Double.parseDouble(amount.getText()))) {
                    JOptionPane.showMessageDialog(frame, "New Balance: " + acc.getBalance());
                    accNo.setText("");
                    amount.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Withdraw Failed!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input!");
            }
        });

        back.addActionListener(e -> layout.show(mainPanel, "menu"));
        return panel;
    }

    private JPanel viewPage() {
        JPanel panel = new JPanel(new GridLayout(4, 1));
        JTextField accNo = new JTextField();
        JButton viewBtn = new JButton("View");
        JButton back = new JButton("Back");

        panel.add(new JLabel("Account No:"));
        panel.add(accNo);
        panel.add(viewBtn);
        panel.add(back);

        viewBtn.addActionListener(e -> {
            try {
                Account acc = bank.getAccount(Integer.parseInt(accNo.getText()));
                if (acc != null) {
                    JOptionPane.showMessageDialog(frame, acc.toString());
                } else {
                    JOptionPane.showMessageDialog(frame, "Account not found.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input!");
            }
        });

        back.addActionListener(e -> layout.show(mainPanel, "menu"));
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BankAppGUI().start());
    }
}
