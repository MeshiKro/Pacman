package misc;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;

import model.AllScoreBoardRecords;
import model.AnswerObject;
import model.QuestionObject;
import model.ScoreboardRecord;
import model.questions;

public class JsonWriterEx {

	Gson gson = new Gson();

	public boolean serialazation(ArrayList<QuestionObject> arg) throws Exception {
		questions helper = new questions();


		AnswerObject[] answers = new AnswerObject[4];

		answers[0] = new AnswerObject("111", false);
		answers[1] = new AnswerObject("222", false);
		answers[2] = new AnswerObject("333", false);
		answers[3] = new AnswerObject("444", true);

		QuestionObject Question = new QuestionObject("Hello How Are you?", "3", "Scorpions", answers);

		helper.questions.addAll(arg);
		helper.questions.add(Question);


		FileWriter writer = new FileWriter("QuestionBank.json");
		try {
			gson.toJson(helper, writer);

		} catch (JsonIOException e) {
			e.printStackTrace();
			return false;
		}

		writer.flush(); // flush data to file <---
		writer.close(); // close write

		return true;
	}

	
	public void writeQuestions()  {
		
		

	}
/*	private static questions createNewQuestion() {
		String[] answers = new String[4];

		answers[0] = "111";
		answers[1] = "222";
		answers[2] = "333";
		answers[3] = "444";
		new questions("Hello How Are you?",answers, "2","2", "Scorpions");

		return null;
	}

	private static ArrayList<questions> getExsistQuestion(ArrayList<QuestionObject> arg) {
		ArrayList<questions> helper = new ArrayList<questions>();

		for (int i = 0; i < arg.size(); i++) {
			String[] answers = new String[4];
			String correctAns = "";
			for (int j = 0; j < 4; j++) {
				answers[j] = arg.get(j).answers[0].answer;

				if (arg.get(j).answers[0].isCorrect)
					correctAns = Integer.toString(j);
			}

			helper.add(new questions(arg.get(i).question, answers, correctAns, arg.get(i).level.toString(),
					arg.get(i).team));
		}
		return helper;
	}
*/
	
	public void writeScordboardRecords(String nickname,int score ,	String date) {

		
		
		JsonRead JR = new JsonRead();

		ArrayList<ScoreboardRecord> records = 	JR.readScoreBoardFromJson();	
		/*System.out.println("reading:");

		System.out.println(records);
		System.out.println();*/
		int index = userExsist(records,nickname,score);
		if(index >= 0)
		records.get(index).setScore(score);
		else
			records.add(new ScoreboardRecord(nickname, score, date));
		
		records.sort(new Comparator<ScoreboardRecord>() {

			@Override
			public int compare(ScoreboardRecord o1, ScoreboardRecord o2) {
				Integer  s1 = o1.getScore();
				Integer  s2 = o2.getScore();

		        return (-1)*(s1.compareTo(s2));
			}
			
		});
		
		if(records.size() >10)
			records.remove(10);
		
		
		AllScoreBoardRecords allRecords = new AllScoreBoardRecords(records);
		/*System.out.println();

		System.out.println(records);
		System.out.println();*/

		FileWriter writer;
		try {
			writer = new FileWriter("Scoreboard.json");
			gson.toJson(allRecords, writer);
			writer.flush();
			writer.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private int userExsist(ArrayList<ScoreboardRecord> records, String nickname,int newScore ) {
		for(int i=0;i<records.size();i++)
		{
			 ScoreboardRecord current = records.get(i);
			if(current.getNickname().trim().equals(nickname.trim()) && current.getScore() < newScore) {
				
				return i;
			}
		}
		return -1;
	}
}
