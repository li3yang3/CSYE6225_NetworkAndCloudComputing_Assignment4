package stepFunction;

import java.util.Map;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import courseservice.studentDAO.Course;

public class payment implements RequestHandler<Object, Object> {
	private static AmazonDynamoDB client = AmazonDynamoDBClientBuilder
			.standard()
			.withRegion("us-west-2")
			.build();
	private DynamoDBMapper mapper = new DynamoDBMapper(client);
		
	    @Override
	    public Object handleRequest(Object input, Context context) {
	        context.getLogger().log("Input: " + input);
	        ObjectMapper objectMapper = new ObjectMapper();
	        Map<String, Object> map = objectMapper.convertValue(input, Map.class);
	        Object studentObj = map.get("Student");
	        Map<String, Object> studentMap = objectMapper.convertValue(studentObj, Map.class);
	        String courseId = map.get("CourseId").toString();
	        String studentId = studentMap.get("id").toString();
	        Course course = mapper.load(Course.class, courseId);
	        
	        if(course.getStudentIds().contains(studentId)) {
	        		input = "Student Already Registered!";
	        }
	        
	        return input;
	    }
}
