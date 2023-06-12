package canvas.component.basic;

import java.awt.Color;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

public class UseCaseComponent extends BasicComponent {
    static double sqrt2 = Math.sqrt(2);

    public UseCaseComponent(Point position, String name) {
        super(position, name);
        this.setLayout(null);
        this.setOpaque(false);
    }

    @Override
    protected void resize() {
        setPreferredSize(calculateSize());
        super.resize();
        setNamePanelLocationToCenter();
        namePanel.setSize(namePanel.getPreferredSize());
    }

    private Dimension calculateSize() {
        return new Dimension(((int) Math.round(sqrt2 * namePanel.getPreferredSize().getWidth())),
                ((int) Math.round(sqrt2 * namePanel.getPreferredSize().getHeight())));
    }

    private void setNamePanelLocationToCenter() {
        namePanel.setLocation((this.getWidth() - namePanel.getWidth()) / 2,
                (this.getHeight() - namePanel.getHeight()) / 2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(getBackground());
        g2d.fillOval(0, 0, getWidth() - 1, getHeight() - 1);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(1, 1, getWidth() - 2, getHeight() - 2);
        super.paintComponent(g);
    }
}