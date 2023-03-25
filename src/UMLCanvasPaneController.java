import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Point;
import java.awt.Color;
import java.awt.Component;

public class UMLCanvasPaneController {
    JLayeredPane canvasPane;
    Mode mode;
    CanvasPaneListener listener;

    UMLCanvasPaneController(JLayeredPane canvasPane, Mode mode) {
        this.canvasPane = canvasPane;
        this.mode = mode;
        initialize();
    }

    private void initialize() {
        canvasPane.setLayout(null);
        canvasPane.setBorder(BorderFactory.createTitledBorder("Canvas Area"));
        canvasPane.setBackground(Color.WHITE);
        listener = new CanvasPaneListener(canvasPane, mode);
        canvasPane.addMouseListener(listener);
        canvasPane.addMouseMotionListener(listener);
    }
}

class CanvasPaneListener implements MouseListener, MouseMotionListener {
    JLayeredPane canvasPane;
    Mode mode;
    BasicObject tempComponent;
    Point tempDirectionPoint;
    boolean componentSelectSucess;

    CanvasPaneListener(JLayeredPane canvasPane, Mode mode) {
        this.canvasPane = canvasPane;
        this.mode = mode;
    }

    void createClassObject(Point position) {
        ClassObject temp = new ClassObject(position);
        canvasPane.add(temp, canvasPane.highestLayer());
        canvasPane.repaint();
    }

    void createUseCaseObject(Point position) {
        UseCaseObject temp = new UseCaseObject(position);
        canvasPane.add(temp, canvasPane.highestLayer());
        canvasPane.repaint();
    }

    void createAssociaitonObject(BasicObject componentA, Point directionPointA, BasicObject componentB,
            Point directionPointB) {
        System.out.println("creating line...");
        ConnectObject temp = new ConnectObject(directionPointA, directionPointB);
        temp.setLocation(Math.min(directionPointA.x, directionPointB.x),
                Math.min(directionPointA.y, directionPointB.y));
        canvasPane.add(temp, Math.min(canvasPane.getLayer(componentA), canvasPane.getLayer(componentB)) - 1);
        canvasPane.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (mode.mode == Mode.SELECT) {
            if (canvasPane.getComponentAt(e.getX(), e.getY()) == canvasPane) {
                for (Component comp : canvasPane.getComponents()) {
                    ((BasicObject) comp).setDirectionPointVisible(false);
                }
            } else if (componentSelectSucess) {
                BasicObject target = (BasicObject) canvasPane.getComponentAt(e.getX(), e.getY());
                for (Component comp : canvasPane.getComponents()) {
                    ((BasicObject) comp).setDirectionPointVisible(false);
                }
                target.setDirectionPointVisible(true);
            }
        } else if (mode.mode == Mode.CLASS) {
            createClassObject(e.getPoint());
            // createAssociaitonObject(e.getPoint(), new Point(e.getPoint().x + 100,
            // e.getPoint().y + 100));
        } else if (mode.mode == Mode.USE_CASE) {
            createUseCaseObject(e.getPoint());
        } else if (mode.mode == Mode.ASSOCIATION) {
            if (componentSelectSucess) {
                createClassObject(tempDirectionPoint);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (mode.mode != Mode.SELECT) {
            for (Component comp : canvasPane.getComponents()) {
                if (comp instanceof BasicObject) {
                    ((BasicObject) comp).setDirectionPointVisible(false);
                }
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (canvasPane.getComponentAt(e.getX(), e.getY()) instanceof BasicObject) {
            tempComponent = (BasicObject) canvasPane.getComponentAt(e.getX(), e.getY());
            tempDirectionPoint = tempComponent.atWhichDirectionPoint(
                    new Point(e.getPoint().x - tempComponent.getLocation().x,
                            e.getPoint().y - tempComponent.getLocation().y));
            tempDirectionPoint.setLocation(tempDirectionPoint.x + tempComponent.getLocation().x,
                    tempDirectionPoint.y + tempComponent.getLocation().y);
            componentSelectSucess = true;
            System.out.println("componentSelectSucess");
        } else {
            componentSelectSucess = false;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (canvasPane.getComponentAt(e.getX(), e.getY()) instanceof BasicObject) {
            System.out.println("componentSelectSucess");
            BasicObject temp2Component = (BasicObject) canvasPane.getComponentAt(e.getX(), e.getY());
            Point temp2DirectionPoint = temp2Component.atWhichDirectionPoint(
                    new Point(e.getPoint().x - temp2Component.getLocation().x,
                            e.getPoint().y - temp2Component.getLocation().y));
            temp2DirectionPoint.setLocation(temp2DirectionPoint.x + temp2Component.getLocation().x,
                    temp2DirectionPoint.y + temp2Component.getLocation().y);
            if (componentSelectSucess) {
                if (mode.mode == Mode.ASSOCIATION) {
                    createAssociaitonObject(tempComponent, tempDirectionPoint, temp2Component, temp2DirectionPoint);
                } else if (mode.mode == Mode.GENERALIZATION) {
                    createAssociaitonObject(tempComponent, tempDirectionPoint, temp2Component, temp2DirectionPoint);
                } else if (mode.mode == Mode.COMPOSITION) {
                    createAssociaitonObject(tempComponent, tempDirectionPoint, temp2Component, temp2DirectionPoint);
                }
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub

    }
}