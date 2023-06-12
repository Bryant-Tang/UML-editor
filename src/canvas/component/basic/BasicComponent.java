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
    public Port getPort(Point position) {
        return new Port(this, getClosestSide(position));
    }

    private String getClosestSide(Point position) {
        double distance = Double.MAX_VALUE;
        String side = null;
        if (distance >= calculateDistance(position, getTopCenterPositionOfCanvas())) {
            side = Port.TOP_SIDE;
            distance = calculateDistance(position, getTopCenterPositionOfCanvas());
        }
        if (distance >= calculateDistance(position, getBottomCenterPositionOfCanvas())) {
            side = Port.BOTTOM_SIDE;
            distance = calculateDistance(position, getBottomCenterPositionOfCanvas());
        }
        if (distance >= calculateDistance(position, getLeftCenterPositionOfCanvas())) {
            side = Port.LEFT_SIDE;
            distance = calculateDistance(position, getLeftCenterPositionOfCanvas());
        }
        if (distance >= calculateDistance(position, getRightCenterPositionOfCanvas())) {
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
            return getTopCenterPositionOfCanvas();
        } else if (side.equals(Port.BOTTOM_SIDE)) {
            return getBottomCenterPositionOfCanvas();
        } else if (side.equals(Port.LEFT_SIDE)) {
            return getLeftCenterPositionOfCanvas();
        } else if (side.equals(Port.RIGHT_SIDE)) {
            return getRightCenterPositionOfCanvas();
        } else {
            return null;
        }
    }

    private Point getTopCenterPositionOfCanvas() {
        return new Point(getLocationOfCanvas().x + getWidth() / 2, getLocationOfCanvas().y);
    }

    private Point getBottomCenterPositionOfCanvas() {
        return new Point(getLocationOfCanvas().x + getWidth() / 2, getLocationOfCanvas().y + getHeight());
    }

    private Point getLeftCenterPositionOfCanvas() {
        return new Point(getLocationOfCanvas().x, getLocationOfCanvas().y + getHeight() / 2);
    }

    private Point getRightCenterPositionOfCanvas() {
        return new Point(getLocationOfCanvas().x + getWidth(), getLocationOfCanvas().y + getHeight() / 2);
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
