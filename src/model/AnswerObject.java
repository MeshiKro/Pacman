package model;




// This class Describes Our answer Object that will be Located as A question Mark On the Game Screen//
public class AnswerObject {
	
	
    public String answer;
    public boolean isCorrect;
    
    
    public AnswerObject(String answerString, boolean isCorrect) {
        this.answer = answerString;
        this.isCorrect = isCorrect;
    }

    public String getaBody() {
        return answer;
    }

    public void setaBody(String aBody) {
        this.answer = aBody;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

	@Override
	public String toString() {
		return "[answerString=" + answer + ", isCorrect=" + isCorrect + "]\n";
	}
    
}
