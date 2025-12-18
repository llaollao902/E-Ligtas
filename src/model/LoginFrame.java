package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFrame extends JFrame {

    // user management
    private UserManager userManager;

    // swing components
    private JLabel loginLabel;
    private JLabel signUpLabelButton;
    private JLabel titleLabel;
    private JLabel passwordLabel;
    private JLabel usernameLabel;
    private JLabel signUpTextLabel;

    private JTextField usernameField;
    private JPasswordField passwordField;

    // panels for layout
    private JPanel leftPanel;
    private JPanel rightPanel;

    // panels acting as buttons
    private JPanel loginButtonPanel;
    private JPanel signUpButtonPanel;

    // combined sign-up panel (label + button)
    private JPanel signUpPanel;

    public LoginFrame() {
        userManager = new UserManager(); // initialize user manager
        initComponents(); // initialize swing components
        setupActions(); // set up mouse listeners
        setupLayout(); // arrange components on the frame
    }

    // initialize components
    private void initComponents() {

        // labels
        titleLabel = new JLabel("LOGIN", SwingConstants.CENTER);
        usernameLabel = new JLabel("Username");
        passwordLabel = new JLabel("Password");
        signUpTextLabel = new JLabel("Don't have an account?");
        loginLabel = new JLabel("Login", SwingConstants.CENTER);
        signUpLabelButton = new JLabel("Sign Up");

        // text fields
        usernameField = new JTextField();
        passwordField = new JPasswordField();

        // fonts
        Font labelFont = new Font("Century Gothic", Font.PLAIN, 14);
        Font buttonFont = new Font("Century Gothic", Font.PLAIN, 14);
        Font smallButtonFont = new Font("Century Gothic", Font.PLAIN, 12);
        Font titleFont = new Font("Century Gothic", Font.BOLD, 36);

        // apply fonts
        titleLabel.setFont(titleFont);
        usernameLabel.setFont(labelFont);
        passwordLabel.setFont(labelFont);
        signUpTextLabel.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        loginLabel.setFont(buttonFont);
        signUpLabelButton.setFont(smallButtonFont);
        usernameField.setFont(labelFont);
        passwordField.setFont(labelFont);

        // login button as panel
        loginButtonPanel = new JPanel(new BorderLayout());
        loginButtonPanel.setBackground(new Color(240, 240, 240));
        loginButtonPanel.setPreferredSize(new Dimension(90, 30));
        loginButtonPanel.setMaximumSize(new Dimension(90, 30));
        loginButtonPanel.add(loginLabel, BorderLayout.CENTER);

        // sign-up button panel
        signUpButtonPanel = new JPanel(new BorderLayout());
        signUpButtonPanel.setOpaque(false);
        signUpButtonPanel.setPreferredSize(new Dimension(60, 20));
        signUpButtonPanel.add(signUpLabelButton, BorderLayout.CENTER);
        signUpButtonPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // combined sign-up panel
        signUpPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        signUpPanel.setOpaque(false);
        signUpPanel.add(signUpTextLabel);
        signUpPanel.add(signUpButtonPanel);

        // panels
        leftPanel = new JPanel();
        leftPanel.setBackground(new Color(37, 82, 103));
        leftPanel.setPreferredSize(new Dimension(335, 540));

        rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(new Color(218, 230, 235));
        rightPanel.setPreferredSize(new Dimension(625, 540));
    }

    // set up mouse actions
    private void setupActions() {

        // login click
        loginButtonPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loginAction();
            }
        });

        // signup click
        signUpButtonPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                signUpAction();
            }
        });

        // pressing enter triggers login
        usernameField.addActionListener(e -> loginAction());
        passwordField.addActionListener(e -> loginAction());
    }

    // login
    private void loginAction() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (userManager.login(username, password)) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            Dashboard dashboard = new Dashboard();
            dashboard.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.");
        }
    }

    // open signup frame
    private void signUpAction() {
        SignUpFrame signup = new SignUpFrame(userManager);
        signup.setVisible(true);
        this.setVisible(false);
    }

    // layout components
    private void setupLayout() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(960, 540));
        setLayout(new BorderLayout());

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        JPanel innerForm = new JPanel();
        innerForm.setOpaque(false);

        JPanel centerWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        centerWrapper.setOpaque(false);
        centerWrapper.add(innerForm);

        rightPanel.add(centerWrapper, BorderLayout.CENTER);

        GroupLayout layout = new GroupLayout(innerForm);
        innerForm.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // horizontal layout
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(titleLabel, GroupLayout.Alignment.CENTER)
                .addComponent(usernameLabel)
                .addComponent(usernameField, 300, 300, 300)
                .addComponent(passwordLabel)
                .addComponent(passwordField, 300, 300, 300)
                .addComponent(loginButtonPanel, GroupLayout.Alignment.TRAILING)
                .addComponent(signUpPanel, GroupLayout.Alignment.CENTER)
        );

        // vertical layout
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGap(60)
                .addComponent(titleLabel, 50, 50, 50)
                .addGap(20)
                .addComponent(usernameLabel)
                .addComponent(usernameField, 30, 30, 30)
                .addGap(10)
                .addComponent(passwordLabel)
                .addComponent(passwordField, 30, 30, 30)
                .addGap(20)
                .addComponent(loginButtonPanel, 30, 30, 30)
                .addGap(15)
                .addComponent(signUpPanel)
        );

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
