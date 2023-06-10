package canvas.component.factory;

import java.awt.Point;

import canvas.component.base.CanvasComponent;
import canvas.component.basic.ClassComponent;
import canvas.component.basic.UseCaseComponent;

public class BasicComponentFactory implements CanvasComponentFactory {
    public static final String CLASS_COMPONENT = "class";
    public static final String USE_CASE_COMPONENT = "use_case";
    static Point leftTopPosition = new Point(0, 0);
    static String defualtName = "name";

    @Override
    public CanvasComponent create(String type, Point position) {
        if (position == null) {
            position = leftTopPosition;
        }
        if (type.equals(CLASS_COMPONENT)) {
            return new ClassComponent(position, defualtName);
        } else if (type.equals(USE_CASE_COMPONENT)) {
            return new UseCaseComponent(position, defualtName);
        } else {
            return null;
        }
    }
}
