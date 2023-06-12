package menu;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import menu.item.GroupMenuItem;
import menu.item.RenameMenuItem;
import menu.item.UngroupMenuItem;

public class MenuControll {
    // constant value
    static String fileMenuText = "File";
    static String editMenuText = "Edit";

    // singleton instaance
    private static MenuControll uniqueInstance;

    private MenuControll() {
    }

    public static MenuControll getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new MenuControll();
        }
        return uniqueInstance;
    }

    private JMenuBar menuBar;

    public JMenuBar getMenuBar() {
        if (menuBar == null) {
            initMenuBar();
        }
        return menuBar;
    }

    private void initMenuBar() {
        menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu(fileMenuText);
        JMenu editMenu = new JMenu(editMenuText);
        editMenu.add(new RenameMenuItem());
        editMenu.add(new GroupMenuItem());
        editMenu.add(new UngroupMenuItem());
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
    }
}
