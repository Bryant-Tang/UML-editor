package canvas.mode;

import canvas.component.factory.CanvasComponentFactory;

public abstract class AddComponentMode extends Mode {
    protected CanvasComponentFactory factory;

    /**
     * add a CanvasComponent to CanvasPanel
     */
    protected AddComponentMode(CanvasComponentFactory factory) {
        this.factory = factory;
    }
}
