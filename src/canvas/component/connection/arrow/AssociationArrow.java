package canvas.component.connection.arrow;

import java.awt.Graphics;

/**
 * a concrete component of an arrow that represent the arrow of a association
 * line in the UML diagram.
 */
public class AssociationArrow extends Arrow {
    @Override
    protected void paintComponent(Graphics g) {
        if (direction.equals(GO_UP)) {
            drawLine(g, getTopCenterInnerPosition(), getLeftCenterInnerPosition());
            drawLine(g, getTopCenterInnerPosition(), getBottomCenterInnerPosition());
            drawLine(g, getTopCenterInnerPosition(), getRightCenterInnerPosition());
        } else if (direction.equals(GO_DOWN)) {
            drawLine(g, getBottomCenterInnerPosition(), getLeftCenterInnerPosition());
            drawLine(g, getBottomCenterInnerPosition(), getTopCenterInnerPosition());
            drawLine(g, getBottomCenterInnerPosition(), getRightCenterInnerPosition());
        } else if (direction.equals(GO_LEFT)) {
            drawLine(g, getLeftCenterInnerPosition(), getTopCenterInnerPosition());
            drawLine(g, getLeftCenterInnerPosition(), getBottomCenterInnerPosition());
            drawLine(g, getLeftCenterInnerPosition(), getRightCenterInnerPosition());
        } else if (direction.equals(GO_RIGHT)) {
            drawLine(g, getRightCenterInnerPosition(), getLeftCenterInnerPosition());
            drawLine(g, getRightCenterInnerPosition(), getBottomCenterInnerPosition());
            drawLine(g, getRightCenterInnerPosition(), getTopCenterInnerPosition());
        }
        super.paintComponent(g);
    }
}
