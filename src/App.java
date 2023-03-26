import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

public class App {
    static Mode mode;
    static UMLMenuBarController UMLMenuBar;
    static UMLButtonPaneController UMLButtonPane;
    static UMLCanvasPaneController UMLCanvasPane;
    static ArrayList<BasicObject> selectComponent = new ArrayList<>();
    static ArrayList<CompositeObject> selectGroup = new ArrayList<>();

    private static void createApp() {
        JFrame mainFrame = new JFrame("UML editor");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mode = new Mode(Mode.SELECT);

        JMenuBar menuBar = new JMenuBar();
        mainFrame.setJMenuBar(menuBar);
        UMLMenuBar = new UMLMenuBarController(mainFrame, menuBar, selectComponent, selectGroup);

        JPanel contentPane = new JPanel(new BorderLayout(5, 5));
        mainFrame.setContentPane(contentPane);

        JPanel buttonPane = new JPanel(new BorderLayout(5, 5));
        contentPane.add(buttonPane, BorderLayout.LINE_START);
        UMLButtonPane = new UMLButtonPaneController(buttonPane, mode);

        JLayeredPane canvasPane = new JLayeredPane();
        contentPane.add(canvasPane, BorderLayout.CENTER);
        UMLCanvasPane = new UMLCanvasPaneController(canvasPane, mode, selectComponent, selectGroup);

        mainFrame.setSize(new Dimension(960, 640));
        mainFrame.setMinimumSize(new Dimension(640, 640));
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createApp();
            }
        });
    }
}
