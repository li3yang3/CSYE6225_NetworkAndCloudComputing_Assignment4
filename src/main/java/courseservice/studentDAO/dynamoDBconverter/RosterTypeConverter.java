package courseservice.studentDAO.dynamoDBconverter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import courseservice.studentDAO.Roster;

public class RosterTypeConverter implements DynamoDBTypeConverter<String, Roster>{
	

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	private static final TypeReference<Roster> TYPE_REFERENCE = new TypeReference<Roster>(){};
	
	@Override
    public String convert(Roster roster) {
               try {
            return OBJECT_MAPPER.writeValueAsString(roster);
        } catch (Exception e) {
        		return null;
        }
    }

    @Override
    public Roster unconvert(String s) {
        try {
           return OBJECT_MAPPER.readValue(s, TYPE_REFERENCE);
        } catch (Exception e) {
        		return null;
        }
    }
}

