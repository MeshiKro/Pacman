package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

//import javafx.scene.input.MouseEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputAdapter;
import javafx.embed.swing.JFXPanel;
import controller.SysData;
import javafx.application.Platform;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import misc.MapData;
import misc.MapEditor;
import model.Ghost;


public class PacWindow extends JFrame {
	public static int pacmanLife = 2;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static boolean openMainScreen = false;
	public static boolean isPause = false;
	public static PacWindow pacWindowContext;

	BufferedImage myPicture = null;


	@SuppressWarnings("static-access")
	public PacWindow() {
		if(SysData.score==0)
			pacmanLife=2;
		SysData.userLostLife = true;
		pacWindowContext = this;
		setIconImage(new ImageIcon("./resources/images/pac/pac2.png").getImage());

		setTitle("Scorpion Pacman");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		getContentPane().setBackground(Color.black);

        setSize(794, 707);
		setLocationRelativeTo(null);

		try {
			if (pacmanLife == 2)
				myPicture = ImageIO.read(new File("./resources/images/pac/3lives.png"));
			if (pacmanLife == 1)
				myPicture = ImageIO.read(new File("./resources/images/pac/2lives.png"));
			if (pacmanLife == 0)
				myPicture = ImageIO.read(new File("./resources/images/pac/1lives.png"));
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (myPicture == null)
			try {
				myPicture = ImageIO.read(new File("./resources/images/pac/0lives.png"));
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		picLabel.setHorizontalAlignment(JLabel.LEFT);
		/*JLabel picLabel2 = new JLabel();
		picLabel2.setHorizontalAlignment(JLabel.LEFT);

		picLabel2.addMouseListener(new MouseInputAdapter() {

			public void mouseClicked(MouseEvent e) {

				openMainScreen();

			}
		});*/
		 BufferedImage pausePic = null;
		try {
			pausePic = ImageIO.read(new File("./resources/images/pac/ButtonPauseSmall.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 JLabel picLabel2 = new JLabel(new ImageIcon(pausePic));
		 picLabel2.setHorizontalAlignment(JLabel.LEFT);
		 
		 picLabel2.addMouseListener(new MouseInputAdapter() {
				public void mouseClicked(MouseEvent e)   {
					isPause=!isPause;
					if(isPause==true) {
						Ghost.stopScreenForQ = true;
						SysData.pacman.moveTimer.stop();
						SysData.pacman.animTimer.stop();
						BufferedImage playPic = null;
				 		try {
				 			playPic = ImageIO.read(new File("./resources/images/pac/ButtonPlaySmall.png"));
				 		} catch (IOException o) {
				 			// TODO Auto-generated catch block
				 			o.printStackTrace();
				 		}
				 		picLabel2.setIcon(new ImageIcon(playPic));
				 		 return;
						
					}
					if(isPause==false) {
						Ghost.stopScreenForQ = false;
						SysData.pacman.moveTimer.start();
						SysData.pacman.animTimer.start();
						BufferedImage pausePic = null;
				 		try {
				 			pausePic = ImageIO.read(new File("./resources/images/pac/ButtonPauseSmall.png"));
				 		} catch (IOException o) {
				 			// TODO Auto-generated catch block
				 			o.printStackTrace();
				 		}
				 		picLabel2.setIcon(new ImageIcon(pausePic));
				 		 return;
					}
			    }       
			});

		JLabel scoreboard = new JLabel();
		if (SysData.score < 10)
			scoreboard = new JLabel("     Level : 1       Score : " + SysData.score, new ImageIcon(myPicture),
					SwingConstants.HORIZONTAL);
		if (SysData.score >= 10 && SysData.score < 100)
			scoreboard = new JLabel("    Level : 1       Score : " + SysData.score, new ImageIcon(myPicture),
					SwingConstants.HORIZONTAL);
		if (SysData.score >= 100)
			scoreboard = new JLabel("   Level : 1       Score : " + SysData.score, new ImageIcon(myPicture),
					SwingConstants.HORIZONTAL);

		scoreboard.setHorizontalAlignment(JLabel.LEFT);
		scoreboard.setFont(new Font("OCR A Extended", Font.PLAIN, 23));

		scoreboard.setForeground(new Color(255, 243, 36));

		MapData map1 = getMapFromResource("/resources/maps/map1_c.txt");
		adjustMap(map1);
		SysData pb = new SysData(scoreboard, map1, this);

		pb.setBorder(new CompoundBorder(new EmptyBorder(7, 7, 7, 7), new LineBorder(Color.BLUE)));
		addKeyListener(pb.pacman);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
		buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 20));
		buttonPane.add(Box.createHorizontalGlue());
		buttonPane.add(picLabel2);
		buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPane.add(scoreboard);
		buttonPane.setBackground(Color.BLACK);
		this.getContentPane().add(buttonPane, BorderLayout.SOUTH);
		pb.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		pb.setAlignmentY(java.awt.Component.CENTER_ALIGNMENT);

		this.getContentPane().add(pb, BorderLayout.CENTER);
		setVisible(true);
	}
	
	public void pasueGame()
	{
		Ghost.stopScreenForQ = true;
		SysData.pacman.moveTimer.stop();
		SysData.pacman.animTimer.stop();
	}
	public void CloseFrame(){
	    super.dispose();
	}
	protected void openMainScreen() {
		openMainScreen = true;
		JFrame frame = new JFrame();

		JFXPanel jfxPanel = new JFXPanel();
		Platform.runLater(() -> {
			Parent root = null;
			try {
				root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
			} catch (IOException e1) {

				e1.printStackTrace();
			}
			jfxPanel.setScene(new Scene(root, 1060, 650));

		});
		frame.add(jfxPanel);
		frame.setSize(1060, 650);
		frame.setLocationRelativeTo(null); // this method display the JFrame to center position of a screen
		frame.setVisible(true);

		pacWindowContext.dispose(); // close window

	}

	public int[][] loadMap(int mx, int my, String relPath) {
		try {
			@SuppressWarnings("resource")
			Scanner scn = new Scanner(this.getClass().getResourceAsStream(relPath));
			int[][] map;
			map = new int[mx][my];
			for (int y = 0; y < my; y++) {
				for (int x = 0; x < mx; x++) {
					map[x][y] = scn.nextInt();
				}
			}
			return map;
		} catch (Exception e) {
			System.err.println("Error Reading Map File !");
		}
		return null;
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

	// Dynamically Generate Map Segments
	public void adjustMap(MapData mapd) {
		int[][] map = mapd.getMap();
		int mx = mapd.getX();
		int my = mapd.getY();
		for (int y = 0; y < my; y++) {
			for (int x = 0; x < mx; x++) {
				boolean l = false;
				boolean r = false;
				boolean t = false;
				boolean b = false;
				boolean tl = false;
				boolean tr = false;
				boolean bl = false;
				boolean br = false;

				if (map[x][y] > 0 && map[x][y] < 26) {
					int mustSet = 0;
					// LEFT
					if (x > 0 && map[x - 1][y] > 0 && map[x - 1][y] < 26) {
						l = true;
					}
					// RIGHT
					if (x < mx - 1 && map[x + 1][y] > 0 && map[x + 1][y] < 26) {
						r = true;
					}
					// TOP
					if (y > 0 && map[x][y - 1] > 0 && map[x][y - 1] < 26) {
						t = true;
					}
					// Bottom
					if (y < my - 1 && map[x][y + 1] > 0 && map[x][y + 1] < 26) {
						b = true;
					}
					// TOP LEFT
					if (x > 0 && y > 0 && map[x - 1][y - 1] > 0 && map[x - 1][y - 1] < 26) {
						tl = true;
					}
					// TOP RIGHT
					if (x < mx - 1 && y > 0 && map[x + 1][y - 1] > 0 && map[x + 1][y - 1] < 26) {
						tr = true;
					}
					// Bottom LEFT
					if (x > 0 && y < my - 1 && map[x - 1][y + 1] > 0 && map[x - 1][y + 1] < 26) {
						bl = true;
					}
					// Bottom RIGHT
					if (x < mx - 1 && y < my - 1 && map[x + 1][y + 1] > 0 && map[x + 1][y + 1] < 26) {
						br = true;
					}

					// Decide Image to View
					if (!r && !l && !t && !b) {
						mustSet = 23;
					}
					if (r && !l && !t && !b) {
						mustSet = 22;
					}
					if (!r && l && !t && !b) {
						mustSet = 25;
					}
					if (!r && !l && t && !b) {
						mustSet = 21;
					}
					if (!r && !l && !t && b) {
						mustSet = 19;
					}
					if (r && l && !t && !b) {
						mustSet = 24;
					}
					if (!r && !l && t && b) {
						mustSet = 20;
					}
					if (r && !l && t && !b && !tr) {
						mustSet = 11;
					}
					if (r && !l && t && !b && tr) {
						mustSet = 2;
					}
					if (!r && l && t && !b && !tl) {
						mustSet = 12;
					}
					if (!r && l && t && !b && tl) {
						mustSet = 3;
					}
					if (r && !l && !t && b && br) {
						mustSet = 1;
					}
					if (r && !l && !t && b && !br) {
						mustSet = 10;
					}
					if (!r && l && !t && b && bl) {
						mustSet = 4;
					}
					if (r && !l && t && b && !tr) {
						mustSet = 15;
					}
					if (r && !l && t && b && tr) {
						mustSet = 6;
					}
					if (!r && l && t && b && !tl) {
						mustSet = 17;
					}
					if (!r && l && t && b && tl) {
						mustSet = 8;
					}
					if (r && l && !t && b && !br) {
						mustSet = 14;
					}
					if (r && l && !t && b && br) {
						mustSet = 5;
					}
					if (r && l && t && !b && !tr) {
						mustSet = 16;
					}
					if (r && l && t && !b && tr) {
						mustSet = 7;
					}
					if (!r && l && !t && b && !bl) {
						mustSet = 13;
					}
					if (r && l && t && b && br && tl) {
						mustSet = 9;
					}
					if (r && l && t && b && !br && !tl) {
						mustSet = 18;
					}

					map[x][y] = mustSet;
				}
				mapd.setMap(map);
			}
		}
		System.out.println("Map Adjust OK !");
	}

}