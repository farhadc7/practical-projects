package sample;


import javafx.scene.input.KeyEvent;

class FXController extends Controller{
   private String moveText;
  public void setInputKey(Object move){
      super.move =((KeyEvent)move).getText();
      moveText=(String)super.move;
  }
  public int getInputKey(){
      if(moveText.charAt(0) == 'w'){ pressedKey=1; }
      else if(moveText.charAt(0) == 'd'){pressedKey= 2;}
      else if(moveText.charAt(0) == 's'){pressedKey=3;}
      else if(moveText.charAt(0) == 'a'){pressedKey=4;}

    return pressedKey;
  }
}
