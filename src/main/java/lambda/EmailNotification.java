package lambda;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent.DynamodbStreamRecord;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;

public class EmailNotification implements RequestHandler<DynamodbEvent, String> {
	private AmazonSNS client = AmazonSNSClientBuilder.standard()
			.withRegion(Regions.US_WEST_2).build();
	private static String PROFESSOR_NOTIFICATION = "arn:aws:sns:us-west-2:765234098147:ProfessorNotification";
	
	public String handleRequest(DynamodbEvent input, Context context) {
    	
    	// Read DDB records
    	for(DynamodbStreamRecord record: input.getRecords()) {
    		if(record == null) {
    			continue;
    		}
    		
    		//check for location to be Seattle
    		// Send Notification
    		
    	}
	    	// Business Logic to decide to send a notification
			String inputString = String.valueOf(input);
			context.getLogger().log("intput:" + input);
	    	
	    	// Send Notification
	    	
	    	//sendEmail
    		String output = "Hello Everyone! " ;
    		String outputBody = output + "The professor has just posted a notification";
    		sendEmailNotification(output, outputBody);
    		return output;
    }
    
    private void sendEmailNotification(final String subject, final String message) {
    		PublishRequest publishRequest = new PublishRequest(PROFESSOR_NOTIFICATION, message);
    		client.publish(publishRequest);	
    } 
}
