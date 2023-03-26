import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

public class UMLMenuBarController {
    JMenuBar menuBar;

    JMenuItem group;
    JMenuItem unGroup;
    JMenuItem changeObjectName;

    ArrayList<BasicObject> selectComponent;
    ArrayList<CompositeObject> selectGroup;

    UMLMenuBarController(JMenuBar UMLMenuBar, ArrayList<BasicObject> selectComponent,
            ArrayList<CompositeObject> selectGroup) {
        this.menuBar = UMLMenuBar;
        this.selectComponent = selectComponent;
        this.selectGroup = selectGroup;
        initialize();
    }

    private void initialize() {
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        group = new JMenuItem("Group");
        group.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("group menu item click");
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
                System.out.println("ungroup menu item click");
                if (selectGroup.size() == 1) {
                    selectGroup.get(0).dissolve();
                }
            }
        });
        changeObjectName = new JMenuItem("Change Object Name");
        editMenu.add(group);
        editMenu.add(unGroup);
        editMenu.add(changeObjectName);
    }
}
