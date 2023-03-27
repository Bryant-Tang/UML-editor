import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.BoxLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Dimension;

import java.util.ArrayList;

public class UMLMenuBarController {
    JMenuBar menuBar;

    JMenuItem group;
    JMenuItem unGroup;
    JMenuItem changeObjectName;

    JTextField changeNameInputField;

    ArrayList<BasicObject> selectComponent;
    ArrayList<CompositeObject> selectGroup;

    UMLMenuBarController(JFrame mainFrame, JMenuBar UMLMenuBar, ArrayList<BasicObject> selectComponent,
            ArrayList<CompositeObject> selectGroup) {
        this.menuBar = UMLMenuBar;
        this.selectComponent = selectComponent;
        this.selectGroup = selectGroup;
        initialize(mainFrame);
    }

    JFrame createChangeNameFrame(){
        JFrame changeNameFrame = new JFrame();
        changeNameFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        changeNameFrame.setSize(new Dimension(360, 240));
        JLabel tempLabel = new JLabel("Enter new name: ");
        changeNameInputField = new JTextField();
        changeNameInputField
                .setPreferredSize(new Dimension(changeNameFrame.getWidth() - tempLabel.getPreferredSize().width - 50,
                        changeNameInputField.getPreferredSize().height));
        JPanel tempJPanel = new JPanel();
        tempJPanel.setLayout(new BoxLayout(tempJPanel, BoxLayout.Y_AXIS));
        tempJPanel.add(new Box.Filler(null, new Dimension(0, changeNameFrame.getHeight()), null));
        JPanel tempInputPanel = new JPanel();
        tempInputPanel.add(tempLabel, BorderLayout.WEST);
        tempInputPanel.add(changeNameInputField, BorderLayout.EAST);
        tempJPanel.add(tempInputPanel);
        tempJPanel.add(new Box.Filler(null, new Dimension(0, changeNameFrame.getHeight()), null));
        changeNameFrame.add(tempJPanel, BorderLayout.CENTER);
        tempJPanel = new JPanel();
        tempJPanel.add(new CancelButton(changeNameFrame), BorderLayout.WEST);
        tempJPanel.add(new Box.Filler(null, new Dimension(50, 0), null), BorderLayout.CENTER);
        tempJPanel.add(new OkButton(changeNameFrame, selectComponent, changeNameInputField), BorderLayout.EAST);
        changeNameFrame.add(tempJPanel, BorderLayout.SOUTH);
        changeNameFrame.setAlwaysOnTop(true);
        changeNameFrame.setResizable(false);
        return changeNameFrame;
    }

    private void initialize(JFrame mainFrame) {
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        group = new JMenuItem("Group");
        group.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CompositeObject tempGroup = new CompositeObject();
                for (CompositeObject comp : selectGroup) {
                    tempGroup.addComposite(comp);
                }
                for (BasicObject comp : selectComponent) {
                    if (comp.belongGroup == null) {
                        tempGroup.addBasic(comp);
                    }
                }
            }
        });
        unGroup = new JMenuItem("UnGroup");
        unGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectGroup.size() == 1) {
                    selectGroup.get(0).dissolve();
                }
            }
        });
        changeObjectName = new JMenuItem("Change Object Name");
        changeObjectName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame changeNameFrame = createChangeNameFrame();
                changeNameFrame.setVisible(true);
                changeNameFrame.setLocationRelativeTo(mainFrame);
            }
        });
        editMenu.add(group);
        editMenu.add(unGroup);
        editMenu.add(changeObjectName);
    }
}

class CancelButton extends JButton implements ActionListener {
    JFrame changeNameFrame;

    CancelButton(JFrame changeNameFrame) {
        super("Cancel");
        this.changeNameFrame = changeNameFrame;
        this.addActionListener(this);

        this.setVerticalTextPosition(AbstractButton.CENTER);
        this.setHorizontalTextPosition(AbstractButton.CENTER);
        this.setFocusPainted(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        changeNameFrame.dispose();
    }
}

class OkButton extends JButton implements ActionListener {
    JFrame changeNameFrame;
    ArrayList<BasicObject> selectComponent;
    JTextField inputField;

    OkButton(JFrame changeNameFrame, ArrayList<BasicObject> selectComponent, JTextField inputField) {
        super("OK");
        this.changeNameFrame = changeNameFrame;
        this.selectComponent = selectComponent;
        this.inputField = inputField;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (selectComponent.size() == 1) {
            selectComponent.get(0).setObjectName(inputField.getText());
        }
        changeNameFrame.dispose();
    }
}