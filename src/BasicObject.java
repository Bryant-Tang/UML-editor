import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.BorderLayout;

import java.lang.Math;
import java.util.ArrayList;

class Oval extends JPanel {
    Oval() {
        super();
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(getBackground());
        g2d.fillOval(0, 0, getWidth() - 1, getHeight() - 1);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(1, 1, getWidth() - 2, getHeight() - 2);

    }
}

public class BasicObject extends JLayeredPane {
    JPanel upPoint;
    JPanel rightPoint;
    JPanel downPoint;
    JPanel leftPoint;
    JLayeredPane contentPane;
    JLabel nameLabel;
    private Boolean directionPointVisible = true;
    ArrayList<ConnectObject> connectLines;
    CompositeObject belongGroup = null;

    BasicObject() {
        super();
        nameLabel = new JLabel("");
        connectLines = new ArrayList<>();
        setOpaque(false);
        upPoint = new JPanel();
        upPoint.setSize(new Dimension(10, 10));
        upPoint.setBackground(Color.BLACK);

        rightPoint = new JPanel();
        rightPoint.setSize(new Dimension(10, 10));
        rightPoint.setBackground(Color.BLACK);

        downPoint = new JPanel();
        downPoint.setSize(new Dimension(10, 10));
        downPoint.setBackground(Color.BLACK);

        leftPoint = new JPanel();
        leftPoint.setSize(new Dimension(10, 10));
        leftPoint.setBackground(Color.BLACK);

        contentPane = new JLayeredPane();
        contentPane.setSize(new Dimension(getWidth() - 10, getHeight() - 10));

        this.setLayout(null);
        add(upPoint);
        add(rightPoint);
        add(downPoint);
        add(leftPoint);
        add(contentPane);
        setSizeAuto();

        setDirectionPointVisible(false);
    }

    void setContentPaneSize() {
    }

    void setObjectName(String name) {
        nameLabel.setText(name);
        setContentPaneSize();
        setSizeAuto();
        this.repaint();
    }

    Boolean pointIsIn(Point p) {
        if (this.getX() <= p.x && p.x <= (this.getX() + this.getWidth()) && this.getY() <= p.y
                && p.y <= (this.getY() + this.getHeight())) {
            return true;
        } else {
            return false;
        }
    }

    Boolean isInRange(Point aPoint, Point bPoint) {
        Point leftTop = new Point(Math.min(aPoint.x, bPoint.x), Math.min(aPoint.y, bPoint.y));
        Point rightBtm = new Point(Math.max(aPoint.x, bPoint.x), Math.max(aPoint.y, bPoint.y));
        if (this.getX() >= leftTop.x && this.getY() >= leftTop.y && this.getX() + this.getWidth() <= rightBtm.x
                && this.getY() + this.getHeight() <= rightBtm.y) {
            return true;
        } else {
            return false;
        }
    }

    public void moveWithConnectLine(Point p) {
        Boolean visible = this.directionPointVisible;
        this.setDirectionPointVisible(false);
        this.setLocation(p.x, p.y);
        this.reLocateConnectLine();
        this.setDirectionPointVisible(visible);
    }

    void addLine(ConnectObject line) {
        connectLines.add(line);
    }

    void removeLine(ConnectObject line) {
        connectLines.remove(line);
    }

    void reLocateConnectLine() {
        for (ConnectObject line : connectLines) {
            line.adjustLine();
        }
    }

    void setDirectionPointVisible(boolean visible) {
        if (visible != directionPointVisible) {
            if (visible) {
                this.setLocation(this.getX() - 10, this.getY() - 10);
            } else {
                this.setLocation(this.getX() + 10, this.getY() + 10);
            }
        }
        directionPointVisible = visible;
        upPoint.setVisible(directionPointVisible);
        rightPoint.setVisible(directionPointVisible);
        downPoint.setVisible(directionPointVisible);
        leftPoint.setVisible(directionPointVisible);
        setSizeAuto();
        this.repaint();
    }

    Boolean getDirectionPointVisible() {
        return directionPointVisible;
    }

    void setDirectionPointLocation() {
        upPoint.setLocation((getWidth() / 2) - 5, 0);
        rightPoint.setLocation(getWidth() - 10, (getHeight() / 2) - 5);
        downPoint.setLocation((getWidth() / 2) - 5, getHeight() - 10);
        leftPoint.setLocation(0, (getHeight() / 2) - 5);
        if (directionPointVisible) {
            contentPane.setLocation(10, 10);
        } else {
            contentPane.setLocation(0, 0);
        }

    }

    public void setSizeAuto() {
        if (directionPointVisible) {
            setSize(new Dimension(contentPane.getPreferredSize().width + 20,
                    contentPane.getPreferredSize().height + 20));
        } else {
            setSize(new Dimension(contentPane.getPreferredSize()));
        }
        setDirectionPointLocation();
    }

