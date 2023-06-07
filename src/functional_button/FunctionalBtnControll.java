package functional_button;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

public class FunctionalBtnControll {
    // singleton instaance
    static FunctionalBtnControll uniqueInstance;

    private FunctionalBtnControll() {
    }

    public static FunctionalBtnControll getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new FunctionalBtnControll();
        }
        return uniqueInstance;
    }

    JPanel functioanalBtnPanel;

    public JPanel getFunctioanalBtnPanel() {
        if (functioanalBtnPanel == null) {
            initFunctioanalBtnPanel();
        }
        return functioanalBtnPanel;
    }

    void initFunctioanalBtnPanel() {
        functioanalBtnPanel = new JPanel();
    }
}
