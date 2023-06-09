package canvas.component.factory;

import java.awt.Point;

import canvas.component.base.CanvasComponent;

public interface CanvasComponentFactory {
    public CanvasComponent create(Point position);
}
