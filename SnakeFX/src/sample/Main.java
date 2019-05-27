package sample;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.shape.*;

import java.util.List;


public class Main extends Application {
    Group root;
    private Display displaySnake =new DisplayFX();
    private Display displayFood=new  DisplayFX();
    private Display displaySnake1 =new DisplayFX();
    private Snake snake=new Snake(displaySnake,10);
    private Controller controller=new FXController();
    private Food food= new Food(displayFood,10);

    private Timeline timeLine1=new Timeline();
    private KeyFrame keyFrame1;
    private KeyEvent keyEvent;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Snake");
        root=new Group();

        Scene scene =new Scene(root, displaySnake.getDisplayWidth(), displaySnake.getDisplayHeight());

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                keyEvent=event;
                Runnable snake1=new Runnable(){
                    @Override
                    public void run() {
                        timeLine1.stop();
                        keyFrame1=new KeyFrame(Duration.millis(200), new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                root.getChildren().clear();
                                displaySnake(snake,root);
                                displayFood();
                            }
                        });
                        timeLine1=new Timeline();
                        timeLine1.setCycleCount(Timeline.INDEFINITE);
                        timeLine1.getKeyFrames().add(keyFrame1);
                        timeLine1.playFromStart();
                        //////////////end of run //////////////////////
                    }
                };
                Thread t1=new Thread(snake1);
                t1.start();
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private  void displaySnake(Snake snake,Group g){
        snake.grow(food);
        controller.setInputKey(keyEvent);
        snake.setDirection(controller);
        snake.displaySnake();
        List<Rectangle> snakeBody = displaySnake.getNodeItems();
        for(Rectangle re : snakeBody){
            g.getChildren().add(re);
        }
    }
    private void displayFood(){
        List<Rectangle> foodBody =displayFood.getNodeItems();
        root.getChildren().add(foodBody.get(0));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
