package canvas.component;

import java.awt.Point;

import canvas.component.base.CanvasComponent;
import main.Calculate;

public class Port {
    // public constant value
    public static final String TOP_SIDE = "top";
    public static final String BOTTOM_SIDE = "bottom";
    public static final String LEFT_SIDE = "left";
    public static final String RIGHT_SIDE = "right";

    // constant value
    static Point leftTopPosition = Calculate.ZERO_POINT;

    // record the CanvasComponent that this Port belong to
    private CanvasComponent comp;
    // record which side of the CanvasComponent that this Port belong to
    private String side;

    public Port(CanvasComponent comp, String side) {
        this.comp = comp;
        this.side = side;
    }

    /**
     * get the position(on CanvasPanel) of this Port
     * 
     * @return the position(on CanvasPanel) of this Port
     */
    public Point getPosition() {
        if (comp.getPortPosition(side) != null) {
            return comp.getPortPosition(side);
        } else {
            return leftTopPosition;
        }
    }

    /**
     * get which side of the CanvasComponent that this Port belong to
     * 
     * @return the side of the CanvasComponent that this Port belong to
     */
    public String getSide() {
        return side;
    }
}
