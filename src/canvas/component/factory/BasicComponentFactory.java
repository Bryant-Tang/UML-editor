package canvas.component.factory;

import java.awt.Point;

import canvas.component.Port;
import canvas.component.base.CanvasComponent;
import canvas.component.basic.ClassComponent;
import canvas.component.basic.UseCaseComponent;

public class BasicComponentFactory implements CanvasComponentFactory {
    public static final String CLASS = "class";
    public static final String USE_CASE = "use_case";
    static Point leftTopPosition = new Point(0, 0);
    static String defualtName = "name";

    @Override
    public CanvasComponent create(String type, Point position) {
        if (position == null) {
            position = leftTopPosition;
        }
        if (type.equals(CLASS)) {
            return new ClassComponent(position, defualtName);
        } else if (type.equals(USE_CASE)) {
            return new UseCaseComponent(position, defualtName);
        } else {
            return null;
        }
    }

    @Override
    public CanvasComponent create(String type, Port startPort, Port endPort) {
        return null;
    }
}
