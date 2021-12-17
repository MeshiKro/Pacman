package misc;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;

import model.AllScoreBoardRecords;
import model.AnswerObject;
import model.QuestionInJson;
import model.QuestionObject;
import model.ScoreboardRecord;
import model.questions;
import view.QuestionsListScreen;

public class JsonWriterEx {

	public boolean writeQuestions(QuestionInJson q) {
		questions qu = getArrayOfQuestionFromJSON();
		qu.questions.add(q);

		return writeQuestionToJson(qu);

	}

	private boolean writeQuestionToJson(questions qu) {
		FileWriter writer;
		try {
			writer = new FileWriter("QuestionBank.json");
			Gson gson = new Gson();

			gson.toJson(qu, writer);
			writer.flush();
			writer.close();
			return true;
		} catch (JsonIOException e) {

			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public void deleteQuestion() {
		String question = QuestionsListScreen.questionToDelete;
		questions qu = getArrayOfQuestionFromJSON();

		int index = getIndexOfQuestion(qu, question);

		qu.questions.remove(index);

		writeQuestionToJson(qu);

	}

	private int getIndexOfQuestion(questions qu, String question) {
		int index = -1;
		for (int i = 0; i < qu.questions.size(); i++) {
			String q = qu.questions.get(i).getQuestion();
			if (q.equals(question))
				index = i;
		}
		return index;
	}

	private questions getArrayOfQuestionFromJSON() {
		JsonRead jr = new JsonRead();
		ArrayList<QuestionInJson> questionsAndAnswers = jr.readQuestionsFromJson();
		questions qu = new questions();
		qu.questions = questionsAndAnswers;

		return qu;
	}

	public QuestionInJson createNewQuestion(String question, String[] answers, String correct_ans, String level) {
		return new QuestionInJson(question, answers, correct_ans, level, "Scorpions");

	}

	public void writeScordboardRecords(String nickname, int score, String date) {

		JsonRead JR = new JsonRead();

		ArrayList<ScoreboardRecord> records = JR.readScoreBoardFromJson();
		/*
		 * System.out.println("reading:");
		 * 
		 * System.out.println(records); System.out.println();
		 */
		int index = userExsist(records, nickname, score);
		if (index >= 0)
			records.get(index).setScore(score);
		else
			records.add(new ScoreboardRecord(nickname, score, date));

		records.sort(new Comparator<ScoreboardRecord>() {

			@Override
			public int compare(ScoreboardRecord o1, ScoreboardRecord o2) {
				Integer s1 = o1.getScore();
				Integer s2 = o2.getScore();

				return (-1) * (s1.compareTo(s2));
			}

		});

		if (records.size() > 10)
			records.remove(10);

		AllScoreBoardRecords allRecords = new AllScoreBoardRecords(records);
		/*
		 * System.out.println();
		 * 
		 * System.out.println(records); System.out.println();
		 */

		FileWriter writer;
		try {
			writer = new FileWriter("Scoreboard.json");
			Gson gson = new Gson();

			gson.toJson(allRecords, writer);
			writer.flush();
			writer.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private int userExsist(ArrayList<ScoreboardRecord> records, String nickname, int newScore) {
		for (int i = 0; i < records.size(); i++) {
			ScoreboardRecord current = records.get(i);
			if (current.getNickname().trim().equals(nickname.trim()) && current.getScore() < newScore) {

				return i;
			}
		}
		return -1;
	}

	public void SetConfugartion(boolean isMute, String username) {
		
		HashMap<String,String> config = new HashMap<String,String>();
		config.put("isMute", String.valueOf(isMute));
		config.put("username", username);

		FileWriter writer;
		try {
			writer = new FileWriter("Config.json");
			Gson gson = new Gson();

			gson.toJson(config, writer);
			writer.flush();
			writer.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
