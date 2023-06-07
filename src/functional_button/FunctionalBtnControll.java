package functional_button;

import java.util.List;

import javax.swing.JButton;

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

    public List<JButton> getAllFunctionalBtn() {
        // TODO: return btns
        throw new UnsupportedOperationException();
    }
}
