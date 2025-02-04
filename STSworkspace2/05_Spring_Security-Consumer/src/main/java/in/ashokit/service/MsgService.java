package in.ashokit.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MsgService {

	//String provUrl = "http://localhost:8080/loginf";// Previous url for provider i.e Project4 
	String provUrl = "http://localhost:8080/recieve";// Proj 10 must be running
	RestTemplate rt = new RestTemplate();

	public void getMsg() {
		
		/*String username = "Aman";
        String password = "Aman@110193";
        String credentials = username + ":" + password;
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
        
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Basic "+encodedCredentials);*/ //Its old way of doing Encoding
		HttpHeaders headers= new HttpHeaders();
		headers.setBasicAuth("Aman","aman@110193");// it will do the encoding in the background
		headers.setContentType(MediaType.APPLICATION_JSON);
		String jsonBody = "{"
                + "\"name\":\"Raghav\","
                + "\"city\":\"Patiala\","
                + "\"salary\":65000"
                + "}";
		  HttpEntity<String> entity = new HttpEntity<>(jsonBody,headers);// I could have send an Object to using ObjectMappper class
		  						//Its a best practice to create a STO class and send its object which actually matches the structure of our Endpoint 
		  						//where we are sending the Request
		  
		
		  ResponseEntity<String> response = rt.exchange(
		            provUrl, 
		            HttpMethod.POST, 
		            entity, 
		            String.class
		        );
		  System.out.println(response.getBody());

	}

}
