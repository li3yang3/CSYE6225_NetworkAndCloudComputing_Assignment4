package courseservice.studentDAO.dynamoDBconverter;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import courseservice.studentDAO.Lecture;

public class DynamoTypeConverter implements DynamoDBTypeConverter<String, List<Lecture>>{

	    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
		private static final TypeReference<List<Lecture>> TYPE_REFERENCE = new TypeReference<List<Lecture>>(){};
		
		@Override
	    public String convert(List<Lecture> lecturelist) {
	               try {
	            return OBJECT_MAPPER.writeValueAsString(lecturelist);
	        } catch (Exception e) {
	        		return null;
	        }
	    }

	    @Override
	    public List<Lecture> unconvert(String s) {
	        try {
	           return OBJECT_MAPPER.readValue(s, TYPE_REFERENCE);
	        } catch (Exception e) {
	        		return null;
	        }
	    }
	}

