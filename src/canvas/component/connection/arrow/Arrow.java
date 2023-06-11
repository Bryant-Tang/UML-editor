package canvas.component.connection.arrow;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Graphics;

import javax.swing.JPanel;

import canvas.component.connection.ConnectionComponent;

public abstract class Arrow extends JPanel {
    public static final String GO_UP = "go_up";
    public static final String GO_DOWN = "go_down";
    public static final String GO_LEFT = "go_left";
    public static final String GO_RIGHT = "go_right";
    static Dimension arrowSize = new Dimension(10, 10);
    static int borderThick = 1;

    protected ConnectionComponent belongConnection;

    protected Arrow() {
        setOpaque(false);
        setLayout(null);
        setSize(arrowSize);
    }

    public void setBelongConnection(ConnectionComponent belongConnection) {
        this.belongConnection = belongConnection;
    }

    public void shift(int x, int y) {
        setLocation(getX() + x, getY() + y);
    }

    protected Point getTopCenterInnerPosition() {
        return new Point(getWidth() / 2, borderThick);
    }

    protected Point getBottomCenterInnerPosition() {
        return new Point(getWidth() / 2, getHeight() - borderThick);
    }

    protected Point getLeftCenterInnerPosition() {
        return new Point(borderThick, getHeight() / 2);
    }

    protected Point getRightCenterInnerPosition() {
        return new Point(getWidth() - borderThick, getHeight() / 2);
    }

    protected Point getTopLeftInnerPosition() {
        return new Point(borderThick, borderThick);
    }

    protected Point getTopRightInnerPosition() {
        return new Point(getWidth() - borderThick, borderThick);
    }

    protected Point getBottomLeftInnerPosition() {
        return new Point(borderThick, getHeight() - borderThick);
    }

    protected Point getBottomRightInnerPosition() {
        return new Point(getWidth() - borderThick, getHeight() - borderThick);
    }

    protected void drawLine(Graphics g, Point a, Point b) {
        g.drawLine(a.x, a.y, b.x, b.y);
    }
}
