package view;

import util.UIConstants;
import view.components.PanelRound;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * News View - Displays main news and events.
 * Follows MVC pattern - pure UI layer.
 * Extends BaseFrameWithSidebar for shared sidebar functionality (Inheritance).
 */
public class NewsView extends BaseFrameWithSidebar {
    
    // ==================== Constructor ====================
    
    public NewsView() {
        super();
    }
    
    // ==================== Abstract Method Implementations ====================
    
    @Override
    protected String getFrameTitle() {
        return "News";
    }
    
    @Override
    protected PanelRound getActiveMenuItem() {
        return newsItem;
    }
    
    @Override
    protected JComponent createMainContent() {
        PanelRound mainPanel = new PanelRound();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(UIConstants.MAIN_BG);
        mainPanel.setAllBorders(20);
        
        mainPanel.add(createHeader(), BorderLayout.NORTH);
        
        PanelRound body = createBody();
        body.setBorder(new EmptyBorder(18, 18, 18, 18));
        mainPanel.add(body, BorderLayout.CENTER);
        
        return mainPanel;
    }
    
    // ==================== Override Navigation ====================
    
    @Override
    protected void onNewsClick() {
        // Already on this page
    }
    
    // ==================== Header Creation ====================
    
    private PanelRound createHeader() {
        PanelRound header = new PanelRound();
        header.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        header.setBackground(Color.WHITE);
        header.setPreferredSize(new Dimension(780, 50));
        header.setBorder(new EmptyBorder(8, 12, 8, 12));
        header.setAllBorders(15);
        
        JLabel title1 = new JLabel("Main news and events ");
        title1.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, 20));
        title1.setForeground(UIConstants.BLUE_ACCENT);
        
        JLabel title2 = new JLabel("in Miagao, Iloilo");
        title2.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, 20));
        title2.setForeground(Color.GRAY);
        
        header.add(title1);
        header.add(title2);
        
        return header;
    }
    
    // ==================== Body Creation ====================
    
    private PanelRound createBody() {
        PanelRound grid = new PanelRound();
        grid.setLayout(new GridBagLayout());
        grid.setBackground(UIConstants.MAIN_BG);
        grid.setAllBorders(20);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.BOTH;
        
        // Featured news (left, top)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.62;
        gbc.weighty = 0.5;
        grid.add(createFeaturedNews(), gbc);
        
        // Project update (right, spans 2 rows)
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.38;
        gbc.weighty = 0.7;
        gbc.gridheight = 2;
        grid.add(createProjectUpdate(), gbc);
        
        // Info row (left, middle)
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0.3;
        gbc.gridheight = 1;
        grid.add(createInfoRow(), gbc);
        
        // Recent news (right, bottom)
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weighty = 0.3;
        grid.add(createRecentNews(), gbc);
        
        // Clearing operations (left, bottom)
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 0.2;
        grid.add(createClearingOperations(), gbc);
        
        return grid;
    }
    
    // ==================== Card Creation Methods ====================
    
    private PanelRound createFeaturedNews() {
        PanelRound card = createCard();
        card.setLayout(new BorderLayout(10, 0));
        
        JLabel imageLabel = createImageLabel("src/icons/lgu.png", 180, 125);
        
        PanelRound textPanel = createVerticalBox();
        textPanel.add(createSectionLabel("Featured News", 15));
        textPanel.add(Box.createVerticalStrut(4));
        textPanel.add(createBoldLabel("LGU Conducts Pre-Disaster Risk Assessment Ahead of Typhoon Uwan"));
        textPanel.add(createTextLabel("MDRRMC conducted a pre-disaster risk assessment in preparation for Typhoon Uwan."));
        
        card.add(imageLabel, BorderLayout.WEST);
        card.add(textPanel, BorderLayout.CENTER);
        
        return card;
    }
    
    private PanelRound createProjectUpdate() {
        PanelRound card = createCard();
        card.setLayout(new BorderLayout(6, 6));
        
        PanelRound topPanel = createVerticalBox();
        topPanel.add(createSectionLabel("Miagao Project Update", 15));
        topPanel.add(Box.createVerticalStrut(4));
        topPanel.add(createItalicLabel("Concreting of Road, Orbe Extension, Miagao"));
        
        JLabel imageLabel = createImageLabel("src/icons/project.png", 220, 130);
        
        card.add(topPanel, BorderLayout.NORTH);
        card.add(imageLabel, BorderLayout.CENTER);
        
        return card;
    }
    
    private JPanel createInfoRow() {
        JPanel row = new JPanel(new GridLayout(1, 3, 6, 0));
        row.setBackground(UIConstants.MAIN_BG);
        row.setOpaque(false);
        
        row.add(createTodayCard());
        row.add(createAlertCard());
        row.add(createAnnouncementsCard());
        
        return row;
    }
    
    private PanelRound createTodayCard() {
        PanelRound card = createCard();
        card.setLayout(new BorderLayout());
        
        card.add(createSectionLabel("     Date Today", 14), BorderLayout.NORTH);
        
        LocalDate date = LocalDate.now();
        DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MMM yyyy");
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("d");
        
        String monthYear = date.format(monthYearFormatter).toUpperCase();
        String day = date.format(dayFormatter);
        
        JLabel dayLabel = new JLabel(day, SwingConstants.CENTER);
        dayLabel.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, 30));
        dayLabel.setForeground(new Color(204, 153, 0));
        
        JLabel monthLabel = new JLabel(monthYear, SwingConstants.CENTER);
        monthLabel.setFont(new Font(UIConstants.FONT_FAMILY, Font.PLAIN, 12));
        
        card.add(dayLabel, BorderLayout.CENTER);
        card.add(monthLabel, BorderLayout.SOUTH);
        
        return card;
    }
    
    private PanelRound createAlertCard() {
        PanelRound card = createCard();
        card.setLayout(new BorderLayout());
        
        card.add(createSectionLabel("          Alert", 14), BorderLayout.NORTH);
        
        JLabel statusLabel = new JLabel("Safe", SwingConstants.CENTER);
        statusLabel.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, 25));
        statusLabel.setForeground(Color.GREEN);
        
        JLabel descLabel = new JLabel("Normal", SwingConstants.CENTER);
        descLabel.setFont(new Font(UIConstants.FONT_FAMILY, Font.PLAIN, 13));
        
        card.add(statusLabel, BorderLayout.CENTER);
        card.add(descLabel, BorderLayout.SOUTH);
        
        return card;
    }
    
    private PanelRound createAnnouncementsCard() {
        PanelRound card = createCard();
        card.setLayout(new BorderLayout());
        
        card.add(createSectionLabel("Announcements", 14), BorderLayout.NORTH);
        card.add(createTextLabel("No Announcements Today"), BorderLayout.CENTER);
        
        return card;
    }
    
    private PanelRound createRecentNews() {
        PanelRound card = createCard();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        
        card.add(createSectionLabel("Recent News Today", 15));
        card.add(Box.createVerticalStrut(6));
        
        for (int i = 0; i < 3; i++) {
            card.add(createBoldSmallLabel("00:00"));
            card.add(createTextLabel("No Recent News"));
            card.add(Box.createVerticalStrut(8));
        }
        
        return card;
    }
    
    private PanelRound createClearingOperations() {
        PanelRound card = createCard();
        card.setLayout(new BorderLayout(10, 0));
        
        PanelRound textPanel = createVerticalBox();
        textPanel.add(createSectionLabel("<html>CLEARING OPERATIONS IN VARIOUS AREAS OF THE TOWN</html>", 14));
        textPanel.add(createTextLabel(
            "The Municipal Engineer's Office is currently spearheading the clearing operations across multiple " +
            "areas of the municipality, addressing landslides, fallen trees, and accumulated debris caused by Typhoon " +
            "Tino. Meanwhile, the General Services Office is leading efforts to restore cleanliness and order " +
            "throughout the town."
        ));
        
        JLabel imageLabel = createImageLabel("src/icons/clear.jpg", 180, 160);
        
        card.add(textPanel, BorderLayout.CENTER);
        card.add(imageLabel, BorderLayout.EAST);
        
        return card;
    }
    
    // ==================== Helper Methods ====================
    
    private PanelRound createCard() {
        PanelRound panel = new PanelRound();
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setAllBorders(15);
        return panel;
    }
    
    private PanelRound createVerticalBox() {
        PanelRound panel = new PanelRound();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setAllBorders(10);
        return panel;
    }
    
    private JLabel createSectionLabel(String text, int size) {
        JLabel label = new JLabel(text);
        label.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, size));
        label.setForeground(UIConstants.BLUE_ACCENT);
        return label;
    }
    
    private JLabel createBoldLabel(String text) {
        JLabel label = new JLabel("<html><b>" + text + "</b></html>");
        label.setFont(new Font(UIConstants.FONT_FAMILY, Font.PLAIN, 12));
        return label;
    }
    
    private JLabel createBoldSmallLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, 11));
        return label;
    }
    
    private JLabel createTextLabel(String text) {
        JLabel label = new JLabel("<html>" + text + "</html>");
        label.setFont(new Font(UIConstants.FONT_FAMILY, Font.PLAIN, 10));
        return label;
    }
    
    private JLabel createItalicLabel(String text) {
        JLabel label = new JLabel("<html><i>" + text + "</i></html>");
        label.setFont(new Font(UIConstants.FONT_FAMILY, Font.PLAIN, 11));
        return label;
    }
    
    private JLabel createImageLabel(String path, int width, int height) {
        try {
            ImageIcon icon = new ImageIcon(path);
            Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            
            JLabel label = new JLabel(new ImageIcon(img));
            label.setPreferredSize(new Dimension(width, height));
            label.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
            
            return label;
        } catch (Exception e) {
            JLabel placeholder = new JLabel("[Image: " + width + "x" + height + "]");
            placeholder.setPreferredSize(new Dimension(width, height));
            placeholder.setHorizontalAlignment(SwingConstants.CENTER);
            placeholder.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            return placeholder;
        }
    }
    
    // ==================== Main Method ====================
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NewsView().setVisible(true));
    }
}
