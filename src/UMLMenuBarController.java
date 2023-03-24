import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class UMLMenuBarController {
    JMenuBar menuBar;

    JMenuItem group;
    JMenuItem unGroup;
    JMenuItem changeObjectName;

    UMLMenuBarController(JMenuBar UMLMenuBar) {
        this.menuBar = UMLMenuBar;
        initialize();
    }

    private void initialize() {
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        group = new JMenuItem("Group");
        unGroup = new JMenuItem("UnGroup");
        changeObjectName = new JMenuItem("Change Object Name");
        editMenu.add(group);
        editMenu.add(unGroup);
        editMenu.add(changeObjectName);
    }
}