    Point getDirectionPoint(int direction) {
        Point upLocation = new Point(contentPane.getX() + (contentPane.getWidth() / 2),
                contentPane.getY());
        Point rightLocation = new Point(contentPane.getX() + (contentPane.getWidth()),
                contentPane.getY() + (contentPane.getHeight() / 2));
        Point downLocation = new Point(contentPane.getX() + (contentPane.getWidth() / 2),
                contentPane.getY() + (contentPane.getHeight()));
        Point leftLocation = new Point(contentPane.getX(),
                contentPane.getY() + (contentPane.getHeight() / 2));
        if (direction == Direction.UP) {
            return new Point(upLocation.x + this.getX(), upLocation.y + this.getY());
        } else if (direction == Direction.RIGHT) {
            return new Point(rightLocation.x + this.getX(), rightLocation.y + this.getY());
        } else if (direction == Direction.DOWN) {
            return new Point(downLocation.x + this.getX(), downLocation.y + this.getY());
        } else if (direction == Direction.LEFT) {
            return new Point(leftLocation.x + this.getX(), leftLocation.y + this.getY());
        } else {
            return null;
        }
    }

    int atWhichDirection(Point p) {
        p = new Point(p.x - this.getX(), p.y - this.getY());

        Point upLocation = new Point(contentPane.getX() + (contentPane.getWidth() / 2),
                contentPane.getY());
        Point rightLocation = new Point(contentPane.getX() + (contentPane.getWidth()),
                contentPane.getY() + (contentPane.getHeight() / 2));
        Point downLocation = new Point(contentPane.getX() + (contentPane.getWidth() / 2),
                contentPane.getY() + (contentPane.getHeight()));
        Point leftLocation = new Point(contentPane.getX(),
                contentPane.getY() + (contentPane.getHeight() / 2));

        double shortestDistance = p.distance((Point2D) upLocation);
        int shortestDirection = Direction.UP;
        if (p.distance((Point2D) rightLocation) < shortestDistance) {
            shortestDirection = Direction.RIGHT;
            shortestDistance = p.distance((Point2D) rightLocation);
        }
        if (p.distance((Point2D) downLocation) < shortestDistance) {
            shortestDirection = Direction.DOWN;
            shortestDistance = p.distance((Point2D) downLocation);
        }
        if (p.distance((Point2D) leftLocation) < shortestDistance) {
            shortestDirection = Direction.LEFT;
            shortestDistance = p.distance((Point2D) leftLocation);
        }

        return shortestDirection;
    }
}

class ClassObject extends BasicObject {
    JPanel top;
    JPanel mid;
    JPanel btm;

    ClassObject(Point position) {
        super();

        contentPane.setOpaque(false);
        contentPane.setLayout(null);

        top = new JPanel(new BorderLayout());
        top.setBorder(BorderFactory.createMatteBorder(2, 2, 1, 2, Color.BLACK));
        nameLabel.setText("new class");
        top.add(nameLabel);
        top.add(new Box.Filler(new Dimension(0, 0), new Dimension(2, 2), new Dimension(2, 2)), BorderLayout.NORTH);
        top.add(new Box.Filler(new Dimension(0, 0), new Dimension(2, 2), new Dimension(2, 2)), BorderLayout.EAST);
        top.add(new Box.Filler(new Dimension(0, 0), new Dimension(2, 2), new Dimension(2, 2)), BorderLayout.SOUTH);
        top.add(new Box.Filler(new Dimension(0, 0), new Dimension(2, 2), new Dimension(2, 2)), BorderLayout.WEST);

        mid = new JPanel();
        mid.setBorder(BorderFactory.createMatteBorder(1, 2, 1, 2, Color.BLACK));

        btm = new JPanel();
        btm.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 2, Color.BLACK));

        contentPane.add(top);
        contentPane.add(mid);
        contentPane.add(btm);

        setContentPaneSize();
        this.setLocation(position);
        setSizeAuto();

    }

    @Override
    void setContentPaneSize() {
        super.setContentPaneSize();
        top.setSize(top.getPreferredSize());
        top.setLocation(0, 0);
        mid.setSize(top.getPreferredSize());
        mid.setLocation(0, top.getHeight());
        btm.setSize(top.getPreferredSize());
        btm.setLocation(0, top.getHeight() + mid.getHeight());
        contentPane.setPreferredSize(
                new Dimension(top.getWidth(), top.getHeight() + mid.getHeight() + btm.getHeight()));
        contentPane.setSize(contentPane.getPreferredSize());
    }
}

class UseCaseObject extends BasicObject {
    Oval oval;

    UseCaseObject(Point position) {
        super();
        contentPane.setLayout(null);
        contentPane.setOpaque(false);

        nameLabel.setText("new use case");
        oval = new Oval();

        contentPane.add(nameLabel, BorderLayout.CENTER, highestLayer());
        contentPane.add(oval, BorderLayout.CENTER, lowestLayer() - 1);

        setContentPaneSize();
        setLocation(position);
        setSizeAuto();
    }

    @Override
    void setContentPaneSize() {
        super.setContentPaneSize();
        nameLabel.setSize(nameLabel.getPreferredSize());
        oval.setPreferredSize(
                new Dimension(((int) Math.round(Math.sqrt(2) * nameLabel.getPreferredSize().width)),
                        ((int) Math.round(Math.sqrt(2) * nameLabel.getPreferredSize().height))));
        oval.setSize(oval.getPreferredSize());
        oval.setLocation(0, 0);
        nameLabel.setLocation((oval.getWidth() - nameLabel.getWidth()) / 2,
                (oval.getHeight() - nameLabel.getHeight()) / 2);
        contentPane.setPreferredSize(oval.getPreferredSize());
        contentPane.setSize(contentPane.getPreferredSize());
    }
}
