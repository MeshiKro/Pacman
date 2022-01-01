package misc;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;

import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import model.AllScoreBoardRecords;
import model.QuestionInJson;
import model.QuestionWithData;
import model.ScoreboardRecord;
import model.questions;
import view.EditQuestionScreen;
import view.QuestionsListScreen;

@SuppressWarnings("deprecation")
public class JsonWriterEx extends Observable {

	public boolean writeQuestions(QuestionInJson q) {
		questions qu = new questions();

	
		JsonRead.questionsAndAnswers.add(q);

		qu.questions = JsonRead.questionsAndAnswers;
		return writeQuestionToJson(qu);

	}

	

	public boolean writeQuestionsData() {

		ArrayList<QuestionWithData> questionWithData = new ArrayList<QuestionWithData>();
		for (int i = 0; i < JsonRead.questionsAndAnswers.size(); i++) {
			String question = JsonRead.questionsAndAnswers.get(i).getQuestion();
			int index = Integer.parseInt(JsonRead.questionsAndAnswers.get(i).getCorrect_ans()) - 1;
			String correctAnswer = JsonRead.questionsAndAnswers.get(i).getAnswers()[index];

			questionWithData.add(
					new QuestionWithData(question, correctAnswer, JsonRead.questionsAndAnswers.get(i).getAnswers()));

		}

	return	WriteQuestionDataToJSon(questionWithData);
		
	

	}
	private boolean WriteQuestionDataToJSon(ArrayList<QuestionWithData> questionWithData) {
		FileWriter writer;
		try {
			writer = new FileWriter("QuestionData.json");
			Gson gson = new Gson();
			QuestionDataToJSON temp = new QuestionDataToJSON();
			temp.questionWithData = questionWithData;
			gson.toJson(temp, writer);
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
	public class QuestionDataToJSON{
		ArrayList<QuestionWithData> questionWithData = new ArrayList<QuestionWithData>();

	}

	private boolean writeQuestionToJson(questions qu) {
		FileWriter writer;
		try {
			writer = new FileWriter("QuestionBank.json");
			Gson gson = new Gson();

			gson.toJson(qu, writer);
			writer.flush();
			writer.close();
			setChanged();
			notifyObservers();
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

		questions qu = new questions();
		qu.questions = JsonRead.questionsAndAnswers;

		
		int index = -1;
		System.out.print(" JsonRead.questionsAndAnswers.size() " + JsonRead.questionsAndAnswers.size());

		for (int i = 0; i < JsonRead.questionsAndAnswers.size(); i++) {
			String q = JsonRead.questionsAndAnswers.get(i).getQuestion();
			System.out.print(" q " + q);

			if (q.equals(QuestionsListScreen.questionToDelete.toString())) {
				index = i;
				System.out.print(" q " + index);

			}
		}
		
		
		
		System.out.print("index " + index);
		if (index == -1)
			return;
		JsonRead.questionsAndAnswers.remove(index);
		qu.questions = JsonRead.questionsAndAnswers;
		writeQuestionToJson(qu);

	}

	public static int getIndexOfQuestion(questions qu, String question) {
		int index = -1;
		System.out.println(" JsonRead.questionsAndAnswers.size() " + JsonRead.questionsAndAnswers.size());

		for (int i = 0; i < JsonRead.questionsAndAnswers.size(); i++) {
			String q = JsonRead.questionsAndAnswers.get(i).getQuestion();
			System.out.print(" q " + q);

			if (q.equals(question)) {
				index = i;
				System.out.print(" q " + index);

			}
		}
		return index;
	}

	public QuestionInJson createNewQuestion(String question, String[] answers, String correct_ans, String level) {
		return new QuestionInJson(question, answers, correct_ans, level, "Scorpions");

	}

	public void writeScordboardRecords(String nickname, int score, String date) {

		JsonRead JR = new JsonRead();

		ArrayList<ScoreboardRecord> records = JR.readScoreBoardFromJson();
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

		HashMap<String, String> config = new HashMap<String, String>();
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

	public void updateQuestionDataArray(String question, String selectedAnswer) {
		for(int i=0;i<JsonRead.questionWithData.size();i++)
		{
			QuestionWithData current = JsonRead.questionWithData.get(i);
			if(current.getQuestion().equals(question))
			{
				

				HashMap<String, Integer> list =  new HashMap<String, Integer>();
				HashMap<String, Integer> listClone =  ( HashMap<String, Integer>) current.getAnswerData();

					Iterator it = (listClone).entrySet().iterator();

					while (it.hasNext()) {

						Map.Entry pair = (Map.Entry) it.next();
						String ans = pair.getKey().toString();
						int num = Integer.parseInt(pair.getValue().toString().replace(".0", ""));

						list.put(ans,num);


						if(ans.equals(selectedAnswer))
							list.put(ans, ++num);

						

						
						it.remove();
					}

					current.setAnswerData(list);
					JsonRead.questionWithData.set(i, current);
					//System.out.println(	JsonRead.questionWithData.get(i).toString());
					
					WriteQuestionDataToJSon(JsonRead.questionWithData);
					return;
			
			}
			
			
		}

		
	}



	public boolean updateQuestions(String questionToEdit, QuestionInJson q) {

		int index = -1;
		System.out.println(" JsonRead.questionsAndAnswers.size() " + JsonRead.questionsAndAnswers.size());
		JsonRead JR = new JsonRead();
		JsonRead.questionsAndAnswers = JR.readQuestionsFromJson();
		for (int i = 0; i < JsonRead.questionsAndAnswers.size(); i++) {
			String question = JsonRead.questionsAndAnswers.get(i).getQuestion();
			System.out.print(" q " + q);

			if (questionToEdit.equals(question)) {
				index = i;
				System.out.print(" q " + index);

			}
		}
		
		JsonRead.questionsAndAnswers.set(index, q);
		
		
		
		questions qu = new questions();
		qu .questions = JsonRead.questionsAndAnswers;
		return writeQuestionToJson(qu);
	}

}
