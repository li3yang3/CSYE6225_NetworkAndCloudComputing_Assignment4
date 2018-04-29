package courseservice.studentDAO;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;

import courseservice.studentDAO.dynamoDBconverter.BoardTypeConverter;
import courseservice.studentDAO.dynamoDBconverter.DynamoTypeConverter;
import courseservice.studentDAO.dynamoDBconverter.RosterTypeConverter;

@DynamoDBTable(tableName="Courses")
public class Course {
	private String courseId;
	private String courseName;
	private List<Lecture> lectureList;
	private Board board;
	private Roster roster;
	private List<String> studentIds;
	private String professorId;
	

	@DynamoDBHashKey(attributeName = "CourseId")
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	@DynamoDBAttribute(attributeName="StudentId")
	public List<String> getStudentIds() {
		return studentIds;
	}
	public void setStudentIds(List<String> studentIds) {
		this.studentIds = studentIds;
	}
	
	@DynamoDBAttribute(attributeName="CourseName")
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	@DynamoDBTypeConverted(converter = DynamoTypeConverter.class)
	@DynamoDBAttribute(attributeName="LectureList")
	public List<Lecture> getLectureList() {
		return lectureList;
	}

	public void setLectureList(List<Lecture> lectureList) {
		this.lectureList = lectureList;
	}
	
	@DynamoDBTypeConverted(converter = BoardTypeConverter.class)
	@DynamoDBAttribute(attributeName="Board")
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	
	@DynamoDBTypeConverted(converter = RosterTypeConverter.class)
	@DynamoDBAttribute(attributeName="Roster")
	public Roster getRoster() {
		return roster;
	}
	public void setRoster(Roster roster) {
		this.roster = roster;
	}

	@DynamoDBAttribute(attributeName="ProfessorId")
	public String getProfessorId() {
		return professorId;
	}
	public void setProfessorId(String professorId) {
		this.professorId = professorId;
	}
}
