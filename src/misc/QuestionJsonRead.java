package misc;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.sun.glass.ui.CommonDialogs.Type;

import model.QuestionObject;

public class QuestionJsonRead {

	
	
	public static ArrayList<QuestionObject> deserializedQuestions;
	JsonDeserializer<QuestionObject> deserializer = new JsonDeserializer<QuestionObject>() {  
		
		
		
		
		
		
		
		
	    @SuppressWarnings("unused")
		public QuestionObject deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
	        JsonObject jsonObject = json.getAsJsonObject();

	        
	   
	        return new QuestionObject(
	        		
	        		//Same As the JSON format//
	        		jsonObject.get("question").getAsString(),
	                jsonObject.get("answers").getAsJsonArray(),
	                jsonObject.get("correct_ans").getAsInt(),
	                jsonObject.get("level").getAsInt(),
	                jsonObject.get("team").getAsString());
	    }

		@Override
		public QuestionObject deserialize(JsonElement arg0, java.lang.reflect.Type arg1,
				JsonDeserializationContext arg2) throws JsonParseException {
			// TODO Auto-generated method stub
			return null;
		}
	};
	
	
	
}
