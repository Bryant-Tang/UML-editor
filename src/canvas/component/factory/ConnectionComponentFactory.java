package canvas.component.factory;

import java.awt.Point;

import canvas.component.Port;
import canvas.component.base.CanvasComponent;
import canvas.component.connection.ConnectionComponent;
import canvas.component.connection.arrow.AssociationArrow;
import canvas.component.connection.arrow.CompositionArrow;
import canvas.component.connection.arrow.GeneralizationArrow;

public class ConnectionComponentFactory implements CanvasComponentFactory {
    public static final String ASSOCIATION = "association";
    public static final String COMPOSITION = "composition";
    public static final String GENERALIZATION = "generalization";
    public static final String ONLY_LINE = "line";

    @Override
    public CanvasComponent create(String type, Point position) {
        return null;
    }

    @Override
    public CanvasComponent create(String type, Port startPort, Port endPort) {
        ConnectionComponent comp = new ConnectionComponent(startPort, endPort);
        if (type.equals(ASSOCIATION)) {
            comp.setArrow(new AssociationArrow());
        } else if (type.equals(COMPOSITION)) {
            comp.setArrow(new CompositionArrow());
        } else if (type.equals(GENERALIZATION)) {
            comp.setArrow(new GeneralizationArrow());
        }
        return comp;
    }
}
