package in.ashokit.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.ex.CourseNotFoundException;
import lombok.SneakyThrows;

@RestController
public class CourseRestController {
	
	@GetMapping("/course")
	@SneakyThrows
	public ResponseEntity<String> getCourseInfo(@RequestParam("name")String name) {// bcoz of sneakythrows I need NOT write "throws Exception" here
		String msg=null;
		if("SBMS".equals(name)) {
			msg= "New SBMS will start jan Ending..";
			return new ResponseEntity<>(msg,HttpStatus.OK);
		}else if("JRTP".equals(name)) {
			msg="New JRTP will start Feb First";
			return new ResponseEntity<>(msg,HttpStatus.OK);
		}else {
			throw new CourseNotFoundException("Course Not Found");
		}
	}
	
}
