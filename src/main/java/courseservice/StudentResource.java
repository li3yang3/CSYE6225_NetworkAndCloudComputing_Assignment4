package courseservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import courseservice.studentDAO.Board;
import courseservice.studentDAO.Course;
import courseservice.studentDAO.Lecture;
import courseservice.studentDAO.Roster;
import courseservice.studentDAO.Student;
import creator.StudentCreator;
/**
 * Root resource (exposed at "myresource" path)
 */
@Path("students")
public class StudentResource {

	private static AmazonDynamoDB client = AmazonDynamoDBClientBuilder
			.standard()
			.withRegion("us-west-2")
			.build();
	private DynamoDBMapper mapper = new DynamoDBMapper(client);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getAllStudents() {
		return mapper.scan(Student.class, new DynamoDBScanExpression());
	}
	
    @GET
    @Path("{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
    public Student getStudent(@PathParam("studentId") String id) {
		Student student = new Student();
      	student.setStudentId(id);
 
       return mapper.load(student);
    }

    
    @POST
    @Path("{studentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String addStudent(@PathParam("studentId") String id,
    			StudentCreator newstudent) {
    	
     	Student student = new Student();
     	student.setStudentId(id);
     	student.setStudentName(newstudent.getStudentName());
     	student.setImageUrl(newstudent.getImageUrl());
     	student.setEnrolledCourse(newstudent.getEnrolledCourse());
     	student.setEnrolledProgram(newstudent.getEnrolledProgram());
      	
        mapper.save(student);
  		return "Student: " + id +  " Added Successfully";
    }
    
    @PUT
    @Path("{studentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String updateStudent(@PathParam("studentId") String id,
    				StudentCreator newstudent) {
     	Student student = new Student();
     	student.setStudentId(id);
     	student.setStudentName(newstudent.getStudentName());
     	student.setImageUrl(newstudent.getImageUrl());
     	student.setEnrolledCourse(newstudent.getEnrolledCourse());
     	student.setEnrolledProgram(newstudent.getEnrolledProgram());
        
        mapper.save(student);
  		return "Student: " + id +  " Added Successfully";
    }
  
    @DELETE
    @Path("{studentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteStudent(@PathParam("studentId") String id) {
    			Student student = new Student();
    			student.setStudentId(id);
    			mapper.delete(student);
  			return "Student: " + id +  " Deleted Successfully";
    }
}
