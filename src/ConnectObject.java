import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Dimension;

import java.lang.Math;

class Direction {
    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;

    public static int getoOpposite(int direction) {
        if (direction == Direction.UP) {
            return Direction.DOWN;
        } else if (direction == Direction.RIGHT) {
            return Direction.LEFT;
        } else if (direction == Direction.DOWN) {
            return Direction.UP;
        } else if (direction == Direction.LEFT) {
            return Direction.RIGHT;
        } else {
            return -1;
        }
    }
}

class Arrow extends JPanel {
    int direction;

    Arrow(int direction) {
        this.direction = direction;
        setOpaque(false);
        setPreferredSize(new Dimension(20, 20));
        setSize(getPreferredSize());
    }
}

class AssociationLineArrow extends Arrow {
    AssociationLineArrow(int direction) {
        super(direction);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        Point uPoint = new Point(this.getWidth() / 2, 0);
        Point rPoint = new Point(this.getWidth(), this.getHeight() / 2);
        Point dPoint = new Point(this.getWidth() / 2, this.getHeight());
        Point lPoint = new Point(0, this.getHeight() / 2);
        if (this.direction == Direction.UP) {
            g.drawLine(uPoint.x, uPoint.y, lPoint.x, lPoint.y);
            g.drawLine(uPoint.x, uPoint.y, dPoint.x, dPoint.y);
            g.drawLine(uPoint.x, uPoint.y, rPoint.x, rPoint.y);
        } else if (this.direction == Direction.RIGHT) {
            g.drawLine(rPoint.x, rPoint.y, uPoint.x, uPoint.y);
            g.drawLine(rPoint.x, rPoint.y, lPoint.x, lPoint.y);
            g.drawLine(rPoint.x, rPoint.y, dPoint.x, dPoint.y);
        } else if (this.direction == Direction.DOWN) {
            g.drawLine(dPoint.x, dPoint.y, rPoint.x, rPoint.y);
            g.drawLine(dPoint.x, dPoint.y, uPoint.x, uPoint.y);
            g.drawLine(dPoint.x, dPoint.y, lPoint.x, lPoint.y);
        } else if (this.direction == Direction.LEFT) {
            g.drawLine(lPoint.x, lPoint.y, dPoint.x, dPoint.y);
            g.drawLine(lPoint.x, lPoint.y, rPoint.x, rPoint.y);
            g.drawLine(lPoint.x, lPoint.y, uPoint.x, uPoint.y);
        }
    }
}

class GeneralizationLineArrow extends Arrow {
    GeneralizationLineArrow(int direction) {
        super(direction);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        Point uPoint = new Point(this.getWidth() / 2, 0);
        Point rPoint = new Point(this.getWidth(), this.getHeight() / 2);
        Point dPoint = new Point(this.getWidth() / 2, this.getHeight());
        Point lPoint = new Point(0, this.getHeight() / 2);
        Point cPoint = new Point(this.getWidth() / 2, this.getHeight() / 2);
        if (this.direction == Direction.UP) {
            g.drawLine(uPoint.x, uPoint.y, lPoint.x, lPoint.y);
            g.drawLine(cPoint.x, cPoint.y, dPoint.x, dPoint.y);
            g.drawLine(uPoint.x, uPoint.y, rPoint.x, rPoint.y);
            g.drawLine(lPoint.x, lPoint.y, rPoint.x, rPoint.y);
        } else if (this.direction == Direction.RIGHT) {
            g.drawLine(rPoint.x, rPoint.y, uPoint.x, uPoint.y);
            g.drawLine(cPoint.x, cPoint.y, lPoint.x, lPoint.y);
            g.drawLine(rPoint.x, rPoint.y, dPoint.x, dPoint.y);
            g.drawLine(uPoint.x, uPoint.y, dPoint.x, dPoint.y);
        } else if (this.direction == Direction.DOWN) {
            g.drawLine(dPoint.x, dPoint.y, rPoint.x, rPoint.y);
            g.drawLine(cPoint.x, cPoint.y, uPoint.x, uPoint.y);
            g.drawLine(dPoint.x, dPoint.y, lPoint.x, lPoint.y);
            g.drawLine(lPoint.x, lPoint.y, rPoint.x, rPoint.y);
        } else if (this.direction == Direction.LEFT) {
            g.drawLine(lPoint.x, lPoint.y, dPoint.x, dPoint.y);
            g.drawLine(cPoint.x, cPoint.y, rPoint.x, rPoint.y);
            g.drawLine(lPoint.x, lPoint.y, uPoint.x, uPoint.y);
            g.drawLine(uPoint.x, uPoint.y, dPoint.x, dPoint.y);
        }

    }
}

