package model;

import java.awt.*;
import java.util.Arrays;

import org.json.JSONArray;

import com.google.gson.JsonArray;

//this is QuestionObject class, ea question we will read from JarFile//
public class QuestionObject {

	public enum LEVEL {
		EASY, MEDIUM, HARD
	}

	public String questionString;
	public Point position;
	public LEVEL lvl;
	public String teamName;
	public AnswerObject[] answers;

	public QuestionObject(String question, String level, String team, AnswerObject[] answersList) {
		this.questionString = question;
		this.lvl = LEVEL(Integer.parseInt(level));
		this.teamName = team;
		this.answers = answersList;

	}

	// helpFunction//
	private LEVEL LEVEL(int asInt2) {

		if (asInt2 == 1)
			return LEVEL.EASY;
		if (asInt2 == 2)
			return LEVEL.MEDIUM;

		return LEVEL.HARD;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public LEVEL getDiff() {
		return lvl;
	}

	public void setDiff(LEVEL diff) {
		this.lvl = diff;
	}

	public String getqBody() {
		return questionString;
	}

	public void setqBody(String qBody) {
		this.questionString = qBody;
	}

	public AnswerObject[] getAnswers() {
		return answers;
	}

	public void setAnswers(AnswerObject[] answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return " [questionString=" + questionString + ", position=" + position + ", lvl=" + lvl
				+ ", teamName=" + teamName + ",\n answers=" + Arrays.toString(answers) + "]\n";
	}


}
