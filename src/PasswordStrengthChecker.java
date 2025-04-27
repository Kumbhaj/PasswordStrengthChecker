import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PasswordStrengthChecker extends JFrame implements ActionListener {
    private JPasswordField passwordField;
    private JButton checkButton;
    private JTextArea feedbackArea;

    public PasswordStrengthChecker() {
        setTitle("Password Strength Checker");
        setSize(450, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Enter Password:"));
        passwordField = new JPasswordField(20);
        inputPanel.add(passwordField);
        checkButton = new JButton("Check Strength");
        checkButton.addActionListener(this);
        inputPanel.add(checkButton);
        add(inputPanel, BorderLayout.NORTH);

        feedbackArea = new JTextArea();
        feedbackArea.setEditable(false);
        feedbackArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(feedbackArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String password = new String(passwordField.getPassword());
        feedbackArea.setText(analyzePassword(password));
    }

    private String analyzePassword(String password) {
        StringBuilder feedback = new StringBuilder();
        int score = 0;

        feedback.append("Analyzing password: ").append(password).append("\n\n");

        if (password.length() >= 8) {
            feedback.append("✔ Length is 8 or more characters\n");
            score++;
        } else {
            feedback.append("✘ Length is less than 8 characters\n");
        }

        if (password.matches(".*[A-Z].*")) {
            feedback.append("✔ Contains uppercase letter(s)\n");
            score++;
        } else {
            feedback.append("✘ Missing uppercase letter(s)\n");
        }

        if (password.matches(".*[a-z].*")) {
            feedback.append("✔ Contains lowercase letter(s)\n");
            score++;
        } else {
            feedback.append("✘ Missing lowercase letter(s)\n");
        }

        if (password.matches(".*\\d.*")) {
            feedback.append("✔ Contains digit(s)\n");
            score++;
        } else {
            feedback.append("✘ Missing digit(s)\n");
        }

        if (password.matches(".*[!@#$%^&*()_+=|<>?{}\\[\\]~-].*")) {
            feedback.append("✔ Contains special character(s)\n");
            score++;
        } else {
            feedback.append("✘ Missing special character(s)\n");
        }

        feedback.append("\nPassword Strength: ");

        switch (score) {
            case 5 -> feedback.append("Very Strong 🔐");
            case 4 -> feedback.append("Strong ✅");
            case 3 -> feedback.append("Moderate ⚠️");
            case 2 -> feedback.append("Weak ❌");
            default -> feedback.append("Very Weak 🔓");
        }

        return feedback.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PasswordStrengthChecker checker = new PasswordStrengthChecker();
            checker.setVisible(true);
        });
    }
}
