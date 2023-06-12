package menu.item;

import java.awt.event.ActionEvent;
import java.util.List;

import canvas.CanvasControll;
import canvas.component.base.CanvasComponent;

public class UngroupMenuItem extends ActionMenuItem {
    static String ungroupMenuItemText = "ungroup";

    public UngroupMenuItem() {
        super(ungroupMenuItemText);
    }

    private CanvasComponent getOneSelectedComponent() {
        List<CanvasComponent> components = CanvasControll.getInstance().getSelectComponents();
        if (components.size() == 1) {
            return components.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CanvasComponent selectComponent = getOneSelectedComponent();
        if (selectComponent != null) {
            selectComponent.ungroup();
        }
    }
}
