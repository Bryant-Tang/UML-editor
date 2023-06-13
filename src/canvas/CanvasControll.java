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

/**
 * Controlling all things about the canvas, which is the center canvas area of
 * the app.
 * <p>
 * use <code>CanvasControll.getInstance()</code> to get the unique single
 * instance of this class.
 */
public class CanvasControll {
    // constant value
    static Dimension canvasPanelPreferredSize = new Dimension(600, 600);

    // the CanvasPanel
    private JPanel canvasPanel;
    // record all CanvasComponent in the canvasPanel
    private ArrayList<CanvasComponent> allCanvasComponent = new ArrayList<>();

    // singleton instaance
    private static CanvasControll uniqueInstance;

    private CanvasControll() {
        initCanvasPanel();
    }

    /**
     * get the unique single instance
     * 
     * @return the unique single instance of CanvasControll
     */
    public static CanvasControll getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new CanvasControll();
        }
        return uniqueInstance;
    }

    /**
     * get the canvas panel
     * 
     * @return the canvas panel
     */
    public JPanel getCanvasPanel() {
        return canvasPanel;
    }

    private void initCanvasPanel() {
        canvasPanel = new JPanel();
        canvasPanel.setPreferredSize(canvasPanelPreferredSize);
        canvasPanel.setBackground(Color.WHITE);
        canvasPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        canvasPanel.setLayout(null);
    }

    /**
     * get the size of canvas panel
     * 
     * @return the size of canvas panel
     */
    public Dimension getSize() {
        return canvasPanel.getSize();
    }

    /**
     * get the component at specific position(on CanvasPanel)
     * 
     * @param position
     * @return the component at specific position(on CanvasPanel) or null if no
     *         component is at the position(on CanvasPanel)
     */
    public CanvasComponent getComponentAt(Point position) {
        for (CanvasComponent comp : allCanvasComponent) {
            if (comp.isPositionInside(position)) {
                return comp;
            }
        }
        return null;
    }

    /**
     * get all components fully-inside specific rectangle
     * 
     * @param rect
     * @return all components fully-inside specific rectangle
     */
    public List<CanvasComponent> getComponentInsideRectangle(Rectangle rect) {
        ArrayList<CanvasComponent> insideComponents = new ArrayList<>();
        for (CanvasComponent comp : allCanvasComponent) {
            if (comp.isInsideRectangle(rect)) {
                insideComponents.add(comp);
            }
        }
        return insideComponents;
    }

    /**
     * get all components in canvas
     * 
     * @return all components in canvas
     */
    public List<CanvasComponent> getAllComponent() {
        return allCanvasComponent;
    }

    /**
     * add a CanvasComponent into canvas
     * 
     * @param comp the CanvasComponent to add
     */
    public void addComponent(CanvasComponent comp) {
        // use index 0 to add to the head but not the end
        allCanvasComponent.add(0, comp);
        canvasPanel.add(comp, 0);
        canvasPanel.repaint();
    }

    /**
     * remove a CanvasComponent from canvas
     * 
     * @param comp the CanvasComponent to remove
     */
    public void removeComponent(CanvasComponent comp) {
        allCanvasComponent.remove(comp);
        canvasPanel.remove(comp);
        canvasPanel.repaint();
    }

    /**
     * set the mode of canvas
     * 
     * @param mode the mode to set
     */
    public void setMode(Mode mode) {
        clearMode();
        canvasPanel.addMouseListener(mode);
        canvasPanel.addMouseMotionListener(mode);
    }

    // clear all MouseListener and MouseMotionListener in canvasPanel
    private void clearMode() {
        for (MouseListener mode : canvasPanel.getMouseListeners()) {
            canvasPanel.removeMouseListener(mode);
        }
        for (MouseMotionListener mode : canvasPanel.getMouseMotionListeners()) {
            canvasPanel.removeMouseMotionListener(mode);
        }
    }

    /**
     * get all components that is selected
     * 
     * @return all components that is selected
     */
    public List<CanvasComponent> getSelectComponents() {
        ArrayList<CanvasComponent> selectComponents = new ArrayList<>();
        for (CanvasComponent comp : allCanvasComponent) {
            if (comp.isSelect()) {
                selectComponents.add(comp);
            }
        }
        return selectComponents;
    }

    /**
     * set select of the specific CanvasComponent
     * 
     * @param comp   the CanvasComponent to set select
     * @param select the select value to set
     */
    public void setSelectComponent(CanvasComponent comp, boolean select) {
        if (allCanvasComponent.contains(comp)) {
            comp.setSelect(select);
            canvasPanel.repaint();
        }
    }

    /**
     * shift the specific CanvasComponent
     * 
     * @param comp  the CanvasComponent to shift
     * @param shift the vector(of coordinate system) to shift
     */
    public void shiftComponent(CanvasComponent comp, Point shift) {
        if (allCanvasComponent.contains(comp)) {
            comp.shift(shift);
            canvasPanel.repaint();
        }
    }

    /**
     * shift the specific CanvasComponent
     * 
     * @param comp the CanvasComponent to shift
     * @param x    the x-axis movement to shift
     * @param y    the y-axis movement to shift
     */
    public void shiftComponent(CanvasComponent comp, int x, int y) {
        if (allCanvasComponent.contains(comp)) {
            comp.shift(x, y);
            canvasPanel.repaint();
        }
    }
}
