package canvas.component.basic;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

/**
 * a concrete component that represent the class component in the UML class
 * diagram.
 */
public class ClassComponent extends BasicComponent {
    // constant value
    static String emptyContent = " ";
    static int minContentPanelHeight = 10;
    static Dimension dividingLineSize = new Dimension(Integer.MAX_VALUE, 1);
    static int dividingLineThick = 1;

    /**
     * construct a ClassComponent.
     * 
     * @param position the initial position of this component
     * @param name     the initial name of this component
     */
    public ClassComponent(Point position, String name) {
        super(position, name);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        initContent();
    }

    private void initContent() {
        this.add(createDividingLine());
        addContent(emptyContent);
        this.add(createDividingLine());
        addContent(emptyContent);
    }

    /**
     * add content to this ClassComponent
     * 
     * @param content
     */
    public void addContent(String content) {
        JLabel newLabel = new JLabel(content);
        newLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(newLabel);
    }

    private Box.Filler createDividingLine() {
        Box.Filler dividingLine = new Box.Filler(null, null, dividingLineSize);
        dividingLine.setBorder(BorderFactory.createMatteBorder(dividingLineThick, 0, 0, 0, Color.BLACK));
        return dividingLine;
    }
}
