package functional_button;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

public class FunctionalBtnControll {
    static FunctionalBtnControll uniqueInstance;

    private FunctionalBtnControll() {
    }

    public static FunctionalBtnControll getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new FunctionalBtnControll();
        }
        return uniqueInstance;
    }

    public JPanel getFunctioanalBtnPanel() {
        // TODO: return btns
        throw new UnsupportedOperationException();
    }
}
