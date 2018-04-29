package creator;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import courseservice.studentDAO.Board;
import courseservice.studentDAO.Lecture;
import courseservice.studentDAO.Roster;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseCreator {
	private String courseName;
	private List<Lecture> lectureList;
	private Board board;
	private Roster roster;
	private List<String> studentIds;
	private String professorId;
	
	public CourseCreator() {
		super();
	}
	 
	    @JsonCreator
	    public CourseCreator(
	      @JsonProperty("courseName") String name,
	      @JsonProperty("lectureList") List<Lecture> lectureList,
	      @JsonProperty("board") Board board,
	      @JsonProperty("roster") Roster roster,
	      @JsonProperty("studentIds") List<String> studentIds,
	      @JsonProperty("professorId") String professorId) {
	        this.courseName = name;
	        this.lectureList = lectureList;
	        this.board = board;
	        this.roster = roster;     
	        this.studentIds = studentIds;
	        this.professorId = professorId;
	    }
	    
	    @JsonProperty("courseName")
		public String getCourseName() {
			return courseName;
		}
		
		@JsonProperty("lectureList")
		public List<Lecture> getLectureList() {
			return lectureList;
		}
		
		@JsonProperty("board")
		public Board getBoard() {
			return board;
		}
		
		@JsonProperty("roster")
		public Roster getRoster() {
			return roster;
		}
		
		@JsonProperty("studentIds")
		public List<String> getStudentIds() {
			return studentIds;
		}
		
		@JsonProperty("professorId")
		public String getProfessorId() {
			return professorId;
		}
}

