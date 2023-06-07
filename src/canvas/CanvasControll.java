package canvas;

import javax.swing.JPanel;
import java.awt.Dimension;

public class CanvasControll {
    // constant value
    static Dimension canvasPanelPreferredSize = new Dimension(400, 400);
    // singleton instaance
    static CanvasControll uniqueInstance;

    private CanvasControll() {
    }

    public static CanvasControll getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new CanvasControll();
        }
        return uniqueInstance;
    }

    JPanel canvasPanel;

    public JPanel getCanvasPanel() {
        if (canvasPanel == null) {
            initCanvasPanel();
        }
        return canvasPanel;
    }

    void initCanvasPanel() {
        canvasPanel = new JPanel();
        canvasPanel.setPreferredSize(canvasPanelPreferredSize);
    }
}
