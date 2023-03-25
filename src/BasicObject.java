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
    private Boolean directionPointVisible = true;

    BasicObject() {
        super();
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
        contentPane.setSize(new Dimension(getSize().width - 10, getSize().height - 10));

        this.setLayout(null);
        add(upPoint);
        add(rightPoint);
        add(downPoint);
        add(leftPoint);
        add(contentPane);
        setSizeAuto();

        setDirectionPointVisible(false);
    }

    void setDirectionPointVisible(boolean visible) {
        if (visible != directionPointVisible) {
            if (visible) {
                this.setLocation(this.getLocation().x - 10, this.getLocation().y - 10);
            } else {
                this.setLocation(this.getLocation().x + 10, this.getLocation().y + 10);
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
        upPoint.setLocation((getSize().width / 2) - 5, 0);
        rightPoint.setLocation(getSize().width - 10, (getSize().height / 2) - 5);
        downPoint.setLocation((getSize().width / 2) - 5, getSize().height - 10);
        leftPoint.setLocation(0, (getSize().height / 2) - 5);
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

    Point atWhichDirectionPoint(Point p) {
        Point upLocation = new Point(upPoint.getLocation().x + (upPoint.getSize().width / 2),
                upPoint.getLocation().y + (upPoint.getSize().height / 2));
        Point rightLocation = new Point(rightPoint.getLocation().x + (rightPoint.getSize().width / 2),
                rightPoint.getLocation().y + (rightPoint.getSize().height / 2));
        Point downLocation = new Point(downPoint.getLocation().x + (downPoint.getSize().width / 2),
                downPoint.getLocation().y + (downPoint.getSize().height / 2));
        Point leftLocation = new Point(leftPoint.getLocation().x + (leftPoint.getSize().width / 2),
                leftPoint.getLocation().y + (leftPoint.getSize().height / 2));

        double shortestDistance = p.distance((Point2D) upLocation);
        Point shortestDirectionPoint = upLocation;

        if (p.distance((Point2D) rightLocation) < shortestDistance) {
            shortestDirectionPoint = rightLocation;
            shortestDistance = p.distance((Point2D) rightLocation);
        }
        if (p.distance((Point2D) downLocation) < shortestDistance) {
            shortestDirectionPoint = downLocation;
            shortestDistance = p.distance((Point2D) downLocation);
        }
        if (p.distance((Point2D) leftLocation) < shortestDistance) {
            shortestDirectionPoint = leftLocation;
            shortestDistance = p.distance((Point2D) leftLocation);
        }
        return shortestDirectionPoint;
    }
}

class ClassObject extends BasicObject {
    JLabel nameLabel;

    ClassObject(Point position) {
        super();
        contentPane.setOpaque(false);
        contentPane.setLayout(null);

        JPanel top = new JPanel(new BorderLayout());
        top.setBorder(BorderFactory.createMatteBorder(2, 2, 1, 2, Color.BLACK));
        nameLabel = new JLabel("new class");
        top.add(nameLabel);
        top.add(new Box.Filler(new Dimension(0, 0), new Dimension(2, 2), new Dimension(2, 2)), BorderLayout.NORTH);
        top.add(new Box.Filler(new Dimension(0, 0), new Dimension(2, 2), new Dimension(2, 2)), BorderLayout.EAST);
        top.add(new Box.Filler(new Dimension(0, 0), new Dimension(2, 2), new Dimension(2, 2)), BorderLayout.SOUTH);
        top.add(new Box.Filler(new Dimension(0, 0), new Dimension(2, 2), new Dimension(2, 2)), BorderLayout.WEST);
        top.setSize(top.getPreferredSize());
        top.setLocation(0, 0);

        JPanel mid = new JPanel();
        mid.setBorder(BorderFactory.createMatteBorder(1, 2, 1, 2, Color.BLACK));
        mid.setSize(top.getPreferredSize());
        mid.setLocation(0, top.getSize().height);

        JPanel btm = new JPanel();
        btm.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 2, Color.BLACK));
        btm.setSize(top.getPreferredSize());
        btm.setLocation(0, top.getSize().height + mid.getSize().height);

        contentPane.add(top);
        contentPane.add(mid);
        contentPane.add(btm);
        contentPane.setPreferredSize(
                new Dimension(top.getSize().width, top.getSize().height + mid.getSize().height + btm.getSize().height));
        contentPane.setSize(contentPane.getPreferredSize());

        setLocation(position);
        setSizeAuto();
    }
}

class UseCaseObject extends BasicObject {
    JLabel nameLabel;

    UseCaseObject(Point position) {
        super();
        contentPane.setLayout(null);
        contentPane.setOpaque(false);

        nameLabel = new JLabel("new use case");
        nameLabel.setSize(nameLabel.getPreferredSize());
        Oval oval = new Oval();
        oval.setPreferredSize(
                new Dimension(((int) Math.round(Math.sqrt(2) * nameLabel.getPreferredSize().width)),
                        ((int) Math.round(Math.sqrt(2) * nameLabel.getPreferredSize().height))));
        oval.setSize(oval.getPreferredSize());

        oval.setLocation(0, 0);
        nameLabel.setLocation((oval.getSize().width - nameLabel.getSize().width) / 2,
                (oval.getSize().height - nameLabel.getSize().height) / 2);

        contentPane.add(nameLabel, BorderLayout.CENTER, highestLayer());
        contentPane.add(oval, BorderLayout.CENTER, lowestLayer() - 1);

        contentPane.setPreferredSize(oval.getPreferredSize());
        contentPane.setSize(contentPane.getPreferredSize());

        setLocation(position);
        setSizeAuto();
    }
}
