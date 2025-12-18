package model;

import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import static javax.swing.GroupLayout.Alignment.*;
import static javax.swing.LayoutStyle.ComponentPlacement.*;

public class LoginFrame extends javax.swing.JFrame {

    public LoginFrame() {
        initComponents();
        setupActions();
        setupLeftPanelLayout();
        setupLayeredPaneLayout();
        setupMainLayout();
    }

    private void initComponents() {
        // --- Component Declaration ---
        jPanel1 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // --- Panel 1 (Left) ---
        jPanel1.setBackground(new java.awt.Color(37, 82, 103));

        // --- Layered Pane (Login Form) ---
        jLayeredPane1.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.setOpaque(true);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 36));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LOGIN");

        jLabel2.setText("Password");
        jLabel3.setText("Username");

        jRadioButton1.setText("Remember Me");

        jButton1.setText("Login");
    }

 
    // Action Handlers
    private void setupActions() {
        jButton1.addActionListener(e -> loginAction());
        jRadioButton1.addActionListener(e -> rememberMeAction());
        jTextField1.addActionListener(e -> loginAction());
        jTextField2.addActionListener(e -> loginAction());
    }

    private void loginAction() {
        String username = jTextField1.getText();
        String password = jTextField2.getText();

        if ("admin".equals(username) && ("admin".equals(password) || "1234".equals(password))) {
            // Open Dashboard
            Dashboard dashboard = new Dashboard();
            dashboard.setVisible(true);

            // Close current Login frame
            this.dispose();
        } else {
            // Show error message
            JOptionPane.showMessageDialog(this, "Invalid username or password.");
        }
    }


    private void rememberMeAction() {
        // TODO
    }

 
    // Layout Helpers
    private void setupLeftPanelLayout() {
        GroupLayout layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(LEADING)
                .addGap(0, 335, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
        );
    }

    private void setupLayeredPaneLayout() {
        GroupLayout layout = new GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addComponent(jLabel1, 258, 258, 258)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(22, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2)
                        .addComponent(jTextField1, 300, 300, 300)
                        .addGroup(layout.createParallelGroup(TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButton1)
                                .addPreferredGap(RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addComponent(jTextField2, 300, 300, 300)))
                    .addGap(18, 18, 18))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(66, 66, 66)
                    .addComponent(jLabel1, 68, 68, 68)
                    .addPreferredGap(RELATED)
                    .addComponent(jLabel3)
                    .addPreferredGap(RELATED)
                    .addComponent(jTextField1, 37, 37, 37)
                    .addPreferredGap(UNRELATED)
                    .addComponent(jLabel2)
                    .addGap(8, 8, 8)
                    .addComponent(jTextField2, 37, 37, 37)
                    .addGap(25, 25, 25)
                    .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(jRadioButton1)
                        .addComponent(jButton1))
                    .addContainerGap(102, Short.MAX_VALUE))
        );
    }

    private void setupMainLayout() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 
                    		GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jLayeredPane1, GroupLayout.PREFERRED_SIZE, 
                    		GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(2, 2, 2))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(LEADING)
                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLayeredPane1)
        );

        pack();
    }

    // Main Method
    public static void main(String args[]) {
   
    	// Create and display
        java.awt.EventQueue.invokeLater(() -> new LoginFrame().setVisible(true));
    }

    // Variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
}
