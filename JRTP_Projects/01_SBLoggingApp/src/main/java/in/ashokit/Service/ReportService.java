package in.ashokit.Service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReportService {
	
	public void reportService() {
		log.info("***Method Started***");
			try {
				int i=10/0;
			}catch(Exception e) {
				log.error(e.getMessage());
			}
		
		log.info("***Method Ended***");
	}
	
}
