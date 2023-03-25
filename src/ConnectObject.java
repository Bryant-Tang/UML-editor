import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import java.lang.Math;

public class ConnectObject extends JPanel {
    Boolean leftUpToRightDown;

    ConnectObject(Point aPoint, Point bPoint) {
        this.setOpaque(false);
        if (aPoint.x >= bPoint.x && aPoint.y >= bPoint.y) {
            this.setSize(Math.max((aPoint.x - bPoint.x), 1), Math.max((aPoint.y - bPoint.y), 1));
            leftUpToRightDown = true;
        } else if (aPoint.x < bPoint.x && aPoint.y >= bPoint.y) {
            this.setSize(Math.max((bPoint.x - aPoint.x), 1), Math.max((aPoint.y - bPoint.y), 1));
            leftUpToRightDown = false;
        } else if (aPoint.x >= bPoint.x && aPoint.y < bPoint.y) {
            this.setSize(Math.max((aPoint.x - bPoint.x), 1), Math.max((bPoint.y - aPoint.y), 1));
            leftUpToRightDown = false;
        } else if (aPoint.x < bPoint.x && aPoint.y < bPoint.y) {
            this.setSize(Math.max((bPoint.x - aPoint.x), 1), Math.max((bPoint.y - aPoint.y), 1));
            leftUpToRightDown = true;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        if (leftUpToRightDown) {
            g.drawLine(0, 0, this.getSize().width, this.getSize().height);
        } else {
            g.drawLine(this.getSize().width, 0, 0, this.getSize().height);
        }
    }
}
