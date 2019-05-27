package sample;

import java.util.ArrayList;
import java.util.List;

class Food {
    private int xPos=0;
    private int yPos=0;
    private int speed;
    private Display display;
    private List<Integer> xArray=new ArrayList<>();
    private List<Integer> yArray=new ArrayList<>();

    public Food(Display display, int speed){
        this.display = display;
        this.speed= speed;
        xPos=((int)(Math.random()*display.getDisplayWidth()/speed)*speed);
        yPos=((int)(Math.random()*display.getDisplayHeight()/speed)*speed);
         xArray.add(xPos);
         yArray.add(yPos);
         displayFood();
    }
    public int getFoodX(){
      return xArray.get(0);
    }
    public int getFoodY(){
      return yArray.get(0);
    }
    public void generateNewFood(){
        xPos=((int)(Math.random()*display.getDisplayWidth()/speed)*speed);
        yPos=((int)(Math.random()*display.getDisplayHeight()/speed)*speed);
        xArray.set(0,xPos);
        yArray.set(0,yPos);
        displayFood();
    }
    public void displayFood(){
      display.display(xArray, yArray, 2);
    }
}
