package in.ashokit.service;

import org.springframework.stereotype.Service;

import in.ashokit.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	//private Logger log = LoggerFactory.getLogger(UserService.class);
	
	public void saveUser() {
		log.trace("trace messsage******");
		log.debug("****debug message****0");
		
		log.info("info msg: logic starts");
		log.warn("*Warning messages");
		log.error("Error Message starts**");
		log.info("info msg: logic Ends");
	}

}
