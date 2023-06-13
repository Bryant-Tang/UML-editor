package canvas.mode;

import java.awt.event.MouseEvent;
import java.awt.Point;

import canvas.CanvasControll;
import canvas.component.Port;
import canvas.component.base.CanvasComponent;
import canvas.component.factory.ConnectionComponentFactory;

/**
 * the mode to add ConnectionComponent
 */
public class AddConnectionMode extends AddComponentMode {
    // record what type of basic component to add
    private String createType;
    // record the Port of the component that mouse press on
    private Port pressPort;

    /**
     * construct a AddConnectionMode
     * 
     * @param factory    the factory to use to create the component
     * @param createType the type of the component to create
     */
    public AddConnectionMode(ConnectionComponentFactory factory, String createType) {
        super(factory);
        this.createType = createType;
        resetPress();
    }

    // clear pressPort
    private void resetPress() {
        pressPort = null;
    }

    // get the Port of the component at the specific position(on CanvasPanel)
    private Port getPort(Point position) {
        Port port = null;
        CanvasComponent comp = CanvasControll.getInstance().getComponentAt(position);
        if (comp != null) {
            port = comp.getPort(position);
        }
        return port;
    }

    /**
     * set the Port of the component that mouse press on
     */
    @Override
    public void mousePressed(MouseEvent e) {
        pressPort = getPort(e.getPoint());
    }

    /**
     * add ConnectionComponent base on the pressPort that is set when mouse press
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        Port releasePort = getPort(e.getPoint());
        if (pressPort != null && releasePort != null) {
            CanvasControll.getInstance().addComponent(factory.create(createType, pressPort, releasePort));
        }
        resetPress();
    }
}
