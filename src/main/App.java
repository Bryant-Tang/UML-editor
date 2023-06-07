package main;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import canvas.CanvasControll;
import functional_button.FunctionalBtnControll;
import menu.MenuControll;

import java.awt.BorderLayout;

public class App {
    // constant value
    static String appTitle = "UML Editor";

    // the main frame(window) of this app, not construct yet
    static JFrame mainFrame;

    public static void main(String[] args) {
        initMainFrame();
        addMenu();
        addButtonPane();
        addCanvasPane();
        run();
    }

    /**
     * construct the mainFrame
     * do anything about mainFrame that need to initialize
     */
    static void initMainFrame() {
        JFrame mainFrame = new JFrame(appTitle);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * construct menu bar with menu items then add it to main frame
     * do anything about menu that need to initialize
     */
    static void addMenu() {
        JMenuBar menuBar = new JMenuBar();
        for (JMenu menu : MenuControll.getInstance().getAllMenu()) {
            menuBar.add(menu);
        }
        mainFrame.setJMenuBar(menuBar);
    }

    static void addButtonPane() {
        JPanel buttonPane = new JPanel();
        for (JButton btn : FunctionalBtnControll.getInstance().getAllFunctionalBtn()) {
            buttonPane.add(btn);
        }
        mainFrame.add(buttonPane, BorderLayout.LINE_START);
    }

    static void addCanvasPane() {
        mainFrame.add(CanvasControll.getInstance().getCanvasPane(), BorderLayout.CENTER);
    }

    static void run() {
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
