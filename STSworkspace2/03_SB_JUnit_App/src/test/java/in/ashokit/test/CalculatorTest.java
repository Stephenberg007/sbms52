package in.ashokit.test;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

import in.ashokit.beans.Calculator;

//@SpringBootTest
public class CalculatorTest {
	
	@Test
	public void testAdd() {		// Unit Test Method
		Calculator c = new Calculator();
		
		Integer actualResult = c.add(10,20);
		
			//Integer expectedResult =44;
			Integer expectedResult =30;
			// Assertions
			
			Assertions.assertEquals(expectedResult,actualResult);// to validate if our test case is passed or Not
			
	}
	
	@Test
	public void testMultiply() {		// Unit Test Method
		Calculator c = new Calculator();
		
		Integer actualResult = c.multiply(10,20);
		
			//Integer expectedResult =44;
			Integer expectedResult =200;
			// Assertions
			
			Assertions.assertEquals(expectedResult,actualResult);// to validate if our test case is passed or Not
			
	}
	
	
}
