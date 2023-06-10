package canvas.mode;

import canvas.component.factory.CanvasComponentFactory;

public abstract class AddComponentMode implements Mode {
    protected CanvasComponentFactory factory;

    protected AddComponentMode(CanvasComponentFactory factory) {
        this.factory = factory;
    }
}
