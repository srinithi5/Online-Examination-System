import javax.swing.*;
import java.awt.*;

public class OnlineExamSwing {

    static JFrame frame;
    static String username = "student";
    static String password = "1234";
    static int score;
    static Timer timer;
    static int timeLeft;

    public static void main(String[] args) {
        frame = new JFrame("Online Examination System");
        frame.setSize(450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        showLogin();
        frame.setVisible(true);
    }

    static void showLogin() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();
        JButton loginBtn = new JButton("Login");

        panel.add(new JLabel("Username:"));
        panel.add(userField);
        panel.add(new JLabel("Password:"));
        panel.add(passField);
        panel.add(new JLabel(""));
        panel.add(loginBtn);

        loginBtn.addActionListener(e -> {
            if (userField.getText().equals(username)
                    && String.valueOf(passField.getPassword()).equals(password)) {
                showMenu();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid login details");
            }
        });

        frame.setContentPane(panel);
        frame.revalidate();
    }

    static void showMenu() {
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JButton updateBtn = new JButton("Update Profile");
        JButton examBtn = new JButton("Start Exam");
        JButton logoutBtn = new JButton("Logout");

        panel.add(updateBtn);
        panel.add(examBtn);
        panel.add(logoutBtn);

        updateBtn.addActionListener(e -> updateProfile());
        examBtn.addActionListener(e -> startExam());
        logoutBtn.addActionListener(e -> showLogin());

        frame.setContentPane(panel);
        frame.revalidate();
    }

    static void updateProfile() {
        JTextField newUser = new JTextField();
        JPasswordField newPass = new JPasswordField();

        Object[] fields = {
                "New Username:", newUser,
                "New Password:", newPass
        };

        int option = JOptionPane.showConfirmDialog(frame, fields,
                "Update Profile", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            username = newUser.getText();
            password = String.valueOf(newPass.getPassword());
            JOptionPane.showMessageDialog(frame, "Profile Updated Successfully");
        }
    }

    static void startExam() {
        score = 0;
        timeLeft = 20;

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel timerLabel = new JLabel("Time Left: 20 seconds");
        JLabel question = new JLabel("Java is a ______ language.");

        JRadioButton op1 = new JRadioButton("High-level");
        JRadioButton op2 = new JRadioButton("Low-level");
        JRadioButton op3 = new JRadioButton("Machine-level");

        ButtonGroup group = new ButtonGroup();
        group.add(op1);
        group.add(op2);
        group.add(op3);

        JPanel options = new JPanel(new GridLayout(3, 1));
        options.add(op1);
        options.add(op2);
        options.add(op3);

        JButton submitBtn = new JButton("Submit");

        panel.add(timerLabel, BorderLayout.NORTH);
        panel.add(question, BorderLayout.CENTER);
        panel.add(options, BorderLayout.WEST);
        panel.add(submitBtn, BorderLayout.SOUTH);

        timer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time Left: " + timeLeft + " seconds");
            if (timeLeft == 0) {
                timer.stop();
                autoSubmit(op1);
            }
        });
        timer.start();

        submitBtn.addActionListener(e -> {
            timer.stop();
            autoSubmit(op1);
        });

        frame.setContentPane(panel);
        frame.revalidate();
    }


    static void autoSubmit(JRadioButton correctOption) {
        if (correctOption.isSelected()) {
            score++;
        }
        JOptionPane.showMessageDialog(frame,
                "Exam Submitted\nYour Score: " + score);
        showMenu();
    }
}
