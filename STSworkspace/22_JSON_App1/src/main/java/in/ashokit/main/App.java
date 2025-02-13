package in.ashokit.main;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.ashokit.binding.Customer;

public class App {
	public static void main(String[] args) throws Exception{
		App a = new App();
		//a.convertJavaToJson();
		a.convertJsonToJava();
	}
	
	public void convertJavaToJson() throws Exception {
		File f = new File("customer.json");
		Customer cobj = new Customer();
		cobj.setId(101);
		cobj.setName("Meena Devi");
		cobj.setPhNo(8979914l);
		
		
		ObjectMapper mapper= new ObjectMapper();
		mapper.writeValue(f,cobj);//storing java data into a json file...f
		System.out.println("Serialization Done....");
		
		
	}
	public void convertJsonToJava() throws Exception{
		File f = new File("customer.json");
		ObjectMapper mapper = new ObjectMapper();
		//Deserialisation
		Customer customer = mapper.readValue(f,Customer.class);//bcoz of readValue() mapper would read the file and Not create it
		System.out.println(customer);
		
	}
}
