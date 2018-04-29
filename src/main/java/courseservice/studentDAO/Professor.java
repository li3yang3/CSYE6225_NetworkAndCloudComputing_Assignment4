package courseservice.studentDAO;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="Professor")
public class Professor {
	private String professorId;
	private String professorName;
	private String taughtCourse;
	
	@DynamoDBHashKey(attributeName = "ProfessorId")
	public String getProfessorId() {
		return professorId;
	}
	public void setProfessorId(String professorId) {
		this.professorId = professorId;
	}
	
	@DynamoDBAttribute(attributeName = "ProfessorName")
	public String getProfessorName() {
		return professorName;
	}
	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}
	
	@DynamoDBAttribute(attributeName = "TaughtCourse")
	public String getTaughtCourse() {
		return taughtCourse;
	}
	public void setTaughtCourse(String taughtCourse) {
		this.taughtCourse = taughtCourse;
	}

}
