package sample;

import java.util.Random;

import static sample.Main.*;
import static sample.Snake.*;


public class Food {

    static int foodX = 0;
    static int foodY = 0;
    static int foodcolor = 0;
    static int speed = 5;
    static Random rand = new Random();


    public static void newFood() {
        start: while (true) {
            foodX = rand.nextInt(width);
            foodY = rand.nextInt(height);

            for (Corner c : snake) {
                if (c.x == foodX && c.y == foodY) {
                    continue start;
                }
            }
            foodcolor = rand.nextInt(5);
            speed++;
            break;
        }
    }


}
