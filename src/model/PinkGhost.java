package model;

import controller.SysData;
import misc.ImageHelper;
import misc.moveType;
import view.MainScreen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


public class PinkGhost extends Ghost {

    public PinkGhost(int x, int y, SysData pb,int speed){
    	super(x,y,pb,speed);
    }

    @Override
    public void loadImages(){
        ghostR = new Image[2];
        ghostL = new Image[2];
        ghostU = new Image[2];
        ghostD = new Image[2];
        try {
    		if(MainScreen.theme.equals("Basic")){
    		      ghostR[0] = ImageIO.read(this.getClass().getResource("/resources/images/ghost/pink/1.png"));
    	            ghostR[1] = ImageIO.read(this.getClass().getResource("/resources/images/ghost/pink/3.png"));
    	            ghostL[0] = ImageHelper.flipHor(ImageIO.read(this.getClass().getResource("/resources/images/ghost/pink/1.png")));
    	            ghostL[1] = ImageHelper.flipHor(ImageIO.read(this.getClass().getResource("/resources/images/ghost/pink/3.png")));
    	            ghostU[0] = ImageIO.read(this.getClass().getResource("/resources/images/ghost/pink/4.png"));
    	            ghostU[1] = ImageIO.read(this.getClass().getResource("/resources/images/ghost/pink/5.png"));
    	            ghostD[0] = ImageIO.read(this.getClass().getResource("/resources/images/ghost/pink/6.png"));
    	            ghostD[1] = ImageIO.read(this.getClass().getResource("/resources/images/ghost/pink/7.png"));
   		}
   		if(MainScreen.theme.equals("Candy Land")){
   	      ghostR[0] = ImageIO.read(this.getClass().getResource("/resources/images/candyLand/ghost/pink/1.png"));
          ghostR[1] = ImageIO.read(this.getClass().getResource("/resources/images/candyLand/ghost/pink/3.png"));
          ghostL[0] = ImageHelper.flipHor(ImageIO.read(this.getClass().getResource("/resources/images/candyLand/ghost/pink/1.png")));
          ghostL[1] = ImageHelper.flipHor(ImageIO.read(this.getClass().getResource("/resources/images/candyLand/ghost/pink/3.png")));
          ghostU[0] = ImageIO.read(this.getClass().getResource("/resources/images/candyLand/ghost/pink/4.png"));
          ghostU[1] = ImageIO.read(this.getClass().getResource("/resources/images/candyLand/ghost/pink/5.png"));
          ghostD[0] = ImageIO.read(this.getClass().getResource("/resources/images/candyLand/ghost/pink/6.png"));
          ghostD[1] = ImageIO.read(this.getClass().getResource("/resources/images/candyLand/ghost/pink/7.png"));
   		}
   		if(MainScreen.theme.equals("Zombie Land")){
     	      ghostR[0] = ImageIO.read(this.getClass().getResource("/resources/images/zombieLand/ghost/pink/1.png"));
              ghostR[1] = ImageIO.read(this.getClass().getResource("/resources/images/zombieLand/ghost/pink/3.png"));
              ghostL[0] = ImageHelper.flipHor(ImageIO.read(this.getClass().getResource("/resources/images/zombieLand/ghost/pink/1.png")));
              ghostL[1] = ImageHelper.flipHor(ImageIO.read(this.getClass().getResource("/resources/images/zombieLand/ghost/pink/3.png")));
              ghostU[0] = ImageIO.read(this.getClass().getResource("/resources/images/zombieLand/ghost/pink/4.png"));
              ghostU[1] = ImageIO.read(this.getClass().getResource("/resources/images/zombieLand/ghost/pink/5.png"));
              ghostD[0] = ImageIO.read(this.getClass().getResource("/resources/images/zombieLand/ghost/pink/6.png"));
              ghostD[1] = ImageIO.read(this.getClass().getResource("/resources/images/zombieLand/ghost/pink/7.png"));
   		}
        	
        	
      
        }catch(IOException e){
            System.err.println("Cannot Read Images !");
        }
    }

    moveType lastCMove;

    moveType pendMove = moveType.UP;

    @Override
    public moveType getMoveAI(){
        if(isPending){
            if(isStuck){
                if(pendMove == moveType.UP){
                    pendMove = moveType.DOWN;
                }else if(pendMove == moveType.DOWN){
                    pendMove = moveType.UP;
                }else if(pendMove == moveType.LEFT){
                    pendMove = moveType.RIGHT;
                }else if(pendMove == moveType.RIGHT){
                    pendMove = moveType.LEFT;
                }
                return pendMove;
            }else{
                return pendMove;
            }
        }
        if(isDead) {
            return baseReturner.getMove(logicalPosition.x,logicalPosition.y, parentBoard.ghostBase.x,parentBoard.ghostBase.y);
        }else {
            if (lastCMove == null || isStuck) {
                ArrayList<moveType> pm = getPossibleMoves();
                int i = ThreadLocalRandom.current().nextInt(pm.size());
                lastCMove = pm.get(i);
                return lastCMove;
            } else {
                return lastCMove;
            }
        }
    }
}
