package view;

import view.components.PanelRound;
import util.UIConstants;
import util.IconLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// abstract class for the side bar navigation
public abstract class BaseFrameWithSidebar extends JFrame {
    
    // components of the sidebar
    protected PanelRound sidebarPanel;
    protected PanelRound dashboardItem;
    protected PanelRound reportItem;
    protected PanelRound hotlineItem;
    protected PanelRound newsItem;
    protected PanelRound logoutItem;
    protected PanelRound activePanel;
    
    // === Constructor ===
    public BaseFrameWithSidebar() {
        initializeFrame();
        buildUI();
    }
    
    // === method for the template ===
    private void buildUI() {
        sidebarPanel = createSidebar();
        JComponent mainContent = createMainContent();
        
        add(sidebarPanel, BorderLayout.WEST);
        add(mainContent, BorderLayout.CENTER);
        
        pack();
        setLocationRelativeTo(null);
    }
    
    // === Abstract Methods ===
    
    /**
     * @return The main content component
     */
    protected abstract JComponent createMainContent();
    
    /**
     * @return The active menu item
     */
    protected abstract PanelRound getActiveMenuItem();
    
    /**
     * @return Title for the frame
     */
    protected abstract String getFrameTitle();
    
    
    // initializes the frame
    private void initializeFrame() {
        setTitle(getFrameTitle());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(UIConstants.WINDOW_WIDTH, UIConstants.WINDOW_HEIGHT));
        setLayout(new BorderLayout());
    }
    
    /**
     * @return Configured sidebar panel
     */
    protected PanelRound createSidebar() {
        PanelRound sidebar = new PanelRound();
        sidebar.setBackground(UIConstants.SIDEBAR_COLOR);
        sidebar.setPreferredSize(new Dimension(UIConstants.SIDEBAR_WIDTH, UIConstants.WINDOW_HEIGHT));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        
        initializeMenuItems();
        setupMenuNavigation();
        layoutSidebar(sidebar);
        
        return sidebar;
    }

    // creates the items at the side bar
    private void initializeMenuItems() {
        dashboardItem = createMenuItem(UIConstants.ICON_DASHBOARD, "Dashboard");
        reportItem = createMenuItem(UIConstants.ICON_REPORT, "Report Incident");
        hotlineItem = createMenuItem(UIConstants.ICON_HOTLINE, "Hotlines");
        newsItem = createMenuItem(UIConstants.ICON_NEWS, "News");
        logoutItem = createMenuItem(UIConstants.ICON_LOGOUT, "Log out");
        
        setActivePanel(getActiveMenuItem());
    }
    
    // set ups the listeners for navigation
    protected void setupMenuNavigation() {
        addNavigationListener(dashboardItem, this::onDashboardClick);
        addNavigationListener(reportItem, this::onReportClick);
        addNavigationListener(hotlineItem, this::onHotlineClick);
        addNavigationListener(newsItem, this::onNewsClick);
        addNavigationListener(logoutItem, this::onLogoutClick);
    }
    
    // layouts the sidebar by adding into the frame
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
    
    /**
     * @param iconFileName Icon file name
     * @param text Menu text
     * @return Configured menu item panel
     */
    protected PanelRound createMenuItem(String iconFileName, String text) {
        PanelRound item = createMenuItemPanel();
        JLabel iconLabel = IconLoader.createIconLabel(iconFileName);
        JLabel textLabel = createTextLabel(text);
        
        item.putClientProperty("textLabel", textLabel);
        item.add(iconLabel);
        item.add(textLabel);
        
        addHoverEffect(item);
        
        return item;
    }

    
    // helper method that creates the layout of the menu item
    private PanelRound createMenuItemPanel() {
        PanelRound item = new PanelRound();
        item.setBackground(UIConstants.SIDEBAR_COLOR);
        item.setRoundBottomLeft(50);
        item.setRoundTopLeft(50);
        item.setMaximumSize(new Dimension(Integer.MAX_VALUE, UIConstants.MENU_ITEM_HEIGHT));
        item.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 10));
        return item;
    }

    // creates the layout of the text
    private JLabel createTextLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(UIConstants.FONT_MENU);
        label.setForeground(Color.WHITE);
        return label;
    }

    
    // creates the hover effect at the sidebar
    private void addHoverEffect(PanelRound item) {
        item.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                if (item != activePanel) {
                    item.setBackground(UIConstants.HOVER_COLOR);
                }
            }
            
            public void mouseExited(MouseEvent evt) {
                if (item != activePanel) {
                    item.setBackground(UIConstants.SIDEBAR_COLOR);
                }
            }
        });
    }
    
    /**
     * adds the navigation listener to a menu item
     * @param menuItem Menu panel
     * @param action Action to execute
     */
    protected void addNavigationListener(PanelRound menuItem, Runnable action) {
        menuItem.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                setActivePanel(menuItem);
                action.run();
            }
        });
    }
    
    /**
     * Set the active menu panel.
     * @param newActivePanel The panel to make active
     */
    protected void setActivePanel(PanelRound newActivePanel) {
        resetPreviousActivePanel();
        activateNewPanel(newActivePanel);
    }

    // resets the previous action panel
    private void resetPreviousActivePanel() {
        if (activePanel != null) {
            activePanel.setBackground(UIConstants.SIDEBAR_COLOR);
            JLabel prevTextLabel = (JLabel) activePanel.getClientProperty("textLabel");
            if (prevTextLabel != null) {
                prevTextLabel.setForeground(Color.WHITE);
            }
        }
    }

    // activates the new panel
    private void activateNewPanel(PanelRound newActivePanel) {
        activePanel = newActivePanel;
        activePanel.setBackground(UIConstants.ACTIVE_BG);
        JLabel newTextLabel = (JLabel) activePanel.getClientProperty("textLabel");
        if (newTextLabel != null) {
            newTextLabel.setForeground(UIConstants.ACTIVE_TEXT);
        }
    }
    
    // === Navigation Methods ===
    
    protected void onDashboardClick() {
        navigateToFrame("view.DashboardView");
    }
    
    protected void onReportClick() {
        navigateToFrame("view.ReportIncidentView");
    }
    
    protected void onHotlineClick() {
        navigateToFrame("view.HotlinesView");
    }
    
    protected void onNewsClick() {
        navigateToFrame("view.NewsView");
    }
    
    protected void onLogoutClick() {
        dispose();
    }
    
    /**
     * navigates to another frame by class name
     * @param className Fully qualified class name
     */
    protected void navigateToFrame(String className) {
        try {
            Class<?> frameClass = Class.forName(className);
            JFrame frame = (JFrame) frameClass.getDeclaredConstructor().newInstance();
            frame.setVisible(true);
            dispose();
        } catch (Exception e) {
            showError("Navigation Error", "Unable to navigate to " + className);
        }
    }
    
    /**
     * show error dialog.
     * @param title Dialog title
     * @param message Error message
     */
    protected void showError(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }
}
