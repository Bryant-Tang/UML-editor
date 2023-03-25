import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;

public class App {
    static Mode mode;
    static UMLMenuBarController UMLMenuBar;
    static UMLButtonPaneController UMLButtonPane;
    static UMLCanvasPaneController UMLCanvasPane;

    private static void createApp() {
        JFrame frame = new JFrame("UML editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mode = new Mode(Mode.SELECT);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        UMLMenuBar = new UMLMenuBarController(menuBar);

        JPanel contentPane = new JPanel(new BorderLayout(5, 5));
        frame.setContentPane(contentPane);

        JPanel buttonPane = new JPanel(new BorderLayout(5, 5));
        contentPane.add(buttonPane, BorderLayout.LINE_START);
        UMLButtonPane = new UMLButtonPaneController(buttonPane, mode);

        JLayeredPane canvasPane = new JLayeredPane();
        contentPane.add(canvasPane, BorderLayout.CENTER);
        UMLCanvasPane = new UMLCanvasPaneController(canvasPane, mode);

        frame.setSize(new Dimension(960, 640));
        frame.setMinimumSize(new Dimension(640, 640));
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createApp();
            }
        });
    }
}
