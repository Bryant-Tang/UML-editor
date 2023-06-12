package canvas.component.connection;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import canvas.CanvasControll;
import canvas.component.Port;
import canvas.component.base.CanvasComponent;
import canvas.component.connection.arrow.Arrow;

public class ConnectionComponent extends CanvasComponent {
    // constant value
    static Point leftTopPosition = new Point(0, 0);

    // this connection is from startPort to endPort
    // the start Port
    private Port startPort;
    // the end Port
    private Port endPort;
    // the arrow of this connection
    private Arrow arrow;

    public ConnectionComponent(Port startPort, Port endPort) {
        super(leftTopPosition);
        setConnect(startPort, endPort);
        resize();
        setOpaque(false);
        setLayout(null);
    }

    /**
     * set the arrow of this connection
     * 
     * @param arrow
     */
    public void setArrow(Arrow arrow) {
        if (this.arrow == null) {
            initArrow(arrow);
        } else {
            this.arrow = arrow;
        }
        this.arrow.setDirection(getArrowDirection());
    }

    private void initArrow(Arrow arrow) {
        this.arrow = arrow;
        add(arrow);
    }

    /**
     * set the start Port and the end Port
     * 
     * @param startPort
     * @param endPort
     */
    public void setConnect(Port startPort, Port endPort) {
        this.startPort = startPort;
        this.endPort = endPort;
    }

    @Override
    protected void resize() {
        setPreferredSize(CanvasControll.getInstance().getSize());
        super.resize();
        setArrowLocation();
    }

    private void setArrowLocation() {
        if (arrow != null) {
            arrow.setLocation(endPort.getPosition());
            arrow.shift(-(arrow.getWidth() / 2), -(arrow.getHeight() / 2));
            if (endPort.getSide().equals(Port.TOP_SIDE)) {
                arrow.shift(0, -(arrow.getHeight() / 2));
            } else if (endPort.getSide().equals(Port.BOTTOM_SIDE)) {
                arrow.shift(0, arrow.getHeight() / 2);
            } else if (endPort.getSide().equals(Port.LEFT_SIDE)) {
                arrow.shift(-(arrow.getWidth() / 2), 0);
            } else if (endPort.getSide().equals(Port.RIGHT_SIDE)) {
                arrow.shift(arrow.getWidth() / 2, 0);
            }
        }
    }

    // calculate and return the line end point by the end port and the arrow
    private Point getLineEndPosition() {
        Point lineEndPosition = new Point(endPort.getPosition());
        if (arrow != null) {
            if (endPort.getSide().equals(Port.TOP_SIDE)) {
                lineEndPosition.y -= arrow.getHeight();
            } else if (endPort.getSide().equals(Port.BOTTOM_SIDE)) {
                lineEndPosition.y += arrow.getHeight();
            } else if (endPort.getSide().equals(Port.LEFT_SIDE)) {
                lineEndPosition.x -= arrow.getWidth();
            } else if (endPort.getSide().equals(Port.RIGHT_SIDE)) {
                lineEndPosition.x += arrow.getWidth();
            }
        }
        return lineEndPosition;
    }

    /**
     * get the direction of the arrow of this connection
     * 
     * @return the direction as String(should be
     *         <code>Port.TOP_SIDE</code>/<code>Port.BOTTOM_SIDE</code>/<code>Port.LEFT_SIDE</code>/<code>Port.RIGHT_SIDE</code>)
     */
    public String getArrowDirection() {
        if (endPort.getSide().equals(Port.TOP_SIDE)) {
            return Arrow.GO_DOWN;
        } else if (endPort.getSide().equals(Port.BOTTOM_SIDE)) {
            return Arrow.GO_UP;
        } else if (endPort.getSide().equals(Port.LEFT_SIDE)) {
            return Arrow.GO_RIGHT;
        } else if (endPort.getSide().equals(Port.RIGHT_SIDE)) {
            return Arrow.GO_LEFT;
        }
        return null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawLine(startPort.getPosition().x, startPort.getPosition().y, getLineEndPosition().x,
                getLineEndPosition().y);
        super.paintComponent(g);
    }

    /**
     * check if the given position(on CanvasPanel) is inside this component
     * 
     * @param position
     * @return always be false since no point can inside a connection
     */
    @Override
    public boolean isPositionInside(Point position) {
        return false;
    }

    /**
     * check if the given rectangle(on CanvasPanel) is fully-contain this component
     * 
     * @param rect
     * @return always be false since no rectangle can contain a connection
     */
    @Override
    public boolean isInsideRectangle(Rectangle rect) {
        return false;
    }

    /**
     * do nothing because connection component is not selectable for now
     * 
     * @param select
     */
    @Override
    public void setSelect(boolean select) {
        // connection component is not selectable for now
    }

    /**
     * do nothing because connection component is not shiftable for now
     * 
     * @param shift the vector of coordinate system
     */
    @Override
    public void shift(Point shift) {
        // connection component is not shiftable for now
    }

    /**
     * do nothing because connection component is not shiftable for now
     * 
     * @param x the x-axis movement
     * @param y the y-axis movement
     */
    @Override
    public void shift(int x, int y) {
        // connection component is not shiftable for now
    }
}
