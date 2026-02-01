package view.components;

import util.UIUtils;
import javax.swing.*;
import java.awt.*;

public class HeroCard extends JPanel { 
    public HeroCard(JButton loginBtn, JButton signUpBtn) {
        setLayout(new GridBagLayout());
        setOpaque(false); 
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        // Title
        JLabel title = new JLabel("E-LIGTAS");
        title.setFont(new Font(UIUtils.FONT_NAME, Font.BOLD, 80));
        title.setForeground(Color.WHITE);
        
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 20, 0, 20);
        add(title, gbc);

        // Subtitle
        JLabel subtitle = new JLabel("Centralized Emergency Reporting System");
        subtitle.setFont(new Font(UIUtils.FONT_NAME, Font.ITALIC, 24));
        subtitle.setForeground(Color.WHITE);
        
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 20, 10, 20);
        add(subtitle, gbc);

        // Button Container
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(loginBtn);
        buttonPanel.add(signUpBtn);

        gbc.gridy = 2;
        gbc.insets = new Insets(20, 20, 10, 20);
        add(buttonPanel, gbc);
    }
}