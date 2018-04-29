package courseservice.studentDAO;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
@DynamoDBTable(tableName="Students")
public class Student {
	private String studentName;
	private String studentId;
	private String imageUrl;
	private List<String> enrolledCourse;
	private String enrolledProgram;
	private String emaill;
	
	@DynamoDBAttribute(attributeName="StudentName")
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	@DynamoDBHashKey(attributeName = "StudentId")
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	@DynamoDBAttribute(attributeName="ImageUrl")
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	@DynamoDBAttribute(attributeName="EnrolledCourse")
	public List<String> getEnrolledCourse() {
		return enrolledCourse;
	}
	public void setEnrolledCourse(List<String> enrolledCourse) {
		this.enrolledCourse = enrolledCourse;
	}
	@DynamoDBAttribute(attributeName="EnrolledProgram")
	public String getEnrolledProgram() {
		return enrolledProgram;
	}
	public void setEnrolledProgram(String enrolledProgram) {
		this.enrolledProgram = enrolledProgram;
	}
	@DynamoDBAttribute(attributeName="Email")
	public String getEmaill() {
		return emaill;
	}
	public void setEmaill(String emaill) {
		this.emaill = emaill;
	}
}
 