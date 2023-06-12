package button;

import java.awt.Image;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import canvas.component.factory.BasicComponentFactory;
import canvas.component.factory.ConnectionComponentFactory;
import canvas.mode.AddBasicMode;
import canvas.mode.AddConnectionMode;
import canvas.mode.SelectMode;

public class FunctionalBtnControll {
    // public constant value
    public static final String SELECT_BTN = "select";
    public static final String ASSOCIATION_BTN = "association";
    public static final String COMPOSITION_BTN = "composition";
    public static final String GENERALIZATION_BTN = "generalization";
    public static final String CLASS_BTN = "class";
    public static final String USE_CASE_BTN = "use_case";

    // constant value
    static String[] buttonList = { SELECT_BTN, ASSOCIATION_BTN, GENERALIZATION_BTN, COMPOSITION_BTN, CLASS_BTN,
            USE_CASE_BTN };
    static String selectBtnIconPath = "icon/select_button.png";
    static String associaitonBtnIconPath = "icon/association_button.png";
    static String compositionBtnIconPath = "icon/composition_button.png";
    static String generalizationBtnIconPath = "icon/generalization_button.png";
    static String classBtnIconPath = "icon/class_button.png";
    static String useCaseBtnIconPath = "icon/use_case_button.png";
    static Dimension buttonIconSize = new Dimension(50, 50);

    // singleton instaance
    private static FunctionalBtnControll uniqueInstance;

    private FunctionalBtnControll() {
        initFunctionalBtnPanel();
    }

    /**
     * get the unique single instance
     * 
     * @return the unique single instance of FunctionalBtnControll
     */
    public static FunctionalBtnControll getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new FunctionalBtnControll();
        }
        return uniqueInstance;
    }

    private JPanel functionalBtnPanel;

    /**
     * get the functional button panel
     * 
     * @return the functional button panel
     */
    public JPanel getFunctionalBtnPanel() {
        return functionalBtnPanel;
    }

    private void initFunctionalBtnPanel() {
        functionalBtnPanel = new JPanel();
        functionalBtnPanel.setLayout(new BoxLayout(functionalBtnPanel, BoxLayout.PAGE_AXIS));
        for (String btn : buttonList) {
            functionalBtnPanel.add(createFunctionalBtn(btn));
        }
    }

    private FunctionalButton createFunctionalBtn(String type) {
        if (type.equals(SELECT_BTN)) {
            return new FunctionalButton(new SelectMode(), createIcon(selectBtnIconPath));
        } else if (type.equals(ASSOCIATION_BTN)) {
            return new FunctionalButton(
                    new AddConnectionMode(new ConnectionComponentFactory(), ConnectionComponentFactory.ASSOCIATION),
                    createIcon(associaitonBtnIconPath));
        } else if (type.equals(GENERALIZATION_BTN)) {
            return new FunctionalButton(
                    new AddConnectionMode(new ConnectionComponentFactory(), ConnectionComponentFactory.GENERALIZATION),
                    createIcon(generalizationBtnIconPath));
        } else if (type.equals(COMPOSITION_BTN)) {
            return new FunctionalButton(
                    new AddConnectionMode(new ConnectionComponentFactory(), ConnectionComponentFactory.COMPOSITION),
                    createIcon(compositionBtnIconPath));
        } else if (type.equals(CLASS_BTN)) {
            return new FunctionalButton(new AddBasicMode(new BasicComponentFactory(), BasicComponentFactory.CLASS),
                    createIcon(classBtnIconPath));
        } else if (type.equals(USE_CASE_BTN)) {
            return new FunctionalButton(new AddBasicMode(new BasicComponentFactory(), BasicComponentFactory.USE_CASE),
                    createIcon(useCaseBtnIconPath));
        } else {
            return null;
        }
    }

    private ImageIcon createIcon(String path) {
        return new ImageIcon(new ImageIcon(path).getImage()
                .getScaledInstance(buttonIconSize.width, buttonIconSize.height, Image.SCALE_DEFAULT));
    }
}
