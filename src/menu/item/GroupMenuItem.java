package menu.item;

import java.awt.event.ActionEvent;
import java.util.List;

import canvas.CanvasControll;
import canvas.component.base.CanvasComponent;
import canvas.component.factory.GroupComponentFactory;

public class GroupMenuItem extends ActionMenuItem {
    static String groupMenuItemText = "group";

    private GroupComponentFactory factory = new GroupComponentFactory();

    public GroupMenuItem() {
        super(groupMenuItemText);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<CanvasComponent> components = CanvasControll.getInstance().getSelectComponents();
        if (components.size() > 1) {
            CanvasControll.getInstance().addComponent(factory.create(components));
        }
    }
}
