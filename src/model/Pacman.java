package model;

import controller.SysData;
import misc.moveType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Pacman implements KeyListener {

	// Move Vars
	public Timer moveTimer;
	public ActionListener moveAL;
	public moveType activeMove;
	public moveType todoMove;
	boolean isStuck = true;

	// Animation Vars
	public Timer animTimer;
	public ActionListener animAL;
	public Image[] pac;
	public int activeImage = 0;
	public int addFactor = 1;
public int pacmanSpeed =20;
	public Point pixelPosition;
	public Point logicalPosition;
	public int pacmanSpeedMove =100;

	public SysData parentBoard;

	public Pacman(int x, int y, SysData pb) {

		logicalPosition = new Point(x, y);
		pixelPosition = new Point(28 * x, 28 * y);

		parentBoard = pb;

		pac = new Image[5];

		activeMove = moveType.NONE;
		todoMove = moveType.NONE;
		changePacmanColor("");

		// animation timer
		animAL = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				activeImage = activeImage + addFactor;
				if (activeImage == 4 || activeImage == 0) {
					addFactor *= -1;
				}
			}
		};
		animTimer = new Timer(pacmanSpeedMove, animAL);
		animTimer.start();

		moveAL = new ActionListener() {
			@SuppressWarnings("incomplete-switch")
			public void actionPerformed(ActionEvent evt) {
				
				if(pacmanSpeedMove < 100)
				animTimer.setDelay(0);
				/* */
				//animTimer.setDelay(pacmanSpeedMove);
				
				// update logical position
				if ((pixelPosition.x % 28 == 0) && (pixelPosition.y % 28 == 0)) {
					if (!isStuck) {
						switch (activeMove) {
						case RIGHT:
							logicalPosition.x++;
							break;
						case LEFT:
							logicalPosition.x--;
							break;
						case UP:
							logicalPosition.y--;
							break;
						case DOWN:
							logicalPosition.y++;
							break;
						}
						// send update message
						parentBoard.dispatchEvent(new ActionEvent(this, Messages.UPDATE, null));
					}
					isStuck = true;
					animTimer.stop();

					 if (todoMove != moveType.NONE && isPossibleMove(todoMove)) {

						activeMove = todoMove;
						todoMove = moveType.NONE;
					}
					 // Left Tannel
					else if(InTannel(28,336))
					{
						System.out.print("left tannl");
						transportPacman(700,336,25,12);
					}
					 // Right Tannel
					else if(InTannel(700,336))

					{
						System.out.print("Right tannl");
						transportPacman(28,336,1,12);

					}
					 
					else	 if ( pixelPosition.y == 336 && pixelPosition.x == 728 && todoMove == moveType.RIGHT) {						
							/*logicalPosition.x= 26;
								logicalPosition.y = 12;

								pixelPosition.y = 336;*/
								//pixelPosition.x = 1;

//								SysData.pacman = new Pacman(logicalPosition.x,logicalPosition.y,pb);	
							return;
							}
				} else {
					isStuck = false;
					animTimer.start();
				}

				switch (activeMove) {
				case RIGHT:
					if ((pixelPosition.x >= (parentBoard.m_x - 1) * 28) && parentBoard.isCustom) {
						return;
					}
					/*
					 * if((logicalPosition.x+1 < parentBoard.m_x) &&
					 * (parentBoard.map[logicalPosition.x+1][logicalPosition.y]>0)){ return; }
					 */
					if (logicalPosition.x >= 0 && logicalPosition.x < parentBoard.m_x - 1 && logicalPosition.y >= 0
							&& logicalPosition.y < parentBoard.m_y - 1) {
						if (parentBoard.map[logicalPosition.x + 1][logicalPosition.y] > 0) {
							return;
						}
					}
					pixelPosition.x++;
					break;
				case LEFT:
					if ((pixelPosition.x <= 0) && parentBoard.isCustom) {
						return;
					}
					/*
					 * if((logicalPosition.x-1 >= 0) &&
					 * (parentBoard.map[logicalPosition.x-1][logicalPosition.y]>0)){ return; }
					 */
					if (logicalPosition.x > 0 && logicalPosition.x < parentBoard.m_x - 1 && logicalPosition.y >= 0
							&& logicalPosition.y < parentBoard.m_y - 1) {
						if (parentBoard.map[logicalPosition.x - 1][logicalPosition.y] > 0) {
							return;
						}
					}
					pixelPosition.x--;
					break;
				case UP:
					if ((pixelPosition.y <= 0) && parentBoard.isCustom) {
						return;
					}
					/*
					 * if((logicalPosition.y-1 >= 0) &&
					 * (parentBoard.map[logicalPosition.x][logicalPosition.y-1]>0)){ return; }
					 */
					if (logicalPosition.x >= 0 && logicalPosition.x < parentBoard.m_x - 1 && logicalPosition.y >= 0
							&& logicalPosition.y < parentBoard.m_y - 1) {
						if (parentBoard.map[logicalPosition.x][logicalPosition.y - 1] > 0) {
							return;
						}
					}
					pixelPosition.y--;
					break;
				case DOWN:
					if ((pixelPosition.y >= (parentBoard.m_y - 1) * 28) && parentBoard.isCustom) {
						return;
					}
					/*
					 * if((logicalPosition.y+1 < parentBoard.m_y) &&
					 * (parentBoard.map[logicalPosition.x][logicalPosition.y+1]>0)){ return; }
					 */
					if (logicalPosition.x >= 0 && logicalPosition.x < parentBoard.m_x - 1 && logicalPosition.y >= 0
							&& logicalPosition.y < parentBoard.m_y - 1) {
						if (parentBoard.map[logicalPosition.x][logicalPosition.y + 1] > 0) {
							return;
						}
					}
					pixelPosition.y++;
					break;

				}

				// send Messege to PacBoard to check collision
				parentBoard.dispatchEvent(new ActionEvent(this, Messages.COLTEST, null));

			}
		};
		moveTimer = new Timer(pacmanSpeed, moveAL);
		moveTimer.start();

	}

	protected void transportPacman(int px, int py, int lx, int ly) {
		pixelPosition.x = px;
		pixelPosition.y = py;
		
		logicalPosition.x = lx;
		logicalPosition.y = ly;

	}

	protected boolean InTannel(int x,int y) {
	return	(pixelPosition.x ==x) &&  (pixelPosition.y ==y);
			
		

		
	}

	public void changePacmanColor(String color) {
		try {

			pac[0] = ImageIO.read(this.getClass().getResource("/resources/images/pac/" + color + "pac0.png"));
			pac[1] = ImageIO.read(this.getClass().getResource("/resources/images/pac/" + color + "pac1.png"));
			pac[2] = ImageIO.read(this.getClass().getResource("/resources/images/pac/" + color + "pac2.png"));
			pac[3] = ImageIO.read(this.getClass().getResource("/resources/images/pac/" + color + "pac3.png"));
			pac[4] = ImageIO.read(this.getClass().getResource("/resources/images/pac/" + color + "pac4.png"));
		} catch (IOException e) {
			System.err.println("Cannot Read Images !");
		}

	}

	public boolean isPossibleMove(moveType move) {
		try {
			if (logicalPosition.x >= 0 && logicalPosition.x < parentBoard.m_x - 1 && logicalPosition.y >= 0
					&& logicalPosition.y < parentBoard.m_y - 1) {
				switch (move) {
				case RIGHT:
					return !(parentBoard.map[logicalPosition.x + 1][logicalPosition.y] > 0);
				case LEFT:
					return !(parentBoard.map[logicalPosition.x - 1][logicalPosition.y] > 0);
				case UP:
					return !(parentBoard.map[logicalPosition.x][logicalPosition.y - 1] > 0);
				case DOWN:
					return !(parentBoard.map[logicalPosition.x][logicalPosition.y + 1] > 0);
				default:
					break;
				}
			}
		} catch (Exception ex) {
			return false;
		}
		return false;
	}


	public Image getPacmanImage() {
		return pac[activeImage];
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		//
	}

	@Override
	public void keyTyped(KeyEvent ke) {
		//
	}

	// Handle Arrow Keys
	@Override
	public void keyPressed(KeyEvent ke) {
		switch (ke.getKeyCode()) {
		case 37:
			todoMove = moveType.LEFT;
			break;
		case 38:
			todoMove = moveType.UP;
			break;
		case 39:
			todoMove = moveType.RIGHT;
			break;
		case 40:
			todoMove = moveType.DOWN;
			break;
		case 82:
			parentBoard.dispatchEvent(new ActionEvent(this, Messages.RESET, null));
			break;
		}
		// System.out.println(ke.getKeyCode());
	}

}
