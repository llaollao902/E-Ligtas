package model;

import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import static javax.swing.GroupLayout.Alignment.*;
import static javax.swing.LayoutStyle.ComponentPlacement.*;

public class SignUpFrame extends javax.swing.JFrame {

    public SignUpFrame() {
        initComponents();
        setupActions();
        setupLeftPanelLayout();
        setupFormLayout();
        setupMainLayout();
    }

    private void initComponents() {

        // Panels
        leftPanel = new javax.swing.JPanel();
        formPane = new javax.swing.JLayeredPane();

        // Labels
        titleLabel = new javax.swing.JLabel();
        firstNameLabel = new javax.swing.JLabel();
        lastNameLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();

        // Fields
        firstName = new javax.swing.JTextField();
        lastName = new javax.swing.JTextField();
        username = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();

        // Button
        signUpButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // Left panel
        leftPanel.setBackground(new java.awt.Color(37, 82, 103));

        // Form pane
        formPane.setBackground(new java.awt.Color(218, 230, 235));
        formPane.setOpaque(true);

        // Title
        titleLabel.setFont(new java.awt.Font("Century Gothic", 1, 36));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("SIGN UP");

        // Labels text
        firstNameLabel.setText("First Name");
        lastNameLabel.setText("Last Name");
        usernameLabel.setText("Username");
        passwordLabel.setText("Password");

        // Button text
        signUpButton.setText("Sign Up");
    }

    // ---------------- Actions ----------------
    private void setupActions() {
        signUpButton.addActionListener(e -> signUpAction());

        firstName.addActionListener(e -> signUpAction());
        lastName.addActionListener(e -> signUpAction());
        username.addActionListener(e -> signUpAction());
        password.addActionListener(e -> signUpAction());
    }

    private void signUpAction() {
        String first = firstName.getText().trim();
        String last  = lastName.getText().trim();
        String user  = username.getText().trim();
        String pass  = new String(password.getPassword());

        if (first.isEmpty() || last.isEmpty() || user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.");
            return;
        }

        JOptionPane.showMessageDialog(this, "Sign up successful!");
    }

    // ---------------- Layout ----------------
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

    private void setupFormLayout() {
        GroupLayout layout = new GroupLayout(formPane);
        formPane.setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(39)
                    .addComponent(titleLabel, 258, 258, 258))
                .addGroup(TRAILING, layout.createSequentialGroup()
                    .addContainerGap(22, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(LEADING)
                                .addComponent(firstNameLabel)
                                .addComponent(firstName, 150, 150, 150))
                            .addPreferredGap(RELATED)
                            .addGroup(layout.createParallelGroup(LEADING)
                                .addComponent(lastNameLabel)
                                .addComponent(lastName, 150, 150, 150)))
                        .addComponent(usernameLabel)
                        .addComponent(username, 306, 306, 306)
                        .addComponent(passwordLabel)
                        .addComponent(password, 306, 306, 306)
                        .addGroup(TRAILING, layout.createSequentialGroup()
                            .addComponent(signUpButton)
                            .addGap(129))))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(66)
                    .addComponent(titleLabel, 68, 68, 68)
                    .addPreferredGap(RELATED)
                    .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(firstNameLabel)
                        .addComponent(lastNameLabel))
                    .addPreferredGap(RELATED)
                    .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(firstName, 37, 37, 37)
                        .addComponent(lastName, 37, 37, 37))
                    .addPreferredGap(UNRELATED)
                    .addComponent(usernameLabel)
                    .addGap(8)
                    .addComponent(username, 37, 37, 37)
                    .addPreferredGap(RELATED)
                    .addComponent(passwordLabel)
                    .addPreferredGap(RELATED)
                    .addComponent(password, 37, 37, 37)
                    .addGap(18)
                    .addComponent(signUpButton)
                    .addContainerGap(45, Short.MAX_VALUE))
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
                    .addComponent(formPane))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(LEADING)
                .addComponent(leftPanel, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(formPane)
        );

        pack();
    }

    // ---------------- Main ----------------
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new SignUpFrame().setVisible(true));
    }

    // ---------------- Variables ----------------
    private javax.swing.JPanel leftPanel;
    private javax.swing.JLayeredPane formPane;

    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel firstNameLabel;
    private javax.swing.JLabel lastNameLabel;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JLabel passwordLabel;

    private javax.swing.JTextField firstName;
    private javax.swing.JTextField lastName;
    private javax.swing.JTextField username;
    private javax.swing.JPasswordField password;

    private javax.swing.JButton signUpButton;
}
