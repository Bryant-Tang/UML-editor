package canvas;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import canvas.component.base.CanvasComponent;
import canvas.component.basic.ClassComponent;
import canvas.component.basic.UseCaseComponent;
import canvas.component.factory.BasicComponentFactory;
import canvas.component.factory.ConnectionComponentFactory;
import canvas.mode.AddBasicMode;
import canvas.mode.AddConnectionMode;
import canvas.mode.Mode;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Point;
import java.util.ArrayList;

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
    private ArrayList<CanvasComponent> comps = new ArrayList<>();

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
        addComponent(new ClassComponent(new Point(10, 10), "name"));
        addComponent(new UseCaseComponent(new Point(210, 210), "name"));
        setMode(new AddConnectionMode(new ConnectionComponentFactory(), ConnectionComponentFactory.ASSOCIATION));
    }

    public Dimension getSize() {
        return canvasPanel.getSize();
    }

    public CanvasComponent getComponentAt(Point position) {
        for (CanvasComponent comp : comps) {
            if (comp.isPositionInside(position)) {
                return comp;
            }
        }
        return null;
    }

    public void addComponent(CanvasComponent comp) {
        // use index 0 to add to the head but not the end
        comps.add(0, comp);
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
