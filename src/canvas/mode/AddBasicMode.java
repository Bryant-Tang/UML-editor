package canvas.mode;

import java.awt.event.MouseEvent;

import canvas.CanvasControll;
import canvas.component.factory.BasicComponentFactory;

public class AddBasicMode extends AddComponentMode {
    private String createType;

    public AddBasicMode(BasicComponentFactory factory, String createType) {
        super(factory);
        this.createType = createType;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        CanvasControll.getInstance().addComponent(factory.create(createType, e.getPoint()));
    }
}
