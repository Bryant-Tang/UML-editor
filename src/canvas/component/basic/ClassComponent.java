package canvas.component.basic;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

public class ClassComponent extends BasicComponent {
    // constant value
    static String emptyContent = " ";
    static int minContentPanelHeight = 10;
    static Dimension dividingLineSize = new Dimension(Integer.MAX_VALUE, 1);

    // attribute
    private int attributeEndIndex = nameIndex;
    private int methodEndIndex = nameIndex;

    public ClassComponent(Point position, String name) {
        super(position, name);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        initContent();
    }

    private void initContent() {
        addAttribute(emptyContent);
        addMethod(emptyContent);
    }

    public void addAttribute(String attribute) {
        if (isNoAttribute()) {
            attributeEndIndex += 1;
            methodEndIndex += 1;
            this.add(createDividingLine(), attributeEndIndex);
        }
        attributeEndIndex += 1;
        methodEndIndex += 1;
        JLabel newLabel = new JLabel(attribute);
        newLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(newLabel, attributeEndIndex);
        this.resize();
    }

    public void addMethod(String method) {
        if (isNoMethod()) {
            methodEndIndex += 1;
            this.add(createDividingLine(), methodEndIndex);
        }
        methodEndIndex += 1;
        JLabel newLabel = new JLabel(method);
        newLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(newLabel, methodEndIndex);
        this.resize();
    }

    private boolean isNoAttribute() {
        return attributeEndIndex == nameIndex;
    }

    private boolean isNoMethod() {
        return methodEndIndex == attributeEndIndex;
    }

    private Box.Filler createDividingLine() {
        Box.Filler dividingLine = new Box.Filler(null, null, dividingLineSize);
        dividingLine.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
        return dividingLine;
    }
}
