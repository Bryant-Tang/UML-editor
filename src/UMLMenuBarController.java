import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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

    JFrame changeNameFrame;

    ArrayList<BasicObject> selectComponent;
    ArrayList<CompositeObject> selectGroup;

    UMLMenuBarController(JFrame mainFrame, JMenuBar UMLMenuBar, ArrayList<BasicObject> selectComponent,
            ArrayList<CompositeObject> selectGroup) {
        this.menuBar = UMLMenuBar;
        this.selectComponent = selectComponent;
        this.selectGroup = selectGroup;
        initialize(mainFrame);
    }

    private void initialize(JFrame mainFrame) {
        changeNameFrame = new JFrame();
        changeNameFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        changeNameFrame.setSize(new Dimension(360, 240));
        changeNameFrame.add(new CancelButton(changeNameFrame),BorderLayout.SOUTH);

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
                changeNameFrame.setVisible(true);

                changeNameFrame.setLocationRelativeTo(mainFrame);
                if (selectComponent.size() == 1) {
                    selectComponent.get(0).nameLabel.setText("123");
                }
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
        changeNameFrame.setVisible(false);
    }
}