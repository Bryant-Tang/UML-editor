package canvas.component.basic;

import java.awt.Color;
import java.awt.Point;

import main.Calculate;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

/**
 * a concrete component that represent the use case component in the UML class
 * diagram.
 */
public class UseCaseComponent extends BasicComponent {
    // constant value
    static double sqrt2 = Math.sqrt(2);
    static int borderThick = 1;
    static Point leftTop = Calculate.ZERO_POINT;

    /**
     * construct a UseCaseComponent.
     * 
     * @param position the initial position of this component
     * @param name     the initial name of this component
     */
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
        namePanel.setLocation(Calculate.half(this.getWidth() - namePanel.getWidth()),
                Calculate.half(this.getHeight() - namePanel.getHeight()));
    }

    private Point getInnerLeftTop() {
        return new Point(leftTop.x + borderThick, leftTop.y + borderThick);
    }

    private Point getInnerRightBottom() {
        return new Point(getWidth() - borderThick, getHeight() - borderThick);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(getBackground());
        g2d.fillOval(leftTop.x, leftTop.y, getInnerRightBottom().x - leftTop.x, getInnerRightBottom().y - leftTop.y);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(borderThick));
        g2d.drawOval(getInnerLeftTop().x, getInnerLeftTop().y, getInnerRightBottom().x - getInnerLeftTop().x,
                getInnerRightBottom().y - getInnerLeftTop().y);
        super.paintComponent(g);
    }
}