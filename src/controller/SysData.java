package controller;

import misc.*;
import model.*;
import view.PacWindow;
import view.MainScreen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.KeyEvent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

public class SysData extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static SysData syso = null;

	public Timer redrawTimer;
	public ActionListener redrawAL;

	public int[][] map;
	public Image[] mapSegments;

	public Image foodImage;
	public Image[] pfoodImage;

	public Image goImage;
	public Image vicImage;

	public static Pacman pacman;
	public ArrayList<Food> foods;
	public ArrayList<Bomb> pufoods;
	public ArrayList<Ghost> ghosts;
	public ArrayList<Teleport> teleports;

	// this is the Question Dynamic DataBase that we can manipulate in the
	// Question-Wizard//
	public static ArrayList<QuestionInJson> questions;

	public boolean isCustom = false;
	public boolean isGameOver = false;
	public boolean isWin = false;
	public boolean drawScore = false;
	public boolean clearScore = false;
	public int scoreToAdd = 0;

	int speedGhost = 30;

	public int score;
	public JLabel scoreboard;

	public LoopPlayer siren;
	public boolean mustReactivateSiren = false;
	public LoopPlayer pac6;

	public Point ghostBase;

	public int m_x;
	public int m_y;
	public int pacmanLife = 3;
	int level =1;
	public MapData md_backup;
	public PacWindow windowParent;
	boolean isSiren = MainScreen.isMute;
	public static boolean userHasBomb = false;

	public SysData(JLabel scoreboard, MapData md, PacWindow pw) {
		this.scoreboard = scoreboard;
		this.setDoubleBuffered(true);
		md_backup = md;
		windowParent = pw;

		m_x = md.getX();
		m_y = md.getY();
		this.map = md.getMap();

		this.isCustom = md.isCustom();
		this.ghostBase = md.getGhostBasePosition();
		SysData.questions = JsonRead.questionsAndAnswers;

		pacman = new Pacman(md.getPacmanPosition().x, md.getPacmanPosition().y, this);
		addKeyListener(pacman);

		foods = new ArrayList<>();
		pufoods = new ArrayList<>();
		ghosts = new ArrayList<>();
		teleports = new ArrayList<>();

		if (!isCustom) {
			for (int i = 0; i < m_x; i++) {
				for (int j = 0; j < m_y; j++) {
					if (map[i][j] == 0)
						foods.add(new Food(i, j));
				}
			}
		} else {
			foods = md.getFoodPositions();
		}

		pufoods = md.getpufoodPositions();

		ghosts = new ArrayList<>();
		for (GhostData gd : md.getGhostsData()) {
			switch (gd.getType()) {
			case RED:
				ghosts.add(new RedGhost(gd.getX(), gd.getY(), this, speedGhost));
				break;
			case PINK:
				ghosts.add(new PinkGhost(gd.getX(), gd.getY(), this, speedGhost));
				break;
			case CYAN:
				ghosts.add(new CyanGhost(gd.getX(), gd.getY(), this, speedGhost));
				break;
			}
		}

		teleports = md.getTeleports();

		setLayout(null);
		setSize(20 * m_x, 20 * m_y);
		setBackground(Color.black);

		mapSegments = new Image[28];
		mapSegments[0] = null;
		for (int ms = 1; ms < 28; ms++) {
			try {
				if (MainScreen.theme.equals("Basic")) {
					mapSegments[ms] = ImageIO
							.read(this.getClass().getResource("/resources/images/map segments/" + ms + ".png"));
				}
				if (MainScreen.theme.equals("Candy Land")) {
					mapSegments[ms] = ImageIO.read(
							this.getClass().getResource("/resources/images/candyLand/map segments/" + ms + ".png"));
				}
				if (MainScreen.theme.equals("Zombie Land")) {
					mapSegments[ms] = ImageIO.read(
							this.getClass().getResource("/resources/images/zombieLand/map segments/" + ms + ".png"));
				}

			} catch (Exception e) {
			}
		}

		pfoodImage = new Image[5];
		for (int ms = 0; ms < 5; ms++) {
			try {
				if (MainScreen.theme.equals("Basic")) {
					pfoodImage[ms] = ImageIO.read(this.getClass().getResource("/resources/images/food/" + ms + ".png"));
				}
				if (MainScreen.theme.equals("Candy Land")) {
					pfoodImage[ms] = ImageIO
							.read(this.getClass().getResource("/resources/images/candyLand/food/" + ms + ".png"));
				}
				if (MainScreen.theme.equals("Zombie Land")) {
					pfoodImage[ms] = ImageIO
							.read(this.getClass().getResource("/resources/images/zombieLand/food/" + ms + ".png"));
				}

			} catch (Exception e) {
			}
		}
		try {
			if (MainScreen.theme.equals("Basic")) {
				foodImage = ImageIO.read(this.getClass().getResource("/resources/images/food.png"));
			}
			if (MainScreen.theme.equals("Candy Land")) {
				foodImage = ImageIO.read(this.getClass().getResource("/resources/images/candyLand/food.png"));
			}
			if (MainScreen.theme.equals("Zombie Land")) {
				foodImage = ImageIO.read(this.getClass().getResource("/resources/images/zombieLand/food.png"));
			}
			goImage = ImageIO.read(this.getClass().getResource("/resources/images/gameover.png"));
			vicImage = ImageIO.read(this.getClass().getResource("/resources/images/victory.png"));
			// pfoodImage = ImageIO.read(this.getClass().getResource("/images/pfood.png"));
		} catch (Exception e) {
		}

		redrawAL = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// Draw Board
				repaint();
			}
		};
		redrawTimer = new Timer(16, redrawAL);
		redrawTimer.start();

		// SoundPlayer.play("pacman_start.wav");
		if (!isSiren) {
			siren = new LoopPlayer("siren.wav");
			pac6 = new LoopPlayer("pac6.wav");
			siren.start();
		}

		// Add Lisneter to Space
		InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = getActionMap();

		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "space");
		am.put("space", new AbstractAction() {
			public void actionPerformed(ActionEvent evt) {
				if (userHasBomb)
					blowBomb();
			}
		});

	}

	public void collisionTest() {
		Rectangle pr = new Rectangle(pacman.pixelPosition.x + 13, pacman.pixelPosition.y + 13, 2, 2);
		Ghost ghostToRemove = null;
		for (Ghost g : ghosts) {
			Rectangle gr = new Rectangle(g.pixelPosition.x, g.pixelPosition.y, 28, 28);

			if (pr.intersects(gr)) {
				if (!g.isDead()) {
					if (!g.isWeak()) {
						// Game Over
						if (!isSiren) {
							SoundPlayer.play("pacman_lose.wav");
							siren.stop();
						}
						pacman.moveTimer.stop();
						pacman.animTimer.stop();
						g.moveTimer.stop();
						isGameOver = true;
						JsonWriterEx JW = new JsonWriterEx();
						String date = java.time.LocalDate.now().toString();

						JW.writeScordboardRecords(GlobalFuncations.username, score, date);
						if(score==0)
							scoreboard.setText("Press R to try again!		\t\t score: "+score);
						if(score<100 && score>=10)
							scoreboard.setText("Press R to try again!		\t\t score:"+score);
						if(score>=100)
							scoreboard.setText("Press R to try again!	\t\t score:"+score);
						// scoreboard.setForeground(Color.red);
						break;
					} else {
						// Eat Ghost
						if (!isSiren) {
							SoundPlayer.play("pacman_eatghost.wav");
						}
						// getGraphics().setFont(new Font("Arial",Font.BOLD,20));
						drawScore = true;
						scoreToAdd++;
						if (ghostBase != null)
							g.die();
						else
							ghostToRemove = g;
					}
				}
			}
		}

		if (ghostToRemove != null) {
			ghosts.remove(ghostToRemove);
		}
	}

	public void update() {

		Food foodToEat = null;
		// Check food eat
		for (Food f : foods) {
			if (pacman.logicalPosition.x == f.position.x && pacman.logicalPosition.y == f.position.y)
				foodToEat = f;
		}
		if (foodToEat != null) {
			if (!isSiren) {
				SoundPlayer.play("pacman_eat.wav");
			}
			foods.remove(foodToEat);
			score++;

			// Levels:
			if(score<10)
				scoreboard.setText("     Level : 1       Score : " + score);
			if (score <= 50&&score>=10) {

				scoreboard.setText("    Level : 1       Score : " + score);
			}
			/*
			 * } else if (score >= 51 ) {
			 * scoreboard.setText("       Level : 2       Score : " + score); // to change
			 * the level to a counter MapData map =
			 * getMapFromResource("/resources/maps/��map_level2M.txt"); changeMap(map); //
			 * variable
			 */
		} else if (score >= 51 && score <= 100) {
			
			scoreboard.setText("    Level : 2       Score : " + score); // to change the level to a counter
			if(level <2) {
			MapData map = getMapFromResource("/resources/maps/��map_level2M.txt");
			changeMap(map);
			level =2;
			}

		} else if (score >= 101 && score <= 150) {
			scoreboard.setText("   Level : 3       Score : " + score); // to change the level to a counter
			if(level <3) {
		       MapData map1 = getMapFromResource("/resources/maps/map1_c.txt");
			changeMap(map1);
			pacman.pacmanSpeed = 0;
			pacman.pacmanSpeedMove = 30;
			level =3;
			}

		} else if (score >= 151){
			scoreboard.setText("   Level : 4       Score : " + score); // to change the level to a counter
			if(level <4) {
			updateGhostSpeed(); // variable
			level =4;
			}
		}
 

		//winning
		if (score >= 200) {
			if(this.siren!=null)
			siren.stop();
			if(pac6!=null)
			pac6.stop();
			if (!isSiren) {
				SoundPlayer.play("pacman_eat.wav");
			}
			isWin = true;
			JsonWriterEx JW = new JsonWriterEx();
			String date = java.time.LocalDate.now().toString();



			JW.writeScordboardRecords(GlobalFuncations.username, score, date);

			pacman.moveTimer.stop();
			for (Ghost g : ghosts) {
				g.moveTimer.stop();
			}
		}
	

	Bomb puFoodToEat = null;
	// Check pu food eat
	for(
	Bomb puf:pufoods)
	{
		if (pacman.logicalPosition.x == puf.position.x && pacman.logicalPosition.y == puf.position.y)
			puFoodToEat = puf;
	}if(puFoodToEat!=null)
	{
		// SoundPlayer.play("pacman_eat.wav");
		switch (puFoodToEat.type) {
		// If User Eat Bomb
		case 0:
			pufoods.remove(puFoodToEat);
			mustReactivateSiren = true;
			pacman.changePacmanColor("Purple");
			userHasBomb = true;
			if (!isSiren) {
				siren.stop();
				pac6.start();
			}
			for (Ghost g : ghosts) {
				g.weaken();
			}
			scoreToAdd = 0;
			break;
		default:
			if (!isSiren) {
				SoundPlayer.play("pacman_eatfruit.wav");
			}
			pufoods.remove(puFoodToEat);
			scoreToAdd = 1;

			drawScore = true;
		}
		// score ++;
		// scoreboard.setText(" Score : "+score);
	}

	// Check Ghost Undie
	for(
	Ghost g:ghosts)
	{
		if (g.isDead() && g.logicalPosition.x == ghostBase.x && g.logicalPosition.y == ghostBase.y) {
			g.undie();
		}
	}

	// Check Teleport
	for(
	Teleport tp:teleports)
	{
		if (pacman.logicalPosition.x == tp.getFrom().x && pacman.logicalPosition.y == tp.getFrom().y
				&& pacman.activeMove == tp.getReqMove()) {
			// System.out.println("TELE !");
			pacman.logicalPosition = tp.getTo();
			pacman.pixelPosition.x = pacman.logicalPosition.x * 28;
			pacman.pixelPosition.y = pacman.logicalPosition.y * 28;
		}
	}

	// Check isSiren
	if(!isSiren)
	{
		for (Ghost g : ghosts) {
			if (g.isWeak()) {
				isSiren = false;
			}
		}
		if (!isSiren) {
			// pac6.stop();
			if (mustReactivateSiren) {
				mustReactivateSiren = false;
				siren.start();
			}
		}

	}

	}

	private void changeMap(MapData newMap) {
		 m_x = newMap.getX();
			m_y = newMap.getY();
			this.map = newMap.getMap();	
		

	}

	public  MapData getMapFromResource(String relPath) {
	        String mapStr = "";
	        try {
	            Scanner scn = new Scanner(this.getClass().getResourceAsStream(relPath));
	            StringBuilder sb = new StringBuilder();
	            String line;
	            while (scn.hasNextLine()) {
	                line = scn.nextLine();
	                sb.append(line).append('\n');
	            }
	            mapStr = sb.toString();
	        } catch (Exception e) {
	            System.err.println("Error Reading Map File !");
	        }
	        if ("".equals(mapStr)) {
	            System.err.println("Map is Empty !");
	        }
	        return MapEditor.compileMap(mapStr);
	    }

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// DEBUG ONLY !
		/*
		 * for(int ii=0;ii<=m_x;ii++){ g.drawLine(ii*28+10,10,ii*28+10,m_y*28+10); }
		 * for(int ii=0;ii<=m_y;ii++){ g.drawLine(10,ii*28+10,m_x*28+10,ii*28+10); }
		 */

		// Draw Walls
		g.setColor(Color.blue);
		for (int i = 0; i < m_x; i++) {
			for (int j = 0; j < m_y; j++) {
				if (map[i][j] > 0) {
					// g.drawImage(10+i*28,10+j*28,28,28);
					g.drawImage(mapSegments[map[i][j]], 10 + i * 28, 10 + j * 28, null);
				}
			}
		}

		// Draw Food
		g.setColor(new Color(204, 122, 122));
		for (Food f : foods) {
			// g.fillOval(f.position.x*28+22,f.position.y*28+22,4,4);
			g.drawImage(foodImage, 10 + f.position.x * 28, 10 + f.position.y * 28, null);
		}

		// Draw PowerUpFoods
		g.setColor(new Color(204, 174, 168));
		for (Bomb f : pufoods) {
			// g.fillOval(f.position.x*28+20,f.position.y*28+20,8,8);
			g.drawImage(pfoodImage[f.type], 10 + f.position.x * 28, 10 + f.position.y * 28, null);
		}

		// Draw Pacman
		switch (pacman.activeMove) {
		case NONE:
		case RIGHT:
			g.drawImage(pacman.getPacmanImage(), 10 + pacman.pixelPosition.x, 10 + pacman.pixelPosition.y, null);
			break;
		case LEFT:
			g.drawImage(ImageHelper.flipHor(pacman.getPacmanImage()), 10 + pacman.pixelPosition.x,
					10 + pacman.pixelPosition.y, null);
			break;
		case DOWN:
			g.drawImage(ImageHelper.rotate90(pacman.getPacmanImage()), 10 + pacman.pixelPosition.x,
					10 + pacman.pixelPosition.y, null);
			break;
		case UP:
			g.drawImage(ImageHelper.flipVer(ImageHelper.rotate90(pacman.getPacmanImage())), 10 + pacman.pixelPosition.x,
					10 + pacman.pixelPosition.y, null);
			break;
		}

		// Draw Ghosts
		for (Ghost gh : ghosts) {
			g.drawImage(gh.getGhostImage(), 10 + gh.pixelPosition.x, 10 + gh.pixelPosition.y, null);
		}

		if (clearScore) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			drawScore = false;
			clearScore = false;
		}

		if (drawScore) {
			// System.out.println("must draw score !");
			g.setFont(new Font("Arial", Font.BOLD, 15));
			g.setColor(Color.yellow);
			Integer s = scoreToAdd * 100;
			g.drawString(s.toString(), pacman.pixelPosition.x + 13, pacman.pixelPosition.y + 50);
			// drawScore = false;
			score += s;
			if (score<10)
				scoreboard.setText("     Level : 1       Score : " + score);
			if (score <= 50&&score>=10) {
				scoreboard.setText("    Level : 1       Score : " + score);
			}
			if (score >= 51 && score <= 100) {
				scoreboard.setText("    Level : 2       Score : " + score);
			}
			if (score >= 101 && score <= 150) {
				scoreboard.setText("   Level : 3       Score : " + score);
			}
			if (score >= 151) {
				scoreboard.setText("   Level : 4       Score : " + score);
			}
		//	scoreboard.setText("    Score : " + score);
			clearScore = true;

		}

		if (isGameOver) {
			g.drawImage(goImage, this.getSize().width / 2 - 315, this.getSize().height / 2 - 75, null);

		}

		if (isWin) {
			g.drawImage(vicImage, this.getSize().width / 2 - 315, this.getSize().height / 2 - 75, null);

		}

	}

	@Override
	public void processEvent(AWTEvent ae) {
	/*	System.out.println(" pacman.pixelPosition.x " + pacman.pixelPosition.x);
		  System.out.println(" pacman.pixelPosition.y " + pacman.pixelPosition.y);
		  
			System.out.println(" pacman.logicalPosition.x " + pacman.logicalPosition.x);
			  System.out.println(" pacman.logicalPosition.y " + pacman.logicalPosition.y);*/
		if (ae.getID() == Messages.UPDATE) {
			update();
		} else if (ae.getID() == Messages.COLTEST) {
			if (!isGameOver) {
				collisionTest();
			}
		} else if (ae.getID() == Messages.RESET) {
			if (isGameOver)
				restart();
		} else {
			super.processEvent(ae);
		}
	}

	public void restart() {
		if (siren != null)
			siren.stop();

		new PacWindow();
		windowParent.dispose();

		/*
		 * removeKeyListener(pacman);
		 * 
		 * isGameOver = false;
		 * 
		 * pacman = new
		 * Pacman(md_backup.getPacmanPosition().x,md_backup.getPacmanPosition().y,this);
		 * addKeyListener(pacman);
		 * 
		 * foods = new ArrayList<>(); pufoods = new ArrayList<>(); ghosts = new
		 * ArrayList<>(); teleports = new ArrayList<>();
		 * 
		 * //TODO : read food from mapData (Map 1)
		 * 
		 * if(!isCustom) { for (int i = 0; i < m_x; i++) { for (int j = 0; j < m_y; j++)
		 * { if (map[i][j] == 0) foods.add(new Food(i, j)); } } }else{ foods =
		 * md_backup.getFoodPositions(); }
		 * 
		 * 
		 * 
		 * pufoods = md_backup.getPufoodPositions();
		 * 
		 * ghosts = new ArrayList<>(); for(GhostData gd : md_backup.getGhostsData()){
		 * switch(gd.getType()) { case RED: ghosts.add(new RedGhost(gd.getX(),
		 * gd.getY(), this)); break; case PINK: ghosts.add(new PinkGhost(gd.getX(),
		 * gd.getY(), this)); break; case CYAN: ghosts.add(new CyanGhost(gd.getX(),
		 * gd.getY(), this)); break; } }
		 * 
		 * teleports = md_backup.getTeleports();
		 */
	}

	public static SysData getSyso() {

		if (syso == null) {

			syso = new SysData(null, null, null);
			return syso;

		}
		return syso;
	}

	public static void setSyso(SysData syso) {
		SysData.syso = syso;
	}

	public static ArrayList<QuestionInJson> getQuestions() {
		return questions;

	}

	// In Case of Level Up increae ghost speed
	public void updateGhostSpeed() {

		for (Ghost g : ghosts) {
g.ghostSpeed = 99999;
		}
	}

	public void blowBomb() {
		pacman.changePacmanColor("");
		userHasBomb = false;

		for (Ghost g : ghosts) {
			if (!g.isDead()) {

				if (ghostNextToPacman(g)) {
					// System.out.println(" remove");
					removeGhost(g);
					return;
				}

			}
		}

	}

	private void removeGhost(Ghost g) {
		g.die();
		ghosts.remove(g);

	}

	// Check if ghost distance is 3 from pacman
	private boolean ghostNextToPacman(Ghost g) {
		Point point = g.logicalPosition;
		int gx = point.x;
		int gy = point.y;

		point = pacman.logicalPosition;
		int px = point.x;
		int py = point.y;

		/*
		 * System.out.println("  gx " + gx); System.out.println("  gy " + gy);
		 * 
		 * System.out.println("  px " + px); System.out.println("  py " + py);
		 * System.out.println("  dis " + (distanceBetweenPoints(gx,gy,px,py)));
		 */

		return (distanceBetweenPoints(gx, gy, px, py) <= 3);
	}

	public double distanceBetweenPoints(double x1, double y1, double x2, double y2) {
		return Math.abs(Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1)));
	}

}
