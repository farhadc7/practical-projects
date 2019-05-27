package sample;


import java.util.ArrayList;
import java.util.List;

class Snake {
    private int xPos=0;
    private int yPos=0;
    private int snakeLength=2;
    private int gameOver=0;
    private int speed;
    private Display display;
    private List<Integer> xArray=new ArrayList<>();
    private List<Integer> yArray=new ArrayList<>();

    public Snake(Display display, int speed){
      this.display =display;
      this.speed =speed;
      for(int i=0;i<snakeLength;i++){
          xArray.add(xPos);
          yArray.add(yPos);
      }
  }
    public Snake(int speed) {
        this.speed = speed;
    }

    int lastKey=2;
  public void setDirection(Controller input){

    if(gameOver !=1){
      int nowPressedKey=input.getInputKey();
    if(nowPressedKey !=-1){
      if(pressReverseKey(nowPressedKey) == lastKey){
        nowPressedKey =lastKey;
      }
        if( nowPressedKey  ==1 ){
          if(yPos <=0){
            yPos = display.getDisplayHeight() - speed;
          }else{
            yPos -=speed;
            lastKey =1;
          }
        }else if(nowPressedKey ==3){
          if(yPos+ speed >= display.getDisplayHeight() ){
            yPos =0;
          }else{
           yPos +=speed;
           lastKey =3;
          }
  
        }else if(nowPressedKey ==2){
          if(xPos+speed >= display.getDisplayWidth()){
             xPos =0;
          }else{
           xPos +=speed;
           lastKey =2;
          }
        }else if(nowPressedKey == 4){
          if(xPos <=0){
            xPos = display.getDisplayWidth() - speed;
          }else{
            xPos -=speed;
            lastKey =4;
          }
        }

        checkAccident();
        snakeMove();
      }
    }
  }

    private int pressReverseKey(int k){
    if(k==2){ return 4;}else 
    if(k==4){ return 2;}else 
    if(k==1){ return 3;}else {
      return 1;
    }
  }
    public void snakeMove(){
        xArray.add(xPos);
        yArray.add(yPos);
     if(xArray.size() >snakeLength){
       xArray.remove(0);
       yArray.remove(0);
     }
  }
  public void displaySnake(){
    display.display(xArray, yArray, 1);
  }
   public void grow(Food food){

     if(food.getFoodX()== xPos && food.getFoodY() == yPos){
       snakeLength++;
       food.generateNewFood();
     }
  }
    private void checkAccident(){
    if(xArray.contains(xPos)){
        int index= xArray.indexOf(xPos);
      if(yArray.get(index) == yPos){
          gameOver=1;
      }
    }
  }
}
