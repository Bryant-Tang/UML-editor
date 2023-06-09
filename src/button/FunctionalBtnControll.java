package button;

import javax.swing.JPanel;

public class FunctionalBtnControll {
    // singleton instaance
    private static FunctionalBtnControll uniqueInstance;

    private FunctionalBtnControll() {
    }

    public static FunctionalBtnControll getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new FunctionalBtnControll();
        }
        return uniqueInstance;
    }

    private JPanel functioanalBtnPanel;

    public JPanel getFunctioanalBtnPanel() {
        if (functioanalBtnPanel == null) {
            initFunctioanalBtnPanel();
        }
        return functioanalBtnPanel;
    }

    private void initFunctioanalBtnPanel() {
        functioanalBtnPanel = new JPanel();
    }
}
