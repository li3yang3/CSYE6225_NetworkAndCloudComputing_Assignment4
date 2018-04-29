package stepFunction;

import java.util.Map;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent.DynamodbStreamRecord;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;

public class lambdaFunction implements RequestHandler<DynamodbEvent, String> {
	private AmazonSNS client = AmazonSNSClientBuilder.standard()
			.withRegion(Regions.US_WEST_2).build();
	private static String PROFESSOR_NOTIFICATION = "arn:aws:sns:us-west-2:765234098147:ProfessorNotification";
	
	public String handleRequest(DynamodbEvent input, Context context) {
    	
		context.getLogger().log("intput:" + input);
    	// Read DDB records
    	for(DynamodbStreamRecord record: input.getRecords()) {
    		if(record == null) {
    			continue;
    		}
    		Map<String, AttributeValue> map = record.getDynamodb().getNewImage();
    		System.out.println(record.toString());
    		String courseId = map.get("CourseId").getS();

    		String output = "Hello! " ;
    		String outputBody = output + courseId + " registered!";
    		sendEmailNotification(output, outputBody);
    	}
    	return input.toString();
	
    }
    
    private void sendEmailNotification(final String subject, final String message) {
    		PublishRequest publishRequest = new PublishRequest(PROFESSOR_NOTIFICATION, message);
    		client.publish(publishRequest);	
    } 
}
