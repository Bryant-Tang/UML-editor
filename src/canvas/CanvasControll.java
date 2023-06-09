package canvas;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import canvas.component.basic.ClassComponent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

public class CanvasControll {
    // constant value
    static Dimension canvasPanelPreferredSize = new Dimension(400, 400);

    // singleton instaance
    private static CanvasControll uniqueInstance;

    private CanvasControll() {
    }

    public static CanvasControll getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new CanvasControll();
        }
        return uniqueInstance;
    }

    private JPanel canvasPanel;

    public JPanel getCanvasPanel() {
        if (canvasPanel == null) {
            initCanvasPanel();
        }
        return canvasPanel;
    }

    private void initCanvasPanel() {
        canvasPanel = new JPanel();
        canvasPanel.setPreferredSize(canvasPanelPreferredSize);
        canvasPanel.setBackground(Color.WHITE);
        canvasPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        canvasPanel.setLayout(null);
        ClassComponent c = new ClassComponent(new Point(100, 100), "class");
        canvasPanel.add(c);

    }
}
