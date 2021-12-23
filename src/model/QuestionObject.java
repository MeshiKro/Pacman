package model;

import java.awt.*;
import java.io.Serializable;
import java.util.Arrays;


//this is QuestionObject class, ea question we will read from JarFile//
public class QuestionObject implements Serializable {

	/**
	 * 
	 */

	public enum LEVEL {
		EASY, MEDIUM, HARD
	}
	
	private static final long serialVersionUID = 1L;
	public String question;
	public AnswerObject[] answers;
	public LEVEL level;
	public String team;
	
	public Point position;


	public QuestionObject(String question, String level, String team, AnswerObject[] answersList) {
		this.question = question;
		this.level = LEVEL(Integer.parseInt(level));
		this.team = team;
		this.answers = answersList;

	}

	// help Function//
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
		return level;
	}

	public void setDiff(LEVEL diff) {
		this.level = diff;
	}

	public String getqBody() {
		return question;
	}

	public void setqBody(String qBody) {
		this.question = qBody;
	}

	public AnswerObject[] getAnswers() {
		return answers;
	}

	public void setAnswers(AnswerObject[] answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return " [questionString=" + question + ", position=" + position + ", lvl=" + level
				+ ", teamName=" + team + ",\n answers=" + Arrays.toString(answers) + "]\n";
	}


}