package canvas;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import canvas.component.base.CanvasComponent;
import canvas.mode.Mode;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Point;
import java.awt.Rectangle;

import java.util.ArrayList;
import java.util.List;

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
    private ArrayList<CanvasComponent> allCanvasComponent = new ArrayList<>();

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
    }

    public Dimension getSize() {
        return canvasPanel.getSize();
    }

    public CanvasComponent getComponentAt(Point position) {
        for (CanvasComponent comp : allCanvasComponent) {
            if (comp.isPositionInside(position)) {
                return comp;
            }
        }
        return null;
    }

    public List<CanvasComponent> getComponentInsideRectangle(Rectangle rect) {
        ArrayList<CanvasComponent> insideComponents = new ArrayList<>();
        for (CanvasComponent comp : allCanvasComponent) {
            if (comp.isInsideRectangle(rect)) {
                insideComponents.add(comp);
            }
        }
        return insideComponents;
    }

    public List<CanvasComponent> getAllComponent() {
        return allCanvasComponent;
    }

    public void addComponent(CanvasComponent comp) {
        // use index 0 to add to the head but not the end
        allCanvasComponent.add(0, comp);
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

    public List<CanvasComponent> getSelectComponents() {
        ArrayList<CanvasComponent> selectComponents = new ArrayList<>();
        for (CanvasComponent comp : allCanvasComponent) {
            if (comp.isSelect()) {
                selectComponents.add(comp);
            }
        }
        return selectComponents;
    }
}
