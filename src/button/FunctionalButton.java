package button;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.UIManager;

import canvas.CanvasControll;
import canvas.mode.Mode;

public class FunctionalButton extends JButton implements FocusListener {
    static String buttonUIManagerKey = "Button.border";
    static int selectedBorderThick = 5;
    static Dimension size = new Dimension(80, 80);
    private Mode mode;

    public FunctionalButton(Mode mode, ImageIcon icon) {
        super(icon);
        this.mode = mode;
        addFocusListener(this);
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
        setFocusPainted(false);
    }

    @Override
    public void focusGained(FocusEvent e) {
        CanvasControll.getInstance().setMode(mode);
        setBorder(BorderFactory.createMatteBorder(selectedBorderThick, selectedBorderThick, selectedBorderThick,
                selectedBorderThick, Color.BLACK));
    }

    @Override
    public void focusLost(FocusEvent e) {
        setBorder(UIManager.getBorder(buttonUIManagerKey));
    }
}
