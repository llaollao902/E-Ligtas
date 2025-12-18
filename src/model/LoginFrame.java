package model;

import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import static javax.swing.GroupLayout.Alignment.*;
import static javax.swing.LayoutStyle.ComponentPlacement.*;

public class LoginFrame extends javax.swing.JFrame {

    public LoginFrame() {
        initComponents();
        setupActions();
        setupLeftPanelLayout();
        setupLayeredPaneLayout();
        setupMainLayout();
    }

    private void initComponents() {
        // --- Component Declaration ---
        leftPanel = new javax.swing.JPanel();
        layeredPane = new javax.swing.JLayeredPane();
        titleLabel = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        passwordLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        rememberMeRadio = new javax.swing.JRadioButton();
        loginButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // --- Panel 1 (Left) ---
        leftPanel.setBackground(new java.awt.Color(37, 82, 103));

        // --- Layered Pane (Login Form) ---
        layeredPane.setBackground(new java.awt.Color(255, 255, 255));
        layeredPane.setOpaque(true);

        titleLabel.setFont(new java.awt.Font("SansSerif", 1, 36));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("LOGIN");

        passwordLabel.setText("Password");
        usernameLabel.setText("Username");

        rememberMeRadio.setText("Remember Me");

        loginButton.setText("Login");
    }

 
    // Action Handlers
    private void setupActions() {
        loginButton.addActionListener(e -> loginAction());
        rememberMeRadio.addActionListener(e -> rememberMeAction());
        usernameField.addActionListener(e -> loginAction());
        passwordField.addActionListener(e -> loginAction());
    }

    private void loginAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if ("admin".equals(username) && ("admin".equals(password) || "1234".equals(password))) {
            // Open Dashboard
            Dashboard dashboard = new Dashboard();
            dashboard.setVisible(true);

            // Close current Login frame
            this.dispose();
        } else {
            // Show error message
            JOptionPane.showMessageDialog(this, "Invalid username or password.");
        }
    }

    private void rememberMeAction() {
        // TODO
    }

 
    // Layout Helpers
    private void setupLeftPanelLayout() {
        GroupLayout layout = new GroupLayout(leftPanel);
        leftPanel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(LEADING)
                .addGap(0, 335, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
        );
    }

    private void setupLayeredPaneLayout() {
        GroupLayout layout = new GroupLayout(layeredPane);
        layeredPane.setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addComponent(titleLabel, 258, 258, 258)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(22, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(usernameLabel)
                        .addComponent(passwordLabel)
                        .addComponent(usernameField, 300, 300, 300)
                        .addGroup(layout.createParallelGroup(TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rememberMeRadio)
                                .addPreferredGap(RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(loginButton))
                            .addComponent(passwordField, 300, 300, 300)))
                    .addGap(18, 18, 18))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(66, 66, 66)
                    .addComponent(titleLabel, 68, 68, 68)
                    .addPreferredGap(RELATED)
                    .addComponent(usernameLabel)
                    .addPreferredGap(RELATED)
                    .addComponent(usernameField, 37, 37, 37)
                    .addPreferredGap(UNRELATED)
                    .addComponent(passwordLabel)
                    .addGap(8, 8, 8)
                    .addComponent(passwordField, 37, 37, 37)
                    .addGap(25, 25, 25)
                    .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(rememberMeRadio)
                        .addComponent(loginButton))
                    .addContainerGap(102, Short.MAX_VALUE))
        );
    }

    private void setupMainLayout() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(leftPanel, GroupLayout.PREFERRED_SIZE, 
                    		GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 
                    		GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(2, 2, 2))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(LEADING)
                .addComponent(leftPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(layeredPane)
        );

        pack();
    }

    // Main Method
    public static void main(String args[]) {
   
    	// Create and display
        java.awt.EventQueue.invokeLater(() -> new LoginFrame().setVisible(true));
    }

    // Variables
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JLayeredPane layeredPane;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JRadioButton rememberMeRadio;
    private javax.swing.JTextField usernameField;
    private javax.swing.JTextField passwordField;
}
