package util;

import java.awt.*;

/**
 * Central location for all UI constants and styling.
 * Follows DRY (Don't Repeat Yourself) principle.
 * Makes it easy to maintain consistent styling across the application.
 */
public class UIConstants {
    
    // ==================== Colors ====================
    public static final Color SIDEBAR_COLOR = new Color(37, 82, 103);
    public static final Color ACTIVE_BG = new Color(218, 230, 235);
    public static final Color ACTIVE_TEXT = new Color(37, 82, 103);
    public static final Color HOVER_COLOR = new Color(25, 62, 79);
    public static final Color MAIN_BG = new Color(218, 230, 235);
    public static final Color CARD_COLOR = Color.WHITE;
    public static final Color HOTLINE_ACCENT = new Color(3, 92, 108);
    public static final Color BLUE_ACCENT = new Color(27, 90, 120);
    
    // ==================== Dimensions ====================
    public static final int SIDEBAR_WIDTH = 180;
    public static final int WINDOW_WIDTH = 960;
    public static final int WINDOW_HEIGHT = 540;
    public static final int MENU_ITEM_HEIGHT = 50;
    
    // ==================== Fonts ====================
    public static final String FONT_FAMILY = "Century Gothic";
    public static final Font FONT_TITLE = new Font(FONT_FAMILY, Font.BOLD, 24);
    public static final Font FONT_HEADER = new Font(FONT_FAMILY, Font.BOLD, 32);
    public static final Font FONT_SUBTITLE = new Font(FONT_FAMILY, Font.PLAIN, 14);
    public static final Font FONT_MENU = new Font(FONT_FAMILY, Font.BOLD, 14);
    public static final Font FONT_REGULAR = new Font(FONT_FAMILY, Font.PLAIN, 12);
    public static final Font FONT_SMALL = new Font(FONT_FAMILY, Font.PLAIN, 11);
    
    // ==================== Icon Paths ====================
    public static final String ICON_DASHBOARD = "src/icons/icons8-dashboard-16.png";
    public static final String ICON_REPORT = "src/icons/icons8-danger-16 (1).png";
    public static final String ICON_HOTLINE = "src/icons/icons8-telephone-16 (1).png";
    public static final String ICON_NEWS = "src/icons/icons8-news-16.png";
    public static final String ICON_LOGOUT = "src/icons/icons8-logout-16.png";
    public static final String ICON_HELP = "help (1).png";
    
    // Status colors
    public static final Color STATUS_PENDING = new Color(255, 153, 0);
    public static final Color STATUS_RESPONDING = new Color(0, 153, 204);
    public static final Color STATUS_RESOLVED = new Color(51, 153, 0);
    
    // ==================== Private Constructor ====================
    private UIConstants() {
        // Prevent instantiation
    }
    
    // ==================== Helper Methods ====================
    
    /**
     * Get status color based on status string.
     * @param status Status string
     * @return Color for the status
     */
    public static Color getStatusColor(String status) {
        return switch (status.toLowerCase()) {
            case "pending" -> STATUS_PENDING;
            case "responding" -> STATUS_RESPONDING;
            case "resolved" -> STATUS_RESOLVED;
            default -> Color.GRAY;
        };
    }
    
    /**
     * Get icon path for incident type.
     * @param type Incident type
     * @return Icon path
     */
    public static String getIncidentIcon(String type) {
        return switch (type.toLowerCase()) {
            case "fire" -> "/icons/icons8-fire-20.png";
            case "flood" -> "/icons/icons8-flood-20.png";
            case "accident" -> "/icons/icons8-accident-20.png";
            case "crime" -> "/icons/icons8-crime-20.png";
            case "medical" -> "/icons/icons8-medical-20.png";
            default -> "/icons/icons8-alert-20.png";
        };
    }
}
