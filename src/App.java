import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Component;

public class App {
    private static void createAndShowGUI() {
        // 创建 JFrame 实例
        JFrame frame = new JFrame("UML editor");
        // Setting the width and height of frame
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        UMLMenuBar menuBar = new UMLMenuBar(frame);
        JPanel rootPanel = new JPanel();
        rootPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        frame.add(rootPanel);
        UMLFunctionalButtonPanel buttonPanel = new UMLFunctionalButtonPanel(rootPanel);
        UMLCanvasAreaPanel canvasAreaPanel = new UMLCanvasAreaPanel(rootPanel);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // 显示应用 GUI
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
