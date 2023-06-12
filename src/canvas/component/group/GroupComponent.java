package canvas.component.group;

import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;

import canvas.CanvasControll;
import canvas.component.base.CanvasComponent;

public class GroupComponent extends CanvasComponent {
    ArrayList<CanvasComponent> components = new ArrayList<>();

    public GroupComponent(Point position) {
        super(position);
        setLayout(null);
        setOpaque(false);
    }

    private Point getLeftTop(Point a, Point b) {
        return new Point(Math.min(a.x, b.x), Math.min(a.y, b.y));
    }

    private Point getLeftTop(List<CanvasComponent> components) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        for (CanvasComponent comp : components) {
            if (comp.getX() < minX) {
                minX = comp.getX();
            }
            if (comp.getY() < minY) {
                minY = comp.getY();
            }
        }
        return new Point(minX, minY);
    }

    private Point getRightBottom(List<CanvasComponent> components) {
        int maxX = 0;
        int maxY = 0;
        for (CanvasComponent comp : components) {
            if (comp.getX() + comp.getWidth() > maxX) {
                maxX = comp.getX() + comp.getWidth();
            }
            if (comp.getY() + comp.getHeight() > maxY) {
                maxY = comp.getY() + comp.getHeight();
            }
        }
        return new Point(maxX, maxY);
    }

    private void setLocationWithoutMovingContent(Point position) {
        for (CanvasComponent comp : components) {
            comp.shift(new Point(getLocation().x - position.x, getLocation().y - position.y));
        }
        setLocation(position);
    }

    private void setLocationWithoutMovingContent(int x, int y) {
        setLocationWithoutMovingContent(new Point(x, y));
    }

    private Dimension getInnerComponentMinSize() {
        return new Dimension(getRightBottom(components).x - getLeftTop(components).x,
                getRightBottom(components).y - getLeftTop(components).y);
    }

    @Override
    protected void resize() {
        setPreferredSize(getInnerComponentMinSize());
        Point innerLeftTopLocaiton = getLeftTop(components);
        setLocationWithoutMovingContent(innerLeftTopLocaiton.x + getX(), innerLeftTopLocaiton.y + getY());
        super.resize();
    }

    @Override
    public void addGroupContent(CanvasComponent comp) {
        setLocationWithoutMovingContent(getLeftTop(getLocation(), comp.getLocation()));
        CanvasControll.getInstance().removeComponent(comp);
        comp.setSelect(false);
        comp.shift(new Point(-getX(), -getY()));
        this.components.add(comp);
        this.add(comp);
    }

    @Override
    public void removeGroupContent(CanvasComponent comp) {
        remove(comp);
        comp.shift(getLocation());
        CanvasControll.getInstance().addComponent(comp);
    }

    @Override
    public void ungroup() {
        for (CanvasComponent comp : components) {
            remove(comp);
            comp.shift(getLocation());
            CanvasControll.getInstance().addComponent(comp);
        }
        CanvasControll.getInstance().removeComponent(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (select) {
            setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        } else {
            setBorder(BorderFactory.createEmptyBorder());
        }
        super.paintComponent(g);
    }
}