class CompositionLineArrow extends Arrow {
    CompositionLineArrow(int direction) {
        super(direction);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        Point uPoint = new Point(this.getWidth() / 2, 0);
        Point rPoint = new Point(this.getWidth(), this.getHeight() / 2);
        Point dPoint = new Point(this.getWidth() / 2, this.getHeight());
        Point lPoint = new Point(0, this.getHeight() / 2);

        g.drawLine(uPoint.x, uPoint.y, lPoint.x, lPoint.y);
        g.drawLine(dPoint.x, dPoint.y, lPoint.x, lPoint.y);
        g.drawLine(uPoint.x, uPoint.y, rPoint.x, rPoint.y);
        g.drawLine(dPoint.x, dPoint.y, rPoint.x, rPoint.y);
    }
}

class ConnectLine extends JPanel {
    Boolean leftUpToRightDown;

    ConnectLine() {
        super();
        this.setOpaque(false);
        this.setSize(0, 0);
    }

    ConnectLine(Point aPoint, Point bPoint) {
        super();
        this.setOpaque(false);
        this.adjustSize(aPoint, bPoint);
    }

    void adjustSize(Point aPoint, Point bPoint) {
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
            g.drawLine(1, 1, Math.max(1, this.getWidth() - 1), Math.max(1, this.getHeight() - 1));
        } else {
            g.drawLine(Math.max(1, this.getWidth() - 1), 1, 1, Math.max(1, this.getHeight() - 1));
        }
    }
}

public class ConnectObject extends JLayeredPane {
    ConnectLine line;
    Arrow arrow;
    BasicObject aComponent;
    BasicObject bComponent;
    int aComponentDirection;
    int bComponentDirection;

    ConnectObject(BasicObject aComponent, BasicObject bComponent, int aComponentDirection, int bComponentDirection,
            Arrow arrow) {
        super();
        this.aComponent = aComponent;
        this.bComponent = bComponent;
        this.aComponentDirection = aComponentDirection;
        this.bComponentDirection = bComponentDirection;
        this.arrow = arrow;
        line = new ConnectLine();
        this.adjustLine();
        this.add(arrow);
        this.add(line);

        this.aComponent.addLine(this);
        this.bComponent.addLine(this);
    }

