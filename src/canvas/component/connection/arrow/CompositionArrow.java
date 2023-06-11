package canvas.component.connection.arrow;

import java.awt.Graphics;

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
