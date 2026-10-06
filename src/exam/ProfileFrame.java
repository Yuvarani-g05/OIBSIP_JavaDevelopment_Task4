package exam;

import javax.swing.*;
import java.awt.*;

public class ProfileFrame extends JFrame {

    JTextField nameField;
    JPasswordField passwordField;
    JButton startButton;

    ProfileFrame() {

        setTitle("Student Profile");

        setSize(400, 300);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        JPanel panel = new JPanel();

        panel.setLayout(
            new GridLayout(4, 2, 10, 10)
        );

        JLabel nameLabel =
            new JLabel("Display Name:");

        JLabel passwordLabel =
            new JLabel("New Password:");

        nameField =
            new JTextField();

        passwordField =
            new JPasswordField();

        startButton =
            new JButton("Start Exam");

        panel.add(nameLabel);
        panel.add(nameField);

        panel.add(passwordLabel);
        panel.add(passwordField);

        panel.add(new JLabel());
        panel.add(startButton);

        add(panel);

        startButton.addActionListener(
            e -> startExam()
        );
    }

    void startExam() {

        String displayName =
            nameField.getText();

        String newPassword =
            new String(
                passwordField.getPassword()
            );

        if (
            displayName.isEmpty()
            ||
            newPassword.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                this,
                "Please enter your profile details."
            );

            return;
        }

        JOptionPane.showMessageDialog(
            this,
            "Profile Updated Successfully!"
        );

        dispose();

        ExamFrame examFrame =
            new ExamFrame();

        examFrame.setVisible(true);
    }
}