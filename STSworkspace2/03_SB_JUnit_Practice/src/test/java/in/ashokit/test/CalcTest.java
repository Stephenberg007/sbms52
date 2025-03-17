package in.ashokit.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import in.ashokit.beans.Calculator;

@SpringBootTest
public class CalcTest {
	Calculator c = new Calculator();
	
	@Test
	public void addTest() {
		//Calculator c = new Calculator();
		Integer actualResult = c.add(45,500);
		Integer expectedResult = 545;
		
		Assertions.assertEquals(expectedResult, actualResult);
		
	}
	
	@Test
	public void calculateTest() {
		Integer actualResult = c.calulate(15, 6);
		Integer expectedResult = 90;
		assertEquals(expectedResult,actualResult);
	}
	
}