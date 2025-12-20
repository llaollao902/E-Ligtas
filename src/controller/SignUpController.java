package controller;

import view.SignUpFrame;
import view.LoginFrame;
import model.UserManager;
import model.User;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SignUpController {
    private SignUpFrame view;
    private UserManager model;

    public SignUpController(SignUpFrame view, UserManager model) {
        this.view = view;
        this.model = model;

        // Attach listeners
        this.view.getSignUpButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleSignUp();
            }
        });

        this.view.getBackButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                navigateToLogin();
            }
        });
    }

    private void handleSignUp() {
        String first = view.getFirstName();
        String last = view.getLastName();
        String user = view.getUsername();
        String pass = view.getPassword();

        if (first.isEmpty() || last.isEmpty() || user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(view, "All fields are required.");
            return;
        }

        if (model.usernameExists(user)) {
            JOptionPane.showMessageDialog(view, "Username already exists!");
            return;
        }

        model.signup(user, pass, first, last);
        JOptionPane.showMessageDialog(view, "Account created successfully!");
        navigateToLogin();
    }

    // HELPER METHOD to ensure Controller is always attached
    private void navigateToLogin() {
        view.dispose(); // Close current Sign Up window
        
        LoginFrame loginFrame = new LoginFrame(); // Create the UI
        new LoginController(loginFrame, model);   // ATTACH THE BRAIN (The Controller)
        loginFrame.setVisible(true);              // Show the window
    }
}