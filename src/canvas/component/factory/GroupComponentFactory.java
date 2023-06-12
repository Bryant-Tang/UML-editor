package canvas.component.factory;

import java.awt.Point;
import java.util.List;

import canvas.component.base.CanvasComponent;
import canvas.component.group.GroupComponent;

public class GroupComponentFactory extends CanvasComponentFactory {

    private Point getLeftTop(List<CanvasComponent> components) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        for (CanvasComponent comp : components) {
            if (comp.getX() < minX) {
                minX = comp.getX();
            }
            if (comp.getY() < minY) {
                minY = comp.getY();
            }
        }
        return new Point(minX, minY);
    }

    /**
     * create GroupComponent
     * 
     * @param components the component inside the create group
     * @return the create component
     */
    @Override
    public CanvasComponent create(List<CanvasComponent> components) {
        CanvasComponent group = new GroupComponent(getLeftTop(components));
        for (CanvasComponent comp : components) {
            group.addGroupContent(comp);
        }
        return group;
    }
}
