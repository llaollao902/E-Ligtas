/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Windows
 */

import java.awt.*;
import javax.swing.JLabel;

public class ShadowLabel extends JLabel {

    private Color shadowColor = new Color(0, 0, 0, 100); // semi-transparent black
    private int xOffset = 3;
    private int yOffset = 3;

    public ShadowLabel(String text) {
        super(text);
        setForeground(Color.BLACK);
        setFont(new Font("Segoe UI", Font.BOLD, 48));
    }

    public void setShadowOffset(int x, int y) {
        this.xOffset = x;
        this.yOffset = y;
        repaint();
    }

    public void setShadowColor(Color color) {
        this.shadowColor = color;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        FontMetrics fm = g2.getFontMetrics(getFont());
        String text = getText();

        // Draw shadow first
        g2.setColor(shadowColor);
        g2.drawString(text, xOffset, fm.getAscent() + yOffset);

        // Draw main text
        g2.setColor(getForeground());
        g2.drawString(text, 0, fm.getAscent());

        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        FontMetrics fm = getFontMetrics(getFont());
        int width = fm.stringWidth(getText()) + xOffset;
        int height = fm.getHeight() + yOffset;
        return new Dimension(width, height);
    }
    
    @Override
    public void setForeground(Color fg) {
        super.setForeground(fg);
        // Also update shadow color if needed
    }
}
