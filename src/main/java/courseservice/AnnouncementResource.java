package courseservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

import courseservice.studentDAO.Announcement;
import courseservice.studentDAO.Course;
import creator.AnnouncementCreator;
import creator.CourseCreator;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("notification")
public class AnnouncementResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
	private static AmazonDynamoDB client = AmazonDynamoDBClientBuilder
			.standard()
			.withRegion("us-west-2")
			.build();
	private DynamoDBMapper mapper = new DynamoDBMapper(client);
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Announcement> getAllCourses() {
		return mapper.scan(Announcement.class, new DynamoDBScanExpression());
	}
	
    @GET
    @Path("{announcementId}")
	@Produces(MediaType.APPLICATION_JSON)
    public Announcement getCourse(@PathParam("announcementId") String id) {
    		Announcement announcement = new Announcement();
    		announcement.setAnnouncementId(id);;
 
        return mapper.load(announcement);
    }
    
    @POST
    @Path("{announcementId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String addCourse(@PathParam("announcementId") String id,
    		AnnouncementCreator newAnnouncement) {
    	
    		Announcement announcement = new Announcement();
		announcement.setAnnouncementId(id);
		announcement.setContent(newAnnouncement.getContent());
		announcement.setContent(newAnnouncement.getDate());
  	
        mapper.save(announcement);
        
  		return "Announcement: " + id +  " Added Successfully";
    }
    
    @PUT
    @Path("{announcementId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String updateCourse(@PathParam("announcementId") String id,
    		AnnouncementCreator newAnnouncement) {
    	
		Announcement announcement = new Announcement();
		announcement.setAnnouncementId(id);
		announcement.setContent(newAnnouncement.getContent());
		announcement.setContent(newAnnouncement.getDate());
		
	    mapper.save(announcement);
    
		return "Announcement: " + id +  " Added Successfully";
    }
  
    @DELETE
    @Path("{announcementId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteCourse(@PathParam("announcementId") String id) {
		Announcement announcement = new Announcement();
		announcement.setAnnouncementId(id);
      	mapper.delete(announcement);
      	
  		return Response.ok( "Announcement: " + id +  " Deleted Successfully").build();
    }
}
