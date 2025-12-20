package view.components;

import java.awt.*;
import javax.swing.JLabel;

// Custom JLabel with a drop-shadow effect for high-visibility titles
public class ShadowLabel extends JLabel {

    private Color shadowColor = new Color(0, 0, 0, 100);
    private int xOffset = 3;
    private int yOffset = 3;

    public ShadowLabel(String text) {
        super(text);
        // Default styling - usually overridden in the View
        setFont(new Font("Segoe UI", Font.BOLD, 48));
    }

    // ==== SETTERS ====

    public void setShadowOffset(int x, int y) {
        this.xOffset = x;
        this.yOffset = y;
        repaint();
    }

    public void setShadowColor(Color color) {
        this.shadowColor = color;
        repaint();
    }

    // ==== PAINTING ====

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        
        // Enable Antialiasing for smooth text
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        FontMetrics fm = g2.getFontMetrics(getFont());
        String text = getText();

        // 1. Draw shadow (offset position)
        g2.setColor(shadowColor);
        g2.drawString(text, xOffset, fm.getAscent() + yOffset);

        // 2. Draw main text (original position)
        g2.setColor(getForeground());
        g2.drawString(text, 0, fm.getAscent());

        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        FontMetrics fm = getFontMetrics(getFont());
        int width = fm.stringWidth(getText()) + Math.abs(xOffset);
        int height = fm.getHeight() + Math.abs(yOffset);
        return new Dimension(width, height);
    }

}
