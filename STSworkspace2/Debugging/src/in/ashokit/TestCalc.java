package in.ashokit;

public class TestCalc {
	public static void main(String[] args) {
		System.out.println("DeBugging .....");
		Calculator c = new Calculator();
		//addition 
		c.addition(11,20);
		
		// mul
		c.multiply(15,20);
		
		//sub
		c.subtract(50, 40);
		int myarr[]={1,2,5,2,20};
		int sum = c.arrSum(myarr);
		System.out.println("Sum :- "+sum);
	}

}
