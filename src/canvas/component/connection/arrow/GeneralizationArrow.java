package canvas.component.connection.arrow;

import java.awt.Graphics;

public class GeneralizationArrow extends Arrow {
    @Override
    protected void paintComponent(Graphics g) {
        if (belongConnection != null) {
            if (belongConnection.getArrowDirection().equals(GO_UP)) {
                drawLine(g, getTopCenterInnerPosition(), getBottomLeftInnerPosition());
                drawLine(g, getBottomLeftInnerPosition(), getBottomRightInnerPosition());
                drawLine(g, getBottomRightInnerPosition(), getTopCenterInnerPosition());
            } else if (belongConnection.getArrowDirection().equals(GO_DOWN)) {
                drawLine(g, getBottomCenterInnerPosition(), getTopLeftInnerPosition());
                drawLine(g, getTopLeftInnerPosition(), getTopRightInnerPosition());
                drawLine(g, getTopRightInnerPosition(), getBottomCenterInnerPosition());
            } else if (belongConnection.getArrowDirection().equals(GO_LEFT)) {
                drawLine(g, getLeftCenterInnerPosition(), getTopRightInnerPosition());
                drawLine(g, getTopRightInnerPosition(), getBottomRightInnerPosition());
                drawLine(g, getBottomRightInnerPosition(), getLeftCenterInnerPosition());
            } else if (belongConnection.getArrowDirection().equals(GO_RIGHT)) {
                drawLine(g, getRightCenterInnerPosition(), getTopLeftInnerPosition());
                drawLine(g, getTopLeftInnerPosition(), getBottomLeftInnerPosition());
                drawLine(g, getBottomLeftInnerPosition(), getRightCenterInnerPosition());
            }
        }
        super.paintComponent(g);
    }
}