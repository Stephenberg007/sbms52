package in.ashokit;

public class Calculator {
	String name;
	int age;

	public void addition(int a, int b) {
		int result = a+b;
		System.out.println(result);
		System.out.println(age);
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
