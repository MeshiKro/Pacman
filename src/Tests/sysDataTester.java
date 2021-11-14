package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.swing.JLabel;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;

import controller.SysData;
import misc.MapData;
import misc.QuestionJsonRead;
import model.QuestionObject;
import view.PacWindow;

public class sysDataTester {

	
	  public JLabel scoreboard;
	  public MapData md_backup;
	  public PacWindow windowParent;

	
	
	
	@BeforeClass
	@Test
	public void SysData_Constructor_test() {
	
		
		
		
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
			
			}
			
		
	
	
	
	
	@Test
	public void QuestionSer_test( ArrayList<QuestionObject> QuestionArray) {
		
        Gson gson = new Gson();
		String output = gson.toJson(QuestionArray);
		
		System.out.println("the Question ArrayList to Json convert Succeeded. \n this is the output: \n "+ output);
		
		

	}
	
	
	@Test
	public ArrayList<QuestionObject> questionDeSer_test() {
			QuestionJsonRead r = new QuestionJsonRead ();
			 r.readQuestionsFromJson();
			 System.out.println(QuestionJsonRead.questionsAndAnswers);
			 return QuestionJsonRead.questionsAndAnswers;
		}
	}
	
	
	


