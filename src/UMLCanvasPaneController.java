import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.Color;
import java.awt.Component;

public class UMLCanvasPaneController {
    JLayeredPane canvasPane;
    Mode mode;
    CanvasPaneListener listener;

    UMLCanvasPaneController(JLayeredPane canvasPane, Mode mode, ArrayList<BasicObject> selectComponent,
            ArrayList<CompositeObject> selectGroup) {
        this.canvasPane = canvasPane;
        this.mode = mode;
        initialize(selectComponent, selectGroup);
    }

    private void initialize(ArrayList<BasicObject> selectComponent, ArrayList<CompositeObject> selectGroup) {
        canvasPane.setLayout(null);
        canvasPane.setBorder(BorderFactory.createTitledBorder("Canvas Area"));
        canvasPane.setBackground(Color.WHITE);
        listener = new CanvasPaneListener(canvasPane, mode, selectComponent, selectGroup);
        canvasPane.addMouseListener(listener);
        canvasPane.addMouseMotionListener(listener);
    }
}

class CanvasPaneListener implements MouseListener, MouseMotionListener {
    JLayeredPane canvasPane;
    Mode mode;
    ArrayList<BasicObject> selectComponent;
    ArrayList<Point> selectPoint = new ArrayList<>();
    ArrayList<CompositeObject> selectGroup;
    Boolean pressSelectSucess;
    Boolean releaseSelectSucess;
    JLayeredPane groupSelectPane;

    CanvasPaneListener(JLayeredPane canvasPane, Mode mode, ArrayList<BasicObject> selectComponent,
            ArrayList<CompositeObject> selectGroup) {
        this.canvasPane = canvasPane;
        this.mode = mode;
        this.selectComponent = selectComponent;
        this.selectGroup = selectGroup;
        groupSelectPane = new JLayeredPane();
        groupSelectPane.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        canvasPane.add(groupSelectPane);
    }

    void createClassObject(Point position) {
        ClassObject temp = new ClassObject(position);
        temp.setLayer(temp, canvasPane.highestLayer() + 1);

        canvasPane.add(temp);
        canvasPane.repaint();
    }

    void createUseCaseObject(Point position) {
        UseCaseObject temp = new UseCaseObject(position);
        temp.setLayer(temp, canvasPane.highestLayer() + 1);
        canvasPane.add(temp);
        canvasPane.repaint();
    }

    void createAssociaitonObject(BasicObject componentA, BasicObject componentB, int directionA, int directionB) {
        ConnectObject temp = new ConnectObject(componentA, componentB, directionA, directionB,
                new AssociationLineArrow(Direction.getoOpposite(directionB)));
        temp.setLayer(temp, canvasPane.lowestLayer() - 1);
        canvasPane.add(temp);
        canvasPane.repaint();
    }

    void createGeneralizationObject(BasicObject componentA, BasicObject componentB, int directionA, int directionB) {
        ConnectObject temp = new ConnectObject(componentA, componentB, directionA, directionB,
                new GeneralizationLineArrow(Direction.getoOpposite(directionB)));
        temp.setLayer(temp, canvasPane.lowestLayer() - 1);
        canvasPane.add(temp);
        canvasPane.repaint();
    }

    void createCompositionObject(BasicObject componentA, BasicObject componentB, int directionA, int directionB) {
        ConnectObject temp = new ConnectObject(componentA, componentB, directionA, directionB,
                new CompositionLineArrow(Direction.getoOpposite(directionB)));
        temp.setLayer(temp, canvasPane.lowestLayer() - 1);
        canvasPane.add(temp);
        canvasPane.repaint();
    }

    void setAllVisible(Boolean visible) {
        for (Component comp : canvasPane.getComponents()) {
            if (comp instanceof BasicObject) {
                ((BasicObject) comp).setDirectionPointVisible(false);
            }
        }
    }

    void clearSelectComponent() {
        selectComponent.clear();
        selectPoint.clear();
        selectGroup.clear();
        pressSelectSucess = false;
        releaseSelectSucess = false;
        setAllVisible(false);
    }

    BasicObject singleSelect(Point p) {
        BasicObject tempComponent = null;
        for (Component comp : canvasPane.getComponents()) {
            if (comp instanceof BasicObject) {
                if (((BasicObject) comp).pointIsIn(p)) {
                    if (tempComponent == null
                            || JLayeredPane.getLayer((BasicObject) comp) > JLayeredPane.getLayer(tempComponent)) {
                        tempComponent = (BasicObject) comp;
                    }
                }
            }
        }
        if (tempComponent != null && tempComponent.belongGroup != null) {
            if (!selectGroup.contains(tempComponent.belongGroup.getRoot())) {
                selectGroup.add(tempComponent.belongGroup.getRoot());
            }
        }
        return tempComponent;
    }

