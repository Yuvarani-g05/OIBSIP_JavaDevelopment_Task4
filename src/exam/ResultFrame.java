package exam;

import javax.swing.*;
import java.awt.*;

public class ResultFrame extends JFrame {

    int score;
    int totalQuestions;
    int timeTaken;

    JLabel scoreLabel;
    JLabel timeLabel;
    JLabel correctLabel;
    JLabel incorrectLabel;

    JButton logoutButton;

    ResultFrame(int score, int totalQuestions, int timeTaken) {

        this.score = score;
        this.totalQuestions = totalQuestions;
        this.timeTaken = timeTaken;

        setTitle("Exam Result");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 10, 10));

        JLabel titleLabel = new JLabel(
            "EXAM RESULT",
            SwingConstants.CENTER
        );

        titleLabel.setFont(
            new Font("Arial", Font.BOLD, 24)
        );

        scoreLabel = new JLabel(
            "Score: " + score + " / " + totalQuestions,
            SwingConstants.CENTER
        );

        int minutes = timeTaken / 60;
        int seconds = timeTaken % 60;

        timeLabel = new JLabel(
            String.format(
                "Time Taken: %02d:%02d",
                minutes,
                seconds
            ),
            SwingConstants.CENTER
        );

        correctLabel = new JLabel(
            "Correct Answers: " + score,
            SwingConstants.CENTER
        );

        int incorrect = totalQuestions - score;

        incorrectLabel = new JLabel(
            "Incorrect Answers: " + incorrect,
            SwingConstants.CENTER
        );

        logoutButton = new JButton("Logout");

        panel.add(titleLabel);
        panel.add(scoreLabel);
        panel.add(timeLabel);
        panel.add(correctLabel);
        panel.add(incorrectLabel);
        panel.add(logoutButton);

        add(panel);

        logoutButton.addActionListener(e -> logout());
    }

    void logout() {

        dispose();

        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
    }
}