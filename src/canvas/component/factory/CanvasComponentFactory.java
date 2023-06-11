package canvas.component.factory;

import java.awt.Point;

import canvas.component.Port;
import canvas.component.base.CanvasComponent;

public interface CanvasComponentFactory {
    public CanvasComponent create(String type, Point position);

    public CanvasComponent create(String type, Port startPort, Port endPort);
}
