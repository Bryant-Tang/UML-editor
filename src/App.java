import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class App {
    static String appTitle = "UML Editor";
    static Dimension canvasPreferredSize = new Dimension(400, 400);

    public static void main(String[] args) {
        JFrame mainFrame = createMainFrame();
        addMenu(mainFrame);
        addButtonPane(mainFrame);
        addCanvasPane(mainFrame);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    static JFrame createMainFrame() {
        JFrame mainFrame = new JFrame(appTitle);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return mainFrame;
    }

    static void addMenu(JFrame mainFrame) {
        JMenuItem renameMenuItem = new JMenuItem("rename");
        JMenuItem groupMenuItem = new JMenuItem("group");
        JMenuItem ungroupMenuItem = new JMenuItem("ungroup");
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenuBar menuBar = new JMenuBar();
        editMenu.add(renameMenuItem);
        editMenu.add(groupMenuItem);
        editMenu.add(ungroupMenuItem);
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        mainFrame.setJMenuBar(menuBar);
    }

    static void addButtonPane(JFrame mainFrame) {
        JPanel buttonPane = new JPanel();
        mainFrame.add(buttonPane, BorderLayout.LINE_START);
    }

    static void addCanvasPane(JFrame mainFrame) {
        JPanel canvas = new JPanel();
        canvas.setPreferredSize(canvasPreferredSize);
        canvas.setBackground(Color.WHITE);
        mainFrame.add(canvas, BorderLayout.CENTER);
    }
}
