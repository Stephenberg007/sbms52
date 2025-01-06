package in.ashokit.ex;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class AppExceptionHandler {
	@ExceptionHandler(value= Exception.class)
	public ResponseEntity<ExInfo> handleException(Exception e) {
		String message = e.getMessage();
		ExInfo exInfo= new ExInfo();
		exInfo.setExCode("EX110193");
		exInfo.setExMsg(message);
		exInfo.setExDate(LocalDateTime.now());
		
		return new ResponseEntity<>(exInfo,HttpStatus.INTERNAL_SERVER_ERROR);
	}	
		@ExceptionHandler(value= CourseNotFoundException.class)
		public ResponseEntity<ExInfo> handleCourseEx(CourseNotFoundException e) {
			String message = e.getMessage();
			ExInfo exInfo= new ExInfo();
			exInfo.setExCode("EXC_Course_1122");
			exInfo.setExMsg(message);
			exInfo.setExDate(LocalDateTime.now());
			
			return new ResponseEntity<>(exInfo,HttpStatus.INTERNAL_SERVER_ERROR);
}
}