package canvas.mode;

import java.awt.event.MouseEvent;

import canvas.CanvasControll;
import canvas.component.factory.BasicComponentFactory;

public class AddBasicMode extends AddComponentMode {
    // record what type of basic component to add
    private String createType;

    public AddBasicMode(BasicComponentFactory factory, String createType) {
        super(factory);
        this.createType = createType;
    }

    /**
     * add a BasicComponent to CanvasPanel
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        CanvasControll.getInstance().addComponent(factory.create(createType, e.getPoint()));
    }
}
