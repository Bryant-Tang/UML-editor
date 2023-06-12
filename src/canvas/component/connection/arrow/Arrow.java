package canvas.component.connection.arrow;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Graphics;

import javax.swing.JPanel;

public abstract class Arrow extends JPanel {
    // public constant value
    public static final String GO_UP = "go_up";
    public static final String GO_DOWN = "go_down";
    public static final String GO_LEFT = "go_left";
    public static final String GO_RIGHT = "go_right";

    // constant value
    static Dimension arrowSize = new Dimension(10, 10);
    static int borderThick = 1;

    // the direction of this arrow(should be equal to
    // GO_UP/GO_DOWN/GO_LEFT/GO_RIGHT)
    protected String direction;

    protected Arrow() {
        setOpaque(false);
        setLayout(null);
        setSize(arrowSize);
    }

    /**
     * set the direction of this arrow
     * 
     * @param direction
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * Shift this component by the Point.
     * (Just see the point as a vector of coordinate system)
     * 
     * @param shift the vector of coordinate system
     */
    public void shift(Point shift) {
        shift(shift.x, shift.y);
    }

    /**
     * Shift this component by the x and y
     * (Just see the (x,y) as a vector of coordinate system)
     * 
     * @param x the x-axis movement
     * @param y the y-axis movement
     */
    public void shift(int x, int y) {
        setLocation(getX() + x, getY() + y);
    }

    /**
     * get the position of top center of this arrow (inside the border)
     * 
     * @return the position of top center of this arrow
     */
    protected Point getTopCenterInnerPosition() {
        return new Point(getWidth() / 2, borderThick);
    }

    /**
     * get the position of bottom center of this arrow (inside the border)
     * 
     * @return the position of bottom center of this arrow
     */
    protected Point getBottomCenterInnerPosition() {
        return new Point(getWidth() / 2, getHeight() - borderThick);
    }

    /**
     * get the position of left center of this arrow (inside the border)
     * 
     * @return the position of left center of this arrow
     */
    protected Point getLeftCenterInnerPosition() {
        return new Point(borderThick, getHeight() / 2);
    }

    /**
     * get the position of right center of this arrow (inside the border)
     * 
     * @return the position of right center of this arrow
     */
    protected Point getRightCenterInnerPosition() {
        return new Point(getWidth() - borderThick, getHeight() / 2);
    }

    /**
     * get the position of top left of this arrow (inside the border)
     * 
     * @return the position of top left of this arrow
     */
    protected Point getTopLeftInnerPosition() {
        return new Point(borderThick, borderThick);
    }

    /**
     * get the position of top right of this arrow (inside the border)
     * 
     * @return the position of top right of this arrow
     */
    protected Point getTopRightInnerPosition() {
        return new Point(getWidth() - borderThick, borderThick);
    }

    /**
     * get the position of bottom left of this arrow (inside the border)
     * 
     * @return the position of bottom left of this arrow
     */
    protected Point getBottomLeftInnerPosition() {
        return new Point(borderThick, getHeight() - borderThick);
    }

    /**
     * get the position of bottom right of this arrow (inside the border)
     * 
     * @return the position of bottom right of this arrow
     */
    protected Point getBottomRightInnerPosition() {
        return new Point(getWidth() - borderThick, getHeight() - borderThick);
    }

    /**
     * use a Graphics(g) to draw a line between point a and point b
     * 
     * @param g the Graphics to use
     * @param a the first point
     * @param b the second point
     */
    protected void drawLine(Graphics g, Point a, Point b) {
        g.drawLine(a.x, a.y, b.x, b.y);
    }
}
