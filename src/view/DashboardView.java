package view;

import controller.DashboardController;
import model.Incident;
import model.IncidentStatistics;
import util.UIConstants;
import view.components.PanelRound;
import view.dialogs.ReportCardDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;


// Dashboard View - Main dashboard displaying incident reports and statistics.
// Extends BaseFrameWithSidebar for shared sidebar functionality (Inheritance).
public class DashboardView extends BaseFrameWithSidebar {
    
    private final DashboardController controller;
    
    // UI Components
    private JTable incidentsTable;
    private JLabel totalIncidentsLabel;
    private JLabel pendingLabel;
    private JLabel respondingLabel;
    private JLabel resolvedLabel;
    private JLabel fireCountLabel;
    private JLabel floodCountLabel;
    private JLabel accidentCountLabel;
    private JLabel crimeCountLabel;
    private JLabel medicalCountLabel;
    private JLabel reportsCountLabel;
    private JComboBox<String> typeFilterBox;
    private JComboBox<String> statusFilterBox;
    private JComboBox<String> locationFilterBox;
    private JTextField searchField;
    private List<Incident> allIncidents;
    
    // ==== Constructor ====
    
    public DashboardView() {
        super();
        this.controller = new DashboardController();
        loadData();
    }
    
    // ==== Abstract Method Implementations ====
    
    @Override
    protected String getFrameTitle() {
        return "Emergency Response System - Dashboard";
    }
    
    @Override
    protected PanelRound getActiveMenuItem() {
        return dashboardItem;
    }

    // creates the main scroll pane for all the content
    @Override
    protected JComponent createMainContent() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(UIConstants.MAIN_BG);
        
        mainPanel.add(createStatsPanel());
        mainPanel.add(createIncidentTypesPanel());
        mainPanel.add(createReportsPanel());
        
