package src;

import java.awt.*;
import javax.swing.*;

public class HomeScreen extends JFrame {

    private JPanel mainPanel;
    private JLabel backgroundLabel;
    private JLabel dictLogoLabel;
    private JLabel lguLogoLabel;
    private JLabel unusedSubtitleLabel;
    private JLabel unusedTitleLabel;
    private JButton signUpButton;
    private JButton loginButton;

    public HomeScreen() {
        initComponents();
        setupHeroCard();
        setupActions();
    }

    // ================= UI SETUP =================

    private void setupHeroCard() {
        mainPanel.setLayout(null);

        mainPanel.remove(unusedSubtitleLabel);
        mainPanel.remove(unusedTitleLabel);

        PanelRound heroCard = new PanelRound();
        heroCard.setBounds(250, 120, 540, 320);
        heroCard.setLayout(new GridBagLayout());
        heroCard.setBackground(new Color(20, 40, 70, 150));
        heroCard.setRoundTopLeft(30);
        heroCard.setRoundTopRight(30);
        heroCard.setRoundBottomLeft(30);
        heroCard.setRoundBottomRight(30);

        mainPanel.add(heroCard);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        ShadowLabel title = new ShadowLabel("E-LIGTAS");
        title.setFont(new Font("Century Gothic", Font.BOLD, 80));
        title.setForeground(Color.WHITE);
        title.setShadowOffset(3, 3);

        gbc.gridy = 0;
        gbc.insets = new Insets(10, 20, 0, 20);
        heroCard.add(title, gbc);

        ShadowLabel subtitle = new ShadowLabel("Centralized Emergency Reporting System");
        subtitle.setFont(new Font("Century Gothic", Font.ITALIC, 24));
        subtitle.setForeground(Color.WHITE);
        subtitle.setShadowOffset(2, 2);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 20, 10, 20);
        heroCard.add(subtitle, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);

        mainPanel.remove(signUpButton);
        mainPanel.remove(loginButton);

        buttonPanel.add(loginButton);
        buttonPanel.add(signUpButton);

        gbc.gridy = 2;
        gbc.insets = new Insets(20, 20, 10, 20);
        heroCard.add(buttonPanel, gbc);

        mainPanel.setComponentZOrder(backgroundLabel, mainPanel.getComponentCount() - 1);
    }

    private void setupActions() {
        loginButton.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            dispose();
        });

        signUpButton.addActionListener(e -> {
            new SignUpFrame(null).setVisible(true);
            dispose();
        });
    }

    // ================= INIT COMPONENTS =================

    private void initComponents() {
        mainPanel = new JPanel();
        backgroundLabel = new JLabel();
        dictLogoLabel = new JLabel();
        lguLogoLabel = new JLabel();
        unusedSubtitleLabel = new JLabel();
        unusedTitleLabel = new JLabel();
        signUpButton = new JButton();
        loginButton = new JButton();

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        mainPanel.setPreferredSize(new Dimension(1053, 542));
        mainPanel.setBackground(new Color(218, 230, 235));

        dictLogoLabel.setIcon(loadIcon("icons/DICT_LOGO.png"));
        dictLogoLabel.setBounds(910, 30, 100, 100);
        mainPanel.add(dictLogoLabel);

        lguLogoLabel.setIcon(loadIcon("icons/MIA_LGU_LOGO.png"));
        lguLogoLabel.setBounds(30, 30, 100, 100);
        mainPanel.add(lguLogoLabel);

        signUpButton.setText("Sign-Up");
        signUpButton.setFont(new Font("Century Gothic", Font.BOLD, 18));
        signUpButton.setBackground(new Color(12, 65, 96));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setIcon(loadIcon("icons/SIGN_IN.png"));

        loginButton.setText("Log In");
        loginButton.setFont(new Font("Century Gothic", Font.BOLD, 18));
        loginButton.setBackground(new Color(12, 65, 96));
        loginButton.setForeground(Color.WHITE);
        loginButton.setIcon(loadIcon("icons/icons8-logout-16.png"));

        backgroundLabel.setIcon(loadIcon("icons/HOMESCREEN_BG.png"));
        backgroundLabel.setBounds(0, 0, 1060, 540);
        mainPanel.add(backgroundLabel);

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }

    private ImageIcon loadIcon(String path) {
        java.net.URL url = getClass().getResource(path);
        if (url == null) {
            System.err.println("Missing resource: " + path);
            return null;
        }
        return new ImageIcon(url);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HomeScreen().setVisible(true));
    }
}
