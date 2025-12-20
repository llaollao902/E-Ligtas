package controller;

import view.HomeScreen;
import view.LoginFrame;
import view.SignUpFrame;
import model.UserManager;

public class HomeController {
    private HomeScreen view;
    private UserManager model;

    public HomeController(HomeScreen view, UserManager model) {
        this.view = view;
        this.model = model;

        // 1. Handle Login Button Click
        view.getLoginButton().addActionListener(e -> {
            view.dispose(); // Close Home
            LoginFrame loginFrame = new LoginFrame();
            new LoginController(loginFrame, model); // Attach Login Controller
            loginFrame.setVisible(true);
        });

        // 2. Handle Sign Up Button Click
        view.getSignUpButton().addActionListener(e -> {
            view.dispose(); // Close Home
            SignUpFrame signUpFrame = new SignUpFrame(model);
            new SignUpController(signUpFrame, model); // Attach SignUp Controller
            signUpFrame.setVisible(true);
        });
    }
}