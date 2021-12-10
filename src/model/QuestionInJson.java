package model;

import java.util.Arrays;

public class QuestionInJson {

	String question;
	String [] answers;
	String correct_ans;
	String level;
	String team;
	public QuestionInJson(String question, String[] answers, String correct_ans, String level, String team) {
		super();
		this.question = question;
		this.answers = answers;
		this.correct_ans = correct_ans;
		this.level = level;
		this.team = team;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String[] getAnswers() {
		return answers;
	}
	public void setAnswers(String[] answers) {
		this.answers = answers;
	}
	public String getCorrect_ans() {
		return correct_ans;
	}
	public void setCorrect_ans(String correct_ans) {
		this.correct_ans = correct_ans;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	@Override
	public String toString() {
		return "QuestionInJson [question=" + question + ", answers=" + Arrays.toString(answers) + ", correct_ans="
				+ correct_ans + ", level=" + level + ", team=" + team + "]";
	}

	
}
