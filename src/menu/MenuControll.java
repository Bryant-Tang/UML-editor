package menu;

import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MenuControll {
    static MenuControll uniqueInstance;

    private MenuControll() {
    }

    public static MenuControll getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new MenuControll();
        }
        return uniqueInstance;
    }

    public List<JMenu> getAllMenu() {
        //TODO: return all menu
        throw new UnsupportedOperationException();
    }
}
