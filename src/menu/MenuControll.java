package menu;

import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuControll {
    // constant value
    static String fileMenuText = "File";
    static String editMenuText = "Edit";
    static String renameMenuItemText = "rename";
    static String groupMenuItemText = "group";
    static String ungroupMenuItemText = "ungroup";

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
        JMenuItem renameMenuItem = new JMenuItem(renameMenuItemText);
        JMenuItem groupMenuItem = new JMenuItem(groupMenuItemText);
        JMenuItem ungroupMenuItem = new JMenuItem(ungroupMenuItemText);
        editMenu.add(renameMenuItem);
        editMenu.add(groupMenuItem);
        editMenu.add(ungroupMenuItem);
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
    }
}
