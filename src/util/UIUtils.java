package util;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class UIUtils {
    // Colors
    public static final Color PRIMARY_BLUE = new Color(12, 65, 96);
    public static final Color BACKGROUND_LIGHT = new Color(218, 230, 235);
    public static final Color BUTTON_COLOR = new Color(240, 240, 240);
    
    // Fonts
    public static final String FONT_NAME = "Century Gothic";

    /**
     * Loads an icon from the resources folder.
     */
    public static ImageIcon loadIcon(String path) {
        String finalPath = path.startsWith("/") ? path : "/" + path;
        URL url = UIUtils.class.getResource(finalPath);
        return (url != null) ? new ImageIcon(url) : null;
    }

    /**
     * Loads and scales an icon in one step. 
     * Use -1 for height to maintain aspect ratio.
     */
    public static ImageIcon loadAndScaleIcon(String path, int width, int height) {
        ImageIcon icon = loadIcon(path);
        if (icon != null) {
            Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        }
        return null;
    }
}