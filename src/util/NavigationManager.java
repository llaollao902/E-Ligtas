package util;

import javax.swing.*;

/**
 * Utility class for managing navigation between frames.
 * Follows Single Responsibility Principle - handles navigation only.
 */
public class NavigationManager {
    
    /**
     * Navigate to a new frame and dispose of the current frame.
     * @param targetFrameClass Class of the frame to navigate to
     * @param currentFrame Current frame to dispose
     */
    public static void navigateTo(Class<?> targetFrameClass, JFrame currentFrame) {
        try {
            JFrame newFrame = (JFrame) targetFrameClass.getDeclaredConstructor().newInstance();
            newFrame.setVisible(true);
            currentFrame.dispose();
        } catch (Exception e) {
            showNavigationError(currentFrame, targetFrameClass.getSimpleName());
        }
    }
    
    /**
     * Show error message when navigation fails.
     * @param parent Parent component for dialog
     * @param frameName Name of target frame
     */
    private static void showNavigationError(JFrame parent, String frameName) {
        JOptionPane.showMessageDialog(
            parent,
            "Unable to navigate to " + frameName,
            "Navigation Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
}
