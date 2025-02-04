package in.ashokit.beans;

import org.springframework.stereotype.Component;

@Component
public class Calculator {
	
	public Integer add(int i, int j) {
		
		return i+j;
	}
public Integer multiply(int i, int j) {
		
		return i*j;
	}


}
