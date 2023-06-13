package menu.item;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenuItem;

import canvas.CanvasControll;
import canvas.component.base.CanvasComponent;

/**
 * a MenuItem that do things when being clicked.
 * <p>
 * invoke <code>actionPerformed()</code> when being clicked.
 * make sure to override it.
 */
public abstract class ActionMenuItem extends JMenuItem implements ActionListener {
    /**
     * construct a ActionMenuItem
     * 
     * @param content the content to show inside this menu item
     */
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
