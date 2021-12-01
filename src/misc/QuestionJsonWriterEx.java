package misc;

import java.io.FileWriter;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;

import model.AnswerObject;
import model.QuestionObject;
import model.questions;

public class QuestionJsonWriterEx {

	
	public boolean serialazation(ArrayList<QuestionObject> arg) throws Exception {
		
		questions helper = new questions();
		
		 AnswerObject[] answers = new AnswerObject[4];
		 
		answers[0]= new AnswerObject("111", false);
		answers[1]= new AnswerObject("222", false);
		answers[2]= new AnswerObject("333", false);
		answers[3]= new AnswerObject("444", true);

		
		QuestionObject Question = new QuestionObject("Hello How Are you?", "3", "Scorpions",answers );
		
		helper.questions.addAll(arg);
		helper.questions.add(Question);

		
		
		Gson gson = new Gson();
        
		FileWriter writer = new FileWriter("QuestionBank.json");
        try {
			gson.toJson(helper,writer);
			
		} catch (JsonIOException e) {
			e.printStackTrace();
			return false;
		}
        
        
        writer.flush(); //flush data to file   <---
        writer.close(); //close write 
        
		
		return true;
	}
	
	
	
}

