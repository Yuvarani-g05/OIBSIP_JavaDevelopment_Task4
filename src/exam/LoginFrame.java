package exam;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton;


    LoginFrame() {

        setTitle("Online Examination System");

        setSize(400, 300);

        setDefaultCloseOperation(
            JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);


        // ==========================
        // PANEL
        // ==========================

        JPanel panel = new JPanel();

        panel.setLayout(
            new GridLayout(
                4,
                2,
                10,
                10
            )
        );


        // ==========================
        // LABELS
        // ==========================

        JLabel usernameLabel =
            new JLabel("Username:");

        JLabel passwordLabel =
            new JLabel("Password:");


        // ==========================
        // INPUT FIELDS
        // ==========================

        usernameField =
            new JTextField();

        passwordField =
            new JPasswordField();


        // ==========================
        // LOGIN BUTTON
        // ==========================

        loginButton =
            new JButton("Login");


        // ==========================
        // ADD COMPONENTS
        // ==========================

        panel.add(usernameLabel);
        panel.add(usernameField);

        panel.add(passwordLabel);
        panel.add(passwordField);

        panel.add(new JLabel());
        panel.add(loginButton);


        add(panel);


        // ==========================
        // LOGIN ACTION
        // ==========================

        loginButton.addActionListener(
            e -> login()
        );
    }


    // ==================================================
    // LOGIN METHOD
    // ==================================================

    void login() {

        String username =
            usernameField.getText();

        String password =
            new String(
                passwordField.getPassword()
            );


        // ==========================
        // CHECK LOGIN
        // ==========================

        if (
            username.equals("student")
            &&
            password.equals("1234")
        ) {

            JOptionPane.showMessageDialog(
                this,
                "Login Successful!"
            );


            // Close login window

            dispose();


            // Open profile screen

            ProfileFrame profileFrame =
                new ProfileFrame();

            profileFrame.setVisible(true);


        } else {

            JOptionPane.showMessageDialog(
                this,
                "Invalid Username or Password"
            );
        }
    }


    // ==================================================
    // MAIN METHOD
    // ==================================================

    public static void main(
        String[] args
    ) {

        LoginFrame frame =
            new LoginFrame();

        frame.setVisible(true);
    }
}