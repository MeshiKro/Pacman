package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
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
import javax.swing.JDialog;
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
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import misc.MapData;
import misc.MapEditor;
import  javafx.embed.swing.JFXPanel;

public class PacWindow extends JFrame {
	public static int pacmanLife = 2;
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	  BufferedImage myPicture = null;
	public void mouseClicked(MouseEvent me) {
	    Point clicked = me.getPoint();
	    Rectangle bounds = new Rectangle(250, 250, myPicture.getWidth(), myPicture.getHeight());
	    if (bounds.contains(clicked)) {
	        System.out.println("?????????????");
	    }
	}
	
	public PacWindow()  {
		
	
		setIconImage(new ImageIcon("./resources/images/pac/pac2.png").getImage());

	//	setIconImage(new ImageIcon(getClass().getResource("./resources/images/pac/pac2.png")).getImage());
		
        setTitle("Scorpion Pacman");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.black);

        setSize(794, 707);
        setLocationRelativeTo(null);
        
     //   BufferedImage myPicture = null;
   		try {
   			if(pacmanLife==2)
   			myPicture = ImageIO.read(new File("./resources/images/pac/3lives.png"));
   			if(pacmanLife==1)
   	   			myPicture = ImageIO.read(new File("./resources/images/pac/2lives.png"));
   			if(pacmanLife==0)
   	   			myPicture = ImageIO.read(new File("./resources/images/pac/1lives.png"));
   			//if(pacmanLife==0)
   	   			//myPicture = ImageIO.read(new File("./resources/images/pac/0lives.png"));
   			
   		} catch (IOException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
if(myPicture==null)
	try {
		myPicture = ImageIO.read(new File("./resources/images/pac/0lives.png"));
	} catch (IOException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
   		 JLabel picLabel = new JLabel(new ImageIcon(myPicture));
   		 picLabel.setHorizontalAlignment(JLabel.LEFT);
   		 
   		    BufferedImage homePicture = null;
    		try {
    			homePicture = ImageIO.read(new File("./resources/images/pac/homeBtn.png"));
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		 JLabel picLabel2 = new JLabel(new ImageIcon(homePicture));
    		 picLabel2.setHorizontalAlignment(JLabel.LEFT);
    		 
    		 picLabel2.addMouseListener(new MouseInputAdapter() {
    	
    			  public void mouseClicked(MouseEvent e)   {
    			        JFrame frame = new JFrame();
    			        JFXPanel jfxPanel = new JFXPanel();
    			        Platform.runLater(() -> {
    			            Parent root = null;
							try {
								root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}  // create JavaFX content, can be in a separate class
    			            Scene scene = new Scene(root);
    			            jfxPanel.setScene(scene);
    			        });
    			        frame.add(jfxPanel);
    			 //       frame.setSize(...);
    			        frame.setVisible(true);
    			    }       
    			});
    		 
    		/*picLabel2.addMouseListener(new MouseInputAdapter() {
    		     public void mouseClicked(MouseEvent e)  
    		     {  
    		   /* 	 GlobalFuncations.switchScreen(this, "MainScreen",
    		 				(getClass().getResource("/view/" + "MainScreen" + ".fxml")), "");
    		    	*/
    	/*	    	 Parent root = null;
					try {
						root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
//    		 		Parent root = FXMLLoader.load(getClass().getResource("/view/ConfirmPopUp.fxml"));
    		    	Stage primaryStage = new Stage();
    		 		Scene scene = new Scene(root);
    		 		primaryStage.setScene(scene);
    		 		primaryStage.setResizable(true);
    		 		primaryStage.setTitle("Pacman");
    		 		primaryStage.getIcons().add(new Image("/images/pacicon.png"));
    		 		primaryStage.initStyle(StageStyle.DECORATED);
    		 		primaryStage.show();

    		     }  
    		 }); */

    	/*	 picLabel2.addMouseListener(new MouseAdapter() {
    		        public void mouseClicked(MouseEvent e) {
    		           JFrame jf=new JFrame("new one");
    		        jf.setBackground(Color.BLACK);
    		        jf.setSize(new Dimension(200,70));
    		        jf.setVisible(true);
    		        jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
    		        }
    		    });*/

        JLabel scoreboard = new JLabel("     Level : 1       Score : "+SysData.score,new ImageIcon(myPicture), SwingConstants.HORIZONTAL);
        scoreboard.setHorizontalAlignment(JLabel.LEFT);
        scoreboard.setFont(new Font("OCR A Extended", Font.PLAIN, 23));
        
       // scoreboard.
        
        
    /*    this.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent me) {
        	//Solution Code goes here
        	 }
        	});*/
        
        scoreboard.setForeground(new Color(255, 243, 36));

       MapData map1 = getMapFromResource("/resources/maps/map1_c.txt");
      // MapData map1 = getMapFromResource("/resources/maps/þþmap_level2M.txt");
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
    //    this.add(Box.createRigidArea(new Dimension(10, 0)));
      //  this.getContentPane().add(picLabel2, BorderLayout.SOUTH);
 
       // this.getContentPane().add(picLabel);
        this.getContentPane().add(pb);
        setVisible(true);
    }

    public PacWindow(MapData md) {
//        setTitle("AKP Pacman v1.0");
  //      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    //    setLayout(new BorderLayout());
      //  getContentPane().setBackground(Color.black);

//        setSize(794, 884);
  //      setLocationRelativeTo(null);
    	JLabel scoreboard = new JLabel("      Level : 1       Score : "+SysData.score);
        scoreboard.setForeground(new Color(255, 243, 36));

        JFrame frame=new JFrame("first way");
        
        JPanel  panel = new JPanel();
        panel.setSize(794, 707);
        
    
		 BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("./resources/images/pac/pac2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		 picLabel.setHorizontalAlignment(JLabel.RIGHT);
        
        //int[][] mapLoaded = loadMap(27,29,"/maps/map1.txt");
        adjustMap(md);
        SysData pb = new SysData(scoreboard, md, this);
        pb.setBorder(new CompoundBorder(new EmptyBorder(10, 10, 10, 10), new LineBorder(Color.BLUE)));
        addKeyListener(pb.pacman);

        frame.setSize(1000, 900);
        
frame.getContentPane().add(scoreboard, BorderLayout.SOUTH);
frame.getContentPane().add(pb);
this.getContentPane().add(picLabel, BorderLayout.SOUTH);
this.getContentPane().add(frame);
frame.setVisible(true);
      //  setVisible(true);
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

    //Dynamically Generate Map Segments
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
                    //LEFT
                    if (x > 0 && map[x - 1][y] > 0 && map[x - 1][y] < 26) {
                        l = true;
                    }
                    //RIGHT
                    if (x < mx - 1 && map[x + 1][y] > 0 && map[x + 1][y] < 26) {
                        r = true;
                    }
                    //TOP
                    if (y > 0 && map[x][y - 1] > 0 && map[x][y - 1] < 26) {
                        t = true;
                    }
                    //Bottom
                    if (y < my - 1 && map[x][y + 1] > 0 && map[x][y + 1] < 26) {
                        b = true;
                    }
                    //TOP LEFT
                    if (x > 0 && y > 0 && map[x - 1][y - 1] > 0 && map[x - 1][y - 1] < 26) {
                        tl = true;
                    }
                    //TOP RIGHT
                    if (x < mx - 1 && y > 0 && map[x + 1][y - 1] > 0 && map[x + 1][y - 1] < 26) {
                        tr = true;
                    }
                    //Bottom LEFT
                    if (x > 0 && y < my - 1 && map[x - 1][y + 1] > 0 && map[x - 1][y + 1] < 26) {
                        bl = true;
                    }
                    //Bottom RIGHT
                    if (x < mx - 1 && y < my - 1 && map[x + 1][y + 1] > 0 && map[x + 1][y + 1] < 26) {
                        br = true;
                    }

                    //Decide Image to View
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

                    //System.out.println("MAP SEGMENT : " + mustSet);
                    map[x][y] = mustSet;
                }
                mapd.setMap(map);
            }
        }
        System.out.println("Map Adjust OK !");
    }


}