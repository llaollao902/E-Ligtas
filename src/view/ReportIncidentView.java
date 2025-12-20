package view;

import controller.ReportIncidentController;
import util.UIConstants;
import view.components.PanelRound;

import javax.swing.*;
import java.awt.*;

public class ReportIncidentView extends BaseFrameWithSidebar {
    
    private ReportIncidentController controller;
    
    // Form components
    private JComboBox<String> typeComboBox;
    private JComboBox<String> locationComboBox;
    private JTextArea descriptionArea;
    private JTextField nameField;
    private JTextField contactField;
    
    // Placeholder constants
    private static final String PLACEHOLDER_DESCRIPTION = "Provide detailed information...";
    private static final String PLACEHOLDER_NAME = "Juan Dela Cruz";
    private static final String PLACEHOLDER_CONTACT = "09XX XXX XXXX";
    
    public ReportIncidentView() {
        super();
    }

    private ReportIncidentController getController() {
        if (this.controller == null) {
            this.controller = new ReportIncidentController();
        }
        return this.controller;
    }
    
    @Override
    protected String getFrameTitle() {
        return "Report Incident";
    }
    
    @Override
    protected PanelRound getActiveMenuItem() {
        return reportItem;
    }
    
    @Override
    protected JComponent createMainContent() {
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(UIConstants.MAIN_BG);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); 
        
        //Create Components
        JLabel titleLabel = new JLabel("Report an Incident");
        titleLabel.setFont(new Font("Century Gothic", Font.BOLD, 24));
        titleLabel.setForeground(UIConstants.ACTIVE_TEXT);
        
        JLabel subtitleLabel = new JLabel("Provide accurate information to help us respond quickly.");
        subtitleLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
        subtitleLabel.setForeground(Color.GRAY);
        
        JPanel formPanel = createFormPanel();
        
