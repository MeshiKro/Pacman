package model;

import controller.SysData;
import misc.BFSFinder;
import misc.moveType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

//this class is a parent for all the different Ghosts in the game//
public abstract class Ghost {
	// Anim Vars
	public Timer animTimer;
	public ActionListener animAL;

	// Pending Vars
	public Timer pendingTimer;
	public ActionListener pendingAL;

	// Move Vars
	public Timer moveTimer;
	public ActionListener moveAL;
	public moveType activeMove;
	protected boolean isStuck = true;
	public boolean isPending = false;

	public Timer unWeakenTimer1;
	public Timer unWeakenTimer2;
	public ActionListener unweak1;
	public ActionListener unweak2;
	public int unweakBlinks;
	public boolean isWhite = false;

	protected boolean isWeak = false;
	protected boolean isDead = false;

	public boolean isWeak() {
		return isWeak;
	}

	public boolean isDead() {
		return isDead;
	}

	
	public Image ghostImg;
	public int activeImage = 0;
	public int addFactor = 1;

	public Point pixelPosition;
	public Point logicalPosition;

	public Image[] ghostR;
	public Image[] ghostL;
	public Image[] ghostU;
	public Image[] ghostD;

	public Image[] ghostW;
	public Image[] ghostWW;
	public Image ghostEye;

	public int ghostNormalDelay;
	public int ghostWeakDelay = 30;
	public int ghostDeadDelay = 5;

	public BFSFinder baseReturner;
	public int ghostSpeed = 100;
	public static boolean stopScreenForQ=false;

	protected SysData parentBoard;

	public Ghost(int x, int y, SysData pb, int ghostDelay) {

		logicalPosition = new Point(x, y);
		pixelPosition = new Point(28 * x, 28 * y);

		parentBoard = pb;

		activeMove = moveType.RIGHT;

		ghostNormalDelay = ghostDelay;

		loadImages();


		try {
			ghostEye = ImageIO.read(this.getClass().getResource("/resources/images/eye.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// animation timer
		animAL = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				activeImage = (activeImage + 1) % 2;
			}
		};
		animTimer = new Timer(ghostSpeed, animAL);
		animTimer.start();

		moveAL = new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				if (stopScreenForQ) {
					return;
				}
				animTimer.setDelay(ghostSpeed);
				
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
						default:
							break;
						}
						parentBoard.dispatchEvent(new ActionEvent(this, Messages.UPDATE, null));
					}

