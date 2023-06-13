package canvas.component.base;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JPanel;

import canvas.component.Port;
import main.Calculate;

/**
 * the base class of the component inside canvas panel.
 */
public abstract class CanvasComponent extends JPanel {
    // constant value
    static Dimension defaultSize = new Dimension(1, 1);

    // record if this component is selected
    protected boolean select = false;

    // record the outter CanvasComponent(could be a GroupComponent for now)
    protected CanvasComponent parentCanvasComponent = null;

    /**
     * construct a CanvasComponent.
     * <p>
     * all subclass should pass a Point as the initial position to this constructor
     * 
     * @param position the initial position of this component
     */
    protected CanvasComponent(Point position) {
        super();
        this.setLocation(position);
        this.setSize(defaultSize);
    }

    /**
     * Invoke before <code>paint()</code>
     * <p>
     * Reset the location and size of this component and the JComponent inside.
     * <p>
     * If the layout manager is null, should override this method to set the
     * location and size.
     */
    protected void resize() {
        this.revalidate();
        this.setSize(getPreferredSize());
    }

    @Override
    public void paint(Graphics g) {
        this.resize();
        super.paint(g);
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
     * get the Port of this component, and the Port is closest to the given
     * position
     * 
     * @param position a position(on CanvasPanel)
     * @return null for now
     */
    public Port getPort(Point position) {
        return null;
    }

    /**
     * get the port position(on CanvasPanel)
     * 
     * @param side specific which side of the port
     * @return null for now
     */
    public Point getPortPosition(String side) {
        return null;
    }

    /**
     * check if the given position(on CanvasPanel) is inside this component
     * 
     * @param position
     * @return true if the position is inside this component, false if not.
     */
    public boolean isPositionInside(Point position) {
        return position.x >= getX() && position.x <= getX() + getWidth() && position.y >= getY()
                && position.y <= getY() + getHeight();
    }

    /**
     * check if the given rectangle(on CanvasPanel) is fully-contain this component
     * 
     * @param rect
     * @return true if the rectangle is fully-contain this component, false if not.
     */
    public boolean isInsideRectangle(Rectangle rect) {
        return getX() >= rect.x && getY() >= rect.y && getX() + getWidth() <= rect.x + rect.width
                && getY() + getHeight() <= rect.y + rect.height;

    }

    /**
     * set the select attribute to given value
     * 
     * @param select
     */
    public void setSelect(boolean select) {
        this.select = select;
    }

    /**
     * check if this component is selected
     * 
     * @return true if this component is selected, false if not.
     */
    public boolean isSelect() {
        return select;
    }

    /**
     * add a CanvasComponent into this component
     * <p>
     * do nothing for now
     * 
     * @param comp the component to add
     */
    public void addGroupContent(CanvasComponent comp) {
    }

    /**
     * remove a CanvasComponent from this component
     * <p>
     * do nothing for now
     * 
     * @param comp the component to remove
     */
    public void removeGroupContent(CanvasComponent comp) {
    }

    /**
     * destruct a group
     * <p>
     * do nothing for now
     */
    public void ungroup() {
    }

    /**
     * get the right bottom position of this component
     * 
     * @return the right bottom position of this component
     */
    public Point getRigntBottomLocation() {
        return new Point(getX() + getWidth(), getY() + getHeight());
    }

    /**
     * set the outter CanvasComponent of this component
     * 
     * @param parent the outter CanvasComponent
     */
    public void setParent(CanvasComponent parent) {
        this.parentCanvasComponent = parent;
    }

    /**
     * get the outter CanvasComponent of this component
     * 
     * @return the outter CanvasComponent
     */
    public CanvasComponent getParentCanvasComponent() {
        return parentCanvasComponent;
    }

    /**
     * get the location(on CanvasPanel) of this component
     * 
     * @return the location(on CanvasPanel) of this component
     */
    public Point getLocationOnCanvasPanel() {
        if (getParentCanvasComponent() != null) {
            return Calculate.addTwoPoint(getLocation(), getParentCanvasComponent().getLocationOnCanvasPanel());
        } else {
            return getLocation();
        }
    }
}
