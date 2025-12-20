package view;

import controller.HotlinesController;
import model.HotlineContact;
import util.IconLoader;
import util.UIConstants;
import view.components.PanelRound;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

 // Hotlines View - displays emergency hotlines with navigation sidebar.
 // Extends BaseFrameWithSidebar for shared sidebar functionality
public class HotlinesView extends BaseFrameWithSidebar {
    
    private HotlinesController controller;
    
    // === Constructor ===
    
    public HotlinesView() {
        super();
    }

    // helper method
    private HotlinesController getController() {
        if (this.controller == null) {
            this.controller = new HotlinesController();
        }
        return this.controller;
    }
    
    // ==== Abstract Method Implementations ====
    
    @Override
    protected String getFrameTitle() {
        return "Emergency Hotlines";
    }
    
    @Override
    protected PanelRound getActiveMenuItem() {
        return hotlineItem;
    }
    
    @Override
    protected JComponent createMainContent() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(UIConstants.MAIN_BG);
        
        panel.add(createHeader(), BorderLayout.NORTH);
        panel.add(createHotlinesGrid(), BorderLayout.CENTER);
        panel.add(createBackButton(), BorderLayout.SOUTH);
        
        return panel;
    }
        
    @Override
    protected void onHotlineClick() {
        // already on this page
    }
    
    // creates the header of the hotline page
    private JPanel createHeader() {
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        header.setBackground(Color.WHITE);
        
        JLabel iconLabel = createHeaderIcon();
        JPanel textPanel = createHeaderText();
        
        header.add(iconLabel);
        header.add(textPanel);
        
        return header;
    }

    // creates the header icon and checks if the icon exists
    private JLabel createHeaderIcon() {
        try {
            JLabel iconLabel = IconLoader.createIconLabel(UIConstants.ICON_HELP, 32, 32);
            if (iconLabel.getIcon() == null) {
                iconLabel.setText("[ICON]");
                iconLabel.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, 32));
            }
            return iconLabel;
        } catch (Exception e) {
            return new JLabel("[?]");
        }
    }

    // creates the header test
    private JPanel createHeaderText() {
        JPanel textPanel = new JPanel();
        textPanel.setBackground(Color.WHITE);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        
        JLabel title = new JLabel("Emergency Hotlines");
        title.setFont(UIConstants.FONT_HEADER);
        
        JLabel subtitle = new JLabel("Contact numbers for emergency services");
        subtitle.setFont(UIConstants.FONT_SUBTITLE);
        subtitle.setForeground(Color.GRAY);
        
        textPanel.add(title);
        textPanel.add(subtitle);
        
        return textPanel;
    }
    
    // formats the grid of the hotlines    
    private JPanel createHotlinesGrid() {
        JPanel grid = new JPanel(new GridLayout(2, 3, 20, 20));
        grid.setBackground(UIConstants.MAIN_BG);
        grid.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        addHotlineCards(grid);
        
        return grid;
    }

    // adds the hotline cards to the UI layout
    private void addHotlineCards(JPanel grid) {
        List<HotlineContact> hotlines = getController().getAllHotlines();
        
        if (hotlines != null) {
            for (HotlineContact hotline : hotlines) {
                grid.add(createHotlineCard(hotline));
            }
        }
    }

    // creates the specific hotline cards
    private JPanel createHotlineCard(HotlineContact hotline) {
        PanelRound card = new PanelRound();
        card.setBackground(Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        card.setAllBorders(20);
        
        JLabel nameLabel = createCardLabel(hotline.getName(), Font.BOLD, 12, Color.BLACK);
        JLabel descLabel = createCardLabel(hotline.getDescription(), Font.PLAIN, 11, Color.DARK_GRAY);
        JLabel numberLabel = createCardLabel(hotline.getNumber(), Font.BOLD, 12, UIConstants.HOTLINE_ACCENT);
        
        makeClickable(numberLabel, hotline.getLink());
        
        card.add(nameLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(descLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(numberLabel);
        
        return card;
    }

    // helper method to create the card label
    private JLabel createCardLabel(String text, int style, int size, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(new Font(UIConstants.FONT_FAMILY, style, size));
        label.setForeground(color);
        return label;
    }

    // makes the hotlines clickable and opens to their corresponding url
    private void makeClickable(JLabel label, String url) {
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openURL(url);
            }
        });
    }

    // helper method to open the link, includes exception
    private void openURL(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            showError("Link Error", "Cannot open link: " + e.getMessage());
        }
    }
    
    // creates the back to dashboard button     
    private JPanel createBackButton() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        panel.setBackground(UIConstants.MAIN_BG);
        
        JButton backButton = new JButton("Back to Dashboard");
        backButton.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, 12));
        backButton.setBackground(UIConstants.SIDEBAR_COLOR);
        backButton.setForeground(Color.WHITE);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        backButton.addActionListener(e -> navigateToFrame("view.DashboardView"));
        
        panel.add(backButton);
        return panel;
    }
    
    // === Main Method ===    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                HotlinesView hotlines = new HotlinesView();
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
