package courseservice;

import java.util.ArrayList;
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

import courseservice.studentDAO.Course;
import creator.CourseCreator;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("classes")
public class ClassResource {

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
	public List<Course> getAllCourses() {
		Course course = new Course();
		course.setCourseId("fffff");
		List<Course> res = new ArrayList<>();
		res.add(course);
		return res;
//		return mapper.scan(Course.class, new DynamoDBScanExpression());
	}
	
    @GET
    @Path("{courseId}")
	@Produces(MediaType.APPLICATION_JSON)
    public Course getCourse(@PathParam("courseId") String id) {
    		Course course = new Course();
      	course.setCourseId(id);
 
       return mapper.load(course);
    }
    
    @POST
    @Path("{courseId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String addCourse(@PathParam("courseId") String id,
    			CourseCreator newcourse) {
    	
      	Course course = new Course();
      	course.setCourseId(id);
      	course.setCourseName(newcourse.getCourseName());
      	course.setStudentIds(newcourse.getStudentIds());
      	course.setBoard(newcourse.getBoard());
      	course.setLectureList(newcourse.getLectureList());
      	course.setRoster(newcourse.getRoster());
  	
       
        mapper.save(course);
        
  		return "Course: " + id +  " Added Successfully";
    }
    
    @PUT
    @Path("{courseId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String updateCourse(@PathParam("courseId") String id,
    			CourseCreator newcourse) {
    	
      	Course course = new Course();
      	course.setCourseId(id);
      	course.setCourseName(newcourse.getCourseName());
      	course.setStudentIds(newcourse.getStudentIds());
      	course.setBoard(newcourse.getBoard());
      	course.setLectureList(newcourse.getLectureList());
      	course.setRoster(newcourse.getRoster());
  	
        mapper.save(course);

  		return "Course: " + id +  " Updated Successfully";
    }
  
    @DELETE
    @Path("{courseId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteCourse(@PathParam("courseId") String id) {
      	Course course = new Course();
      	course.setCourseId(id);
      	
      	mapper.delete(course);
  		return Response.ok( "Course: " + id +  " Deleted Successfully").build();
    }
}
