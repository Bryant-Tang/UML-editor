package canvas.component.base;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Dimension;

public abstract class CanvasComponent extends JPanel {
    // constant value
    static Dimension defaultSize = new Dimension(1, 1);

    protected CanvasComponent(Point position) {
        super();
        this.setLocation(position);
        this.setSize(defaultSize);
    }

    private void resize() {
        this.revalidate();
        this.setSize(getPreferredSize());
    }

    @Override
    public void paint(Graphics g) {
        this.resize();
        super.paint(g);
    }
}
