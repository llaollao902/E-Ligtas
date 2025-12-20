package controller;

import view.DashboardView;

import view.LoginFrame;
import view.SignUpFrame;
import model.UserManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class LoginController {
    private LoginFrame view;
    private UserManager model;

    public LoginController(LoginFrame view, UserManager model) {
        this.view = view;
        this.model = model;

        // 1. Handle Login Logic
        view.getLoginButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String u = view.getUsername();
                String p = view.getPassword();
                
                if (u.isEmpty() || p.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Please fill in all fields.");
                    return;
                }

                if (model.login(u, p)) {
                    JOptionPane.showMessageDialog(view, "Login Successful!");
                    view.dispose();
                    DashboardView dashboard = new DashboardView();
                    dashboard.setVisible(true);
                    new DashboardController();
                } else {
                    JOptionPane.showMessageDialog(view, "Invalid Credentials");
                }
            }
        });

        // 2. Handle Navigation to Sign Up
        view.getSignUpButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.dispose(); 
                
                // 1. Create the UI
                SignUpFrame signUpFrame = new SignUpFrame(model);
                
                // 2. ATTACH THE CONTROLLER (This makes the Sign Up buttons work!)
                new SignUpController(signUpFrame, model);
                
                // 3. Show the window
                signUpFrame.setVisible(true);
            }
        });
    }
}