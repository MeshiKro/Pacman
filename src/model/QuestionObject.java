package model;
import java.awt.*;

import org.json.JSONArray;

import com.google.gson.JsonArray;




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
    

    
    
    //Constructor - Need to Add Assets
    /*public QuestionObject(int id, Point position, LEVEL diff, String qBody, JSONArray[] answers) {
        this.qUestionID = id;
        this.position = position;
        this.lvl = diff;
        this.questionString = qBody;
        this.answers = answers;
    }*/

    
    //This Constructor Fit The JSON format//
    public QuestionObject(String asString, JsonArray jsonArray, int asInt, int asInt2, String asString2) {
    	
        this.qUestionID = asInt;
        this.lvl = LEVEL(asInt2);
        this.questionString = asString;
    }
    
	//helpFunction//
	private LEVEL LEVEL(int asInt2) {
		
		if(asInt2==1)
			return LEVEL.EASY;
		if(asInt2==2)
			return LEVEL.MEDIUM;
		
		return LEVEL.HARD;
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
