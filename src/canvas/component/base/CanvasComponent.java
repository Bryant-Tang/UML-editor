package canvas.component.base;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Dimension;

public abstract class CanvasComponent extends JPanel {
    static Dimension defaultSize = new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);

    protected CanvasComponent(Point position) {
        this.setLocation(position);
        this.setSize(defaultSize);
    }

    protected void resize() {
        this.setSize(getPreferredSize());
    }

    @Override
    protected void paintComponent(Graphics g) {
        this.resize();
        super.paintComponent(g);
    }
}
