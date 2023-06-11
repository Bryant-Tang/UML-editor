package canvas.component.connection;

import java.awt.Graphics;
import java.awt.Point;

import canvas.CanvasControll;
import canvas.component.Port;
import canvas.component.base.CanvasComponent;

public class ConnectionComponent extends CanvasComponent {
    static Point leftTopPosition = new Point(0, 0);

    private Port startPort;
    private Port endPort;
    private Arrow arrow;

    public ConnectionComponent(Port startPort, Port endPort, Arrow arrow) {
        super(leftTopPosition);
        initArrow(arrow);
        setConnect(startPort, endPort);
        resize();
        setOpaque(false);
        setLayout(null);
    }

    private void initArrow(Arrow arrow) {
        this.arrow = arrow;
        add(arrow);
    }

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

    private Point getLineEndPosition() {
        Point lineEndPosition = new Point(endPort.getPosition());
        if (endPort.getSide().equals(Port.TOP_SIDE)) {
            lineEndPosition.y -= arrow.getHeight();
        } else if (endPort.getSide().equals(Port.BOTTOM_SIDE)) {
            lineEndPosition.y += arrow.getHeight();
        } else if (endPort.getSide().equals(Port.LEFT_SIDE)) {
            lineEndPosition.x -= arrow.getWidth();
        } else if (endPort.getSide().equals(Port.RIGHT_SIDE)) {
            lineEndPosition.x += arrow.getWidth();
        }
        return lineEndPosition;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawLine(startPort.getPosition().x, startPort.getPosition().y, getLineEndPosition().x,
                getLineEndPosition().y);
        super.paintComponent(g);
    }
}
