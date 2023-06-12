package canvas.component.basic;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Dimension;

import canvas.component.Port;
import canvas.component.base.CanvasComponent;
import main.Calculate;

public abstract class BasicComponent extends CanvasComponent {
    // constant value
    static int borderThick = 1;
    static Dimension portSize = new Dimension(5, 5);
    static Point leftTop = Calculate.ZERO_POINT;

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
        if (distance >= Calculate.calculateDistance(position, getTopCenterPositionOnCanvasPanel())) {
            side = Port.TOP_SIDE;
            distance = Calculate.calculateDistance(position, getTopCenterPositionOnCanvasPanel());
        }
        if (distance >= Calculate.calculateDistance(position, getBottomCenterPositionOnCanvasPanel())) {
            side = Port.BOTTOM_SIDE;
            distance = Calculate.calculateDistance(position, getBottomCenterPositionOnCanvasPanel());
        }
        if (distance >= Calculate.calculateDistance(position, getLeftCenterPositionOnCanvasPanel())) {
            side = Port.LEFT_SIDE;
            distance = Calculate.calculateDistance(position, getLeftCenterPositionOnCanvasPanel());
        }
        if (distance >= Calculate.calculateDistance(position, getRightCenterPositionOnCanvasPanel())) {
            side = Port.RIGHT_SIDE;
        }
        return side;
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
        return new Point(getLocationOnCanvasPanel().x + Calculate.half(getWidth()), getLocationOnCanvasPanel().y);
    }

    private Point getBottomCenterPositionOnCanvasPanel() {
        return new Point(getLocationOnCanvasPanel().x + Calculate.half(getWidth()),
                getLocationOnCanvasPanel().y + getHeight());
    }

    private Point getLeftCenterPositionOnCanvasPanel() {
        return new Point(getLocationOnCanvasPanel().x, getLocationOnCanvasPanel().y + Calculate.half(getHeight()));
    }

    private Point getRightCenterPositionOnCanvasPanel() {
        return new Point(getLocationOnCanvasPanel().x + getWidth(),
                getLocationOnCanvasPanel().y + Calculate.half(getHeight()));
    }

    private Point getTopCenterInnerPosition() {
        return new Point(Calculate.half(getWidth()), leftTop.y);
    }

    private Point getBottomCenterInnerPosition() {
        return new Point(Calculate.half(getWidth()), getHeight() - borderThick);
    }

    private Point getLeftCenterInnerPosition() {
        return new Point(leftTop.x, Calculate.half(getHeight()));
    }

    private Point getRightCenterInnerPosition() {
        return new Point(getWidth() - borderThick, Calculate.half(getHeight()));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (select) {
            paintPort(g);
        }
    }

    private Point getTopPortLocation() {
        return new Point(getTopCenterInnerPosition().x - Calculate.half(portSize.width), getTopCenterInnerPosition().y);
    }

    private Point getBottomPortLocation() {
        return new Point(getBottomCenterInnerPosition().x - Calculate.half(portSize.width),
                getBottomCenterInnerPosition().y - portSize.height);
    }

    private Point getLeftPortLocation() {
        return new Point(getLeftCenterInnerPosition().x,
                getLeftCenterInnerPosition().y - Calculate.half(portSize.height));
    }

    private Point getRightPortLocation() {
        return new Point(getRightCenterInnerPosition().x - portSize.width,
                getRightCenterInnerPosition().y - Calculate.half(portSize.height));
    }

    private void paintPort(Graphics g) {
        g.fillRect(getTopPortLocation().x, getTopPortLocation().y, portSize.width, portSize.height);
        g.fillRect(getBottomPortLocation().x, getBottomPortLocation().y, portSize.width, portSize.height);
        g.fillRect(getLeftPortLocation().x, getLeftPortLocation().y, portSize.width, portSize.height);
        g.fillRect(getRightPortLocation().x, getRightPortLocation().y, portSize.width, portSize.height);
    }
}
