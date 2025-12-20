package util;

import javax.swing.*;
import java.awt.*;

/**
 * Utility class for loading icons consistently.
 * Follows Single Responsibility Principle.
 */
public class IconLoader {
    
    /**
     * Load an icon from the resources folder.
     * Tries multiple paths and returns null if not found.
     * @param iconFileName Name of icon file
     * @return ImageIcon or null if not found
     */
    public static ImageIcon loadIcon(String iconFileName) {
        try {
            ImageIcon icon = new ImageIcon(
                IconLoader.class.getResource(iconFileName)
            );
            if (icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                return icon;
            }
            
            icon = new ImageIcon(IconLoader.class.getResource(iconFileName));
            if (icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                return icon;
            }
        } catch (Exception e) {
            // Icon not found, return null
        }
        return null;
    }
    
    /**
     * Create a JLabel with an icon, or a placeholder if icon not found.
     * @param iconFileName Name of icon file
     * @return JLabel with icon or placeholder
     */
    public static JLabel createIconLabel(String iconFileName) {
        ImageIcon icon = loadIcon(iconFileName);
        if (icon != null && icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
            return new JLabel(icon);
        }
        JLabel placeholder = new JLabel();
        placeholder.setPreferredSize(new Dimension(16, 16));
        return placeholder;
    }
    
    /**
     * Create a JLabel with an icon at a specific size, or placeholder if not found.
     * @param iconFileName Name of icon file
     * @param width Width of icon
     * @param height Height of icon
     * @return JLabel with icon or placeholder
     */
    public static JLabel createIconLabel(String iconFileName, int width, int height) {
        ImageIcon icon = loadIcon(iconFileName);
        if (icon != null && icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
            return new JLabel(icon);
        }
        JLabel placeholder = new JLabel();
        placeholder.setPreferredSize(new Dimension(width, height));
        return placeholder;
    }
}
