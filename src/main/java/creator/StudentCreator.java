package creator;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentCreator {
	
	private String studentName;
	private String imageUrl;
	private List<String> enrolledCourse;
	private String enrolledProgram;
	private String emaill;
	
	public StudentCreator() {
		super();
	}
	
	@JsonCreator
	public StudentCreator(
			@JsonProperty("studentName") String studentName,
			@JsonProperty("imageUrl") String imageUrl,
			@JsonProperty("enrolledCourse") List<String> enrolledCourse,
			@JsonProperty("enrolledProgram") String enrolledProgram,
			@JsonProperty("email") String email) {
		this.studentName = studentName;
		this.enrolledCourse = enrolledCourse;
		this.enrolledCourse = enrolledCourse;
		this.enrolledProgram = enrolledProgram;
		this.emaill = email;
	}
	
	@JsonProperty("studentName")
	public String getStudentName() {
		return studentName;
	}
	
	@JsonProperty("imageUrl")
	public String getImageUrl() {
		return imageUrl;
	}
	
	@JsonProperty("enrolledCourse")
	public List<String> getEnrolledCourse() {
		return enrolledCourse;
	}
	
	@JsonProperty("enrolledProgram")
	public String getEnrolledProgram() {
		return enrolledProgram;
	}
}