package source;

import java.util.List;
/**
 * Created by Artur Artir Markowski on 2017-01-02.
 */
public class Container {
    private List<Box> boxList;
    private Box box = null;

    public Container() {
    }

    public void createNewBox(String name){
        boxList.add(new Box(name));
    }

    public void removeBox(String name){
        if (boxList.get(boxList.indexOf(name)).isExist()) boxList.remove(boxList.get(boxList.indexOf(name)));
    }



}
