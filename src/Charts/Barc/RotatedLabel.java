package Charts.Barc;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class RotatedLabel extends JLabel {

    private double angle;

    public RotatedLabel() {
        super();
    }

    public void setTextAndAngle(String text, double angle) {
        super.setText(text);
        this.angle = Math.toRadians(angle);
        FontMetrics fm = getFontMetrics(getFont());
        int width = fm.stringWidth(text);
        int height = fm.getHeight();
        setPreferredSize(new Dimension(width*12, height*12));
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        FontMetrics fm = g2.getFontMetrics();
        double textWidth = fm.stringWidth(getText());
        double textHeight = fm.getHeight();
        double x = getWidth() / 2.0;
        double y = getHeight() / 2.0;
        AffineTransform aT = AffineTransform.getTranslateInstance(x, y);
        aT.rotate(angle, 0, 0); // Rotar alrededor del centro del texto
        g2.transform(aT);
        g2.drawString(getText(), (float) (-textWidth/2 + fm.getAscent()),  (float) (textHeight / 2));
        g2.dispose();
    }
}
