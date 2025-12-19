package model;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class News extends JFrame {

    private static final Color BG = new Color(222, 235, 239);
    private static final Color BLUE = new Color(27, 90, 120);
    private static final Color SIDEBAR_COLOR = new Color(37, 82, 103);
    private static final Color CARD = Color.WHITE;
    private static final Color ACTIVE_BG = new Color(218, 230, 235);
    private static final Color ACTIVE_TEXT = new Color(37, 82, 103);
    private static final Color HOVER_COLOR = new Color(25, 62, 79);

    // Sidebar panels
    private PanelRound sidebarPanel;
    private PanelRound dashboardItem;
    private PanelRound reportItem;
    private PanelRound hotlineItem;
    private PanelRound newsItem;
    private PanelRound logoutItem;
    
    // Track active panel
    private PanelRound activePanel = null;

    public News() {
        initUI();
    }

    private void initUI() {
        setTitle("News");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(960, 540);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Create sidebar similar to Dashboard
        sidebarPanel = createSidebar();
        add(sidebarPanel, BorderLayout.WEST);
        
        // Main content
        PanelRound mainContent = main();
        add(mainContent, BorderLayout.CENTER);
    }

    /* ================= SIDEBAR ================= */
    private PanelRound createSidebar() {
        PanelRound panel = new PanelRound();
        panel.setBackground(SIDEBAR_COLOR);
        panel.setPreferredSize(new Dimension(180, 540));

        // Create menu items with icons
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

        newsItem = createMenuItem(
                "/icons/icons8-news-16.png",
                "News"
        );

        logoutItem = createMenuItem(
                "/icons/icons8-logout-16.png",
                "Log out"
        );
        
        // Set initial active panel (newsItem)
        setActivePanel(newsItem);

        // Add mouse listeners for navigation
        dashboardItem.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                setActivePanel(dashboardItem);
                Dashboard dashboard = new Dashboard();
                dashboard.setVisible(true);
                News.this.dispose();
            }
        });

        reportItem.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                setActivePanel(reportItem);
                //ReportIncident report = new ReportIncident();
                //report.setVisible(true);
                News.this.dispose();
            }
        });

        hotlineItem.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                setActivePanel(hotlineItem);
                JOptionPane.showMessageDialog(News.this, "Hotlines feature coming soon!");
            }
        });

        newsItem.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                setActivePanel(newsItem);
                // Already on News page, do nothing
            }
        });

        logoutItem.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                setActivePanel(logoutItem);
                //LoginFrame login = new LoginFrame();
                //login.setVisible(true);
                News.this.dispose();
            }
        });

        // Layout
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

    // MENU ITEM (same as in Dashboard)
    private PanelRound createMenuItem(String iconPath, String text) {
        PanelRound item = new PanelRound();
        item.setBackground(SIDEBAR_COLOR);
        item.setRoundBottomLeft(50);
        item.setRoundTopLeft(50);
        item.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        item.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 10));

        // Try to load icon, fallback to text if not found
        JLabel iconLabel;
        try {
            iconLabel = new JLabel(new ImageIcon(getClass().getResource(iconPath)));
        } catch (Exception e) {
            iconLabel = new JLabel();
        }

        JLabel textLabel = new JLabel(text);
        textLabel.setFont(new Font("Century Gothic", Font.BOLD, 14));
        textLabel.setForeground(Color.WHITE);
        
        // Store the text label reference
        item.putClientProperty("textLabel", textLabel);

        item.add(iconLabel);
        item.add(textLabel);

        // Add hover effect
        item.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                if (item != activePanel) { // Don't change color for active item
                    item.setBackground(HOVER_COLOR);
                }
            }

            public void mouseExited(MouseEvent evt) {
                if (item != activePanel) { // Keep active item highlighted
                    item.setBackground(SIDEBAR_COLOR);
                }
            }
        });

        return item;
    }
    
    // Method to set active panel
    private void setActivePanel(PanelRound newActivePanel) {
        // Reset the previously active panel
        if (activePanel != null) {
            activePanel.setBackground(SIDEBAR_COLOR);
            JLabel prevTextLabel = (JLabel) activePanel.getClientProperty("textLabel");
            if (prevTextLabel != null) {
                prevTextLabel.setForeground(Color.WHITE);
            }
        }
        
        // Set the new active panel
        activePanel = newActivePanel;
        activePanel.setBackground(ACTIVE_BG);
        JLabel newTextLabel = (JLabel) activePanel.getClientProperty("textLabel");
        if (newTextLabel != null) {
            newTextLabel.setForeground(ACTIVE_TEXT);
        }
    }

    /* ================= MAIN ================= */

    private PanelRound main() {
        PanelRound m = new PanelRound();
        m.setLayout(new BorderLayout());
        m.setBackground(BG);
        m.setAllBorders(20);

        m.add(header(), BorderLayout.NORTH);

        PanelRound body = body();
        body.setBorder(new EmptyBorder(18, 18, 18, 18));
        m.add(body, BorderLayout.CENTER);

        return m;
    }

    private PanelRound header() {
        PanelRound h = new PanelRound();
        h.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        h.setBackground(CARD);
        h.setPreferredSize(new Dimension(780, 50));
        h.setBorder(new EmptyBorder(8, 12, 8, 12));
        h.setAllBorders(15);

        JLabel t1 = new JLabel("Main news and events ");
        t1.setFont(new Font("Century Gothic", Font.BOLD, 20));
        t1.setForeground(BLUE);

        JLabel t2 = new JLabel("in Miagao, Iloilo");
        t2.setFont(new Font("Century Gothic", Font.BOLD, 20));
        t2.setForeground(Color.GRAY);

        h.add(t1);
        h.add(t2);

        return h;
    }

    /* ================= BODY ================= */

    private PanelRound body() {
        PanelRound grid = new PanelRound();
        grid.setLayout(new GridBagLayout());
        grid.setBackground(BG);
        grid.setAllBorders(20);

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 6, 6, 6);
        g.fill = GridBagConstraints.BOTH;

        g.gridx = 0; g.gridy = 0; g.weightx = 0.62; g.weighty = 0.5;
        grid.add(featured(), g);

        g.gridx = 1; g.gridy = 0; g.weightx = 0.38; g.weighty = 0.7; g.gridheight = 2;
        grid.add(project(), g);

        g.gridx = 0; g.gridy = 1; g.weighty = 0.3; g.gridheight = 1;
        grid.add(infoRow(), g);

        g.gridx = 1; g.gridy = 2; g.weighty = 0.3;
        grid.add(recent(), g);

        g.gridx = 0; g.gridy = 2; g.weighty = 0.2;
        grid.add(clearing(), g);

        return grid;
    }

    /* ================= CARDS ================= */

    private PanelRound featured() {
        PanelRound c = card();
        c.setLayout(new BorderLayout(10, 0));

        c.add(imageIcon("src/model/lgu.png", 180, 125), BorderLayout.WEST);

        PanelRound txt = vbox();
        txt.add(section("Featured News", 15));
        txt.add(Box.createVerticalStrut(4));
        txt.add(bold("LGU Conducts Pre-Disaster Risk Assessment Ahead of Typhoon Uwan"));
        txt.add(text("MDRRMC conducted a pre-disaster risk assessment in preparation for Typhoon Uwan."));

        c.add(txt, BorderLayout.CENTER);
        return c;
    }

    private PanelRound project() {
        PanelRound c = card();
        c.setLayout(new BorderLayout(6, 6));

        PanelRound top = vbox();
        top.add(section("Miagao Project Update", 15));
        top.add(Box.createVerticalStrut(4));
        top.add(textItalic("Concreting of Road, Orbe Extension, Miagao"));

        c.add(top, BorderLayout.NORTH);
        c.add(imageIcon("src/model/project.png", 220, 130), BorderLayout.CENTER);

        return c;
    }

    private JPanel infoRow() {
        JPanel r = new JPanel(new GridLayout(1, 3, 6, 0));
        r.setBackground(BG);
        r.setOpaque(false);

        r.add(today());
        r.add(weather());
        r.add(advisory());

        return r;
    }

    private PanelRound today() {
        PanelRound c = card();
        c.setLayout(new BorderLayout());

        c.add(section("     Date Today", 14), BorderLayout.NORTH);
        
        LocalDate date = LocalDate.now();
        
        DateTimeFormatter formatMonthYear = DateTimeFormatter.ofPattern("MMM yyyy");
        DateTimeFormatter formatDay = DateTimeFormatter.ofPattern("d");
        
        String monthYear = date.format(formatMonthYear).toUpperCase();
        String day = date.format(formatDay).toUpperCase();

        JLabel d = new JLabel(day, SwingConstants.CENTER);
        d.setFont(new Font("Century Gothic", Font.BOLD, 30));
        d.setForeground(new Color(204, 153, 0));

        JLabel m = new JLabel(monthYear, SwingConstants.CENTER);
        m.setFont(new Font("Century Gothic", Font.PLAIN, 12));

        c.add(d, BorderLayout.CENTER);
        c.add(m, BorderLayout.SOUTH);

        return c;
    }

    private PanelRound weather() {
        PanelRound c = card();
        c.setLayout(new BorderLayout());

        c.add(section("          Alert", 14), BorderLayout.NORTH);

        JLabel t = new JLabel("Safe", SwingConstants.CENTER);
        t.setFont(new Font("Century Gothic", Font.BOLD, 25));
        t.setForeground(Color.GREEN);

        JLabel w = new JLabel("Normal", SwingConstants.CENTER);
        w.setFont(new Font("Century Gothic", Font.PLAIN, 13));

        c.add(t, BorderLayout.CENTER);
        c.add(w, BorderLayout.SOUTH);

        return c;
    }

    private PanelRound advisory() {
        PanelRound c = card();
        c.setLayout(new BorderLayout());

        c.add(section("Announements", 14), BorderLayout.NORTH);
        c.add(text("No Announcements Today"), BorderLayout.CENTER);

        return c;
    }

    private PanelRound recent() {
        PanelRound c = card();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));

        c.add(section("Recent News Today", 15));
        c.add(Box.createVerticalStrut(6));

        for (int i = 0; i < 3; i++) {
            c.add(textBold("00:00"));
            c.add(text("No Recent News"));
            c.add(Box.createVerticalStrut(8));
        }

        return c;
    }

    private PanelRound clearing() {
        PanelRound c = card();
        c.setLayout(new BorderLayout(10, 0));

        PanelRound txt = vbox();
        txt.add(section("<html> CLEARING OPERATIONS IN VARIOUS AREAS OF THE TOWN <html>", 14));
        txt.add(text("The Municipal Engineer's Office is currently spearheading the clearing operations across multiple "
                + "areas of the municipality, addressing landslides, fallen trees, and accumulated debris caused by Typhoon "
                + "Tino. Meanwhile, the General Services Office is leading efforts to restore cleanliness and order "
                + "throughout the town."));

        c.add(txt, BorderLayout.CENTER);
        c.add(imageIcon("src/model/clear.jpg", 180, 160), BorderLayout.EAST);

        return c;
    }

    /* ================= HELPERS ================= */

    private PanelRound card() {
        PanelRound p = new PanelRound();
        p.setBackground(CARD);
        p.setBorder(new EmptyBorder(10, 10, 10, 10));
        p.setAllBorders(15);
        return p;
    }

    private PanelRound vbox() {
        PanelRound p = new PanelRound();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(CARD);
        p.setAllBorders(10);
        return p;
    }

    private JLabel section(String s, int size) {
        JLabel l = new JLabel(s);
        l.setFont(new Font("Century Gothic", Font.BOLD, size));
        l.setForeground(BLUE);
        return l;
    }

    private JLabel bold(String s) {
        JLabel l = new JLabel("<html><b>" + s + "</b></html>");
        l.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        return l;
    }

    private JLabel text(String s) {
        JLabel l = new JLabel("<html>" + s + "</html>");
        l.setFont(new Font("Century Gothic", Font.PLAIN, 10));
        return l;
    }

    private JLabel textBold(String s) {
        JLabel l = new JLabel(s);
        l.setFont(new Font("Century Gothic", Font.BOLD, 11));
        return l;
    }

    private JLabel textItalic(String s) {
        JLabel l = new JLabel("<html><i>" + s + "</i></html>");
        l.setFont(new Font("Century Gothic", Font.PLAIN, 11));
        return l;
    }

    private JLabel imageIcon(String path, int w, int h) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);

        JLabel l = new JLabel(new ImageIcon(img));
        l.setPreferredSize(new Dimension(w, h));
        l.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

        return l;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new News().setVisible(true));
    }
}
