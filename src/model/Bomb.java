package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import misc.SoundPlayer;

public class Bomb {

    public Point position;
    public int type; //0-4

    public Bomb(int x,int y,int type){
        this.position = new Point(x,y);
        this.type = type;
    }
    

    }
 

    //Add Function "Bomb Near By ghost"//


