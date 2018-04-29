package courseservice.studentDAO;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
@DynamoDBTable(tableName="Announcement")
public class Announcement {
	private String announcementId;
	private String content;
	private String date;
	
	@DynamoDBHashKey(attributeName = "announcementId")
	public String getAnnouncementId() {
		return announcementId;
	}
	public void setAnnouncementId(String announcementId) {
		this.announcementId = announcementId;
	}
	
	@DynamoDBAttribute(attributeName = "Content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@DynamoDBAttribute(attributeName="Date")
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
