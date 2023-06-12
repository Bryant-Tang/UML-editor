package canvas.mode;

import java.awt.event.MouseEvent;
import java.awt.Point;

import canvas.CanvasControll;
import canvas.component.base.CanvasComponent;
import main.Calculate;

public class SelectMode extends Mode {
    // record the Point that mouse press on
    Point pressPoint = null;
    // record the CanvasComponent that mouse press on
    CanvasComponent pressComponent = null;

    // set the select of all components in Canvas to false
    private void clearSelect() {
        for (CanvasComponent comp : CanvasControll.getInstance().getAllComponent()) {
            CanvasControll.getInstance().setSelectComponent(comp, false);
        }
    }

    // clear pressPoint and pressComponent
    private void resetPress() {
        pressPoint = null;
        pressComponent = null;
    }

    /**
     * select the component that mouse click on
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        CanvasComponent comp = CanvasControll.getInstance().getComponentAt(e.getPoint());
        clearSelect();
        if (comp != null) {
            CanvasControll.getInstance().setSelectComponent(comp, true);
        }
    }

    /**
     * set the Point and the CanvasComponent that mouse press on
     */
    @Override
    public void mousePressed(MouseEvent e) {
        pressPoint = e.getPoint();
        pressComponent = CanvasControll.getInstance().getComponentAt(e.getPoint());
    }

    /**
     * select all components inside the rectangle created by the press point and the
     * release point
     * <p>
     * or move the CanvasComponent that mouse press on
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        Point releasePoint = e.getPoint();
        if (!pressPoint.equals(releasePoint)) {
            if (pressComponent == null) {
                clearSelect();
                for (CanvasComponent comp : CanvasControll.getInstance()
                        .getComponentInsideRectangle(Calculate.createRectangle(pressPoint, releasePoint))) {
                    CanvasControll.getInstance().setSelectComponent(comp, true);
                }
            } else {
                CanvasControll.getInstance().shiftComponent(pressComponent,
                        new Point(releasePoint.x - pressPoint.x, releasePoint.y - pressPoint.y));
            }
        }
        resetPress();
    }
}
