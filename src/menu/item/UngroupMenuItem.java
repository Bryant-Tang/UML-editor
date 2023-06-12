package menu.item;

import java.awt.event.ActionEvent;

import canvas.component.base.CanvasComponent;

public class UngroupMenuItem extends ActionMenuItem {
    static String ungroupMenuItemText = "ungroup";

    public UngroupMenuItem() {
        super(ungroupMenuItemText);
    }

    /**
     * ungroup the selected group
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        CanvasComponent selectComponent = getOneSelectedComponent();
        if (selectComponent != null) {
            selectComponent.ungroup();
        }
    }
}
