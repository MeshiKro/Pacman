package controller;

import misc.*;
import model.*;
import view.PacWindow;
import view.yellowQuestScreen;
import view.MainScreen;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.awt.event.KeyEvent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
	// Number of times the user can use 50:50 option
	public static int numberOfFiftyIcon = 2;

	public Image foodImage;
	public Image hardQuestion;
	public Image[] pfoodImage;

	public Image goImage;
	public Image vicImage;

	public static Pacman pacman;
	public ArrayList<Food> foods;
	public ArrayList<Bomb> pufoods;
	public ArrayList<Ghost> ghosts;
	public ArrayList<Teleport> teleports;
	int counter = 0;
	// this is the Question Dynamic DataBase that we can manipulate in the
	// Question-Wizard//
	public static ArrayList<QuestionInJson> questions;

	public boolean isCustom = false;
	public boolean isGameOver = false;
	public boolean isWin = false;
	public boolean isHardQuestionEat = false;
	public boolean isMediumQuestionEat = false;
	public boolean isEasyQuestionEat = false;
	public boolean drawScore = false;
	public boolean clearScore = false;
	public int scoreToAdd = 0;
	public static boolean userSelectedCorrectAnswer = false;
	int speedGhost = 30;

	public static int score;
	public JLabel scoreboard;

	public LoopPlayer siren;
	public boolean mustReactivateSiren = false;
	public LoopPlayer pac6;

	public Point ghostBase;

	public int m_x;
	public int m_y;

	int level = 1;
	public MapData md_backup;
	public PacWindow windowParent;
	boolean isSiren = MainScreen.isMute;
	public static boolean userHasBomb = false;
	public int createFoodDelay = 30;
	public static String qLevel;

	public static int numberOfHelpIcon = 2;
	int seconds = 15;
	int cancelTimer = 0;

	private boolean isLevelUp = false;

	private BufferedImage levelupImage;

	public Timer timer;

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
			// when the pac move a new pac point will be created
			foods.add(new Food(md.getPacmanPosition().x, md.getPacmanPosition().y));
		}

		pufoods = md.getpufoodPositions();

		ghosts = new ArrayList<>();
		for (GhostData gd : md.getGhostsData()) {
			switch (gd.getType()) {
			case RED:
				ghosts.add(new RedGhost(gd.getX(), gd.getY(), this, speedGhost));
				foods.add(new Food(gd.getX(), gd.getY()));
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
			levelupImage = ImageIO.read(this.getClass().getResource("/resources/images/levelup.png"));

			vicImage = ImageIO.read(this.getClass().getResource("/resources/images/victory.png"));
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
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent evt) {
				if (userHasBomb) {
					Ghost newGhostReturn = blowBomb();
					if (newGhostReturn == null) {
						return;
					}
					// Scheduling the return of the ghost
					ScheduledExecutorService schedulerGhost = Executors.newSingleThreadScheduledExecutor();
					Runnable taskGhost = new Runnable() {
						public void run() {
							ghosts.add(newGhostReturn);
						}
					};
					schedulerGhost.schedule(taskGhost, 5, TimeUnit.SECONDS);
					schedulerGhost.shutdown();
				}

			}
		});

	}

	public void collisionTest() throws IOException {

		Rectangle pr = new Rectangle(pacman.pixelPosition.x + 13, pacman.pixelPosition.y + 13, 2, 2);
		Ghost ghostToRemove = null;
		for (Ghost g : ghosts) {
			if (g != null) {
				Rectangle gr = new Rectangle(g.pixelPosition.x, g.pixelPosition.y, 28, 28);

				if (pr.intersects(gr)) {
					if (!g.isDead()) {
						if (!g.isWeak()) {
							// totally Game Over
							if (PacWindow.pacmanLife == 0) {
								if (!isSiren) {
									SoundPlayer.play("pacman_lose.wav");
									siren.stop();
								}
								pacman.moveTimer.stop();
								pacman.animTimer.stop();
								g.moveTimer.stop();

								JsonWriterEx JW = new JsonWriterEx();
								String date = java.time.LocalDate.now().toString();
								JW.writeScordboardRecords(GlobalFuncations.username, score, date);
								if (score <= 9)
									scoreboard.setText("Press R to try again!		\t\t score: " + score);
								else if (score < 100 && score >= 10)
									scoreboard.setText("Press R to try again!		\t\t score:" + score);
								else if (score >= 100)
									scoreboard.setText("Press R to try again	\t\t score:" + score);
								isGameOver = true;
								score = 0;

								break;
							}

							// still have lives
							if (PacWindow.pacmanLife >= 1 && PacWindow.pacmanLife <= 3) {

								pacman.moveTimer.stop();
								pacman.animTimer.stop();
								g.moveTimer.stop();
								isGameOver = true;
								isLevelUp = false;
								PacWindow.pacmanLife--;
								if (siren != null)
									siren.stop();
								new PacWindow();
								windowParent.dispose();
								break;

							}

						} else {
							// Eat Ghost
							if (!isSiren) {
								SoundPlayer.play("pacman_eatghost.wav");
							}
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
			int x = foodToEat.position.x;
			int y = foodToEat.position.y;
			foods.remove(foodToEat);
			score++;
			// Scheduling the return of the pac point
			ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
			Runnable task = new Runnable() {
				public void run() {
					foods.add(new Food(x, y));
				}
			};
			scheduler.schedule(task, createFoodDelay, TimeUnit.SECONDS);
			scheduler.shutdown();

			// Levels:
			if (score < 10) {
				scoreboard.setText("     Level : 1       Score : " + score);

			}
			if (score <= 50 && score >= 10) {
				scoreboard.setText("     Level : 1      Score : " + score);
			}

			if (score >= 51 && score <= 100) {

				scoreboard.setText("     Level : 2      Score : " + score);
				if (level < 2) {
					isLevelUp = true;
					MapData map = getMapFromResource("/resources/maps/þþmap_level2M.txt");
					changeMap(map);
					level = 2;
				}

			}
			if (score >= 101 && score <= 150) {
				scoreboard.setText("     Level : 3     Score : " + score);
				if (level < 3) {
					pacman.pacmanSpeed = 10;
					pacman.moveTimer.setDelay(10);
					isLevelUp = true;
					MapData map1 = getMapFromResource("/resources/maps/map1_c.txt");
					changeMap(map1);

					level = 3;
				}

			}
			if (score >= 151) {
				scoreboard.setText("     Level : 4     Score : " + score);
				if (level < 4) {
					updateGhostSpeed();
					isLevelUp = true;
					level = 4;
				}
			}

		}
		// winning
		if (score >= 200) {
			if (this.siren != null)
				siren.stop();
			if (pac6 != null)
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
		for (Bomb puf : pufoods) {
			if (pacman.logicalPosition.x == puf.position.x && pacman.logicalPosition.y == puf.position.y)
				puFoodToEat = puf;
		}
		if (puFoodToEat != null) {
			switch (puFoodToEat.type) {
			// If User Eat Bomb
			case 0:
				int x = puFoodToEat.position.x;
				int y = puFoodToEat.position.y;
				pufoods.remove(puFoodToEat);
				// Scheduling the return of the bomb
				ScheduledExecutorService schedulerBomb = Executors.newSingleThreadScheduledExecutor();
				Runnable taskBomb = new Runnable() {
					public void run() {
						pufoods.add(new Bomb(x, y, 0));
					}
				};
				schedulerBomb.schedule(taskBomb, createFoodDelay, TimeUnit.SECONDS);
				schedulerBomb.shutdown();
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
			case 2: { // Eat Question - Hard Question

				int fx2 = puFoodToEat.position.x;
				int fy2 = puFoodToEat.position.y;
				pufoods.remove(puFoodToEat);

				stopScreenForQuestion();
				Ghost.stopScreenForQ = true;
				openQuestionScreen("Hard");

				Point newH = PositionLottery2();
				int qx2 = (int) newH.getX();
				int qy2 = (int) newH.getY();
				// Scheduling the return of the Hard question
				ScheduledExecutorService schedulerBomb2 = Executors.newSingleThreadScheduledExecutor();
				Runnable taskBomb2 = new Runnable() {
					public void run() {
						pufoods.add(new Bomb(qx2, qy2, 2));
						for (Food f : foods) {
							if (f.position.x == qx2 && f.position.y == qy2) {
								foods.remove(f);
							}

						}
					}
				};
				foods.add(new Food(fx2, fy2));
				schedulerBomb2.schedule(taskBomb2, createFoodDelay, TimeUnit.SECONDS);
				schedulerBomb2.shutdown();
				isHardQuestionEat = true;
				drawScore = true;
				break;
			}
			// Eat Orange Question - Medium Question
			case 3: {
				int fx3 = puFoodToEat.position.x;
				int fy3 = puFoodToEat.position.y;
				pufoods.remove(puFoodToEat);
				stopScreenForQuestion();
				Ghost.stopScreenForQ = true;
				openQuestionScreen("Medium");
				Point newM = PositionLottery3();
				int qx3 = (int) newM.getX();
				int qy3 = (int) newM.getY();
				// Scheduling the return of the Medium question
				ScheduledExecutorService schedulerBomb3 = Executors.newSingleThreadScheduledExecutor();
				Runnable taskBomb3 = new Runnable() {
					public void run() {
						pufoods.add(new Bomb(qx3, qy3, 3));
						for (Food f : foods) {
							if (f.position.x == qx3 && f.position.y == qy3) {
								foods.remove(f);
								System.out.print(" ");

							}
						}
					}
				};
				foods.add(new Food(fx3, fy3));
				schedulerBomb3.schedule(taskBomb3, createFoodDelay, TimeUnit.SECONDS);
				schedulerBomb3.shutdown();
				isMediumQuestionEat = true;
				drawScore = true;
				break;
			}
			case 4: {

				// Eat Question - Easy Question
				int fx4 = puFoodToEat.position.x;
				int fy4 = puFoodToEat.position.y;
				pufoods.remove(puFoodToEat);
				stopScreenForQuestion();
				Ghost.stopScreenForQ = true;
				openQuestionScreen("Easy");
				Point newE = PositionLottery4();
				int qx4 = (int) newE.getX();
				int qy4 = (int) newE.getY();
				// Scheduling the return of the Easy question
				ScheduledExecutorService schedulerBomb4 = Executors.newSingleThreadScheduledExecutor();
				Runnable taskBomb4 = new Runnable() {
					public void run() {
						pufoods.add(new Bomb(qx4, qy4, 4));
						for (Food f : foods) {
							if (f.position.x == qx4 && f.position.y == qy4) {
								foods.remove(f);
							}

						}

					}
				};
				foods.add(new Food(fx4, fy4));
				schedulerBomb4.schedule(taskBomb4, createFoodDelay, TimeUnit.SECONDS);
				schedulerBomb4.shutdown();
				isEasyQuestionEat = true;
				scoreToAdd = 1;
				drawScore = true;
				break;

			}
			default: {
				if (!isSiren) {
					SoundPlayer.play("pacman_eatfruit.wav");
				}

			}
			}
		}

		// Check Ghost Undie
		for (Ghost g : ghosts) {
			if (g.isDead() && g.logicalPosition.x == ghostBase.x && g.logicalPosition.y == ghostBase.y) {
				g.undie();
			}
		}

		// Check Teleport
		for (Teleport tp : teleports) {
			if (pacman.logicalPosition.x == tp.getFrom().x && pacman.logicalPosition.y == tp.getFrom().y
					&& pacman.activeMove == tp.getReqMove()) {
				pacman.logicalPosition = tp.getTo();
				pacman.pixelPosition.x = pacman.logicalPosition.x * 28;
				pacman.pixelPosition.y = pacman.logicalPosition.y * 28;
			}
		}

		// Check isSiren
		if (!isSiren) {
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

	protected void openQuestionScreen(String questionLevel) {
		JFrame frame = new JFrame();
		qLevel = questionLevel;
		JFXPanel jfxPanel = new JFXPanel();
		Platform.runLater(() -> {
			Parent root = null;
			try {
				if (questionLevel.equals("Medium"))
					root = FXMLLoader.load(getClass().getResource("/view/yellowQuestScreen.fxml"));
				if (questionLevel.equals("Easy"))
					root = FXMLLoader.load(getClass().getResource("/view/yellowQuestScreen.fxml"));
				if (questionLevel.equals("Hard"))
					root = FXMLLoader.load(getClass().getResource("/view/yellowQuestScreen.fxml"));

			} catch (Exception e1) {

				e1.printStackTrace();
			}
			jfxPanel.setScene(new Scene(root, 1060, 650));

		});
		frame.add(jfxPanel);
		frame.setSize(1060, 650);
		frame.setLocationRelativeTo(null); // this method display the JFrame to center position of a screen
		frame.setIconImage(new ImageIcon("./resources/images/pac/pac2.png").getImage());
		frame.setVisible(true);

		java.util.Timer timer = new java.util.Timer();
		TimerTask myTask = new TimerTask() {
			@Override
			public void run() {
				seconds--;

				if(yellowQuestScreen.userSelectAnswer) {
					 
					//cancelTimer +=5;
					seconds =5;
					yellowQuestScreen.userSelectAnswer = false;
				}
				
				//System.out.println(" seconds " + seconds + "   cancelTimer " + cancelTimer);

				if (seconds == 0) {
					timer.cancel();
					frame.dispose();

					contiuneGame();
					updateScore();
					cancelTimer =0;
					seconds =15;
				}

			}

			private void updateScore() {
				if (questionLevel.equals("Easy")) {
					if (userSelectedCorrectAnswer)
						score += 1;
					else
						score -= 10;
					userSelectedCorrectAnswer = false;
				}
				if (questionLevel.equals("Medium")) {
					if (userSelectedCorrectAnswer)
						score += 2;
					else
						score -= 20;
					userSelectedCorrectAnswer = false;
				}
				if (questionLevel.equals("Hard")) {
					if (userSelectedCorrectAnswer)
						score += 3;
					else
						score -= 30;
					userSelectedCorrectAnswer = false;
				}
			}

			private void contiuneGame() {
				Ghost.stopScreenForQ = false;
				pacman.moveTimer.start();
			}

		};

		timer.schedule(myTask, 0, 1000);

		/*
		 * 
		 * timer = new Timer(15000, new ActionListener() { public void
		 * actionPerformed(ActionEvent evt) {
		 * 
		 * timer.stop(); Ghost.stopScreenForQ = false; pacman.moveTimer.start();
		 * 
		 * 
		 * frame.dispose(); if (questionLevel.equals("Easy")) { if
		 * (userSelectedCorrectAnswer) score += 1; else score -= 10;
		 * userSelectedCorrectAnswer = false; } if (questionLevel.equals("Medium")) { if
		 * (userSelectedCorrectAnswer) score += 2; else score -= 20;
		 * userSelectedCorrectAnswer = false; } if (questionLevel.equals("Hard")) { if
		 * (userSelectedCorrectAnswer) score += 3; else score -= 30;
		 * userSelectedCorrectAnswer = false; } } }); timer.start();
		 */
	}

	// In order to stop the game behind the question
	private void stopScreenForQuestion() {
		Ghost.stopScreenForQ = true;
		pacman.moveTimer.stop();
		pacman.animTimer.stop();

	}

	private void changeMap(MapData newMap) {
		m_x = newMap.getX();
		m_y = newMap.getY();
		this.map = newMap.getMap();

	}

	public MapData getMapFromResource(String relPath) {
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
		// Draw Walls
		g.setColor(Color.blue);
		for (int i = 0; i < m_x; i++) {
			for (int j = 0; j < m_y; j++) {
				if (map[i][j] > 0) {
					g.drawImage(mapSegments[map[i][j]], 10 + i * 28, 10 + j * 28, null);
				}
			}
		}

		// Draw Food
		g.setColor(new Color(204, 122, 122));

		for (int i = 0; i < foods.size(); i++) {
			Food f = foods.get(i);
			g.drawImage(foodImage, 10 + f.position.x * 28, 10 + f.position.y * 28, null);

		}

		// Draw PowerUpFoods
		g.setColor(new Color(204, 174, 168));
		for (Bomb f : pufoods) {
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
		default:
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

		// eat fruit
		if (drawScore) {
			g.setFont(new Font("Arial", Font.BOLD, 15));
			g.setColor(Color.yellow);
			Integer s = scoreToAdd * 20;
			g.drawString(s.toString(), pacman.pixelPosition.x + 13, pacman.pixelPosition.y + 50);
			score += s;
			if (score < 10)
				scoreboard.setText("     Level : 1       Score : " + score);
			if (score <= 50 && score >= 10) {
				scoreboard.setText("     Level : 1      Score : " + score);
			}
			if (score >= 51 && score <= 100) {
				scoreboard.setText("     Level : 1      Score : " + score);
			}
			if (score >= 101 && score <= 150) {
				scoreboard.setText("     Level : 3     Score : " + score);
			}
			if (score >= 151) {
				scoreboard.setText("     Level : 3     Score : " + score);
			}
			clearScore = true;

		}

		if (isGameOver && PacWindow.pacmanLife == 0) {
			g.drawImage(goImage, this.getSize().width / 2 - 315, this.getSize().height / 2 - 75, null);

		}

		if (isWin) {
			g.drawImage(vicImage, this.getSize().width / 2 - 315, this.getSize().height / 2 - 75, null);

		}
		if (isLevelUp) {
			g.drawImage(levelupImage, this.getSize().width / 2 - 380, this.getSize().height / 2 - 325, null);
		}

	}

	@Override
	public void processEvent(AWTEvent ae) {
		if (isLevelUp)
			counter++;
		if (counter == 100) {
			isLevelUp = false;
			counter = 0;
		}

		if (ae.getID() == Messages.UPDATE) {
			update();
		} else if (ae.getID() == Messages.COLTEST) {
			if (!isGameOver) {
				try {
					collisionTest();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
			g.ghostSpeed = 10;
			g.moveTimer.setDelay(g.ghostSpeed);
		}
	}

	// The ghost disappears from the game screen
	public Ghost blowBomb() {
		pacman.changePacmanColor("");
		userHasBomb = false;
		Ghost gToReturn = null;
		for (int i = 0; i < ghosts.size(); i++) {
			if (!ghosts.get(i).isDead()) {

				if (ghostNextToPacman(ghosts.get(i))) {
					gToReturn = ghosts.get(i);
					removeGhost(gToReturn);

				}
			}
		}
		return gToReturn;
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
		return (distanceBetweenPoints(gx, gy, px, py) <= 3);
	}

	public double distanceBetweenPoints(double x1, double y1, double x2, double y2) {
		return Math.abs(Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1)));
	}

	// Random point for Hard question
	public Point PositionLottery2() {
		// TODO Auto-generated method stub

		int upperbound = 6;
		Random rand = new Random();
		int randomPoint = rand.nextInt(upperbound);
		Point positionR;
		switch (randomPoint) {
		// If User Eat Bomb
		case 0: {
			positionR = new Point(4, 5);
			return positionR;
		}

		case 1: {
			positionR = new Point(14, 2);
			return positionR;
		}
		case 2: {
			positionR = new Point(9, 14);
			return positionR;
		}
		case 3: {
			positionR = new Point(10, 8);
			return positionR;
		}
		case 4: {
			positionR = new Point(11, 11);
			return positionR;
		}
		case 5: {
			positionR = new Point(4, 1);
			return positionR;
		}
		default: {
			System.out.println("ERROR!");
			return null;
		}
		}

	}

	// Random point for Medium question
	public Point PositionLottery3() {
		// TODO Auto-generated method stub

		int upperbound = 6;
		Random rand = new Random();
		int randomPoint = rand.nextInt(upperbound);
		Point positionR;
		switch (randomPoint) {
		// If User Eat Bomb
		case 0: {
			positionR = new Point(5, 16);
			return positionR;
		}

		case 1: {
			positionR = new Point(3, 8);
			return positionR;
		}
		case 2: {
			positionR = new Point(16, 1);
			return positionR;
		}
		case 3: {
			positionR = new Point(6, 9);
			return positionR;
		}
		case 4: {
			positionR = new Point(3, 20);
			return positionR;
		}
		case 5: {
			positionR = new Point(6, 15);
			return positionR;
		}
		default: {
			System.out.println("ERROR!");
			return null;
		}
		}

	}

	// Random point for Easy question
	public Point PositionLottery4() {
		// TODO Auto-generated method stub

		int upperbound = 6;
		Random rand = new Random();
		int randomPoint = rand.nextInt(upperbound);
		Point positionR;
		switch (randomPoint) {
		// If User Eat Bomb
		case 0: {
			positionR = new Point(20, 3);
			return positionR;
		}

		case 1: {
			positionR = new Point(5, 10);
			return positionR;
		}
		case 2: {
			positionR = new Point(10, 16);
			return positionR;
		}
		case 3: {
			positionR = new Point(1, 18);
			return positionR;
		}
		case 4: {
			positionR = new Point(6, 14);
			return positionR;
		}
		case 5: {
			positionR = new Point(4, 1);
			return positionR;
		}
		default: {
			System.out.println("ERROR!");
			return null;
		}
		}

	}

}
