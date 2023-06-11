package canvas.mode;

import java.awt.event.MouseEvent;
import java.awt.Point;

import canvas.CanvasControll;
import canvas.component.Port;
import canvas.component.base.CanvasComponent;
import canvas.component.factory.ConnectionComponentFactory;

public class AddConnectionMode extends AddComponentMode {
    private String createType;
    private Port pressPort;

    public AddConnectionMode(ConnectionComponentFactory factory, String createType) {
        super(factory);
        this.createType = createType;
        resetPress();
    }

    private void resetPress() {
        pressPort = null;
    }

    private Port getPort(Point position) {
        Port port = null;
        CanvasComponent comp = CanvasControll.getInstance().getComponentAt(position);
        if (comp != null) {
            port = comp.getPort(position);
        }
        return port;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // do nothing
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // do nothing
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // do nothing
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // do nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // do nothing
    }

    @Override
    public void mousePressed(MouseEvent e) {
        pressPort = getPort(e.getPoint());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Port releasePort = getPort(e.getPoint());
        if (pressPort != null && releasePort != null) {
            CanvasControll.getInstance().addComponent(factory.create(createType, pressPort, releasePort));
        }
        resetPress();
    }
}
