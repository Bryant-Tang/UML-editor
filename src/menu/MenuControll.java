package menu;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import menu.item.GroupMenuItem;
import menu.item.RenameMenuItem;
import menu.item.UngroupMenuItem;

/**
 * Controlling all things about the menu on the top side of this app.
 * <p>
 * use <code>MenuControll.getInstance()</code> to get the unique single
 * instance of this class.
 */
public class MenuControll {
    // constant value
    static String fileMenuText = "File";
    static String editMenuText = "Edit";

    // the menu bar to controll
    private JMenuBar menuBar;

    // singleton instaance
    private static MenuControll uniqueInstance;

    private MenuControll() {
        initMenuBar();
    }

    /**
     * get the unique single instance
     * 
     * @return the unique single instance of MenuControll
     */
    public static MenuControll getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new MenuControll();
        }
        return uniqueInstance;
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

    /**
     * get the menu bar
     * 
     * @return the menu bar as JMenuBar
     */
    public JMenuBar getMenuBar() {
        return menuBar;
    }
}
