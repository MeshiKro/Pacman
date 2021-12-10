
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import controller.SysData;
import misc.BFSFinder;
import misc.MapData;
import misc.JsonRead;
import misc.moveType;
import model.QuestionInJson;
import model.QuestionObject;
import model.RedGhost;



//This is a Test Class That Holds Examples of test's we ran to check different components
// this Class will be Separated to different files once we will configure more tests for different methods

public class JunitTests {

	
	
	//Basic Check That we get the  SysData  Class//
	SysData check = SysData.getSyso();
	@Test
	public void test() {
		assertNotNull(check);
	}
	
	
	//map Data Test Checks For SingleTon Design Pattern//
	@Test
	public void MapData_Singleton_Check() {
		MapData mda = MapData.getSinMap();
		MapData mdb = MapData.getSinMap();

			assertEquals(mda, mdb);
		}
	
	
	//This Test Checks That The Ghost AI method is Working//
	@Test
	public void RedGhostAiMove() {
		RedGhost RGhosTest = new RedGhost(15, 15, check,10);
		moveType cheack = RGhosTest.getMoveAI();
		assertNotNull(cheack);
		
		
	}
	
	
	//This Test Checks that we were able to de-serialize the Json File //
	@Test
	public void JsonReadTest() {
		JsonRead check = new JsonRead();
		ArrayList<QuestionInJson> questionsCheck = new ArrayList<QuestionInJson>();
		questionsCheck = check.readQuestionsFromJson();
		assertNotNull(questionsCheck);;
		
	}
	
	
	
	//This Test Checks if The BFS Algorithem is Going as Expected (The Ghosts AI Uses The BFS search to calculate the Direction "move" to Pacman //
	@Test
	public void BFS_Algo_Check() {
		
	SysData BFSarg = new SysData(null, null, null);
	BFSFinder check = new BFSFinder(BFSarg);
	
	assertEquals(check.getMove(10, 10, 12, 12), moveType.UP);
		
	}
	
}

	


