package courseservice.studentDAO.dynamoDBconverter;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import courseservice.studentDAO.Board;

public class BoardTypeConverter implements DynamoDBTypeConverter<String, Board>{
	

	    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
		private static final TypeReference<Board> TYPE_REFERENCE = new TypeReference<Board>(){};
		
		@Override
	    public String convert(Board board) {
	               try {
	            return OBJECT_MAPPER.writeValueAsString(board);
	        } catch (Exception e) {
	        		return null;
	        }
	    }

	    @Override
	    public Board unconvert(String s) {
	        try {
	           return OBJECT_MAPPER.readValue(s, TYPE_REFERENCE);
	        } catch (Exception e) {
	        		return null;
	        }
	    }
	}