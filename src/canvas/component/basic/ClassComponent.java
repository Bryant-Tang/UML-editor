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

    public ClassComponent(Point position, String name) {
        super(position, name);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        initContent();
    }

    @Override
    public Component add(Component comp) {
        Component compArgument = super.add(comp);
        this.resize();
        return compArgument;
    }

    private void initContent() {
        this.add(createDividingLine());
        addContent(emptyContent);
        this.add(createDividingLine());
        addContent(emptyContent);
    }

    public void addContent(String content) {
        JLabel newLabel = new JLabel(content);
        newLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(newLabel);
    }

    private Box.Filler createDividingLine() {
        Box.Filler dividingLine = new Box.Filler(null, null, dividingLineSize);
        dividingLine.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
        return dividingLine;
    }
}
