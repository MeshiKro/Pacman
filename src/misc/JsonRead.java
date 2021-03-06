package misc;

import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import model.QuestionInJson;
import model.QuestionWithData;
import model.ScoreboardRecord;

import view.MainScreen;
import view.QuestionsListScreen;

@SuppressWarnings("deprecation")
public class JsonRead implements Observer {

	public static ArrayList<QuestionInJson> questionsAndAnswers = new ArrayList<QuestionInJson>();

	public static ArrayList<QuestionWithData> questionWithData = new ArrayList<QuestionWithData>();

	public ArrayList<QuestionInJson> readQuestionsFromJson() {

		questionsAndAnswers.clear();
		Path root = FileSystems.getDefault().getPath("").toAbsolutePath();
		Path filePath = Paths.get(root.toString(), "QuestionBank.json");
		String text = "";
		try {
			try {
				text = new String(Files.readAllBytes(Paths.get("QuestionBank.json")), StandardCharsets.UTF_8);
			} catch (Exception e) {
				root = FileSystems.getDefault().getPath("").toAbsolutePath();
				filePath = Paths.get(root.toString(), "QuestionBank.json");
				System.out.println(filePath);
				text = new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);

			}
			System.out.println();
			JSONObject obj = new JSONObject(text);
			JSONArray arr = obj.getJSONArray("questions");
			for (int i = 0; i < arr.length(); i++) {
				String questionString = arr.getJSONObject(i).getString("question");
				JSONArray answers = arr.getJSONObject(i).getJSONArray("answers");
				String correct_ans = arr.getJSONObject(i).getString("correct_ans");
				String level = arr.getJSONObject(i).getString("level");
				String team = arr.getJSONObject(i).getString("team");

				String[] answersList = createAnswerList(answers, correct_ans);

				QuestionInJson question = new QuestionInJson(questionString, answersList, correct_ans, level, team);

				questionsAndAnswers.add(question);
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return questionsAndAnswers;

	}

	public ArrayList<QuestionWithData> readQuestionsDataFromJson() {

		Path root = FileSystems.getDefault().getPath("").toAbsolutePath();
		Path filePath = Paths.get(root.toString(), "QuestionData.json");
		String text = "";
		try {
			try {
				text = new String(Files.readAllBytes(Paths.get("QuestionData.json")), StandardCharsets.UTF_8);
			} catch (Exception e) {
				root = FileSystems.getDefault().getPath("").toAbsolutePath();
				filePath = Paths.get(root.toString(), "QuestionData.json");
				text = new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);

			}
			JSONObject obj = new JSONObject(text);
			JSONArray arr = obj.getJSONArray("questionWithData");
			for (int i = 0; i < arr.length(); i++) {
				String questionString = arr.getJSONObject(i).getString("question");
				String answers = arr.getJSONObject(i).getJSONObject("answerData").toString();
				HashMap<String, Integer> map = new Gson().fromJson(answers, HashMap.class);
				String correct_ans = arr.getJSONObject(i).getString("correct_ans");			
				

				QuestionWithData q = new QuestionWithData(questionString, correct_ans, map);
				questionWithData.add(q);
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return questionWithData;

	}

	public QuestionInJson getQuestionFromJson() {

		for (int i = 0; i < questionsAndAnswers.size(); i++) {
			if (questionsAndAnswers.get(i).getQuestion().equals(QuestionsListScreen.questionString))
				return questionsAndAnswers.get(i);
		}
		return null;
	}

	@SuppressWarnings("unused")
	private String[] createAnswerList(JSONArray answers, String correct_ans) {

		String[] a = new String[4];

		for (int i = 0; i < answers.length(); i++) {
			boolean correct = false;
			if ((Integer.parseInt(correct_ans) - 1) == i)
				correct = true;

			a[i] = answers.get(i).toString();
		}
		return a;
	}

	public ArrayList<ScoreboardRecord> readScoreBoardFromJson() {
		ArrayList<ScoreboardRecord> records = new ArrayList<ScoreboardRecord>();
		try {
			String text = new String(Files.readAllBytes(Paths.get("Scoreboard.json")), StandardCharsets.UTF_8);
			if (text.length() == 0)
				return records;

			JSONObject obj = new JSONObject(text);
			JSONArray arr = obj.getJSONArray("records");
			for (int i = 0; i < arr.length(); i++) {
				String nickname = arr.getJSONObject(i).getString("nickname");
				int score = (arr.getJSONObject(i).getInt("score"));
				String date = arr.getJSONObject(i).getString("date");

				ScoreboardRecord sr = new ScoreboardRecord(nickname, score, date);

				records.add(sr);
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return records;

	}

	public boolean readConfig() {
		try {
			String text = new String(Files.readAllBytes(Paths.get("Config.json")), StandardCharsets.UTF_8);

			String mute = new JSONObject(text).getString("isMute");
			if (mute.equals("true"))
				MainScreen.isMute = true;
			else
				MainScreen.isMute = false;

			GlobalFuncations.username = new JSONObject(text).getString("username");

			return true;
		} catch (Exception ex) {
			return false;
		}

	}

	@Override
	public void update(Observable o, Object arg) {
		questionsAndAnswers.clear();
		readQuestionsFromJson();
	}
}
