package sample;

import javafx.application.Application;
        import javafx.scene.shape.Rectangle;

        import java.util.ArrayList;
        import java.util.List;

public abstract class Display<T> {
    protected  List<T> nodeItems;
    protected int displayWidth=300;
    protected int displayHeight=300;
    int getDisplayWidth(){
        return displayWidth;
    }
    int getDisplayHeight(){
        return displayHeight;
    }
    public List<T> getNodeItems(){
        return nodeItems;
    }

    abstract void display(List<Integer> x,List<Integer> y, int type);
}
