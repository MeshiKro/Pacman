package model;

import java.util.HashMap;

public class QuestionWithData {

	String question;
	String correct_ans;
	HashMap<String, Integer> answerData;

	public QuestionWithData(String question, String correct_ans, String[] answer) {
		super();
		this.question = question;
		this.correct_ans = correct_ans;
		this.answerData = new HashMap<String, Integer>();

		for (int i = 0; i < 4; i++) {
			answerData.put(answer[i], 0);
		}
	}


	public QuestionWithData(String question, String correct_ans,HashMap<String, Integer> answer) {
		super();
		this.question = question;
		this.correct_ans = correct_ans;
		this.answerData = answer;

		
	}
	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getCorrect_ans() {
		return correct_ans;
	}

	public void setCorrect_ans(String correct_ans) {
		this.correct_ans = correct_ans;
	}

	public HashMap<String, Integer> getAnswerData() {
		return answerData;
	}

	public void setAnswerData(HashMap<String, Integer> answerData) {
		this.answerData = answerData;
	}


	@Override
	public String toString() {
		return "QuestionWithData [question=" + question + ", correct_ans=" + correct_ans + ", answerData=" + answerData
				+ "]";
	}
	
	

}
