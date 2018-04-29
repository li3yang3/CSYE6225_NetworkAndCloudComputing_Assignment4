package creator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProfessorCreator {
	private String professorId;
	private String professorName;
	private String taughtCourse;
	
	public ProfessorCreator() {
		super();
	}
	
	@JsonCreator
	public ProfessorCreator(
			@JsonProperty("professorId") String professorId,
			@JsonProperty("professorName") String professorName,
			@JsonProperty("taughtCourse") String taughtCourse) {
		this.professorId = professorId;
		this.professorName = professorName;
		this.taughtCourse = taughtCourse;
	}
	
	@JsonProperty("professorId")
	public String getProfessorId() {
		return professorId;
	}
	
	@JsonProperty("professorName")
	public String getProfessorName() {
		return professorName;
	}
	
	@JsonProperty("taughtCourse")
	public String getTaughtCourse() {
		return taughtCourse;
	}

}
