package model;




// This class Describes Our answer Object that will be Located as A question Mark On the Game Screen//
public class AnswerObject {
	
	
    public String answerString;
    public boolean isCorrect;
    
    
    public AnswerObject(String answerString, boolean isCorrect) {
        this.answerString = answerString;
        this.isCorrect = isCorrect;
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

	@Override
	public String toString() {
		return "[answerString=" + answerString + ", isCorrect=" + isCorrect + "]\n";
	}
    
}
