package canvas.component.connection;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Arrow extends JPanel {
    public Arrow() {
        setLayout(null);
        setSize(10, 10);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void shift(int x, int y) {
        setLocation(getX() + x, getY() + y);
    }
}
