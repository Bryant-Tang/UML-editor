package menu.item;

import java.awt.event.ActionEvent;
import java.util.List;

import canvas.CanvasControll;
import canvas.component.base.CanvasComponent;
import canvas.component.factory.GroupComponentFactory;

public class GroupMenuItem extends ActionMenuItem {
    // constant value
    static String groupMenuItemText = "group";

    public GroupMenuItem() {
        super(groupMenuItemText);
    }

    /**
     * create group component base on the selected components
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        List<CanvasComponent> components = CanvasControll.getInstance().getSelectComponents();
        if (components.size() > 1) {
            CanvasControll.getInstance().addComponent((new GroupComponentFactory()).create(components));
        }
    }
}
