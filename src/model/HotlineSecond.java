package model;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Emergency Hotlines display frame with navigation sidebar.
 * Displays contact information for Philippine emergency services.
 */
@SuppressWarnings("serial")
public class Hotlines extends JFrame {

    // ==================== Constants ====================
    private static final Color SIDEBAR_COLOR = new Color(37, 82, 103);
    private static final Color ACTIVE_BG = new Color(218, 230, 235);
    private static final Color ACTIVE_TEXT = new Color(37, 82, 103);
    private static final Color HOVER_COLOR = new Color(25, 62, 79);
    private static final Color MAIN_BG = new Color(218, 230, 235);
    private static final Color HOTLINE_ACCENT = new Color(3, 92, 108);
    
    private static final int SIDEBAR_WIDTH = 180;
    private static final int WINDOW_WIDTH = 960;
    private static final int WINDOW_HEIGHT = 540;
    private static final int MENU_ITEM_HEIGHT = 50;
    
    // ==================== Components ====================
    private PanelRound sidebarPanel;
    private JPanel mainPanel;
    
    // Sidebar menu items
    private PanelRound dashboardItem;
    private PanelRound reportItem;
    private PanelRound hotlineItem;
    private PanelRound newsItem;
    private PanelRound logoutItem;
    
    private PanelRound activePanel;

    // ==================== Constructor ====================
    public Hotlines() {
        initializeFrame();
        buildUI();
    }

    // ==================== Initialization ====================
    private void initializeFrame() {
        setTitle("Emergency Hotlines");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setLayout(new BorderLayout());
    }

    private void buildUI() {
        sidebarPanel = createSidebar();
        mainPanel = createMainContent();
        
        add(sidebarPanel, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);
        
        pack();
        setLocationRelativeTo(null);
    }

    // ==================== Sidebar Creation ====================
    private PanelRound createSidebar() {
        PanelRound sidebar = new PanelRound();
        sidebar.setBackground(SIDEBAR_COLOR);
        sidebar.setPreferredSize(new Dimension(SIDEBAR_WIDTH, WINDOW_HEIGHT));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        
        initializeMenuItems();
        setupMenuNavigation();
        layoutSidebar(sidebar);
        
        return sidebar;
    }

    private void initializeMenuItems() {
        dashboardItem = createMenuItem("icons8-dashboard-16.png", "Dashboard");
        reportItem = createMenuItem("icons8-danger-16 (1).png", "Report Incident");
        hotlineItem = createMenuItem("icons8-telephone-16 (1).png", "Hotlines");
        newsItem = createMenuItem("icons8-news-16.png", "News");
        logoutItem = createMenuItem("icons8-logout-16.png", "Log out");
        
        setActivePanel(hotlineItem);
    }

    private void setupMenuNavigation() {
        addNavigationListener(dashboardItem, () -> navigateTo(Dashboard.class));
        addNavigationListener(reportItem, () -> dispose()); // TODO: Implement ReportIncident
        addNavigationListener(hotlineItem, () -> {}); // Already on this page
        addNavigationListener(newsItem, () -> navigateTo(News.class));
        addNavigationListener(logoutItem, () -> dispose()); // TODO: Implement LoginFrame
    }

    private void layoutSidebar(PanelRound sidebar) {
        sidebar.add(Box.createVerticalStrut(100));
        sidebar.add(dashboardItem);
        sidebar.add(reportItem);
        sidebar.add(hotlineItem);
        sidebar.add(newsItem);
        sidebar.add(Box.createVerticalGlue());
        sidebar.add(logoutItem);
        sidebar.add(Box.createVerticalStrut(20));
    }

    // ==================== Menu Item Creation ====================
    private PanelRound createMenuItem(String iconFileName, String text) {
        PanelRound item = createMenuItemPanel();
        ImageIcon icon = loadIcon(iconFileName);
        
        JLabel iconLabel = createIconLabel(icon);
        JLabel textLabel = createTextLabel(text);
        
        item.putClientProperty("textLabel", textLabel);
        item.add(iconLabel);
        item.add(textLabel);
        
        addHoverEffect(item);
        
        return item;
    }

