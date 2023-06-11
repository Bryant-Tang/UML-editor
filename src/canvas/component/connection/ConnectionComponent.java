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

    public ConnectionComponent(Port startPort, Port endPort) {
        super(leftTopPosition);
        setConnect(startPort, endPort);
        resize();
        setOpaque(false);
    }

    public void setConnect(Port startPort, Port endPort) {
        this.startPort = startPort;
        this.endPort = endPort;
    }

    @Override
    protected void resize() {
        setPreferredSize(CanvasControll.getInstance().getSize());
        super.resize();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawLine(startPort.getPosition().x, startPort.getPosition().y, endPort.getPosition().x,
                endPort.getPosition().y);
        super.paintComponent(g);
    }
}
