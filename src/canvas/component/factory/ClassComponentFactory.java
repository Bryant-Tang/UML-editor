package canvas.component.factory;

import java.awt.Point;

import canvas.component.base.CanvasComponent;
import canvas.component.basic.ClassComponent;

public class ClassComponentFactory implements CanvasComponentFactory {
    static Point leftTopPosition = new Point(0, 0);

    @Override
    public CanvasComponent create(Point position) {
        if (position != null) {
            return new ClassComponent(position, "class");
        } else {
            return new ClassComponent(leftTopPosition, "class");
        }

    }
}
