package misc;



import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;


public class QuestionEditor extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QuestionEditor(){
        setSize(650,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        getContentPane().setBackground(Color.black);

        JPanel sideBar = new JPanel();
        sideBar.setLayout(new BorderLayout());
        sideBar.setBackground(Color.black);
        JPanel ghostSelection = new JPanel();
        ghostSelection.setLayout(new BoxLayout(ghostSelection,BoxLayout.Y_AXIS));
        ghostSelection.setBackground(Color.black);
        JLabel l0 = new JLabel("1: Add New Question");
        JLabel l1 = new JLabel("2: Remove A Question");
        JLabel l2 = new JLabel("3: Alter A Question");

        //JLabel l4 = new JLabel("1 : Red Ghost (Chaser)");

        l0.setForeground(Color.yellow);
        l1.setForeground(Color.yellow);
        l2.setForeground(Color.yellow);
  
        ghostSelection.add(l0);
        ghostSelection.add(l1);
        ghostSelection.add(l2);


        setLayout(new BorderLayout());
        sideBar.add(ghostSelection,BorderLayout.NORTH);
        getContentPane().add(sideBar,BorderLayout.EAST);

        JTextArea ta = new JTextArea();
        ta.setBackground(Color.black);
        ta.setForeground(Color.yellow);
      
        ta.setBorder(new CompoundBorder(new CompoundBorder(new EmptyBorder(20,10,20,10),new LineBorder(Color.yellow)),new EmptyBorder(10,10,10,10)));
        getContentPane().add(ta);


      
      
        //setLayout(new Grid);

        setVisible(true);
    }

    //Resolve Map
    public static MapData compileMap(String input){
        int mx = input.indexOf('\n');
        int my = StringHelper.countLines(input);
        System.out.println("Making Map "+mx+"x"+my);

        MapData customMap = new MapData(mx,my);
        customMap.setCustom(true);
        int[][] map = new int[mx][my];

        
        customMap.setMap(map);
        customMap.setCustom(true);
        System.out.println("Map Read OK !");
        return customMap;
        //new PacWindow(customMap);
    }

}