    void adjustLine() {
        Point aPoint = aComponent.getDirectionPoint(aComponentDirection);
        Point bPoint = bComponent.getDirectionPoint(bComponentDirection);
        if (aPoint.x >= bPoint.x && aPoint.y >= bPoint.y) {
            Point lineStart = aPoint;
            Point lineEnd;
            Point objectLeftTop;
            Point objectRightBtm = new Point(aPoint);
            Point arrowLeftTop;
            if (arrow.direction == Direction.UP) {
                lineEnd = new Point(bPoint.x, bPoint.y + arrow.getHeight());
                objectLeftTop = new Point(bPoint.x - (arrow.getWidth() / 2), bPoint.y);
                arrowLeftTop = new Point(bPoint.x - (arrow.getWidth() / 2), bPoint.y);
            } else if (arrow.direction == Direction.RIGHT) {
                lineEnd = new Point(bPoint.x - arrow.getWidth(), bPoint.y);
                objectLeftTop = new Point(bPoint.x - arrow.getWidth(), bPoint.y - (arrow.getHeight() / 2));
                arrowLeftTop = new Point(bPoint.x - arrow.getWidth(), bPoint.y - (arrow.getHeight() / 2));
            } else if (arrow.direction == Direction.DOWN) {
                lineEnd = new Point(bPoint.x, bPoint.y - arrow.getHeight());
                objectLeftTop = new Point(bPoint.x - (arrow.getWidth() / 2), bPoint.y - arrow.getHeight());
                arrowLeftTop = new Point(bPoint.x - (arrow.getWidth() / 2), bPoint.y - arrow.getHeight());
            } else if (arrow.direction == Direction.LEFT) {
                lineEnd = new Point(bPoint.x + arrow.getWidth(), bPoint.y);
                objectLeftTop = new Point(bPoint.x, bPoint.y - (arrow.getHeight() / 2));
                arrowLeftTop = new Point(bPoint.x, bPoint.y - (arrow.getHeight() / 2));
            } else {
                lineEnd = bPoint;
                objectLeftTop = bPoint;
                arrowLeftTop = new Point(bPoint.x, bPoint.y);
            }
            line.adjustSize(lineStart, lineEnd);
            this.setSize(objectRightBtm.x - objectLeftTop.x, objectRightBtm.y - objectLeftTop.y);
            this.setLocation(objectLeftTop);
            arrow.setLocation(arrowLeftTop.x - this.getX(), arrowLeftTop.y - this.getY());
            line.setLocation(lineEnd.x - this.getX(), lineEnd.y - this.getY());
        } else if (aPoint.x < bPoint.x && aPoint.y >= bPoint.y) {
            Point lineStart = aPoint;
            Point lineEnd;
            Point objectLeftTop;
            Point objectRightBtm;
            Point arrowLeftTop;
            if (arrow.direction == Direction.UP) {
                lineEnd = new Point(bPoint.x, bPoint.y + arrow.getHeight());
                objectLeftTop = new Point(aPoint.x, bPoint.y);
                objectRightBtm = new Point(bPoint.x + (arrow.getWidth() / 2), aPoint.y);
                arrowLeftTop = new Point(bPoint.x - (arrow.getWidth() / 2), bPoint.y);
            } else if (arrow.direction == Direction.RIGHT) {
                lineEnd = new Point(bPoint.x - arrow.getWidth(), bPoint.y);
                objectLeftTop = new Point(aPoint.x, bPoint.y - (arrow.getHeight() / 2));
                objectRightBtm = new Point(bPoint.x, aPoint.y);
                arrowLeftTop = new Point(bPoint.x - arrow.getWidth(), bPoint.y - (arrow.getHeight() / 2));
            } else if (arrow.direction == Direction.DOWN) {
                lineEnd = new Point(bPoint.x, bPoint.y - arrow.getHeight());
                objectLeftTop = new Point(aPoint.x, bPoint.y - arrow.getHeight());
                objectRightBtm = new Point(bPoint.x + (arrow.getWidth() / 2), aPoint.y);
                arrowLeftTop = new Point(bPoint.x - (arrow.getWidth() / 2), bPoint.y - arrow.getHeight());
            } else if (arrow.direction == Direction.LEFT) {
                lineEnd = new Point(bPoint.x + arrow.getWidth(), bPoint.y);
                objectLeftTop = new Point(aPoint.x, bPoint.y - (arrow.getHeight() / 2));
                objectRightBtm = new Point(bPoint.x + arrow.getWidth(), aPoint.y);
                arrowLeftTop = new Point(bPoint.x, bPoint.y - (arrow.getHeight() / 2));
            } else {
                lineEnd = bPoint;
                objectLeftTop = new Point(aPoint.x, bPoint.y);
                objectRightBtm = new Point(bPoint.x, aPoint.y);
                arrowLeftTop = new Point(bPoint.x, bPoint.y);
            }
            line.adjustSize(lineStart, lineEnd);
            this.setSize(objectRightBtm.x - objectLeftTop.x, objectRightBtm.y - objectLeftTop.y);
            this.setLocation(objectLeftTop);
            arrow.setLocation(arrowLeftTop.x - this.getX(), arrowLeftTop.y - this.getY());
            line.setLocation(lineStart.x - this.getX(), lineEnd.y - this.getY());
        } else if (aPoint.x >= bPoint.x && aPoint.y < bPoint.y) {
            Point lineStart = aPoint;
            Point lineEnd;
            Point objectLeftTop;
            Point objectRightBtm;
            Point arrowLeftTop;
            if (arrow.direction == Direction.UP) {
                lineEnd = new Point(bPoint.x, bPoint.y + arrow.getHeight());
                objectLeftTop = new Point(bPoint.x - (arrow.getWidth() / 2), aPoint.y);
                objectRightBtm = new Point(aPoint.x, bPoint.y + arrow.getHeight());
                arrowLeftTop = new Point(bPoint.x - (arrow.getWidth() / 2), bPoint.y);
            } else if (arrow.direction == Direction.RIGHT) {
                lineEnd = new Point(bPoint.x - arrow.getWidth(), bPoint.y);
                objectLeftTop = new Point(bPoint.x - arrow.getWidth(), aPoint.y);
                objectRightBtm = new Point(aPoint.x, bPoint.y + (arrow.getHeight() / 2));
                arrowLeftTop = new Point(bPoint.x - arrow.getWidth(), bPoint.y - (arrow.getHeight() / 2));
            } else if (arrow.direction == Direction.DOWN) {
                lineEnd = new Point(bPoint.x, bPoint.y - arrow.getHeight());
                objectLeftTop = new Point(bPoint.x - (arrow.getWidth() / 2), aPoint.y);
                objectRightBtm = new Point(aPoint.x, bPoint.y);
                arrowLeftTop = new Point(bPoint.x - (arrow.getWidth() / 2), bPoint.y - arrow.getHeight());
            } else if (arrow.direction == Direction.LEFT) {
                lineEnd = new Point(bPoint.x + arrow.getWidth(), bPoint.y);
                objectLeftTop = new Point(bPoint.x, aPoint.y);
                objectRightBtm = new Point(aPoint.x, bPoint.y + (arrow.getHeight() / 2));
                arrowLeftTop = new Point(bPoint.x, bPoint.y - (arrow.getHeight() / 2));
            } else {
                lineEnd = bPoint;
                objectLeftTop = new Point(bPoint.x, aPoint.y);
                objectRightBtm = new Point(aPoint.x, bPoint.y);
                arrowLeftTop = new Point(bPoint.x, bPoint.y);
            }
            line.adjustSize(lineStart, lineEnd);
            this.setSize(objectRightBtm.x - objectLeftTop.x, objectRightBtm.y - objectLeftTop.y);
            this.setLocation(objectLeftTop);
            arrow.setLocation(arrowLeftTop.x - this.getX(), arrowLeftTop.y - this.getY());
            line.setLocation(lineEnd.x - this.getX(), lineStart.y - this.getY());
        } else if (aPoint.x < bPoint.x && aPoint.y < bPoint.y) {
            Point lineStart = aPoint;
            Point lineEnd;
            Point objectLeftTop;
            Point objectRightBtm;
            Point arrowLeftTop;
            if (arrow.direction == Direction.UP) {
                lineEnd = new Point(bPoint.x, bPoint.y + arrow.getHeight());
                objectLeftTop = new Point(aPoint);
                objectRightBtm = new Point(bPoint.x + (arrow.getWidth() / 2), bPoint.y + arrow.getHeight());
                arrowLeftTop = new Point(bPoint.x - (arrow.getWidth() / 2), bPoint.y);
            } else if (arrow.direction == Direction.RIGHT) {
                lineEnd = new Point(bPoint.x - arrow.getWidth(), bPoint.y);
                objectLeftTop = new Point(aPoint);
                objectRightBtm = new Point(bPoint.x, bPoint.y + (arrow.getHeight() / 2));
                arrowLeftTop = new Point(bPoint.x - arrow.getWidth(), bPoint.y - (arrow.getHeight() / 2));
            } else if (arrow.direction == Direction.DOWN) {
                lineEnd = new Point(bPoint.x, bPoint.y - arrow.getHeight());
                objectLeftTop = new Point(aPoint);
                objectRightBtm = new Point(bPoint.x + (arrow.getWidth() / 2), bPoint.y);
                arrowLeftTop = new Point(bPoint.x - (arrow.getWidth() / 2), bPoint.y - arrow.getHeight());
            } else if (arrow.direction == Direction.LEFT) {
                lineEnd = new Point(bPoint.x + arrow.getWidth(), bPoint.y);
                objectLeftTop = new Point(aPoint);
                objectRightBtm = new Point(bPoint.x + arrow.getWidth(), bPoint.y + (arrow.getHeight() / 2));
                arrowLeftTop = new Point(bPoint.x, bPoint.y - (arrow.getHeight() / 2));
            } else {
                lineEnd = bPoint;
                objectLeftTop = new Point(aPoint);
                objectRightBtm = new Point(bPoint.x, bPoint.y);
                arrowLeftTop = new Point(bPoint.x, bPoint.y);
            }
            line.adjustSize(lineStart, lineEnd);
            this.setSize(objectRightBtm.x - objectLeftTop.x, objectRightBtm.y - objectLeftTop.y);
            this.setLocation(objectLeftTop);
            arrow.setLocation(arrowLeftTop.x - this.getX(), arrowLeftTop.y - this.getY());
            line.setLocation(lineStart.x - this.getX(), lineStart.y - this.getY());
        }
    }
}
