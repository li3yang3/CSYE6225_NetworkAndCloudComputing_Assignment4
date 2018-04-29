package stepFunction;

import java.util.Map;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.stepfunctions.AWSStepFunctions;
import com.amazonaws.services.stepfunctions.AWSStepFunctionsClient;

public class execution implements RequestHandler<Object, String> {

    @Override
    public String handleRequest(Object input, Context context) {
        BasicAWSCredentials credential = new BasicAWSCredentials("access_key"
    			, "secret_key_id");
        AWSStepFunctions stepFunctions = new AWSStepFunctionsClient(credential);
        stepFunctions.setEndpoint("states.us-east-2.amazonaws.com");
        
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.convertValue(input, Map.class);
        DescribeExecutionRequest exectionRequest = new DescribeExecutionRequest();
      
        exectionRequest.setExecutionArn(map.get("executionArn").toString());
        DescribeExecutionResult result = stepFunctions.describeExecution(exectionRequest);
        
        return result.getOutput();
    }

}