import java.util.ArrayList;

public class CompositeObject {
    ArrayList<BasicObject> containBasicObjects = new ArrayList<>();
    ArrayList<CompositeObject> containCompositeObjects = new ArrayList<>();
    CompositeObject parent = null;

    CompositeObject(){
        super();
    }

    void addBasic(BasicObject comp) {
        comp.belongGroup = this;
        containBasicObjects.add(comp);
    }

    void addComposite(CompositeObject comp) {
        comp.parent = this;
        containCompositeObjects.add(comp);
    }

    CompositeObject getRoot() {
        if (parent == null) {
            return this;
        } else {
            return parent.getRoot();
        }
    }

    ArrayList<BasicObject> getAllBasic() {
        ArrayList<BasicObject> temp = new ArrayList<>();
        temp.addAll(containBasicObjects);
        for (CompositeObject comp : containCompositeObjects) {
            temp.addAll(comp.getAllBasic());
        }
        return temp;
    }

    void dissolve() {
        for (BasicObject comp : containBasicObjects) {
            comp.belongGroup = null;
        }
        for (CompositeObject comp : containCompositeObjects) {
            comp.parent = null;
        }
        containBasicObjects = null;
        containCompositeObjects = null;
        parent = null;
    }

}
