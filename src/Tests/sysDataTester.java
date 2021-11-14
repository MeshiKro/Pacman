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
	
	
	
	//if the mda and mdb hold the same refrence - it means our singleton method is working//
	@Test 
	public void MapData_Singleton_Check() {
		MapData mda = MapData.getSinMap();
		MapData mdb = MapData.getSinMap();
		
		if(mda == mdb) {
			System.out.println("Map Data singlton works!");
		}
		
	}
	
	
	//if the sda and sdb hold the same refrence - it means our singleton method is working//
	@Test
	public void SysData_Singleton_Check() {
			SysData sda = SysData.getSyso();
			SysData sdb = SysData.getSyso();
			
			if(sda == sdb) {
				System.out.println("Sys Data singlton works!");
			}
			
		
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
