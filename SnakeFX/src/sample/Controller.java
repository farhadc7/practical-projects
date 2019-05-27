package sample;

public abstract class Controller {
    protected int pressedKey=-1;

    protected Object move;

    abstract void setInputKey(Object move);

    abstract public int getInputKey();
}
