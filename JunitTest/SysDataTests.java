package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import controller.SysData;
import misc.MapData;
import misc.QuestionJsonWriterEx;
import model.QuestionObject;



//This is a Test Class That Holds Examples of test's we ran to check different components
// this Class will be Separated to different files once we will configure more tests for different methods

public class SysDataTests {

	
	
	//Basic Check That we get the same SysData Class//
	SysData check = SysData.getSyso();
	@Test
	public void test() {
		assertNotNull(check);
	}
	
	
	//map Data Test//
	@Test
	public void MapData_Singleton_Check() {
		MapData mda = MapData.getSinMap();
		MapData mdb = MapData.getSinMap();

			assertEquals(mda, mdb);
		}
	
	
	//
	@Test
	public void QuestionSer_test() {
		
		String s = null;
		ArrayList<QuestionObject> QuestionArray;
		QuestionArray = SysData.getQuestions();
		
		 s = QuestionJsonWriterEx.serialazation(QuestionArray);
		
		 assertNotNull(s);
		}

	}
	


	


