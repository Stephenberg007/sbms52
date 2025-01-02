package com.jtc;

import org.springframework.stereotype.Repository;

@Repository
public class ReportDAO {
	public String getNameById(Integer Uid) {
		if(Uid ==100) {
			return "John";
		}
		else if(Uid ==101) {
			return "Carter";
		}
		else {
			return "AMAN MAURYA";
		}
	}

}
