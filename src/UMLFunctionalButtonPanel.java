import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class UMLFunctionalButtonPanel {
    JPanel rootPanel;
    JPanel buttonPanel;

    UMLFunctionalButtonPanel(JPanel rootPanel) {
        this.rootPanel = rootPanel;
        initialize();
    }

    private void initialize() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Buttons"));

        JButton button1 = new JButton("button1");
        JButton button2 = new JButton("button2");
        buttonPanel.add(button1);
        buttonPanel.add(button2);

        rootPanel.add(buttonPanel, BorderLayout.LINE_START);
    }
}
