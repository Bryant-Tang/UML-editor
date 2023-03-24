import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class UMLCanvasAreaPanel {
    JPanel rootPanel;
    JPanel canvasPanel;

    UMLCanvasAreaPanel(JPanel rootPanel) {
        this.rootPanel = rootPanel;
        initialize();
    }

    private void initialize() {
        rootPanel = new JPanel();
        canvasPanel = new JPanel();
        canvasPanel.setSize(new Dimension(400, 400));
        canvasPanel.setBorder(BorderFactory.createTitledBorder("Canvas Area"));
        rootPanel.add(canvasPanel);
    }
}
