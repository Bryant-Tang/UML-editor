package canvas.component.connection.arrow;

import java.awt.Graphics;

/**
 * a concrete component of an arrow that represent the arrow of a composition
 * line in the UML diagram.
 */
public class CompositionArrow extends Arrow {
    @Override
    protected void paintComponent(Graphics g) {
        drawLine(g, getTopCenterInnerPosition(), getLeftCenterInnerPosition());
        drawLine(g, getLeftCenterInnerPosition(), getBottomCenterInnerPosition());
        drawLine(g, getBottomCenterInnerPosition(), getRightCenterInnerPosition());
        drawLine(g, getRightCenterInnerPosition(), getTopCenterInnerPosition());
        super.paintComponent(g);
    }
}
