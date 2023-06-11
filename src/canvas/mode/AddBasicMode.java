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
    public void mouseDragged(MouseEvent e) {
        // do nothing
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // do nothing
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        CanvasControll.getInstance().addComponent(factory.create(createType, e.getPoint()));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // do nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // do nothing
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // do nothing
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // do nothing
    }
}
