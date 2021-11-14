package Tests;

import static org.junit.Assert.*;

import javax.swing.JLabel;

import org.junit.BeforeClass;
import org.junit.Test;

import controller.SysData;
import misc.MapData;
import misc.QuestionJsonRead;
import view.PacWindow;

public class sysDataTester {

	
	  public JLabel scoreboard;
	  public MapData md_backup;
	  public PacWindow windowParent;

	
	
	
	@BeforeClass
	@Test
	public void SysData_Constructor_test() {
	
		SysData CheckInstance = new SysData(scoreboard, md_backup, windowParent);
		
		
	}
	
	@Test
	public void QuestionSer_test() {
		fail("question srlizer is Not Working propely  :(");

	}
	
	@Test
	public void questionDeSer_test() {
		fail("question Desrlizer is Not Working properly :(");
	}
	
	
	

}
