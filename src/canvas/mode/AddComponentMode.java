package canvas.mode;

import canvas.component.factory.CanvasComponentFactory;

/**
 * the mode that have a factory to create a component
 */
public abstract class AddComponentMode extends Mode {
    protected CanvasComponentFactory factory;

    /**
     * construct a AddComponentMode
     * 
     * @param factory the factory to use to create the component
     */
    protected AddComponentMode(CanvasComponentFactory factory) {
        this.factory = factory;
    }
}
