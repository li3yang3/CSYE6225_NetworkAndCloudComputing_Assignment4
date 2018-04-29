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
import courseservice.studentDAO.Professor;
import creator.AnnouncementCreator;
import creator.ProfessorCreator;

@Path("professors")
public class ProfessorResource {
	
	private static AmazonDynamoDB client = AmazonDynamoDBClientBuilder
			.standard()
			.withRegion("us-west-2")
			.build();
	private DynamoDBMapper mapper = new DynamoDBMapper(client);
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Professor> getAllCourses() {
		return mapper.scan(Professor.class, new DynamoDBScanExpression());
	}
	
    @GET
    @Path("{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
    public Professor getCourse(@PathParam("professorId") String id) {
    		Professor professor = new Professor();
    		professor.setProfessorId(id);
 
        return mapper.load(professor);
    }
    
    @POST
    @Path("{professorId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String addCourse(@PathParam("professorId") String id,
    		ProfessorCreator newProfessor) {
    	
    		Professor professor = new Professor();
		professor.setProfessorId(id);
		professor.setProfessorName(newProfessor.getProfessorName());
		professor.setTaughtCourse(newProfessor.getTaughtCourse());
		
        mapper.save(professor);
  		return "Professor: " + id +  " Added Successfully";
    }
    
    @PUT
    @Path("{professorId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String updateCourse(@PathParam("professorId") String id,
    		ProfessorCreator newProfessor) {
    	
    		Professor professor = new Professor();
		professor.setProfessorId(id);
		professor.setProfessorName(newProfessor.getProfessorName());
		professor.setTaughtCourse(newProfessor.getTaughtCourse());
		
	    mapper.save(professor);
    
		return "Professor: " + id +  " Added Successfully";
    }
  
    @DELETE
    @Path("{professorId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteCourse(@PathParam("professorId") String id) {
 		Professor professor = new Professor();
		professor.setProfessorId(id);
      	mapper.delete(professor);
      	
  		return Response.ok( "Professor: " + id +  " Deleted Successfully").build();
    }
}
