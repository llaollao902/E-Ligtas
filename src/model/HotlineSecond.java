import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HotlineSecond extends JFrame {

    // Sidebar panels
    private JPanel sidebarPanel;
    private JPanel dashboardItem;
    private JPanel reportItem;
    private JPanel hotlineItem;
    private JPanel newsItem;
    private JPanel logoutItem;

    // Main content panel
    private JPanel mainPanel;

    public HotlineSecond() {
        initUI();
    }

    private void initUI() {
        setTitle("Emergency Hotlines");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(960, 540));

        sidebarPanel = createSidebar();
        mainPanel = createMainContent();

        setLayout(new BorderLayout());
        add(sidebarPanel, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    // =========================
    // SIDEBAR CREATOR
    // =========================
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

        hotlineItem = createMenuItem(
                "/icons/icons8-telephone-16 (1).png",
                "Hotlines"
        );

        hotlineItem.setBackground(new Color(0, 102, 102)); // active page

        newsItem = createMenuItem(
                "/icons/icons8-news-16.png",
                "News"
        );

        logoutItem = createMenuItem(
                "/icons/icons8-logout-16.png",
                "Log out"
        );

        /*
        reportItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ReportIncident report = new ReportIncident();
                report.setVisible(true);
                HotlineFirst.this.dispose();
            }
        });*/

        logoutItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LoginFrame login = new LoginFrame();
                login.setVisible(true);
                HotlineSecond.this.dispose();
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

    // =========================
    // MENU ITEM
    // =========================
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

    // =========================
    // MAIN CONTENT
    // =========================
    private JPanel createMainContent() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(218, 230, 235));
        panel.setLayout(new BorderLayout());

        panel.add(createHeader(), BorderLayout.NORTH);
        panel.add(createHotlines(), BorderLayout.CENTER);
        panel.add(createNextButton(), BorderLayout.SOUTH);

        return panel;
    }

    // HEADER
    private JPanel createHeader() {
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        header.setBackground(Color.WHITE);

        JLabel icon = new JLabel(new ImageIcon(getClass().getResource("/icons/help (1).png")));

        JLabel title = new JLabel("Emergency Hotlines");
        title.setFont(new Font("Century Gothic", Font.BOLD, 32));

        JLabel subtitle = new JLabel("Contact numbers for emergency services");
        subtitle.setFont(new Font("Century Gothic", Font.PLAIN, 14));
        subtitle.setForeground(Color.GRAY);

        JPanel textPanel = new JPanel();
        textPanel.setBackground(Color.WHITE);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.add(title);
        textPanel.add(subtitle);

        header.add(icon);
        header.add(textPanel);

        return header;
    }

    // HOTLINE CARDS
    private JPanel createHotlines() {
        JPanel grid = new JPanel(new GridLayout(2, 3, 20, 20));
        grid.setBackground(new Color(218, 230, 235));
        grid.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

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

        return grid;
    }

    // SINGLE HOTLINE CARD
    private JPanel createHotlineCard(String name, String desc, String number, String link) {
        JPanel card = new project.PanelRound();
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Century Gothic", Font.BOLD, 12));

        JLabel descLabel = new JLabel(desc);
        descLabel.setFont(new Font("Century Gothic", Font.PLAIN, 11));
        descLabel.setForeground(Color.DARK_GRAY);

        JLabel numberLabel = new JLabel(number);
        numberLabel.setFont(new Font("Century Gothic", Font.BOLD, 12));
        numberLabel.setForeground(new Color(3, 92, 108));
        makeLabelClickable(numberLabel, link);

        card.add(nameLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(descLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(numberLabel);

        return card;
    }

    // NEXT BUTTON
    private JPanel createNextButton() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        panel.setBackground(new Color(218, 230, 235));

        JButton next = new JButton("Back");
        next.addActionListener(e -> {
            HotlineFirst page1 = new HotlineFirst();
            page1.setVisible(true);
            dispose();
        });

        panel.add(next);
        return panel;
    }

    // =========================
    // UTILITIES
    // =========================
    private void makeLabelClickable(JLabel label, String url) {
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    Desktop.getDesktop().browse(new URI(url));
                } catch (IOException | URISyntaxException e) {
                    // fail silently
                }
            }
        });
    }

    // MAIN
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new HotlineFirst().setVisible(true));
    }
}
