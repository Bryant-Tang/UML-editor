import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;

import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.Image;
import java.awt.Dimension;

public class UMLButtonPaneController {
    JPanel buttonPane;
    JButton selectButton;
    JButton associationButton;
    JButton generalizationButton;
    JButton compositionButton;
    JButton classButton;
    JButton useCaseButton;

    UMLButtonPaneController(JPanel buttonPane) {
        this.buttonPane = new JPanel();
        buttonPane.add(this.buttonPane, BorderLayout.CENTER);
        buttonPane.add(Box.createRigidArea(new Dimension(0, 0)), BorderLayout.LINE_START);
        initialize();
    }

    private void initialize() {
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.Y_AXIS));

        buttonPane.add(Box.createRigidArea(new Dimension(0, 5)));
        selectButton = createButton("images/select_button.png", "select");
        buttonPane.add(selectButton);
        buttonPane.add(Box.createRigidArea(new Dimension(0, 5)));
        associationButton = createButton("images/association_button.png", "association");
        buttonPane.add(associationButton);
        buttonPane.add(Box.createRigidArea(new Dimension(0, 5)));
        generalizationButton = createButton("images/generalization_button.png", "generalization");
        buttonPane.add(generalizationButton);
        buttonPane.add(Box.createRigidArea(new Dimension(0, 5)));
        compositionButton = createButton("images/composition_button.png", "composition");
        buttonPane.add(compositionButton);
        buttonPane.add(Box.createRigidArea(new Dimension(0, 5)));
        classButton = createButton("images/class_button.png", "class");
        buttonPane.add(classButton);
        buttonPane.add(Box.createRigidArea(new Dimension(0, 5)));
        useCaseButton = createButton("images/use_case_button.png", "use case");
        buttonPane.add(useCaseButton);
        buttonPane.add(Box.createRigidArea(new Dimension(0, 5)));
    }

    JButton createButton(String filename, String description) {
        ImageIcon buttonIcon = new ImageIcon(new ImageIcon(filename, description).getImage()
                .getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        JButton button = new JButton(description, buttonIcon);
        button.setVerticalTextPosition(AbstractButton.BOTTOM);
        button.setHorizontalTextPosition(AbstractButton.CENTER);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMinimumSize(new Dimension(120, 90));
        button.setPreferredSize(new Dimension(120, 90));
        button.setMaximumSize(new Dimension(120, 90));
        button.addFocusListener(new SelectButtonListener(button));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        return button;
    }
}

class SelectButtonListener implements FocusListener {
    JButton listenButton;

    SelectButtonListener(JButton listenButton) {
        this.listenButton = listenButton;
    }

    @Override
    public void focusGained(FocusEvent e) {
        listenButton.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.BLACK));
    }

    @Override
    public void focusLost(FocusEvent e) {
        listenButton.setBorder(UIManager.getBorder("Button.border"));
    }
}