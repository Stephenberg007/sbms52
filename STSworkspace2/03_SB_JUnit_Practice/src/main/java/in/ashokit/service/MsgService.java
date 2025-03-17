package in.ashokit.service;

import org.springframework.stereotype.Service;

@Service
public class MsgService {
	
	public String getMsg() {
		return "Getting Greet Message";
	}
	
	private String getNewMsg() {
		return "Testing Private Method";
	}
	private String getNewMsg(String msg) {
		return "Second Test "+msg;
	}

}
