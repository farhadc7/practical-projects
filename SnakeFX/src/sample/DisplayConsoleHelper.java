package sample;

public class DisplayConsoleHelper {
    private int xPos;
    private int yPos;
    private int type;


    public DisplayConsoleHelper(int xPos, int yPos, int type){
        this.xPos = xPos;
        this.yPos = yPos;
        this.type =type;
    }

    public int getxPos() {
        return xPos;
    }


    public int getyPos() {
        return yPos;
    }


    public int getType() {
        return type;
    }

}
