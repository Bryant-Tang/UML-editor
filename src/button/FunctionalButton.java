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

/**
 * a FunctionalButton is the button on the left side of the app.
 * <p>
 * change canvas mode to the mode containing inside this instance.
 */
public class FunctionalButton extends JButton implements FocusListener {
    // constant value
    static String buttonUIManagerKey = "Button.border";
    static int selectedBorderThick = 5;
    static Dimension size = new Dimension(80, 80);

    // the mode to set to Cancas when this button be clicked
    private Mode mode;

    /**
     * construct a FunctionalButton.
     * 
     * @param mode the mode to set to canvas, when this button is clicked
     * @param icon the Icon image to display on the button
     */
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

    /**
     * Invoked when this button gains the focus.
     * Setting the mode of Canvas.
     * Setting this button to have black thick border.
     * 
     * @param e the event to be processed
     */
    @Override
    public void focusGained(FocusEvent e) {
        CanvasControll.getInstance().setMode(mode);
        setBorder(BorderFactory.createMatteBorder(selectedBorderThick, selectedBorderThick, selectedBorderThick,
                selectedBorderThick, Color.BLACK));
    }

    /**
     * Invoked when this button loses the focus.
     * Setting this button to have normal border.
     * 
     * @param e the event to be processed
     */
    @Override
    public void focusLost(FocusEvent e) {
        setBorder(UIManager.getBorder(buttonUIManagerKey));
    }
}
