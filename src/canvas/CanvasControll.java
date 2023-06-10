package canvas;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import canvas.component.base.CanvasComponent;
import canvas.component.factory.BasicComponentFactory;
import canvas.mode.AddBasicMode;
import canvas.mode.Mode;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

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
        setMode(new AddBasicMode(new BasicComponentFactory(), BasicComponentFactory.USE_CASE_COMPONENT));
    }

    public void addComponent(CanvasComponent comp) {
        // use index 0 to add to the head but not the end
        canvasPanel.add(comp, 0);
        canvasPanel.repaint();
    }

    public void setMode(Mode mode) {
        clearMode();
        canvasPanel.addMouseListener(mode);
        canvasPanel.addMouseMotionListener(mode);
    }

    private void clearMode() {
        for (MouseListener mode : canvasPanel.getMouseListeners()) {
            canvasPanel.removeMouseListener(mode);
        }
        for (MouseMotionListener mode : canvasPanel.getMouseMotionListeners()) {
            canvasPanel.removeMouseMotionListener(mode);
        }
    }
}
