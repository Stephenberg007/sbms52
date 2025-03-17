package in.ashokit.beans;

import org.springframework.stereotype.Component;

@Component
public class Calculator {
	
	public Integer add(int i, int j) {
		return i+j;
	}
	private Integer multiply(int i, int j) {// Not wrote test case for this But still it got TESTED
		return i*j;							// BCOZ its been used in a public Method calculate()
	}
	
	public Integer calulate(int i, int j) {
		Integer result = multiply(i,j);
		return result;
	}

}