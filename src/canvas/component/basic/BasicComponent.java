package canvas.component.basic;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Dimension;

import canvas.component.Port;
import canvas.component.base.CanvasComponent;

public abstract class BasicComponent extends CanvasComponent {
    static int borderThick = 1;
    static Dimension portSize = new Dimension(5, 5);

    private JLabel nameLabel = new JLabel();
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

    @Override
    public void moveWithShift(Point shift) {
        setLocation(getX() + shift.x, getY() + shift.y);
    }

    @Override
    public Port getPort(Point position) {
        return new Port(this, getClosestSide(position));
    }

    private String getClosestSide(Point position) {
        double distance = Double.MAX_VALUE;
        String side = null;
        if (distance >= calculateDistance(position, getTopCenterPosition())) {
            side = Port.TOP_SIDE;
            distance = calculateDistance(position, getTopCenterPosition());
        }
        if (distance >= calculateDistance(position, getBottomCenterPosition())) {
            side = Port.BOTTOM_SIDE;
            distance = calculateDistance(position, getBottomCenterPosition());
        }
        if (distance >= calculateDistance(position, getLeftCenterPosition())) {
            side = Port.LEFT_SIDE;
            distance = calculateDistance(position, getLeftCenterPosition());
        }
        if (distance >= calculateDistance(position, getRightCenterPosition())) {
            side = Port.RIGHT_SIDE;
        }
        return side;
    }

    private double calculateDistance(Point a, Point b) {
        return Math.sqrt((a.getX() - b.getX()) * (a.getX() - b.getX()) + (a.getY() - b.getY()) * (a.getY() - b.getY()));
    }

    @Override
    public Point getPortPosition(String side) {
        if (side.equals(Port.TOP_SIDE)) {
            return getTopCenterPosition();
        } else if (side.equals(Port.BOTTOM_SIDE)) {
            return getBottomCenterPosition();
        } else if (side.equals(Port.LEFT_SIDE)) {
            return getLeftCenterPosition();
        } else if (side.equals(Port.RIGHT_SIDE)) {
            return getRightCenterPosition();
        } else {
            return null;
        }
    }

    private Point getTopCenterPosition() {
        return new Point(getX() + getWidth() / 2, getY());
    }

    private Point getBottomCenterPosition() {
        return new Point(getX() + getWidth() / 2, getY() + getHeight());
    }

    private Point getLeftCenterPosition() {
        return new Point(getX(), getY() + getHeight() / 2);
    }

    private Point getRightCenterPosition() {
        return new Point(getX() + getWidth(), getY() + getHeight() / 2);
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
    public boolean isPositionInside(Point position) {
        return position.x >= getX() && position.x <= getX() + getWidth() && position.y >= getY()
                && position.y <= getY() + getHeight();
    }

    @Override
    public boolean isInsideRectangle(Rectangle rect) {
        return getX() >= rect.x && getY() >= rect.y && getX() + getWidth() <= rect.x + rect.width
                && getY() + getHeight() <= rect.y + rect.height;
    }

    @Override
    public void setSelect(boolean select) {
        this.select = select;
        super.setSelect(select);
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