        scrollPane.setViewportView(mainPanel);
        return scrollPane;
    }
    
    
    @Override
    protected void onDashboardClick() {
        // already on this page
    }
    
    // uses the controller to load the date
    private void loadData() {
        allIncidents = controller.loadAllIncidents();
        updateStatistics();
        applyFilters();
    }
    
    // refreshes the dashboard data
    public void refreshDashboard() {
        loadData();
    }
    
    // method that creates the ui layout of the stats panel    
    private JPanel createStatsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(UIConstants.MAIN_BG);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        
        JLabel title = new JLabel("Incident Overview");
        title.setFont(UIConstants.FONT_TITLE);
        title.setForeground(UIConstants.ACTIVE_TEXT);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JPanel statsGrid = new JPanel(new GridLayout(1, 4, 15, 0));
        statsGrid.setBackground(UIConstants.MAIN_BG);
        statsGrid.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        statsGrid.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        totalIncidentsLabel = createStatCard("Total Incidents", UIConstants.ACTIVE_TEXT, statsGrid);
        pendingLabel = createStatCard("Pending", UIConstants.STATUS_PENDING, statsGrid);
        respondingLabel = createStatCard("Responding", UIConstants.STATUS_RESPONDING, statsGrid);
        resolvedLabel = createStatCard("Resolved", UIConstants.STATUS_RESOLVED, statsGrid);
        
        panel.add(title);
        panel.add(Box.createVerticalStrut(10));
        panel.add(statsGrid);
        
        return panel;
    }

    // helper method that creates each stat card
    private JLabel createStatCard(String title, Color valueColor, JPanel parent) {
        PanelRound card = new PanelRound();
        card.setBackground(Color.WHITE);
        card.setLayout(new BorderLayout(10, 5));
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        card.setAllBorders(15);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font(UIConstants.FONT_FAMILY, Font.PLAIN, 11));
        titleLabel.setForeground(new Color(102, 102, 102));
        
        JLabel valueLabel = new JLabel("0");
        valueLabel.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, 28));
        valueLabel.setForeground(valueColor);
        
        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);
        
        parent.add(card);
        return valueLabel;
    }
    
    
    // creates the panel for the incident types
    private JPanel createIncidentTypesPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(UIConstants.MAIN_BG);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        JLabel title = new JLabel("Incidents by Type");
        title.setFont(UIConstants.FONT_TITLE);
        title.setForeground(UIConstants.ACTIVE_TEXT);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JPanel typesGrid = new JPanel(new GridLayout(1, 5, 10, 0));
        typesGrid.setBackground(UIConstants.MAIN_BG);
        typesGrid.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
        typesGrid.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        fireCountLabel = createTypeCard("Fire", typesGrid);
        floodCountLabel = createTypeCard("Flood", typesGrid);
        accidentCountLabel = createTypeCard("Accident", typesGrid);
        crimeCountLabel = createTypeCard("Crime", typesGrid);
        medicalCountLabel = createTypeCard("Medical", typesGrid);
        
        panel.add(title);
        panel.add(Box.createVerticalStrut(10));
        panel.add(typesGrid);
        
        return panel;
    }

    // helper method that creates the format of the type cards
    private JLabel createTypeCard(String type, JPanel parent) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(new Color(255, 255, 255));
        card.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        card.setPreferredSize(new Dimension(120, 70));
        
        JLabel countLabel = new JLabel("0");
        countLabel.setFont(new Font(UIConstants.FONT_FAMILY, Font.BOLD, 24));
        countLabel.setForeground(UIConstants.ACTIVE_TEXT);
        countLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel typeLabel = new JLabel(type);
        typeLabel.setFont(new Font(UIConstants.FONT_FAMILY, Font.PLAIN, 11));
        typeLabel.setForeground(new Color(102, 102, 102));
        typeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        card.add(countLabel, BorderLayout.CENTER);
        card.add(typeLabel, BorderLayout.SOUTH);
        
        parent.add(card);
        return countLabel;
    }

    // creates the reports panel
    private JPanel createReportsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(UIConstants.MAIN_BG);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        
        JPanel headerPanel = createReportsHeader();
        headerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JPanel filtersPanel = createFiltersPanel();
        filtersPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JPanel tablePanel = createTablePanel();
        tablePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        panel.add(headerPanel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(filtersPanel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(tablePanel);
        
        return panel;
    }

    // creates the header for the reports
    private JPanel createReportsHeader() {
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        header.setBackground(UIConstants.MAIN_BG);
        header.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        
        JLabel title = new JLabel("Recent Reports ");
        title.setFont(UIConstants.FONT_TITLE);
        title.setForeground(UIConstants.ACTIVE_TEXT);
        
        reportsCountLabel = new JLabel("(0)");
        reportsCountLabel.setFont(new Font(UIConstants.FONT_FAMILY, Font.PLAIN, 18));
        reportsCountLabel.setForeground(Color.GRAY);
        
        header.add(title);
        header.add(reportsCountLabel);
        
        return header;
    }

    // creates the filters panel, the dropdown boxes
    private JPanel createFiltersPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panel.setBackground(UIConstants.MAIN_BG);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        
        // search field
        searchField = new JTextField(15);
        searchField.setFont(UIConstants.FONT_REGULAR);
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { applyFilters(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { applyFilters(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { applyFilters(); }
        });
        
        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setFont(UIConstants.FONT_SMALL);
        
        // Type filter
        typeFilterBox = new JComboBox<>(new String[]{
            "[None]", "Fire", "Flood", "Accident", "Crime", "Medical"
        });
        typeFilterBox.setFont(UIConstants.FONT_SMALL);
        
        // Status filter
        statusFilterBox = new JComboBox<>(new String[]{
            "[None]", "Pending", "Responding", "Resolved"
        });
        statusFilterBox.setFont(UIConstants.FONT_SMALL);
        
        // Location filter
        locationFilterBox = new JComboBox<>(new String[]{
            "[None]", "Agdum", "Aguiauan", "Alimodias", "Awang", "Bacauan", "Bacolod"
        });
        locationFilterBox.setFont(UIConstants.FONT_SMALL);
        
        addFilterListeners();
        
        panel.add(searchLabel);
        panel.add(searchField);
        panel.add(new JLabel("Type:"));
        panel.add(typeFilterBox);
        panel.add(new JLabel("Status:"));
        panel.add(statusFilterBox);
        panel.add(new JLabel("Location:"));
        panel.add(locationFilterBox);
        
        return panel;
    }

    // creates the table panel, includes jtable
    private JPanel createTablePanel() {
        PanelRound panel = new PanelRound();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BorderLayout());
        panel.setAllBorders(15);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
        
        String[] columnNames = {"Type", "Location", "Description", "Contact", "Reporter", "Date", "Status"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        incidentsTable = new JTable(model);
        incidentsTable.setFont(UIConstants.FONT_SMALL);
        incidentsTable.setRowHeight(25);
        incidentsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Add double-click listener
        incidentsTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int viewRow = incidentsTable.getSelectedRow();
                    if (viewRow >= 0) {
                        openReportCard(viewRow);
                    }
                }
            }
        });

        // enables users to scroll through the table
        JScrollPane scrollPane = new JScrollPane(incidentsTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    // the logic for filter listeners, makes the fitlers functional
    
    private void addFilterListeners() {
        ActionListener listener = e -> applyFilters();
        
        if (typeFilterBox != null) typeFilterBox.addActionListener(listener);
        if (statusFilterBox != null) statusFilterBox.addActionListener(listener);
        if (locationFilterBox != null) locationFilterBox.addActionListener(listener);
    }
    
    private void applyFilters() {
        if (incidentsTable == null) return;
        
        String typeFilter = typeFilterBox != null ? typeFilterBox.getSelectedItem().toString() : "[None]";
        String statusFilter = statusFilterBox != null ? statusFilterBox.getSelectedItem().toString() : "[None]";
        String locationFilter = locationFilterBox != null ? locationFilterBox.getSelectedItem().toString() : "[None]";
        String searchText = searchField != null ? searchField.getText() : "";
        
        List<Incident> filtered = controller.getFilteredIncidents(
            typeFilter, statusFilter, locationFilter, searchText
        );
        
        DefaultTableModel model = (DefaultTableModel) incidentsTable.getModel();
        model.setRowCount(0);
        
        for (Incident incident : filtered) {
            model.addRow(incident.toTableRow());
        }
        
        reportsCountLabel.setText("(" + filtered.size() + ")");
    }

    // uses the controller to update the statistics at the dashboard
    private void updateStatistics() {
        IncidentStatistics stats = controller.calculateStatistics(allIncidents);
        
        totalIncidentsLabel.setText(String.valueOf(stats.getTotalIncidents()));
        pendingLabel.setText(String.valueOf(stats.getPendingCount()));
        respondingLabel.setText(String.valueOf(stats.getRespondingCount()));
        resolvedLabel.setText(String.valueOf(stats.getResolvedCount()));
        
        fireCountLabel.setText(String.valueOf(stats.getFireCount()));
        floodCountLabel.setText(String.valueOf(stats.getFloodCount()));
        accidentCountLabel.setText(String.valueOf(stats.getAccidentCount()));
        crimeCountLabel.setText(String.valueOf(stats.getCrimeCount()));
        medicalCountLabel.setText(String.valueOf(stats.getMedicalCount()));
    }

    // opens the report card to view the certain report
    private void openReportCard(int viewRow) {
        int modelRow = incidentsTable.convertRowIndexToModel(viewRow);
        DefaultTableModel model = (DefaultTableModel) incidentsTable.getModel();
        
        String type = model.getValueAt(modelRow, 0).toString();
        String location = model.getValueAt(modelRow, 1).toString();
        String description = model.getValueAt(modelRow, 2).toString();
        String contact = model.getValueAt(modelRow, 3).toString();
        String reporter = model.getValueAt(modelRow, 4).toString();
        String date = model.getValueAt(modelRow, 5).toString();
        String status = model.getValueAt(modelRow, 6).toString();
        
        Incident incident = new Incident(type, location, description, reporter, contact, status, date);
        
        ReportCardDialog dialog = new ReportCardDialog(this, incident);
        dialog.setVisible(true);
    }
    
    // ==== Main Method ====
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            DashboardView dashboard = new DashboardView();
            dashboard.setVisible(true);
        });
    }

}
