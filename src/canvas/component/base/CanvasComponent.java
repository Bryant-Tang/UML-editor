package canvas.component.base;

import javax.swing.JPanel;

import canvas.component.Port;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Rectangle;

public abstract class CanvasComponent extends JPanel {
    // constant value
    static Dimension defaultSize = new Dimension(1, 1);

    protected boolean select = false;

    protected CanvasComponent(Point position) {
        super();
        this.setLocation(position);
        this.setSize(defaultSize);
    }

    protected void resize() {
        this.revalidate();
        this.setSize(getPreferredSize());
    }

    @Override
    public void paint(Graphics g) {
        this.resize();
        super.paint(g);
    }

    public void shift(Point shift) {
        setLocation(getX() + shift.x, getY() + shift.y);
    }

    public Port getPort(Point position) {
        return null;
    }

    public Point getPortPosition(String side) {
        return null;
    }

    public boolean isPositionInside(Point position) {
        return position.x >= getX() && position.x <= getX() + getWidth() && position.y >= getY()
                && position.y <= getY() + getHeight();
    }

    public boolean isInsideRectangle(Rectangle rect) {
        return getX() >= rect.x && getY() >= rect.y && getX() + getWidth() <= rect.x + rect.width
                && getY() + getHeight() <= rect.y + rect.height;

    }

    public void setSelect(boolean select) {
        this.select = select;
        repaint();
    }

    public boolean isSelect() {
        return select;
    }

    public void addGroupContent(CanvasComponent components) {
    }

    public void removeGroupContent(CanvasComponent components) {
    }

    public void ungroup() {
    }

    public Point getRigntBottomLocation() {
        return new Point(getX() + getWidth(), getY() + getHeight());
    }
}
