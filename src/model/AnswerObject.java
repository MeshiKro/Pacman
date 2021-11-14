package model;




// This class Describes Our answer Object that will be Located as A question Mark On the Game Screen//
public class AnswerObject {
	
	
    public int answerID;
    public String answerString;
    public boolean isCorrect;
    
    
    public AnswerObject(int answerID, String answerString, boolean isCorrect) {
        this.answerID = answerID;
        this.answerString = answerString;
        this.isCorrect = isCorrect;
    }

    public int getId() {
        return answerID;
    }

    public void setId(int id) {
        this.answerID = id;
    }

    public String getaBody() {
        return answerString;
    }

    public void setaBody(String aBody) {
        this.answerString = aBody;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
