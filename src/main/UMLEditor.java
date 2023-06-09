/**
 * run Launcher.java to start this app
 */
package main;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import button.FunctionalBtnControll;
import canvas.CanvasControll;
import menu.MenuControll;

public class UMLEditor {
    // constant value
    static String appTitle = "UML Editor";

    // singleton instaance
    private static UMLEditor uniqueInstance;

    private UMLEditor() {
    }

    public static UMLEditor getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new UMLEditor();
        }
        return uniqueInstance;
    }

    // the main frame(window) of this app, not construct yet
    private JFrame mainFrame;

    /**
     * run this app
     */
    public void run() {
        initMainFrame();
        addMenu();
        addButtonPane();
        addCanvasPane();
        show();
    }

    /**
     * construct the mainFrame
     * do anything about mainFrame that need to initialize
     */
    private void initMainFrame() {
        mainFrame = new JFrame(appTitle);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * add menu bar to main frame
     * do anything about menu that need to initialize
     */
    private void addMenu() {
        mainFrame.setJMenuBar(MenuControll.getInstance().getMenuBar());
    }

    /**
     * add functional button panel to main frame
     * do anything about functional button that need to initialize
     */
    private void addButtonPane() {
        mainFrame.add(FunctionalBtnControll.getInstance().getFunctioanalBtnPanel(), BorderLayout.LINE_START);
    }

    /**
     * add canvas panel to main frame
     * do anything about canvas panel that need to initialize
     */
    private void addCanvasPane() {
        mainFrame.add(CanvasControll.getInstance().getCanvasPanel(), BorderLayout.CENTER);
    }

    /**
     * show main frame
     */
    private void show() {
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
