package sample;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;
public class DisplayFX extends Display<Rectangle> {
    int rectangleSize=10;

    public void display(List<Integer> x,List<Integer> y,int type){
        nodeItems =new ArrayList<>();
        Rectangle rectangle;
        for(int i=0;i<x.size(); i++){
            rectangle= new Rectangle(rectangleSize,rectangleSize);
            if(type==1){
                rectangle.setFill(Color.BLACK);
                rectangle.setStroke(Color.RED);
                rectangle.setX(x.get(i));
                rectangle.setY(y.get(i));
            }else if(type==2){
                rectangle.setFill(Color.YELLOW);
                rectangle.setStroke(Color.GREEN);
                rectangle.setX(x.get(i));
                rectangle.setY(y.get(i));
            }
            nodeItems.add(rectangle);
        }
    }
}