        // Main Layout
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGap(51) 
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(titleLabel)
                    .addComponent(subtitleLabel)
                    .addComponent(formPanel))
                .addContainerGap()
        );
        
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGap(5) 
                .addComponent(titleLabel)
                .addGap(2)
                .addComponent(subtitleLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(formPanel)
                .addContainerGap()
        );
        
        return mainPanel;
    }

    // creates the form panel
    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setBackground(UIConstants.MAIN_BG);
        
        // Labels
        JLabel l1 = createLabel("Type of Incident");
        JLabel l2 = createLabel("Location / Barangay");
        JLabel l3 = createLabel("Incident Description");
        JLabel l4 = createLabel("Name");
        JLabel l5 = createLabel("Contact No.");
        
        // Inputs
        typeComboBox = new JComboBox<>(new String[]{
            "Fire", "Flood", "Accident", "Crime", "Medical Emergency"
        });
        typeComboBox.setFont(new Font("Century Gothic", Font.PLAIN, 14));
        
        locationComboBox = new JComboBox<>(new String[]{
            "Agdum", "Aguiauan", "Alimodias", "Awang", "Bacauan", "Bacolod"
        });
        locationComboBox.setFont(new Font("Century Gothic", Font.PLAIN, 14));
        
        descriptionArea = new JTextArea();
        descriptionArea.setFont(new Font("Century Gothic", Font.PLAIN, 14));
        descriptionArea.setForeground(Color.GRAY);
        descriptionArea.setText(PLACEHOLDER_DESCRIPTION);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        addPlaceholderBehavior(descriptionArea, PLACEHOLDER_DESCRIPTION);
        
        JScrollPane sp = new JScrollPane(descriptionArea);
        
        nameField = new JTextField();
        nameField.setFont(new Font("Century Gothic", Font.PLAIN, 14));
        nameField.setForeground(Color.GRAY);
        nameField.setText(PLACEHOLDER_NAME);
        addPlaceholderBehavior(nameField, PLACEHOLDER_NAME);
        
        contactField = new JTextField();
        contactField.setFont(new Font("Century Gothic", Font.PLAIN, 14));
        contactField.setForeground(Color.GRAY);
        contactField.setText(PLACEHOLDER_CONTACT);
        addPlaceholderBehavior(contactField, PLACEHOLDER_CONTACT);
        
        JButton btn = new JButton("Submit Report");
        btn.setBackground(new Color(37, 82, 103));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.addActionListener(e -> handleSubmit());

        // Form Layout
        GroupLayout formLayout = new GroupLayout(formPanel);
        formPanel.setLayout(formLayout);
        
        formLayout.setHorizontalGroup(
            formLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(formLayout.createSequentialGroup()
                    .addGap(15)
                    .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(l1)
                        .addComponent(typeComboBox, 0, 608, Short.MAX_VALUE)
                        .addComponent(l2)
                        .addComponent(locationComboBox, 0, 608, Short.MAX_VALUE)
                        .addComponent(l3)
                        .addComponent(sp)
                        .addGroup(formLayout.createSequentialGroup()
                            .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(l4)
                                .addComponent(nameField, 305, 305, 305))
                            .addGap(20)
                            .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(l5)
                                .addComponent(contactField, 297, 297, 297)))
                        .addComponent(btn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
        );

        formLayout.setVerticalGroup(
            formLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(formLayout.createSequentialGroup()
                    .addComponent(l1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(typeComboBox, 30, 30, 30)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(l2)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(locationComboBox, 30, 30, 30)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(l3)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(sp, 180, 180, 180)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(l4)
                        .addComponent(l5))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(nameField, 30, 30, 30)
                        .addComponent(contactField, 30, 30, 30))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btn, 33, 33, 33))
        );
        
        return formPanel;
    }
    
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Century Gothic", Font.BOLD, 14));
        label.setForeground(UIConstants.ACTIVE_TEXT);
        return label;
    }
    
    private void addPlaceholderBehavior(JTextArea textArea, String placeholder) {
        textArea.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (textArea.getText().equals(placeholder)) {
                    textArea.setText("");
                    textArea.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (textArea.getText().trim().isEmpty()) {
                    textArea.setText(placeholder);
                    textArea.setForeground(Color.GRAY);
                }
            }
        });
    }
    
    private void addPlaceholderBehavior(JTextField textField, String placeholder) {
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (textField.getText().trim().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
    }

    // handles the data using the controller
    private void handleSubmit() {
        ReportIncidentController ctrl = getController();
        
        String incidentType = (String) typeComboBox.getSelectedItem();
        String location = (String) locationComboBox.getSelectedItem();
        String description = descriptionArea.getText().trim();
        String name = nameField.getText().trim();
        String contact = contactField.getText().trim();
        
        if (description.equals(PLACEHOLDER_DESCRIPTION)) description = "";
        if (name.equals(PLACEHOLDER_NAME)) name = "";
        if (contact.equals(PLACEHOLDER_CONTACT)) contact = "";
        
        ReportIncidentController.ValidationResult result = 
            ctrl.submitReport(incidentType, location, description, name, contact);
        
        if (result.isSuccess()) {
            showSuccessDialog(result.getMessage());
        } else {
            showErrorDialog(result.getMessage());
        }
    }
    
    private void showSuccessDialog(String message) {
        int response = JOptionPane.showConfirmDialog(
            this,
            message + "\n\nWould you like to return to the Dashboard?",
            "Success",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.INFORMATION_MESSAGE
        );
        
        if (response == JOptionPane.YES_OPTION) {
            navigateToFrame("view.DashboardView");
        } else {
            resetForm();
        }
    }
    
    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Validation Error", JOptionPane.WARNING_MESSAGE);
    }
    
    private void resetForm() {
        typeComboBox.setSelectedIndex(0);
        locationComboBox.setSelectedIndex(0);
        
        descriptionArea.setText(PLACEHOLDER_DESCRIPTION);
        descriptionArea.setForeground(Color.GRAY);
        
        nameField.setText(PLACEHOLDER_NAME);
        nameField.setForeground(Color.GRAY);
        
        contactField.setText(PLACEHOLDER_CONTACT);
        contactField.setForeground(Color.GRAY);
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new ReportIncidentView().setVisible(true));
    }
}
