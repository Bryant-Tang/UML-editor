package menu.item;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import canvas.CanvasControll;
import canvas.component.base.CanvasComponent;

public class RenameMenuItem extends ActionMenuItem {
    // constant value
    static String renameMenuItemText = "rename";

    public RenameMenuItem() {
        super(renameMenuItemText);
    }

    /**
     * rename the only one selected component base on an additional input dialog
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        CanvasComponent selectComponent = getOneSelectedComponent();
        if (selectComponent != null) {
            String newName = JOptionPane.showInputDialog(new JFrame(), "Enter name:", "Change Name",
                    JOptionPane.QUESTION_MESSAGE);
            if (newName != null) {
                selectComponent.setName(newName);
            }
        }
    }
}
