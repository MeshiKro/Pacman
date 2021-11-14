package misc;

import java.util.ArrayList;

import com.google.gson.Gson;

import model.QuestionObject;

public class QuestionJsonWriterEx {

	
	public void serialazation(ArrayList<QuestionObject> Questions) {
		
	
        Gson gson = new Gson();
		String output = gson.toJson(Questions);
		
		System.out.println(output);
		
		return;
	}
	
	
	
}



	
	
	
	
	

