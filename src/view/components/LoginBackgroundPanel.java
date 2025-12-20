package view.components;

import util.UIUtils;
import javax.swing.*;
import java.awt.*;

/**
 * A custom panel that renders a scaled background image.
 * Primarily used as the left-side branding panel in Login and Sign-Up screens.
 */
public class LoginBackgroundPanel extends JPanel {

    private final Image backgroundImage;

    public LoginBackgroundPanel() {
        // Use the centralized Utility to load the image
        ImageIcon icon = UIUtils.loadIcon("icons/HOMESCREEN_BG.png");
        this.backgroundImage = (icon != null) ? icon.getImage() : null;
        
        setLayout(null); // Retain absolute layout for custom logo positioning
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            
            // Improve scaling quality
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
                               RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            
            // Draw image to fill the entire panel area
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            
            g2d.dispose();
        }
    }
}