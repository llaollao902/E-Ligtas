package model;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class News extends JFrame {

    private static final Color BG = new Color(222, 235, 239);
    private static final Color BLUE = new Color(27, 90, 120);
    private static final Color SIDEBAR_COLOR = new Color(37, 82, 103);
    private static final Color CARD = Color.WHITE;

    // Sidebar panels
    private JPanel sidebarPanel;
    private JPanel dashboardItem;
    private JPanel reportItem;
    private JPanel hotlineItem;
    private JPanel newsItem;
    private JPanel logoutItem;

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
        JPanel mainContent = main();
        add(mainContent, BorderLayout.CENTER);
    }

    /* ================= SIDEBAR ================= */
    private JPanel createSidebar() {
        JPanel panel = new JPanel();
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
        
        // Set current item as active
        newsItem.setBackground(new Color(0, 102, 102));

        logoutItem = createMenuItem(
                "/icons/icons8-logout-16.png",
                "Log out"
        );

        // Add mouse listeners for navigation
        dashboardItem.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                Dashboard dashboard = new Dashboard();
                dashboard.setVisible(true);
                News.this.dispose();
            }
        });

        reportItem.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                //ReportIncident report = new ReportIncident();
                //report.setVisible(true);
                News.this.dispose();
            }
        });

        hotlineItem.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                // Create or open Hotlines frame
                // Hotlines hotlines = new Hotlines();
                // hotlines.setVisible(true);
                // News.this.dispose();
                JOptionPane.showMessageDialog(News.this, "Hotlines feature coming soon!");
            }
        });

        logoutItem.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
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
    private JPanel createMenuItem(String iconPath, String text) {
        JPanel item = new JPanel();
        item.setBackground(SIDEBAR_COLOR);
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

        item.add(iconLabel);
        item.add(textLabel);

        // Add hover effect
        item.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                if (item != newsItem) { // Don't change color for active item
                    item.setBackground(new Color(0, 102, 102));
                }
            }

            public void mouseExited(MouseEvent evt) {
                if (item != newsItem) { // Keep active item highlighted
                    item.setBackground(SIDEBAR_COLOR);
                }
            }
        });

        return item;
    }

    /* ================= MAIN ================= */

    private JPanel main() {
        JPanel m = new JPanel(new BorderLayout());
        m.setBackground(BG);

        m.add(header(), BorderLayout.NORTH);

        JPanel body = body();
        body.setBorder(new EmptyBorder(18, 18, 18, 18));
        m.add(body, BorderLayout.CENTER);

        return m;
    }

    private JPanel header() {
        JPanel h = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        h.setBackground(CARD);
        h.setPreferredSize(new Dimension(780, 50));
        h.setBorder(new EmptyBorder(8, 12, 8, 12));

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

    private JPanel body() {
        JPanel grid = new JPanel(new GridBagLayout());
        grid.setBackground(BG);

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

    private JPanel featured() {
        JPanel c = card();
        c.setLayout(new BorderLayout(10, 0));

        c.add(imageIcon("src/resources/featured.jpg", 180, 120), BorderLayout.WEST);

        JPanel txt = vbox();
        txt.add(section("Featured News", 15));
        txt.add(Box.createVerticalStrut(4));
        txt.add(bold("LGU Conducts Pre-Disaster Risk Assessment Ahead of Typhoon Uwan"));
        txt.add(text("MDRRMC conducted a pre-disaster risk assessment in preparation for Typhoon Uwan."));

        c.add(txt, BorderLayout.CENTER);
        return c;
    }

    private JPanel project() {
        JPanel c = card();
        c.setLayout(new BorderLayout(6, 6));

        JPanel top = vbox();
        top.add(section("Miagao Project Update", 15));
        top.add(Box.createVerticalStrut(4));
        top.add(textItalic("Concreting of Road, Orbe Extension, Miagao"));

        c.add(top, BorderLayout.NORTH);
        c.add(imageIcon("src/model/lgu.jpg", 230, 110), BorderLayout.CENTER);

        return c;
    }

    private JPanel infoRow() {
        JPanel r = new JPanel(new GridLayout(1, 3, 6, 0));
        r.setBackground(BG);

        r.add(today());
        r.add(weather());
        r.add(advisory());

        return r;
    }

    private JPanel today() {
        JPanel c = card();
        c.setLayout(new BorderLayout());

        c.add(section("             Today", 14), BorderLayout.NORTH);

        JLabel d = new JLabel("18", SwingConstants.CENTER);
        d.setFont(new Font("Century Gothic", Font.BOLD, 30));
        d.setForeground(new Color(204, 153, 0));

        JLabel m = new JLabel("Dec 2025", SwingConstants.CENTER);
        m.setFont(new Font("Century Gothic", Font.PLAIN, 12));

        c.add(d, BorderLayout.CENTER);
        c.add(m, BorderLayout.SOUTH);

        return c;
    }

    private JPanel weather() {
        JPanel c = card();
        c.setLayout(new BorderLayout());

        c.add(section("Weather Forecast", 14), BorderLayout.NORTH);

        JLabel t = new JLabel("23°", SwingConstants.CENTER);
        t.setFont(new Font("Century Gothic", Font.BOLD, 30));
        t.setForeground(new Color(0, 102, 153));

        JLabel w = new JLabel("Cloudy", SwingConstants.CENTER);
        w.setFont(new Font("Century Gothic", Font.PLAIN, 13));

        c.add(t, BorderLayout.CENTER);
        c.add(w, BorderLayout.SOUTH);

        return c;
    }

    private JPanel advisory() {
        JPanel c = card();
        c.setLayout(new BorderLayout());

        c.add(section("Advisory", 14), BorderLayout.NORTH);
        c.add(text("No Advisory Today"), BorderLayout.CENTER);

        return c;
    }

    private JPanel recent() {
        JPanel c = card();
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

    private JPanel clearing() {
        JPanel c = card();
        c.setLayout(new BorderLayout(10, 0));

        JPanel txt = vbox();
        txt.add(section("<html> CLEARING OPERATIONS IN VARIOUS AREAS OF THE TOWN <html>", 14));
        txt.add(text("The Municipal Engineer’s Office is currently spearheading the clearing operations across multiple "
        		+ "areas of the municipality, addressing landslides, fallen trees, and accumulated debris caused by Typhoon "
        		+ "Tino. Meanwhile, the General Services Office is leading efforts to restore cleanliness and order "
        		+ "throughout the town."));

        c.add(txt, BorderLayout.CENTER);
        c.add(imageIcon("src/resources/clearing.jpg", 180, 110), BorderLayout.EAST);

        return c;
    }

    /* ================= HELPERS ================= */

    private JPanel card() {
        JPanel p = new JPanel();
        p.setBackground(CARD);
        p.setBorder(new EmptyBorder(10, 10, 10, 10));
        return p;
    }

    private JPanel vbox() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(CARD);
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
        l.setFont(new Font("Century Gothic", Font.PLAIN, 11));
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