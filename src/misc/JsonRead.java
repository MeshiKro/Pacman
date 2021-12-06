package misc;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import model.AnswerObject;
import model.QuestionObject;
import model.ScoreboardRecord;



public class JsonRead {

	public static ArrayList<QuestionObject> questionsAndAnswers = new ArrayList<QuestionObject>();

	public ArrayList<QuestionObject> readQuestionsFromJson() {

		try {
			String text = new String(Files.readAllBytes(Paths.get("QuestionsFormat.json")), StandardCharsets.UTF_8);
			System.out.println(text);
			JSONObject obj = new JSONObject(text);
			JSONArray arr = obj.getJSONArray("questions");
			for (int i = 0; i < arr.length(); i++) {
				String questionString = arr.getJSONObject(i).getString("question");
				JSONArray answers = arr.getJSONObject(i).getJSONArray("answers");
				String correct_ans = arr.getJSONObject(i).getString("correct_ans");
				String level = arr.getJSONObject(i).getString("level");
				String team = arr.getJSONObject(i).getString("team");

				AnswerObject[] answersList = createAnswerList(answers, correct_ans);
				QuestionObject question = createQuestion(questionString, level, team, answersList);

				questionsAndAnswers.add(question);
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return questionsAndAnswers;

	}

	private QuestionObject createQuestion(String questionString, String level, String team,
			AnswerObject[] answersList) {
		QuestionObject q = new QuestionObject(questionString, level, team, answersList);
		return q;
	}

	private AnswerObject[] createAnswerList(JSONArray answers, String correct_ans) {

		AnswerObject[] a = new AnswerObject[4];

		for (int i = 0; i < answers.length(); i++) {
			boolean correct = false;
			if ((Integer.parseInt(correct_ans) - 1) == i)
				correct = true;

			a[i] = new AnswerObject(answers.get(i).toString(), correct);
		}
		return a;
	}

	
	
	public ArrayList<ScoreboardRecord> readScoreBoardFromJson()
	{
		ArrayList<ScoreboardRecord> records = new ArrayList<ScoreboardRecord>();
		try {
			String text = new String(Files.readAllBytes(Paths.get("Scoreboard.json")), StandardCharsets.UTF_8);
			//System.out.println(text);
			if(text.length() ==0)
				return records;
			
			JSONObject obj = new JSONObject(text);
			JSONArray arr = obj.getJSONArray("records");
			for (int i = 0; i < arr.length(); i++) {
				String nickname = arr.getJSONObject(i).getString("nickname");
				int score =(arr.getJSONObject(i).getInt("score"));
				String date = arr.getJSONObject(i).getString("date");


				ScoreboardRecord sr = new ScoreboardRecord(nickname, score, date);

				
				records.add(sr);
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return records;

	}
	
}
