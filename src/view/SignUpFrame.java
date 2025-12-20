package view;

import util.UIUtils;
import model.UserManager;
import view.components.LoginBackgroundPanel;
import javax.swing.*;
import java.awt.*;

public class SignUpFrame extends JFrame {
    private UserManager userManager; // Correctly held for the controller
    private JTextField firstNameField, lastNameField, usernameField;
    private JPasswordField passwordField;
    private JPanel leftPanel, formPanel, signUpButtonPanel, backButtonPanel;
    private JLabel titleLabel, firstNameLabel, lastNameLabel, usernameLabel, passwordLabel, signUpLabel, backLabel;

    public SignUpFrame(UserManager userManager) {
        this.userManager = userManager; // Assigning the model instance
        
        initComponents();
        setupLayout();
        
        // Window Settings
        setTitle("E-LIGTAS - Sign Up");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1053, 542)); 
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        // Centralized Fonts from UIUtils
        Font titleFont = new Font(UIUtils.FONT_NAME, Font.BOLD, 36);
        Font labelFont = new Font(UIUtils.FONT_NAME, Font.PLAIN, 14);
        Font smallFont = new Font(UIUtils.FONT_NAME, Font.PLAIN, 12);

        titleLabel = new JLabel("SIGN UP", SwingConstants.CENTER);
        titleLabel.setFont(titleFont);

        firstNameLabel = new JLabel("First Name");
        lastNameLabel = new JLabel("Last Name");
        usernameLabel = new JLabel("Username");
        passwordLabel = new JLabel("Password");

        firstNameField = new JTextField();
        lastNameField = new JTextField();
        usernameField = new JTextField();
        passwordField = new JPasswordField();

        // Apply styles to all components efficiently
        Component[] formComponents = {firstNameLabel, lastNameLabel, usernameLabel, passwordLabel, 
                                    firstNameField, lastNameField, usernameField, passwordField};
        for (Component c : formComponents) c.setFont(labelFont);

        // Sign Up Button using UIUtils Style
        signUpButtonPanel = new JPanel(new BorderLayout());
        signUpButtonPanel.setBackground(UIUtils.BUTTON_COLOR);
        signUpButtonPanel.setPreferredSize(new Dimension(90, 30));
        signUpLabel = new JLabel("Sign Up", SwingConstants.CENTER);
        signUpLabel.setFont(labelFont);
        signUpButtonPanel.add(signUpLabel, BorderLayout.CENTER);
        signUpButtonPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Back Button Link aligned LEFT
        backButtonPanel = new JPanel(new BorderLayout());
        backButtonPanel.setOpaque(false);
        backLabel = new JLabel("Already have an account? Login", SwingConstants.LEFT);
        backLabel.setFont(smallFont);
        backLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButtonPanel.add(backLabel, BorderLayout.CENTER);

        // Left Panel (Logos)
        leftPanel = new LoginBackgroundPanel();
        leftPanel.setPreferredSize(new Dimension(335, 542));
        leftPanel.setLayout(null);
        
        JLabel eLigtasLabel = new JLabel("E-LIGTAS", SwingConstants.CENTER);
        eLigtasLabel.setFont(new Font(UIUtils.FONT_NAME, Font.BOLD, 48));
        eLigtasLabel.setForeground(Color.WHITE);
        eLigtasLabel.setBounds(0, 20, 335, 60);
        leftPanel.add(eLigtasLabel);

        // Scaled Logos via UIUtils
        int logoW = 120;
        int centerX = (335 - logoW) / 2;
        leftPanel.add(createLogoLabel("icons/DICT_LOGO.png", centerX, 100, logoW));
        leftPanel.add(createLogoLabel("icons/MIA_LGU_LOGO.png", centerX, 240, logoW));

        formPanel = new JPanel(new BorderLayout());
        formPanel.setBackground(UIUtils.BACKGROUND_LIGHT);
    }

    private JLabel createLogoLabel(String path, int x, int y, int size) {
        JLabel label = new JLabel(UIUtils.loadAndScaleIcon(path, size, 120));
        label.setBounds(x, y, size, 120);
        return label;
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        add(leftPanel, BorderLayout.WEST);
        add(formPanel, BorderLayout.CENTER);

        JPanel innerForm = new JPanel();
        innerForm.setOpaque(false);
        
        // Centering Wrapper
        JPanel centerWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 70));
        centerWrapper.setOpaque(false);
        centerWrapper.add(innerForm);
        formPanel.add(centerWrapper, BorderLayout.CENTER);

        GroupLayout layout = new GroupLayout(innerForm);
        innerForm.setLayout(layout);
        layout.setAutoCreateGaps(true);

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
                .addComponent(usernameField, 310, 310, 310)
                .addComponent(passwordLabel)
                .addComponent(passwordField, 310, 310, 310)
                .addComponent(signUpButtonPanel, GroupLayout.Alignment.CENTER, 100, 100, 100)
                .addComponent(backButtonPanel, GroupLayout.Alignment.LEADING)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(titleLabel)
                .addGap(25)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameLabel).addComponent(lastNameLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameField, 30, 30, 30).addComponent(lastNameField, 30, 30, 30))
                .addGap(10)
                .addComponent(usernameLabel)
                .addComponent(usernameField, 30, 30, 30)
                .addGap(10)
                .addComponent(passwordLabel)
                .addComponent(passwordField, 30, 30, 30)
                .addGap(25)
                .addComponent(signUpButtonPanel, 35, 35, 35)
                .addGap(15)
                .addComponent(backButtonPanel)
        );
    }

    // GETTERS
    public JPanel getSignUpButton() { return signUpButtonPanel; }
    public JPanel getBackButton() { return backButtonPanel; }
    public String getFirstName() { return firstNameField.getText().trim(); }
    public String getLastName() { return lastNameField.getText().trim(); }
    public String getUsername() { return usernameField.getText().trim(); }
    public String getPassword() { return new String(passwordField.getPassword()); }
}