					activeMove = getMoveAI();
					isStuck = true;

				} else {
					isStuck = false;
					
				}
				// }
				// TODO : fix ghost movements
				switch (activeMove) {
				case RIGHT:
					if (pixelPosition.x >= (parentBoard.m_x - 1) * 28) {
						return;
					}
					if ((logicalPosition.x + 1 < parentBoard.m_x)
							&& (parentBoard.map[logicalPosition.x + 1][logicalPosition.y] > 0)
							&& ((parentBoard.map[logicalPosition.x + 1][logicalPosition.y] < 26) || isPending)) {
						return;
					}
					pixelPosition.x++;
					break;
				case LEFT:
					if (pixelPosition.x <= 0) {
						return;
					}
					if ((logicalPosition.x - 1 >= 0) && (parentBoard.map[logicalPosition.x - 1][logicalPosition.y] > 0)
							&& ((parentBoard.map[logicalPosition.x - 1][logicalPosition.y] < 26) || isPending)) {
						return;
					}
					pixelPosition.x--;
					break;
				case UP:
					if (pixelPosition.y <= 0) {
						return;
					}
					if ((logicalPosition.y - 1 >= 0) && (parentBoard.map[logicalPosition.x][logicalPosition.y - 1] > 0)
							&& ((parentBoard.map[logicalPosition.x][logicalPosition.y - 1] < 26) || isPending)) {
						return;
					}
					pixelPosition.y--;
					break;
				case DOWN:
					if (pixelPosition.y >= (parentBoard.m_y - 1) * 28) {
						return;
					}
					if ((logicalPosition.y + 1 < parentBoard.m_y)
							&& (parentBoard.map[logicalPosition.x][logicalPosition.y + 1] > 0)
							&& ((parentBoard.map[logicalPosition.x][logicalPosition.y + 1] < 26) || isPending)) {
						return;
					}
					pixelPosition.y++;
					break;
				default:
					break;
				}

				parentBoard.dispatchEvent(new ActionEvent(this, Messages.COLTEST, null));
			}
		};
		moveTimer = new Timer(ghostNormalDelay, moveAL);
		moveTimer.start();

		unweak1 = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				unWeakenTimer2.start();
				unWeakenTimer1.stop();
			}
		};
		unWeakenTimer1 = new Timer(7000, unweak1);

		unweak2 = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (unweakBlinks == 10) {
					unweaken();
					unWeakenTimer2.stop();
				}
				if (unweakBlinks % 2 == 0) {
					isWhite = true;
				} else {
					isWhite = false;
				}
				unweakBlinks++;
			}
		};
		unWeakenTimer2 = new Timer(250, unweak2);

		pendingAL = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isPending = false;
				pendingTimer.stop();
			}
		};
		pendingTimer = new Timer(7000, pendingAL);

		baseReturner = new BFSFinder(pb);
		// start AI
		activeMove = getMoveAI();

	}

	// load Images from Resource
	public abstract void loadImages();

	// get Move Based on AI
	public abstract moveType getMoveAI();

	// get possible Moves
	public ArrayList<moveType> getPossibleMoves() {
		ArrayList<moveType> possibleMoves = new ArrayList<>();

		if (logicalPosition.x >= 0 && logicalPosition.x < parentBoard.m_x - 1 && logicalPosition.y >= 0
				&& logicalPosition.y < parentBoard.m_y - 1) {
			if (!(parentBoard.map[logicalPosition.x + 1][logicalPosition.y] > 0)) {
				possibleMoves.add(moveType.RIGHT);
			}

			if (!(parentBoard.map[logicalPosition.x - 1][logicalPosition.y] > 0)) {
				possibleMoves.add(moveType.LEFT);
			}

			if (!(parentBoard.map[logicalPosition.x][logicalPosition.y - 1] > 0)) {
				possibleMoves.add(moveType.UP);
			}

			if (!(parentBoard.map[logicalPosition.x][logicalPosition.y + 1] > 0)) {
				possibleMoves.add(moveType.DOWN);
			}
		}

		return possibleMoves;
	}

	public Image getGhostImage() {
		if (!isDead) {
			
				switch (activeMove) {
				case RIGHT:
					return ghostR[activeImage];
				case LEFT:
					return ghostL[activeImage];
				case UP:
					return ghostU[activeImage];
				case DOWN:
					return ghostD[activeImage];
				default:
					break;
				}
				return ghostR[activeImage];
			 
		}
			return ghostEye;

	}
	
	public void weaken() {
		moveTimer.setDelay(ghostWeakDelay);
		unweakBlinks = 0;
		isWhite = false;
		unWeakenTimer1.start();
	}

	public void unweaken() {
		isWeak = false;
		moveTimer.setDelay(ghostNormalDelay);
	}

	public void die() {
		isDead = true;
		moveTimer.setDelay(ghostDeadDelay);
	}

	public void undie() {
		// Shift Left Or Right
		int r = ThreadLocalRandom.current().nextInt(3);
		if (r == 0) {
			// Do nothing
		}
		if (r == 1) {
			logicalPosition.x += 1;
			pixelPosition.x += 28;
		}
		if (r == 2) {
			logicalPosition.x -= 1;
			pixelPosition.x -= 28;
		}
		isPending = true;
		pendingTimer.start();

		isDead = false;
		isWeak = false;
		moveTimer.setDelay(ghostNormalDelay);
	}

}
