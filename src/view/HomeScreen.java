package view;

import view.components.PanelRound;
import view.components.ShadowLabel;
import util.UIUtils;
import javax.swing.*;
import java.awt.*;

/**
 * the landing page of the application.
 * provides entry points for login and sign-up.
 */
@SuppressWarnings("serial")
public class HomeScreen extends JFrame {
    private JPanel mainPanel;
    private JButton loginButton;
    private JButton signUpButton;

    public HomeScreen() {
        // window settings
        setTitle("E-LIGTAS - Home");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        initializeComponents();
        assembleLayout();
        
        pack();
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        // match pixel dimensions
        mainPanel = new JPanel(null);
        mainPanel.setPreferredSize(new Dimension(1053, 542));

        // use uiutils for consistent button styling and icon loading
        loginButton = createStyledButton("Log In", "icons/icons8-logout-16.png");
        signUpButton = createStyledButton("Sign-Up", "icons/SIGN_IN.png");
    }

    private void assembleLayout() {
        // 1. logos (using uiutils scaling for sharper images if needed)
        // set to 100x100 based on existing bounds
        mainPanel.add(createLogo("icons/MIA_LGU_LOGO.png", 30, 30));
        mainPanel.add(createLogo("icons/DICT_LOGO.png", 910, 30));

        // 2. the central card
        PanelRound heroCard = new PanelRound();
        heroCard.setLayout(null); 
        heroCard.setAllBorders(30);
        // using a semi-transparent version of primary_blue for brand consistency
        heroCard.setBackground(new Color(12, 65, 96, 200)); 
        heroCard.setBounds(250, 120, 540, 320);

        // title inside the card
        ShadowLabel title = new ShadowLabel("E-LIGTAS");
        title.setFont(new Font(UIUtils.FONT_NAME, Font.BOLD, 80));
        title.setForeground(Color.WHITE);
        title.setBounds(85, 40, 400, 100);
        heroCard.add(title);

        // buttons positioning (restoring exact manual layout)
        loginButton.setBounds(100, 200, 160, 50);
        signUpButton.setBounds(280, 200, 160, 50);
        heroCard.add(loginButton);
        heroCard.add(signUpButton);

        mainPanel.add(heroCard);

        // 3. background (loaded via uiutils)
        JLabel background = new JLabel(UIUtils.loadIcon("icons/HOMESCREEN_BG.png"));
        background.setBounds(0, 0, 1053, 542);
        mainPanel.add(background);
        
        // critical: background remains at the bottom of the z-order
        mainPanel.setComponentZOrder(background, mainPanel.getComponentCount() - 1);
        
        add(mainPanel);
    }

    /**
     * helper to create buttons with consistent brand styling.
     */
    private JButton createStyledButton(String text, String iconPath) {
        JButton btn = new JButton(text, UIUtils.loadIcon(iconPath));
        btn.setFont(new Font(UIUtils.FONT_NAME, Font.BOLD, 18));
        btn.setBackground(UIUtils.PRIMARY_BLUE);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder());
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    /**
     * helper to load and position logo images.
     */
    private JLabel createLogo(String path, int x, int y) {
        // use loadandscaleicon to ensure logos fit perfectly in 100x100
        JLabel logo = new JLabel(UIUtils.loadAndScaleIcon(path, 100, 100));
        logo.setBounds(x, y, 100, 100);
        return logo;
    }

    // getters for navigation
    public JButton getLoginButton() { return loginButton; }
    public JButton getSignUpButton() { return signUpButton; }
}