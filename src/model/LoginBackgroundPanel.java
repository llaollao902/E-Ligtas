package src;

import javax.swing.*;
import java.awt.*;

public class LoginBackgroundPanel extends JPanel {

    private Image backgroundImage;

    public LoginBackgroundPanel() {
        // load image
        backgroundImage = new ImageIcon(getClass().getResource("icons/HOMESCREEN_BG.png")).getImage();
        setLayout(null); // keep absolute positioning for logos
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            // scale image to panel size
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
