package canvas.component.factory;

import java.awt.Point;

import canvas.component.base.CanvasComponent;
import canvas.component.basic.ClassComponent;
import canvas.component.basic.UseCaseComponent;

/**
 * the factory that create BasicComponent
 */
public class BasicComponentFactory extends CanvasComponentFactory {
    // public constant value
    public static final String CLASS = "class";
    public static final String USE_CASE = "use_case";

    // constant value
    static Point leftTopPosition = new Point(0, 0);
    static String defualtName = "name";

    /**
     * create ClassComponent/UseCaseComponent
     * 
     * @param type     the type indicate what component to create (should be
     *                 <code>BasicComponentFactory.CLASS</code>/<code>BasicComponentFactory.USE_CASE</code>)
     * @param position the position(on CanvasPanel) of the create component
     * @return the create component
     */
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
}
