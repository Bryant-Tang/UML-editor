package canvas.component.basic;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Component;
import java.awt.Point;

import canvas.component.base.CanvasComponent;

public abstract class BasicComponent extends CanvasComponent {
    protected JLabel nameLabel = new JLabel();
    protected static int nameIndex = 0;

    protected BasicComponent(Point position, String name) {
        super(position);
        initName(name);
    }

    private void initName(String name) {
        this.setName(name);
        JPanel namePanel = new JPanel();
        namePanel.add(nameLabel);
        namePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(namePanel);
    }

    @Override
    public void setName(String name) {
        nameLabel.setText(name);
        super.setName(name);
    }
}
