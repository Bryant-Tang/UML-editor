package canvas.component.factory;

import java.awt.Point;

import canvas.component.Port;
import canvas.component.base.CanvasComponent;
import canvas.component.connection.ConnectionComponent;

public class ConnectionComponentFactory implements CanvasComponentFactory {
    public static final String ASSOCIATION = "association";

    @Override
    public CanvasComponent create(String type, Point position) {
        return null;
    }

    @Override
    public CanvasComponent create(String type, Port startPort, Port endPort) {
        if (type.equals(ASSOCIATION)) {
            return new ConnectionComponent(startPort, endPort);
        } else {
            return null;
        }
    }
}
