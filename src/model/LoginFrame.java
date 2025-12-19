package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFrame extends JFrame {

    // user management
    private UserManager userManager;

    // swing labels
    private JLabel loginLabel;
    private JLabel signUpLabelButton;
    private JLabel titleLabel;
    private JLabel passwordLabel;
    private JLabel usernameLabel;
    private JLabel signUpTextLabel;

    // input fields
    private JTextField usernameField;
    private JPasswordField passwordField;

    // main panels
    private JPanel leftPanel;
    private JPanel rightPanel;

    // panels acting as buttons
    private JPanel loginButtonPanel;
    private JPanel signUpButtonPanel;

    // combined sign-up text + button
    private JPanel signUpPanel;

    public LoginFrame() {
        userManager = new UserManager(); // initialize user manager
        initComponents();                // initialize swing components
        setupActions();                  // mouse and keyboard actions
        setupLayout();                   // arrange components
    }

    private void initComponents() {

        // labels for right panel
        titleLabel = new JLabel("LOGIN", SwingConstants.CENTER);
        usernameLabel = new JLabel("Username");
        passwordLabel = new JLabel("Password");
        signUpTextLabel = new JLabel("Don't have an account?");
        loginLabel = new JLabel("Login", SwingConstants.CENTER);
        signUpLabelButton = new JLabel("Sign Up");

        // input fields
        usernameField = new JTextField();
        passwordField = new JPasswordField();

        // fonts
        Font labelFont = new Font("Century Gothic", Font.PLAIN, 14);
        Font titleFont = new Font("Century Gothic", Font.BOLD, 36);

        // apply fonts
        titleLabel.setFont(titleFont);
        usernameLabel.setFont(labelFont);
        passwordLabel.setFont(labelFont);
        signUpTextLabel.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        loginLabel.setFont(labelFont);
        signUpLabelButton.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        usernameField.setFont(labelFont);
        passwordField.setFont(labelFont);

        // login button panel
        loginButtonPanel = new JPanel(new BorderLayout());
        loginButtonPanel.setBackground(new Color(240, 240, 240));
        loginButtonPanel.setPreferredSize(new Dimension(60, 30));
        loginButtonPanel.add(loginLabel, BorderLayout.CENTER);

        // sign-up button panel
        signUpButtonPanel = new JPanel(new BorderLayout());
        signUpButtonPanel.setOpaque(false);
        signUpButtonPanel.add(signUpLabelButton, BorderLayout.CENTER);
        signUpButtonPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // combined sign-up panel
        signUpPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        signUpPanel.setOpaque(false);
        signUpPanel.add(signUpTextLabel);
        signUpPanel.add(signUpButtonPanel);

        // LEFT PANEL (background + logos + app title)
        leftPanel = new LoginBackgroundPanel();
        leftPanel.setPreferredSize(new Dimension(335, 540));
        leftPanel.setLayout(null); // absolute positioning

        // E-LIGTAS app title
        JLabel eLigtasLabel = new JLabel("E-LIGTAS", SwingConstants.CENTER);
        eLigtasLabel.setFont(new Font("Century Gothic", Font.BOLD, 48));
        eLigtasLabel.setForeground(Color.WHITE);
        eLigtasLabel.setBounds(0, 20, 335, 60); // top, centered
        leftPanel.add(eLigtasLabel);

        // Load logos
        ImageIcon dictIcon = new ImageIcon(getClass().getResource("icons/DICT_LOGO.png"));
        ImageIcon miaIcon = new ImageIcon(getClass().getResource("icons/MIA_LGU_LOGO.png"));

        // Target width for both logos
        int logoWidth = 120;

        // Scale DICT logo
        Image dictImg = dictIcon.getImage();
        int dictHeight = dictImg.getHeight(null) * logoWidth / dictImg.getWidth(null);
        dictImg = dictImg.getScaledInstance(logoWidth, dictHeight, Image.SCALE_SMOOTH);
        JLabel dictLogo = new JLabel(new ImageIcon(dictImg));
        dictLogo.setBounds((335 - logoWidth) / 2, 100, logoWidth, dictHeight); // below E-LIGTAS

        // Scale MIA LGU logo
        Image miaImg = miaIcon.getImage();
        int miaHeight = miaImg.getHeight(null) * logoWidth / miaImg.getWidth(null);
        miaImg = miaImg.getScaledInstance(logoWidth, miaHeight, Image.SCALE_SMOOTH);
        JLabel miaLogo = new JLabel(new ImageIcon(miaImg));
        miaLogo.setBounds((335 - logoWidth) / 2, 100 + dictHeight + 20, logoWidth, miaHeight); // spacing

        // add logos
        leftPanel.add(dictLogo);
        leftPanel.add(miaLogo);

        // right panel (login form)
        rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(new Color(218, 230, 235));
        rightPanel.setPreferredSize(new Dimension(625, 540));
    }

    private void setupActions() {

        // mouse click on login button
        loginButtonPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loginAction();
            }
        });

        // mouse click on sign-up button
        signUpButtonPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                signUpAction();
            }
        });

        // pressing Enter triggers login
        usernameField.addActionListener(e -> loginAction());
        passwordField.addActionListener(e -> loginAction());
    }

    private void loginAction() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (userManager.login(username, password)) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            new Dashboard().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.");
        }
    }

    private void signUpAction() {
        new SignUpFrame(userManager).setVisible(true);
        setVisible(false);
    }

    private void setupLayout() {

        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(960, 540));
        setLayout(new BorderLayout());

        // main panels
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        // wrapper panels for centering form
        JPanel innerForm = new JPanel();
        innerForm.setOpaque(false);

        JPanel centerWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerWrapper.setOpaque(false);
        centerWrapper.add(innerForm);

        rightPanel.add(centerWrapper, BorderLayout.CENTER);

        // group layout for form
        GroupLayout layout = new GroupLayout(innerForm);
        innerForm.setLayout(layout);
        layout.setAutoCreateGaps(true);

        // horizontal layout
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(titleLabel, GroupLayout.Alignment.CENTER)
                    .addComponent(usernameLabel)
                    .addComponent(usernameField, 300, 300, 300)
                    .addComponent(passwordLabel)
                    .addComponent(passwordField, 300, 300, 300)
                    .addGroup(layout.createSequentialGroup()
                            .addGap(210)
                            .addComponent(loginButtonPanel, 90, 90, 90)
                    )
                    .addComponent(signUpPanel, GroupLayout.Alignment.CENTER)
        );

        // vertical layout
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

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
