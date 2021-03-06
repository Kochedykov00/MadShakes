package sample;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static sample.Food.*;
import static sample.Game.*;
import static sample.Main.*;
import static sample.Snake.takeControl;


public class GameController {

    static int width = 20;
    static int height = 20;
    static int cornersize = 25;
    private static AnimationTimer timer;


    private Pane gameRoot;
    private Scene gameScene;

    Food food;
    Socket socket;
    int id;
    Snake snakeFirst;
    Snake snakeSecond;



    public GameController(Pane gameRoot, Scene gameScene) {
        this.gameRoot = gameRoot;
        this.gameScene = gameScene;
    }


    public GameController(Pane gameRoot, Scene gameScene, Food food, Socket socket, int id, Snake snakeFirst, Snake snakeSecond) {
        this.gameRoot = gameRoot;
        this.gameScene = gameScene;
        this.food = food;
        this.socket = socket;
        this.id = id;
        this.snakeFirst = snakeFirst;
        this.snakeSecond = snakeSecond;
    }




    public void startGame() {

        gameRoot.setPrefSize(width * cornersize, height * cornersize);
        Canvas c = new Canvas(width * cornersize, height * cornersize);
        GraphicsContext gc = c.getGraphicsContext2D();
        gameRoot.getChildren().addAll(c);



        food.setTranslateY((foodY - 1.5) * cornersize);
        food.setTranslateX((foodX - 1.5) * cornersize);


        gameRoot.getChildren().addAll(food);


        timer = new AnimationTimer() {
                long lastTick = 0;


                @Override
                public void handle(long now) {
                    if (lastTick == 0) {
                        try {
                            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                            dataOutputStream.writeInt(snakeFirst.getxPos());
                            dataOutputStream.writeInt(snakeFirst.getyPos());

                            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                            snakeSecond.setxPos(dataInputStream.readInt());
                            snakeSecond.setyPos(dataInputStream.readInt());

                            lastTick = now;

                            snakeSecond.tick(gc);


                            snakeSecond.check(gameRoot, food);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }

                    }


                    if (now - lastTick > 1000000000 / speed) {
                        try {
                            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                            dataOutputStream.writeInt(snakeFirst.getxPos());
                            dataOutputStream.writeInt(snakeFirst.getyPos());

                            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                            snakeSecond.setxPos(dataInputStream.readInt());
                            snakeSecond.setyPos(dataInputStream.readInt());

                            lastTick = now;
                            snakeSecond.tick(gc);


                            snakeSecond.check(gameRoot, food);

                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }


                }


            };
            timer.start();


            takeControl(gameScene);

            food.animation.play();
    }











    public void gameOver() throws NullPointerException{

        timer.stop();
        snakeFirst.deleteSnake();
        snakeSecond.deleteSnake();
        breakFood();
        setStartScene();

        gameRoot.getChildren().removeAll();

    }
}











