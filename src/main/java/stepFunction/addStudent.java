package stepFunction;

import java.util.Map;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import courseservice.studentDAO.Student;

public class addStudent implements RequestHandler<Object, Object> {
	private static AmazonDynamoDB client = AmazonDynamoDBClientBuilder
			.standard()
			.withRegion("us-west-2")
			.build();
	private DynamoDBMapper mapper = new DynamoDBMapper(client);

	  @Override
	    public Object handleRequest(Object input, Context message) {
		    message.getLogger().log("Input: " + input);
	        ObjectMapper objectMapper = new ObjectMapper();
	        Map<String, Object> map = objectMapper.convertValue(input, Map.class);
	        Student student = new Student();
	        Object studentObj = map.get("Student");
	        Map<String, Object> studentMap = objectMapper.convertValue(studentObj, Map.class);
	        student.setStudentId(studentMap.get("id").toString());
	        student.setStudentName(studentMap.get("studentName").toString());
	        student.setEmaill(studentMap.get("email").toString());
	        mapper.save(student);
	        return input;

	  }
}
