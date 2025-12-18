package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SignUpFrame extends JFrame {
    //handles user storage, signup, and validation
    private UserManager userManager;

    //Main UI panels
    private JPanel leftPanel;
    private JPanel formPanel;

    //UI components
    private JLabel titleLabel;
    private JLabel firstNameLabel, lastNameLabel, usernameLabel, passwordLabel;
    private JTextField firstNameField, lastNameField, usernameField;
    private JPasswordField passwordField;

    // panels acting as buttons
    private JPanel signUpButtonPanel;
    private JLabel signUpLabel;

    private JPanel backButtonPanel;
    private JLabel backLabel;

    public SignUpFrame(UserManager userManager) {
        this.userManager = userManager; //receive shared userManager
        initComponents(); //create components
        setupActions(); //add listener
        setupLayout(); //arrange components
    }

    //initialize components
    private void initComponents() {
        // labels
        titleLabel = new JLabel("SIGN UP", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Century Gothic", Font.BOLD, 36));

        //field labels
        firstNameLabel = new JLabel("First Name");
        lastNameLabel = new JLabel("Last Name");
        usernameLabel = new JLabel("Username");
        passwordLabel = new JLabel("Password");

        // input fields
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        usernameField = new JTextField();
        passwordField = new JPasswordField();

        // fonts
        Font labelFont = new Font("Century Gothic", Font.PLAIN, 14);
        Font fieldFont = new Font("Century Gothic", Font.PLAIN, 14);
        Font buttonFont = new Font("Century Gothic", Font.PLAIN, 14);

        //apply fonts
        firstNameLabel.setFont(labelFont);
        lastNameLabel.setFont(labelFont);
        usernameLabel.setFont(labelFont);
        passwordLabel.setFont(labelFont);

        firstNameField.setFont(fieldFont);
        lastNameField.setFont(fieldFont);
        usernameField.setFont(fieldFont);
        passwordField.setFont(fieldFont);

        // Sign Up button as panel (centered)
        signUpButtonPanel = new JPanel(new BorderLayout());
        signUpButtonPanel.setBackground(new Color(240, 240, 240));
        signUpButtonPanel.setPreferredSize(new Dimension(90, 30));
        signUpButtonPanel.setMaximumSize(new Dimension(90, 30));
        signUpLabel = new JLabel("Sign Up", SwingConstants.CENTER);
        signUpLabel.setFont(buttonFont);
        signUpButtonPanel.add(signUpLabel, BorderLayout.CENTER);

        // Back to Login button (bottom-right)
        backButtonPanel = new JPanel(new BorderLayout());
        backButtonPanel.setOpaque(false);
        backButtonPanel.setPreferredSize(new Dimension(100, 25));
        backLabel = new JLabel("Back to Login", SwingConstants.CENTER);
        backLabel.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        backLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButtonPanel.add(backLabel, BorderLayout.CENTER);

        // panels
        leftPanel = new JPanel();
        leftPanel.setBackground(new Color(37, 82, 103));
        leftPanel.setPreferredSize(new Dimension(335, 540));

        formPanel = new JPanel(new BorderLayout());
        formPanel.setBackground(new Color(218, 230, 235));
        formPanel.setPreferredSize(new Dimension(625, 540));

        //frame settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sign Up");
        setPreferredSize(new Dimension(960, 540));
    }

    //event listeners
    private void setupActions() {
        // clicking panel triggers signup
        signUpButtonPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                signUpAction();
            }
        });

        // pressing enter triggers signup
        firstNameField.addActionListener(e -> signUpAction());
        lastNameField.addActionListener(e -> signUpAction());
        usernameField.addActionListener(e -> signUpAction());
        passwordField.addActionListener(e -> signUpAction());

        // back to login
        backButtonPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                backToLogin();
            }
        });
    }

    //signup logic
    private void signUpAction() {
        String first = firstNameField.getText().trim();
        String last  = lastNameField.getText().trim();
        String user  = usernameField.getText().trim();
        String pass  = new String(passwordField.getPassword());

        if (first.isEmpty() || last.isEmpty() || user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.");
            return;
        }

        if (userManager.usernameExists(user)) {
            JOptionPane.showMessageDialog(this, "Username already exists!");
            return;
        }

        userManager.signup(user, pass, first, last);
        JOptionPane.showMessageDialog(this, "Account created successfully!");
        backToLogin();
    }

    private void backToLogin() {
        LoginFrame login = new LoginFrame();
        login.setVisible(true);
        this.dispose();
    }

    //layout setup
    private void setupLayout() {
        setLayout(new BorderLayout());
        add(leftPanel, BorderLayout.WEST);
        add(formPanel, BorderLayout.CENTER);

        JPanel innerForm = new JPanel();
        innerForm.setOpaque(false);

        JPanel centerWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        centerWrapper.setOpaque(false);
        centerWrapper.add(innerForm);

        formPanel.add(centerWrapper, BorderLayout.CENTER);

        GroupLayout layout = new GroupLayout(innerForm);
        innerForm.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // wrapper to center the sign up button
        JPanel signUpWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        signUpWrapper.setOpaque(false);
        signUpWrapper.add(signUpButtonPanel);

        // wrapper to right-align back button
        JPanel backWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        backWrapper.setOpaque(false);
        backWrapper.add(backButtonPanel);

        // Horizontal layout
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(titleLabel, GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(firstNameLabel)
                        .addComponent(firstNameField, 150, 150, 150))
                    .addGap(10)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lastNameLabel)
                        .addComponent(lastNameField, 150, 150, 150)))
                .addComponent(usernameLabel)
                .addComponent(usernameField, 306, 306, 306)
                .addComponent(passwordLabel)
                .addComponent(passwordField, 306, 306, 306)
                .addComponent(signUpWrapper)
                .addComponent(backWrapper)
        );

        // Vertical layout
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGap(60)
                .addComponent(titleLabel, 50, 50, 50)
                .addGap(20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameLabel)
                    .addComponent(lastNameLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameField, 30, 30, 30)
                    .addComponent(lastNameField, 30, 30, 30))
                .addGap(10)
                .addComponent(usernameLabel)
                .addComponent(usernameField, 30, 30, 30)
                .addGap(10)
                .addComponent(passwordLabel)
                .addComponent(passwordField, 30, 30, 30)
                .addGap(20)
                .addComponent(signUpWrapper, 30, 30, 30)
                .addGap(10)
                .addComponent(backWrapper, 30, 30, 30)
        );

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SignUpFrame(new UserManager()).setVisible(true));
    }
}
