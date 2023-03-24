import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Point;

public class UMLCanvasPaneController {
    JLayeredPane canvasPane;

    UMLCanvasPaneController(JLayeredPane canvasPane) {
        this.canvasPane = canvasPane;
        initialize();
    }

    private void initialize() {
        canvasPane.setLayout(null);
        canvasPane.setBorder(BorderFactory.createTitledBorder("Canvas Area"));
        canvasPane.setBackground(Color.WHITE);
        canvasPane.addMouseListener(new CreateObjectListener(canvasPane));
    }
}

class ClassObject extends JPanel {
    ClassObject(Point p) {
        this.setBackground(Color.WHITE);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel top = new JPanel();
        top.setBorder(BorderFactory.createMatteBorder(2, 2, 1, 2, Color.BLACK));
        top.setBackground(Color.WHITE);
        JLabel className = new JLabel("new class");
        top.add(className);

        JPanel mid = new JPanel();
        mid.setBorder(BorderFactory.createMatteBorder(1, 2, 1, 2, Color.BLACK));
        mid.setBackground(Color.WHITE);
        mid.setPreferredSize(top.getPreferredSize());

        JPanel btm = new JPanel();
        btm.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 2, Color.BLACK));
        btm.setBackground(Color.WHITE);
        btm.setPreferredSize(top.getPreferredSize());

        this.add(top);
        this.add(mid);
        this.add(btm);

        this.setLocation(p);
        this.setSize(getPreferredSize());
    }
}

class CreateObjectListener implements MouseListener {
    JLayeredPane canvasPane;

    CreateObjectListener(JLayeredPane canvasPane) {
        this.canvasPane = canvasPane;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        ClassObject temp = new ClassObject(e.getPoint());
        canvasPane.add(temp, canvasPane.highestLayer());
        canvasPane.paintAll(canvasPane.getGraphics());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }
}