    private PanelRound createMenuItemPanel() {
        PanelRound item = new PanelRound();
        item.setBackground(SIDEBAR_COLOR);
        item.setRoundBottomLeft(50);
        item.setRoundTopLeft(50);
        item.setMaximumSize(new Dimension(Integer.MAX_VALUE, MENU_ITEM_HEIGHT));
        item.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 10));
        return item;
    }

    private ImageIcon loadIcon(String iconFileName) {
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/icons/" + iconFileName));
            if (icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                return icon;
            }
            icon = new ImageIcon(getClass().getResource(iconFileName));
            if (icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                return icon;
            }
        } catch (Exception e) {
            // Icon not found, return null
        }
        return null;
    }

    private JLabel createIconLabel(ImageIcon icon) {
        if (icon != null && icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
            return new JLabel(icon);
        }
        JLabel placeholder = new JLabel();
        placeholder.setPreferredSize(new Dimension(16, 16));
        return placeholder;
    }

    private JLabel createTextLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Century Gothic", Font.BOLD, 14));
        label.setForeground(Color.WHITE);
        return label;
    }

    private void addHoverEffect(PanelRound item) {
        item.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (item != activePanel) {
                    item.setBackground(HOVER_COLOR);
                }
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (item != activePanel) {
                    item.setBackground(SIDEBAR_COLOR);
                }
            }
        });
    }

    // ==================== Navigation ====================
    private void addNavigationListener(PanelRound menuItem, Runnable action) {
        menuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setActivePanel(menuItem);
                action.run();
            }
        });
    }

    private void navigateTo(Class<?> frameClass) {
        try {
            JFrame frame = (JFrame) frameClass.getDeclaredConstructor().newInstance();
            frame.setVisible(true);
            dispose();
        } catch (Exception e) {
            showError("Navigation Error", "Unable to navigate to " + frameClass.getSimpleName());
        }
    }

    private void setActivePanel(PanelRound newActivePanel) {
        resetPreviousActivePanel();
        activateNewPanel(newActivePanel);
    }

    private void resetPreviousActivePanel() {
        if (activePanel != null) {
            activePanel.setBackground(SIDEBAR_COLOR);
            JLabel prevTextLabel = (JLabel) activePanel.getClientProperty("textLabel");
            if (prevTextLabel != null) {
                prevTextLabel.setForeground(Color.WHITE);
            }
        }
    }

    private void activateNewPanel(PanelRound newActivePanel) {
        activePanel = newActivePanel;
        activePanel.setBackground(ACTIVE_BG);
        JLabel newTextLabel = (JLabel) activePanel.getClientProperty("textLabel");
        if (newTextLabel != null) {
            newTextLabel.setForeground(ACTIVE_TEXT);
        }
    }

    // ==================== Main Content ====================
    private JPanel createMainContent() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(MAIN_BG);
        
        panel.add(createHeader(), BorderLayout.NORTH);
        panel.add(createHotlinesGrid(), BorderLayout.CENTER);
        panel.add(createBackButton(), BorderLayout.SOUTH);
        
        return panel;
    }

    // ==================== Header ====================
    private JPanel createHeader() {
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        header.setBackground(Color.WHITE);
        
        JLabel iconLabel = createHeaderIcon();
        JPanel textPanel = createHeaderText();
        
        header.add(iconLabel);
        header.add(textPanel);
        
        return header;
    }

    private JLabel createHeaderIcon() {
        ImageIcon icon = loadIcon("help (1).png");
        if (icon != null && icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
            return new JLabel(icon);
        }
        JLabel placeholder = new JLabel("[ICON]");
        placeholder.setFont(new Font("Century Gothic", Font.BOLD, 32));
        return placeholder;
    }

    private JPanel createHeaderText() {
        JPanel textPanel = new JPanel();
        textPanel.setBackground(Color.WHITE);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        
        JLabel title = new JLabel("Emergency Hotlines");
        title.setFont(new Font("Century Gothic", Font.BOLD, 32));
        
        JLabel subtitle = new JLabel("Contact numbers for emergency services");
        subtitle.setFont(new Font("Century Gothic", Font.PLAIN, 14));
        subtitle.setForeground(Color.GRAY);
        
        textPanel.add(title);
        textPanel.add(subtitle);
        
        return textPanel;
    }

    // ==================== Hotlines Grid ====================
    private JPanel createHotlinesGrid() {
        JPanel grid = new JPanel(new GridLayout(2, 3, 20, 20));
        grid.setBackground(MAIN_BG);
        grid.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        addHotlineCards(grid);
        
        return grid;
    }

    private void addHotlineCards(JPanel grid) {
        grid.add(createHotlineCard(
            "National Emergency Hotline",
            "For all emergencies",
            "911",
            "https://bettergov.ph/philippines/hotlines"
        ));
        
        grid.add(createHotlineCard(
            "Bureau of Fire Protection",
            "Fire emergencies",
            "160 / (02) 8426-0219",
            "https://www.foi.gov.ph/agencies/bfp/"
        ));
        
        grid.add(createHotlineCard(
            "Philippine National Police",
            "Crime and security emergencies",
            "117/ (02) 8722-0650",
            "https://www.foi.gov.ph/agencies/pnp/"
        ));
        
        grid.add(createHotlineCard(
            "Philippine Red Cross",
            "Medical emergencies & disasters",
            "143 / (02) 8527-0000",
            "https://redcross.org.ph/contact-us/"
        ));
        
        grid.add(createHotlineCard(
            "NDRRMC",
            "Disaster risk reduction & management",
            "(02) 8911-5061",
            "https://monitoring-dashboard.ndrrmc.gov.ph/"
        ));
        
        grid.add(createHotlineCard(
            "DOH Health Emergency",
            "Health-related emergencies",
            "1555",
            "https://doh.gov.ph/"
        ));
    }

    private JPanel createHotlineCard(String name, String description, String number, String link) {
        PanelRound card = new PanelRound();
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        card.setAllBorders(20);
        
        JLabel nameLabel = createCardLabel(name, Font.BOLD, 12, Color.BLACK);
        JLabel descLabel = createCardLabel(description, Font.PLAIN, 11, Color.DARK_GRAY);
        JLabel numberLabel = createCardLabel(number, Font.BOLD, 12, HOTLINE_ACCENT);
        
        makeClickable(numberLabel, link);
        
        card.add(nameLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(descLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(numberLabel);
        
        return card;
    }

    private JLabel createCardLabel(String text, int style, int size, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Century Gothic", style, size));
        label.setForeground(color);
        return label;
    }

    private void makeClickable(JLabel label, String url) {
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openURL(url);
            }
        });
    }

    private void openURL(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            showError("Link Error", "Cannot open link: " + e.getMessage());
        }
    }

    // ==================== Back Button ====================
    private JPanel createBackButton() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        panel.setBackground(MAIN_BG);
        
        JButton backButton = new JButton("Back to Dashboard");
        backButton.setFont(new Font("Century Gothic", Font.BOLD, 12));
        backButton.setBackground(SIDEBAR_COLOR);
        backButton.setForeground(Color.WHITE);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        backButton.addActionListener(e -> navigateTo(Dashboard.class));
        
        panel.add(backButton);
        return panel;
    }

    // ==================== Utilities ====================
    private void showError(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }

    // ==================== Main ====================
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Hotlines hotlines = new Hotlines();
                hotlines.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, 
                    "Error starting Hotlines: " + e.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
