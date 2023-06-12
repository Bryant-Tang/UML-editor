package menu.item;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenuItem;

import canvas.CanvasControll;
import canvas.component.base.CanvasComponent;

public abstract class ActionMenuItem extends JMenuItem implements ActionListener {
    protected ActionMenuItem(String content) {
        super(content);
        this.addActionListener(this);
    }

    /**
     * get only one selected component
     * <p>
     * if none or multiple component are selected, return null
     * 
     * @return the only one selected component or null
     */
    protected CanvasComponent getOneSelectedComponent() {
        List<CanvasComponent> components = CanvasControll.getInstance().getSelectComponents();
        if (components.size() == 1) {
            return components.get(0);
        } else {
            return null;
        }
    }
}
