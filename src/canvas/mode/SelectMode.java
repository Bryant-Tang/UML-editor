package canvas.mode;

import java.awt.event.MouseEvent;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Dimension;

import canvas.CanvasControll;
import canvas.component.base.CanvasComponent;

public class SelectMode extends Mode {
    Point pressPoint = null;
    CanvasComponent pressComponent = null;

    private void clearSelect() {
        for (CanvasComponent comp : CanvasControll.getInstance().getAllComponent()) {
            comp.setSelect(false);
        }
    }

    private void resetPress() {
        pressPoint = null;
        pressComponent = null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        CanvasComponent comp = CanvasControll.getInstance().getComponentAt(e.getPoint());
        clearSelect();
        if (comp != null) {
            comp.setSelect(true);
        }
    }

    private Rectangle createRectangle(Point a, Point b) {
        Point upperLeft = new Point(Math.min(a.x, b.x), Math.min(a.y, b.y));
        Dimension size = new Dimension(Math.max(a.x, b.x) - upperLeft.x, Math.max(a.y, b.y) - upperLeft.y);
        return new Rectangle(upperLeft, size);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        pressPoint = e.getPoint();
        pressComponent = CanvasControll.getInstance().getComponentAt(e.getPoint());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point releasePoint = e.getPoint();
        if (pressComponent == null && !pressPoint.equals(releasePoint)) {
            clearSelect();
            for (CanvasComponent comp : CanvasControll.getInstance()
                    .getComponentInsideRectangle(createRectangle(pressPoint, releasePoint))) {
                comp.setSelect(true);
            }
        } else if (pressComponent != null) {
            CanvasControll.getInstance().shiftComponent(pressComponent,
                    new Point(releasePoint.x - pressPoint.x, releasePoint.y - pressPoint.y));
        }
        resetPress();
    }
}
