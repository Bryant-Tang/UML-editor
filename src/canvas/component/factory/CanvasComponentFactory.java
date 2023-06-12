package canvas.component.factory;

import java.awt.Point;
import java.util.List;

import canvas.component.Port;
import canvas.component.base.CanvasComponent;

public abstract class CanvasComponentFactory {
    public CanvasComponent create(String type, Point position) {
        return null;
    }

    public CanvasComponent create(String type, Port startPort, Port endPort) {
        return null;
    }

    public CanvasComponent create(List<CanvasComponent> components) {
        return null;
    }
}
