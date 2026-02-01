package view.dialogs;

import model.Incident;
import util.IconLoader;
import util.UIConstants;

import javax.swing.*;
import java.awt.*;

/**
 * Dialog for displaying detailed incident report information.
 * Follows Single Responsibility Principle - only displays incident details.
 * Accepts Incident model object 
 */
public class ReportCardDialog extends JDialog {

    private final Incident incident;
    
    // Layout control
    private static final int LEFT = 14;
    private static final int RIGHT_WIDTH = 357;
    private static final int LINE_GAP = 4;
    
    private int y = 10; // vertical cursor
    
    // ==================== Constructor ====================
    
    public ReportCardDialog(JFrame parent, Incident incident) {
        super(parent, "Incident Report Details", true);
        this.incident = incident;
        initUI();
    }
    
    // ==================== UI Initialization ====================
    
    private void initUI() {
        setSize(400, 600);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);
        
        addHeader(panel);
        addSeparator(panel);
        
        addStatus(panel);
        addLocation(panel);
        addDescription(panel);
        addReporterContact(panel);
        
        addSeparator(panel);
        addTimeline(panel);
        addCloseButton(panel);
        
        setContentPane(panel);
        setLocationRelativeTo(getParent());
    }
    
    // ==================== Header Section ====================
    
    private void addHeader(JPanel p) {
        ImageIcon icon = IconLoader.loadIcon(UIConstants.getIncidentIcon(incident.getType()));
        if (icon != null) {
            addIcon(icon, LEFT, y, p);
        }
        
        JLabel title = label(incident.getType() + " Incident", 15, Font.BOLD);
        title.setBounds(LEFT + 28, y, 250, 20);
        p.add(title);
        
        y += 22;
        
        JLabel subtitle = label("Full details of the incident report", 11, Font.PLAIN);
        subtitle.setForeground(Color.GRAY);
        subtitle.setBounds(LEFT, y, 300, 16);
        p.add(subtitle);
        
        y += 22;
    }
    
    private void addStatus(JPanel p) {
        JLabel statusLabel = label("Status", 14, Font.BOLD);
        statusLabel.setForeground(Color.GRAY);
        statusLabel.setBounds(LEFT, y, 100, 20);
        p.add(statusLabel);
        
        JPanel badge = new JPanel(new GridBagLayout());
        badge.setBounds(270, y - 2, 100, 24);
        badge.setBackground(UIConstants.getStatusColor(incident.getStatus()));
        
        JLabel text = new JLabel(incident.getStatus().toUpperCase());
        text.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, 11));
        text.setForeground(Color.WHITE);
        badge.add(text);
        
        p.add(badge);
        y += 30;
    }
    
    private void addLocation(JPanel p) {
        addIcon("/icons/icons8-location-20.png", LEFT, y, p);
        
        JLabel label = sectionLabel("Location");
        label.setBounds(LEFT + 24, y, 120, 20);
        p.add(label);
        
        y += 22;
        
        JLabel value = text(incident.getLocation());
        value.setBounds(LEFT + 24, y, 320, 16);
        p.add(value);
        
        y += 26;
    }
    
    private void addDescription(JPanel p) {
        JLabel label = sectionLabel("Description");
        label.setBounds(LEFT, y, 150, 20);
        p.add(label);
        
        y += 24;
        
        JTextArea area = new JTextArea(incident.getDescription());
        area.setWrapStyleWord(true);
        area.setLineWrap(true);
        area.setEditable(false);
        area.setFont(new Font(UIConstants.FONT_FAMILY, Font.PLAIN, 12));
        area.setBackground(new Color(234, 234, 234));
        area.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        
        JScrollPane scroll = new JScrollPane(area);
        scroll.setBounds(LEFT, y, RIGHT_WIDTH, 190);
        scroll.setBorder(null);
        
        p.add(scroll);
        y += 200;
    }
    
    private void addReporterContact(JPanel p) {
        addIcon("/icons/icons8-person-20.png", LEFT, y, p);
        
        JLabel reporterLabel = sectionLabel("Reporter");
        reporterLabel.setBounds(LEFT + 24, y, 100, 20);
        p.add(reporterLabel);
        
        y += 22;
        
        JLabel reporterValue = text(incident.getReporter());
        reporterValue.setBounds(LEFT + 24, y, 150, 16);
        p.add(reporterValue);
        
        addIcon("/icons/icons8-call-20.png", 205, y - 22, p);
        
        JLabel contactLabel = sectionLabel("Contact");
        contactLabel.setBounds(230, y - 22, 100, 20);
        p.add(contactLabel);
        
        JLabel contactValue = text(incident.getContact());
        contactValue.setBounds(230, y, 140, 16);
        p.add(contactValue);
        
        y += 28;
    }
    
    private void addTimeline(JPanel p) {
        addIcon("/icons/icons8-clock-20.png", LEFT, y, p);
        
        JLabel label = sectionLabel("Timeline");
        label.setBounds(LEFT + 24, y, 100, 20);
        p.add(label);
        
        y += 22;
        
        JLabel value = text("<html>Reported: " + incident.getDate() + 
                          "<br>Last Updated: " + incident.getDate() + "</html>");
        value.setFont(new Font(UIConstants.FONT_FAMILY, Font.PLAIN, 11));
        value.setBounds(LEFT + 24, y, 300, 30);
        p.add(value);
        
        y += 40;
    }
    
    private void addCloseButton(JPanel p) {
        JButton close = new JButton("Close");
        close.setBounds(290, 515, 75, 25);
        close.setFocusPainted(false);
        close.addActionListener(e -> dispose());
        p.add(close);
    }
    
    private void addSeparator(JPanel p) {
        y += LINE_GAP;
        JSeparator sep = new JSeparator();
        sep.setBounds(LEFT, y, RIGHT_WIDTH, 1);
        p.add(sep);
        y += LINE_GAP + 4;
    }
    
    // ==================== Helper Methods ====================
    
    private JLabel label(String text, int size, int style) {
        JLabel l = new JLabel(text);
        l.setFont(new Font(UIConstants.FONT_FAMILY, style, size));
        l.setForeground(UIConstants.ACTIVE_TEXT);
        return l;
    }
    
    private JLabel sectionLabel(String text) {
        return label(text, 14, Font.BOLD);
    }
    
    private JLabel text(String text) {
        return label(text, 12, Font.PLAIN);
    }
    
    private void addIcon(String path, int x, int y, JPanel p) {
        try {
            JLabel icon = new JLabel(new ImageIcon(getClass().getResource(path)));
            icon.setBounds(x, y, 20, 20);
            p.add(icon);
        } catch (Exception ignored) {}
    }
    
    private void addIcon(ImageIcon icon, int x, int y, JPanel p) {
        JLabel l = new JLabel(icon);
        l.setBounds(x, y, 20, 20);
        p.add(l);
    }
}
