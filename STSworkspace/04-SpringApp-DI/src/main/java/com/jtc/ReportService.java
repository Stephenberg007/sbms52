package com.jtc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
	private ReportDAO reportdao;
	
	@Value("${type}")
	private String type;
	 
	public ReportService() {
		// TODO Auto-generated constructor stub
		System.out.println("Def cons in REPORT SERVICE");
	}
	
		public void setReportDAO(ReportDAO reportdao) {
		System.out.println("setReportDAO() called");
		this.reportdao=reportdao;
	}
	@Autowired
	public ReportService(ReportDAO reportdao) {
		System.out.println("RS constructor called");
		this.reportdao=reportdao;
		
	}
	public void printName(Integer Uid) {
		System.out.println("Report  type:"+type);
		String nameById=reportdao.getNameById(Uid);
		System.out.println(nameById);
	}

}