    ArrayList<BasicObject> groupSelect(Point aPoint, Point bPoint) {
        ArrayList<BasicObject> tempSelect = new ArrayList<>();
        for (Component comp : canvasPane.getComponents()) {
            if (comp instanceof BasicObject) {
                if (((BasicObject) comp).isInRange(aPoint, bPoint)) {
                    if (!tempSelect.contains((BasicObject) comp)) {
                        tempSelect.add((BasicObject) comp);
                    }
                }
            }
        }

        for (BasicObject comp : tempSelect) {
            if (comp.belongGroup != null) {
                if (tempSelect.containsAll(comp.belongGroup.getRoot().getAllBasic())) {
                    if (!selectGroup.contains(comp.belongGroup.getRoot())) {
                        selectGroup.add(comp.belongGroup.getRoot());
                    }
                }
            }
        }
        return tempSelect;
    }

    void moveAll(ArrayList<BasicObject> tempSelect, BasicObject dragedComponent, Point p) {
        int xDisplacement = p.x - dragedComponent.getX();
        int yDisplacement = p.y - dragedComponent.getY();
        for (BasicObject comp : tempSelect) {
            comp.moveWithConnectLine(new Point(comp.getX() + xDisplacement, comp.getY() + yDisplacement));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (mode.mode == Mode.SELECT) {
            setAllVisible(false);
            if (pressSelectSucess && releaseSelectSucess && selectComponent.size() == 1) {
                selectComponent.get(0).setDirectionPointVisible(true);
            }
        } else if (mode.mode == Mode.CLASS) {
            createClassObject(e.getPoint());
        } else if (mode.mode == Mode.USE_CASE) {
            createUseCaseObject(e.getPoint());
        } else {
            setAllVisible(false);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        clearSelectComponent();
        selectPoint.add(e.getPoint());
        BasicObject tempSelect = singleSelect(e.getPoint());
        if (!selectGroup.isEmpty()) {
            if (!selectComponent.contains(tempSelect)) {
                selectComponent.add(tempSelect);
            }
            pressSelectSucess = false;
        } else if (tempSelect == null) {
            pressSelectSucess = false;
        } else {
            if (!selectComponent.contains(tempSelect)) {
                selectComponent.add(tempSelect);
            }
            pressSelectSucess = true;
        }

        canvasPane.remove(groupSelectPane);
        groupSelectPane.setLayer(groupSelectPane, canvasPane.highestLayer() + 1);
        canvasPane.add(groupSelectPane);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        selectPoint.add(e.getPoint());
        BasicObject tempSelect = singleSelect(e.getPoint());
        if (selectGroup.isEmpty() && tempSelect != null) {
            if (!selectComponent.contains(tempSelect)) {
                selectComponent.add(tempSelect);
            }
            releaseSelectSucess = true;
        } else {
            releaseSelectSucess = false;
        }

        if (pressSelectSucess && releaseSelectSucess && (selectComponent.size() == 2)) {
            if (mode.mode == Mode.ASSOCIATION) {
                createAssociaitonObject(selectComponent.get(0), selectComponent.get(1),
                        selectComponent.get(0).atWhichDirection(selectPoint.get(0)),
                        selectComponent.get(1).atWhichDirection(selectPoint.get(1)));
            } else if (mode.mode == Mode.GENERALIZATION) {
                createGeneralizationObject(selectComponent.get(0), selectComponent.get(1),
                        selectComponent.get(0).atWhichDirection(selectPoint.get(0)),
                        selectComponent.get(1).atWhichDirection(selectPoint.get(1)));
            } else if (mode.mode == Mode.COMPOSITION) {
                createCompositionObject(selectComponent.get(0), selectComponent.get(1),
                        selectComponent.get(0).atWhichDirection(selectPoint.get(0)),
                        selectComponent.get(1).atWhichDirection(selectPoint.get(1)));
            }
        }
        groupSelectPane.setVisible(false);
        if (!pressSelectSucess) {
            selectComponent.clear();
            ArrayList<BasicObject> tempSelectArray = groupSelect(selectPoint.get(0), selectPoint.get(1));
            for (BasicObject comp : tempSelectArray) {
                if (comp.belongGroup == null && !selectComponent.contains(comp)) {
                    selectComponent.add(comp);
                }
            }
            if (mode.mode == Mode.SELECT) {
                for (BasicObject comp : selectComponent) {
                    comp.setDirectionPointVisible(true);
                }
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (mode.mode == Mode.SELECT) {
            if (selectGroup.isEmpty() && pressSelectSucess) {
                selectComponent.get(0).moveWithConnectLine(e.getPoint());
            } else if (!selectGroup.isEmpty()) {
                moveAll(selectGroup.get(0).getAllBasic(), selectComponent.get(0), e.getPoint());
            } else if (!pressSelectSucess) {
                setAllVisible(false);
                groupSelectPane.setSize(Math.abs(e.getX() - selectPoint.get(0).x),
                        Math.abs(e.getY() - selectPoint.get(0).y));
                groupSelectPane.setLocation(Math.min(selectPoint.get(0).x, e.getX()),
                        Math.min(selectPoint.get(0).y, e.getY()));
                groupSelectPane.setVisible(true);
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}