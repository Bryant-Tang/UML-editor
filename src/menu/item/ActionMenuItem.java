package menu.item;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public abstract class ActionMenuItem extends JMenuItem implements ActionListener {
    protected ActionMenuItem(String content) {
        super(content);
        this.addActionListener(this);
    }
}
