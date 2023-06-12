package main.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import canvas.component.base.CanvasComponent;
import main.UMLEditor;

public class ChangeNameFrame extends JFrame {
    static String changeNameFrameTitle = "change name";
    static String inputPanelText = "Enter new name: ";
    static Dimension changeNameFrameSize = new Dimension(360, 360);
    // singleton instaance
    private static ChangeNameFrame uniqueInstance;

    CanvasComponent renameComponent = null;
    JTextField inputTextField = new JTextField();

    private ChangeNameFrame() {
        super(changeNameFrameTitle);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(changeNameFrameSize);
        this.add(createInputPanel(), BorderLayout.CENTER);
        this.add(createButtonPanel(), BorderLayout.SOUTH);
        this.setAlwaysOnTop(true);
        this.setResizable(false);
    }

    public static ChangeNameFrame getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ChangeNameFrame();
        }
        return uniqueInstance;
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel(inputPanelText), BorderLayout.LINE_START);
        inputPanel.add(inputTextField, BorderLayout.CENTER);
        return inputPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.add(createCancelBtn());
        buttonPanel.add(createOkBtn());
        return buttonPanel;
    }

    private JButton createCancelBtn() {
        JButton btn = new JButton("Cancel");
        btn.addActionListener(createCancelActionListener());
        return btn;
    }

    private ActionListener createCancelActionListener() {
        return e -> dispose();
    }

    private JButton createOkBtn() {
        JButton btn = new JButton("Ok");
        btn.addActionListener(createOkActionListener());
        return btn;
    }

    private ActionListener createOkActionListener() {
        return e -> {
            rename();
            dispose();
        };
    }

    private void rename() {
        renameComponent.setName(inputTextField.getText());
    }

    public void askRename(CanvasComponent renameComponent) {
        this.renameComponent = renameComponent;
        inputTextField.setText("");
        this.pack();
        UMLEditor.getInstance().showAdditionalFrame(this);
    }
}
