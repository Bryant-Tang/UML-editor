public class Mode {

    public static final int SELECT = 0;
    public static final int ASSOCIATION = 1;
    public static final int GENERALIZATION = 2;
    public static final int COMPOSITION = 3;
    public static final int CLASS = 4;
    public static final int USE_CASE = 5;

    int mode;

    Mode(int mode) {
        this.mode = mode;
    }

    void setMode(int modeValue) {
        mode = modeValue;
    }
}
