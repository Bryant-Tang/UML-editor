package menu.item;

import java.awt.event.ActionEvent;

import canvas.component.base.CanvasComponent;

/**
 * a concrete ActionMenuItem that ungroup the component selected
 */
public class UngroupMenuItem extends ActionMenuItem {
    static String ungroupMenuItemText = "ungroup";

    /**
     * a concrete ActionMenuItem that ungroup the component selected
     */
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
