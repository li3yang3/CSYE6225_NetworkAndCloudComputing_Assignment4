package creator;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnnouncementCreator {
	
	private String announcementId;
	private String content;
	private String date;
	
	public AnnouncementCreator() {
		super();
	}
	
	@JsonCreator
	public AnnouncementCreator(
			@JsonProperty("announcementId") String announcementId,
			@JsonProperty("content") String content,
			@JsonProperty("date") String date) {
		this.announcementId = announcementId;
		this.content = content;
		this.date = date;
	}
	
	@JsonProperty("announcementId")
	public String getAnnouncementId() {
		return announcementId;
	}
	
	@JsonProperty("content")
	public String getContent() {
		return content;
	}
	
	@JsonProperty("date")
	public String getDate() {
		return date;
	}

}
