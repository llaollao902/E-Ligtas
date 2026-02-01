package controller;

import view.SignUpFrame;
import view.LoginFrame;
import model.UserManager;
import model.User;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Controller class responsible for handling user sign-up actions.
 * <p>
 * This class connects the SignUpFrame (view) with the UserManager (model)
 * and manages user registration, validation, and navigation back to
 * the login screen.
 * </p>
 */
public class SignUpController {

    /** Reference to the sign-up UI */
    private SignUpFrame view;

    /** Reference to the user management model */
    private UserManager model;

    /**
     * Constructs a SignUpController and attaches event listeners
     * to the sign-up and back buttons.
     *
     * @param view  the SignUpFrame UI
     * @param model the UserManager handling user data and validation
     */
    public SignUpController(SignUpFrame view, UserManager model) {
        this.view = view;
        this.model = model;

        // ===================== SIGN-UP BUTTON HANDLER =====================
        this.view.getSignUpButton().addMouseListener(new MouseAdapter() {

            /**
             * Triggered when the Sign-Up button is clicked.
             * Initiates the user registration process.
             *
             * @param e mouse click event
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                handleSignUp();
            }
        });

        // ===================== BACK BUTTON HANDLER =====================
        this.view.getBackButton().addMouseListener(new MouseAdapter() {

            /**
             * Triggered when the Back button is clicked.
             * Navigates the user back to the login screen.
             *
             * @param e mouse click event
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                navigateToLogin();
            }
        });
    }

    /**
     * Handles the sign-up logic.
     * <p>
     * Validates user input, checks for duplicate usernames,
     * creates a new user account, and redirects to the login screen.
     * </p>
     */
    private void handleSignUp() {
        // Retrieve user input from the view
        String first = view.getFirstName();
        String last = view.getLastName();
        String user = view.getUsername();
        String pass = view.getPassword();

        // Validate required fields
        if (first.isEmpty() || last.isEmpty() || user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(
                    view,
                    "All fields are required."
            );
            return;
        }

        // Check if username already exists
        if (model.usernameExists(user)) {
            JOptionPane.showMessageDialog(
                    view,
                    "Username already exists!"
            );
            return;
        }

        // Create new user account
        model.signup(user, pass, first, last);

        // Notify user of successful registration
        JOptionPane.showMessageDialog(
                view,
                "Account created successfully!"
        );

        // Redirect back to login screen
        navigateToLogin();
    }

    /**
     * Navigates the user back to the login screen.
     * <p>
     * Disposes the current Sign-Up window, creates a new LoginFrame,
     * attaches its controller, and displays it.
     * </p>
     */
    private void navigateToLogin() {
        // Close current Sign-Up window
        view.dispose();

        // Create Login UI
        LoginFrame loginFrame = new LoginFrame();

        // Attach Login controller (connects UI to model)
        new LoginController(loginFrame, model);

        // Display Login window
        loginFrame.setVisible(true);
    }
}
