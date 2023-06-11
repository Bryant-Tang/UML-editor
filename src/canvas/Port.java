package canvas;

import java.awt.Point;

import canvas.component.base.CanvasComponent;

public class Port {
    public static final String TOP_SIDE = "top";
    public static final String BOTTOM_SIDE = "bottom";
    public static final String LEFT_SIDE = "left";
    public static final String RIGHT_SIDE = "right";
    static Point leftTopPosition = new Point(0, 0);

    private CanvasComponent comp;
    private String side;

    public Port(CanvasComponent comp, String side) {
        this.comp = comp;
        this.side = side;
    }

    public Point getPosition() {
        if (comp.getPortPosition(side) != null) {
            return comp.getPortPosition(side);
        } else {
            return leftTopPosition;
        }
    }
}
