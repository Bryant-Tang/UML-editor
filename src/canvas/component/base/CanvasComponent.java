package canvas.component.base;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Dimension;

public abstract class CanvasComponent extends JPanel {
    // constant value
    static Dimension defaultSize = new Dimension(1, 1);

    protected CanvasComponent(Point position) {
        this.setLocation(position);
        this.setSize(defaultSize);
    }

    protected void resize() {
        this.setSize(getPreferredSize());
    }

    @Override
    public void paint(Graphics g) {
        this.resize();
        super.paint(g);
    }
}
