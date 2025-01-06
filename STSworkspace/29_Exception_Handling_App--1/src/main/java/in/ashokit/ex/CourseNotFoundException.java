package in.ashokit.ex;

public class CourseNotFoundException extends RuntimeException {

	public CourseNotFoundException() {
		
	}
	public CourseNotFoundException(String msg) {
		super(msg);
	}
}
