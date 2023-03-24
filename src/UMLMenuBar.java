import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class UMLMenuBar {
    JFrame rootFrame;
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenu editMenu;
    JMenuItem group;
    JMenuItem unGroup;
    JMenuItem changeObjectName;

    UMLMenuBar(JFrame rootFrame) {
        this.rootFrame = rootFrame;
        initialize();
    }

    private void initialize() {
        menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        
        group = new JMenuItem("Group");
        unGroup = new JMenuItem("UnGroup");
        changeObjectName = new JMenuItem("Change Object Name");
        editMenu.add(group);
        editMenu.add(unGroup);
        editMenu.add(changeObjectName);

        rootFrame.setJMenuBar(menuBar);
    }
}
