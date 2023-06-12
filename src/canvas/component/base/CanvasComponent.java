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

    public void moveWithShift(Point shift) {
    }

    public Port getPort(Point position) {
        return null;
    }

    public Point getPortPosition(String side) {
        return null;
    }

    public boolean isPositionInside(Point position) {
        return false;
    }

    public boolean isInsideRectangle(Rectangle rect) {
        return false;
    }

    public void setSelect(boolean select) {
        repaint();
    }

    public boolean isSelect() {
        return select;
    }
}
