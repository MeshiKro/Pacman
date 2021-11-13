package model;

import java.awt.*;




//this is QuestionObject class, ea question we will read from JarFile//
public class QuestionObject {
	
	
	   public enum LEVEL {
	        EASY, MEDIUM, HARD
	    }
	   
	//Fields As in the JSON Format//
    public int qUestionID;
    public int CorrectAnswer;
    public Point position;
    public LEVEL lvl;
    public String questionString;
    public String teamName;
    public AnswerObject[] answers;
    

    
    
    //Constructor - Need to Add Assets//
    public QuestionObject(int id, Point position, LEVEL diff, String qBody, AnswerObject[] answers) {
        this.qUestionID = id;
        this.position = position;
        this.lvl = diff;
        this.questionString = qBody;
        this.answers = answers;
    }

    public int getId() {
        return qUestionID;
    }

    public void setId(int id) {
        this.qUestionID = id;
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
}
