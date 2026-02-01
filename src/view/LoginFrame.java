package view;

import util.UIUtils;
import view.components.LoginBackgroundPanel;
import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    // ui component declarations
    private JLabel loginLabel, signUpLabelButton, titleLabel, passwordLabel, usernameLabel, signUpTextLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPanel leftPanel, rightPanel, loginButtonPanel, signUpButtonPanel, signUpPanel;

    public LoginFrame() {
        // initialize components and layout
        initComponents();
        setupLayout();
        
        // frame configuration
        setTitle("E-LIGTAS - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1053, 542)); 
        setResizable(false);
        pack();
        setLocationRelativeTo(null); // centers the window on startup
    }

    private void initComponents() {
        // define fonts using uiutils constants
        Font titleFont = new Font(UIUtils.FONT_NAME, Font.BOLD, 36);
        Font labelFont = new Font(UIUtils.FONT_NAME, Font.PLAIN, 14);

        // create header and input labels
        titleLabel = new JLabel("LOGIN", SwingConstants.CENTER);
        titleLabel.setFont(titleFont);
        
        usernameLabel = new JLabel("Username");
        usernameLabel.setFont(labelFont);
        
        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(labelFont);

        // initialize input fields
        usernameField = new JTextField();
        usernameField.setFont(labelFont);
        
        passwordField = new JPasswordField();
        passwordField.setFont(labelFont);

        // create custom login button using a jpanel
        loginLabel = new JLabel("Login", SwingConstants.CENTER);
        loginLabel.setFont(labelFont);
        loginButtonPanel = new JPanel(new BorderLayout());
        loginButtonPanel.setBackground(UIUtils.BUTTON_COLOR);
        loginButtonPanel.add(loginLabel, BorderLayout.CENTER);
        loginButtonPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // create the sign-up prompt section
        signUpTextLabel = new JLabel("Don't have an account?");
        signUpTextLabel.setFont(new Font(UIUtils.FONT_NAME, Font.PLAIN, 12));
        
        signUpLabelButton = new JLabel("Sign Up");
        signUpLabelButton.setFont(new Font(UIUtils.FONT_NAME, Font.PLAIN, 12));
        
        // wrapping sign-up label in a panel for click detection
        signUpButtonPanel = new JPanel(new BorderLayout());
        signUpButtonPanel.setOpaque(false);
        signUpButtonPanel.add(signUpLabelButton, BorderLayout.CENTER);
        signUpButtonPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // combine text and button in a flow layout
        signUpPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        signUpPanel.setOpaque(false);
        signUpPanel.add(signUpTextLabel);
        signUpPanel.add(signUpButtonPanel);

        // left branding panel with custom background
        leftPanel = new LoginBackgroundPanel();
        leftPanel.setPreferredSize(new Dimension(335, 542));
        leftPanel.setLayout(null); // absolute positioning for logos

        // branding title
        JLabel eLigtasLabel = new JLabel("E-LIGTAS", SwingConstants.CENTER);
        eLigtasLabel.setFont(new Font(UIUtils.FONT_NAME, Font.BOLD, 48));
        eLigtasLabel.setForeground(Color.WHITE);
        eLigtasLabel.setBounds(0, 20, 335, 60);
        leftPanel.add(eLigtasLabel);

        // load and position logos from resources
        int logoW = 120;
        int centerX = (335 - logoW) / 2;
        
        JLabel dictLogo = new JLabel(UIUtils.loadAndScaleIcon("icons/DICT_LOGO.png", logoW, 120));
        dictLogo.setBounds(centerX, 100, logoW, 120);
        leftPanel.add(dictLogo);

        JLabel miaLogo = new JLabel(UIUtils.loadAndScaleIcon("icons/MIA_LGU_LOGO.png", logoW, 120));
        miaLogo.setBounds(centerX, 240, logoW, 120);
        leftPanel.add(miaLogo);

        // container for the right-side form
        rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(UIUtils.BACKGROUND_LIGHT);
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // set fixed size for the right side
        rightPanel.setPreferredSize(new Dimension(1053 - 335, 542));
        
        // assemble main frame structure
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        // center the form inside the right panel
        JPanel innerForm = new JPanel();
        innerForm.setOpaque(false);
        
        JPanel centerWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerWrapper.setOpaque(false);
        centerWrapper.add(innerForm);
        rightPanel.add(centerWrapper, BorderLayout.CENTER);

        // define structural alignment using grouplayout
        GroupLayout layout = new GroupLayout(innerForm);
        innerForm.setLayout(layout);
        layout.setAutoCreateGaps(true);

        // horizontal structure: aligning components to 300px width
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(titleLabel, GroupLayout.Alignment.CENTER)
                .addComponent(usernameLabel)
                .addComponent(usernameField, 300, 300, 300)
                .addComponent(passwordLabel)
                .addComponent(passwordField, 300, 300, 300)
                .addGroup(layout.createSequentialGroup().addGap(210).addComponent(loginButtonPanel, 90, 90, 90))
                .addComponent(signUpPanel, GroupLayout.Alignment.CENTER)
        );

        // vertical structure: defining sequential spacing
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGap(60) 
                .addComponent(titleLabel)
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
    }

    // getters for controller access
    public JPanel getLoginButton() { return loginButtonPanel; }
    public JPanel getSignUpButton() { return signUpButtonPanel; }
    public String getUsername() { return usernameField.getText().trim(); }
    public String getPassword() { return new String(passwordField.getPassword()); }
}