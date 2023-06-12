package canvas.component.basic;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Dimension;

import canvas.component.Port;
import canvas.component.base.CanvasComponent;

public abstract class BasicComponent extends CanvasComponent {
    // constant value
    static int borderThick = 1;
    static Dimension portSize = new Dimension(5, 5);

    // the Label of the name of this component
    private JLabel nameLabel = new JLabel();
    // the Panel that contain the name Label
    protected JPanel namePanel = new JPanel();

    protected BasicComponent(Point position, String name) {
        super(position);
        initName(name);
    }

    private void initName(String name) {
        this.setName(name);
        namePanel.setOpaque(false);
        namePanel.add(nameLabel);
        namePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(namePanel);
    }

    @Override
    public void setName(String name) {
        nameLabel.setText(name);
        super.setName(name);
    }

    /**
     * get the Port of this component, and the Port is closest to the given
     * position
     * 
     * @param position a position(on CanvasPanel)
     * @return the Port closest to the given position
     */
    @Override
    public Port getPort(Point position) {
        return new Port(this, getClosestSide(position));
    }

    private String getClosestSide(Point position) {
        double distance = Double.MAX_VALUE;
        String side = null;
        if (distance >= calculateDistance(position, getTopCenterPositionOnCanvasPanel())) {
            side = Port.TOP_SIDE;
            distance = calculateDistance(position, getTopCenterPositionOnCanvasPanel());
        }
        if (distance >= calculateDistance(position, getBottomCenterPositionOnCanvasPanel())) {
            side = Port.BOTTOM_SIDE;
            distance = calculateDistance(position, getBottomCenterPositionOnCanvasPanel());
        }
        if (distance >= calculateDistance(position, getLeftCenterPositionOnCanvasPanel())) {
            side = Port.LEFT_SIDE;
            distance = calculateDistance(position, getLeftCenterPositionOnCanvasPanel());
        }
        if (distance >= calculateDistance(position, getRightCenterPositionOnCanvasPanel())) {
            side = Port.RIGHT_SIDE;
        }
        return side;
    }

    /**
     * calculate the distance between two Point
     * 
     * @param a
     * @param b
     * @return the distance between a and b
     */
    protected double calculateDistance(Point a, Point b) {
        return Math.sqrt((a.getX() - b.getX()) * (a.getX() - b.getX()) + (a.getY() - b.getY()) * (a.getY() - b.getY()));
    }

    /**
     * get the port position(on CanvasPanel)
     * 
     * @param side specific which side of the port
     * @return the position(on CanvasPanel) of the port on the side
     */
    @Override
    public Point getPortPosition(String side) {
        if (side.equals(Port.TOP_SIDE)) {
            return getTopCenterPositionOnCanvasPanel();
        } else if (side.equals(Port.BOTTOM_SIDE)) {
            return getBottomCenterPositionOnCanvasPanel();
        } else if (side.equals(Port.LEFT_SIDE)) {
            return getLeftCenterPositionOnCanvasPanel();
        } else if (side.equals(Port.RIGHT_SIDE)) {
            return getRightCenterPositionOnCanvasPanel();
        } else {
            return null;
        }
    }

    private Point getTopCenterPositionOnCanvasPanel() {
        return new Point(getLocationOnCanvasPanel().x + getWidth() / 2, getLocationOnCanvasPanel().y);
    }

    private Point getBottomCenterPositionOnCanvasPanel() {
        return new Point(getLocationOnCanvasPanel().x + getWidth() / 2, getLocationOnCanvasPanel().y + getHeight());
    }

    private Point getLeftCenterPositionOnCanvasPanel() {
        return new Point(getLocationOnCanvasPanel().x, getLocationOnCanvasPanel().y + getHeight() / 2);
    }

    private Point getRightCenterPositionOnCanvasPanel() {
        return new Point(getLocationOnCanvasPanel().x + getWidth(), getLocationOnCanvasPanel().y + getHeight() / 2);
    }

    private Point getTopCenterInnerPosition() {
        return new Point(getWidth() / 2, 0);
    }

    private Point getBottomCenterInnerPosition() {
        return new Point(getWidth() / 2, getHeight() - borderThick);
    }

    private Point getLeftCenterInnerPosition() {
        return new Point(0, getHeight() / 2);
    }

    private Point getRightCenterInnerPosition() {
        return new Point(getWidth() - borderThick, getHeight() / 2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (select) {
            paintPort(g);
        }
    }

    private void paintPort(Graphics g) {
        g.fillRect(getTopCenterInnerPosition().x - portSize.width / 2, getTopCenterInnerPosition().y, portSize.width,
                portSize.height);
        g.fillRect(getBottomCenterInnerPosition().x - portSize.width / 2,
                getBottomCenterInnerPosition().y - portSize.height, portSize.width,
                portSize.height);
        g.fillRect(getLeftCenterInnerPosition().x, getLeftCenterInnerPosition().y - portSize.height / 2, portSize.width,
                portSize.height);
        g.fillRect(getRightCenterInnerPosition().x - portSize.width,
                getRightCenterInnerPosition().y - portSize.height / 2, portSize.width,
                portSize.height);
    }
}
