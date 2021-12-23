package model;

import java.awt.*;

public class PowerUpFood {

    public Point position;
    public int type; //Range of special edible objects 0-4 according to their images in the array:
                    // 0= Bomb, 2= Hard question, 3= Medium question, 4= Easy question

    public PowerUpFood(int x,int y,int type){
        position = new Point(x,y);
        this.type = type;
    }

}
