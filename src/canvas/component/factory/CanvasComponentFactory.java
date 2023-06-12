package canvas.component.factory;

import java.awt.Point;
import java.util.List;

import canvas.component.Port;
import canvas.component.base.CanvasComponent;

public abstract class CanvasComponentFactory {
    /**
     * create CanvasComponent
     * <p>
     * do NOT use this function because this is an empty function
     * 
     * @param type
     * @param position
     * @return null
     */
    public CanvasComponent create(String type, Point position) {
        return null;
    }

    /**
     * create CanvasComponent
     * <p>
     * do NOT use this function because this is an empty function
     * 
     * @param type
     * @param position
     * @return null
     */
    public CanvasComponent create(String type, Port startPort, Port endPort) {
        return null;
    }

    /**
     * create CanvasComponent
     * <p>
     * do NOT use this function because this is an empty function
     * 
     * @param type
     * @param position
     * @return null
     */
    public CanvasComponent create(List<CanvasComponent> components) {
        return null;
    }
}
