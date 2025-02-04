package in.ashokit;

public class Calculator {

	public void addition(int a, int b) {
		int result = a+b;
		System.out.println(result);
	}
	
	public void multiply(int a, int b) {
		int result = a*b;
		System.out.println(result);
	}
	public void subtract(int a, int b) {
		int result = a-b;
		System.out.println(result);
	}
	public int arrSum(int arr[]) {
		//int arr[]= {2,15,13,17,13}; Hard Coding
		int sum =0;
		for(int i=0;i<arr.length;i++) {
			sum = sum+arr[i];
		}
		sum = sum+11;
		//System.out.println("Sum is :- "+sum);
		return sum;
	}

}
