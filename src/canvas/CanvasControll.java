package canvas;

import javax.swing.JPanel;

public class CanvasControll {
    static CanvasControll uniqueInstance;

    private CanvasControll() {
    }

    public static CanvasControll getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new CanvasControll();
        }
        return uniqueInstance;
    }

    public JPanel getCanvasPanel() {
        // TODO: return all menu
        throw new UnsupportedOperationException();
    }
}
