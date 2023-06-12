package menu.item;

import java.awt.event.ActionEvent;
import java.util.List;

import canvas.CanvasControll;
import canvas.component.base.CanvasComponent;
import main.frame.ChangeNameFrame;

public class RenameMenuItem extends ActionMenuItem {
    static String renameMenuItemText = "rename";

    public RenameMenuItem() {
        super(renameMenuItemText);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<CanvasComponent> selectComponents = CanvasControll.getInstance().getSelectComponents();
        if (selectComponents.size() == 1) {
            ChangeNameFrame.getInstance().askRename(selectComponents.get(0));
        }
    }
}
