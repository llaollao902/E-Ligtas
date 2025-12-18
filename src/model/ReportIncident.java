package model;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class ReportIncident extends JFrame {

    // Sidebar panels
    private JPanel sidebarPanel;
    private JPanel dashboardItem;
    private JPanel reportItem;
    private JPanel hotlineItem;
    private JPanel newsItem;
    private JPanel logoutItem;

    public ReportIncident() {
        initUI();
    }

    private void initUI() {
        setTitle("Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(960, 540));

        sidebarPanel = createSidebar();

        setLayout(new BorderLayout());
        add(sidebarPanel, BorderLayout.WEST);

        pack();
        setLocationRelativeTo(null);
    }

    // SIDEBAR CREATOR
    private JPanel createSidebar() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(37, 82, 103));
        panel.setPreferredSize(new Dimension(180, 540));

        dashboardItem = createMenuItem(
                "/icons/icons8-dashboard-16.png",
                "Dashboard"
        );

        reportItem = createMenuItem(
                "/icons/icons8-danger-16 (1).png",
                "Report Incident"
        );
        
        reportItem.setBackground(new Color(0, 102, 102));

        hotlineItem = createMenuItem(
                "/icons/icons8-telephone-16 (1).png",
                "Hotlines"
        );

        newsItem = createMenuItem(
                "/icons/icons8-news-16.png",
                "News"
        );

        logoutItem = createMenuItem(
                "/icons/icons8-logout-16.png",
                "Log out"
        );
        
        // Events
        logoutItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	// Back to Login Frame
                LoginFrame login = new LoginFrame();
                login.setVisible(true);

                // Close current Frame
                ReportIncident.this.dispose();
            }
        });
        
        dashboardItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	// Back to Dashboard Frame
                Dashboard dashboard = new Dashboard();
                dashboard.setVisible(true);

                // Close current Frame
                ReportIncident.this.dispose();
            }
        });

        // SIDEBAR LAYOUT 
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(Box.createVerticalStrut(100));
        panel.add(dashboardItem);
        panel.add(reportItem);
        panel.add(hotlineItem);
        panel.add(newsItem);

        panel.add(Box.createVerticalGlue());
        panel.add(logoutItem);
        panel.add(Box.createVerticalStrut(20));

        return panel;
    }

    // MENU ITEM
    private JPanel createMenuItem(String iconPath, String text) {
        JPanel item = new JPanel();
        item.setBackground(new Color(37, 82, 103));
        item.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        item.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 10));

        JLabel iconLabel = new JLabel(new ImageIcon(getClass().getResource(iconPath)));
        JLabel textLabel = new JLabel(text);
        textLabel.setFont(new Font("Century Gothic", Font.BOLD, 14));
        textLabel.setForeground(Color.WHITE);

        item.add(iconLabel);
        item.add(textLabel);

        return item;
    }

    // MAIN
    public static void main(String[] args) {
    	// Create and Display
       EventQueue.invokeLater(() -> new ReportIncident().setVisible(true));
    }
}
