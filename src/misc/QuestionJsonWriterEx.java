package misc;
import model.QuestionObject;

public class QuestionJsonWriterEx {


    public static void main(String[] args) {

        var user = new StringBuilder();

        var writer = new JSONWriter(user);

        writer.QuestionObject();
        writer.key("question").value();
        writer.key("answers").value("[\r\n"
        		+ "                \"answer1\",\r\n"
        		+ "                \"answer2\",\r\n"
        		+ "                \"answer3\",\r\n"
        		+ "                \"answer4\"\r\n"
        		+ "            ]");
        
        writer.key("correct_ans").value(2);
        writer.key("level").value(1);
        writer.key("team").value("animal");
  
        writer.endArray();

        writer.endObject();

        System.out.println(QuestionObject.class);
    }
	
	
	
	
	
	
	
	
}
