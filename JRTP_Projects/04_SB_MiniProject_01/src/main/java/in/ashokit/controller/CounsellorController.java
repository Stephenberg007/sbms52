package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.bindings.CounsellorDTO;
import in.ashokit.bindings.DashboardDTO;
import in.ashokit.service.CounsellorServiceImpl;
import in.ashokit.service.EnquiryServImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {
	@Autowired
	private CounsellorServiceImpl cServImpl;
	@Autowired
	private EnquiryServImpl eServImpl;
	
	@GetMapping("/index")
	public String index(Model model) {
		CounsellorDTO cObj = new CounsellorDTO();//empty object for FORM BINDING...empty fields
		model.addAttribute("counsellor",cObj);
		return "index";
	}
	@GetMapping("/logout")
	public String logout(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);
		session.invalidate();
		//return "redirect:/index (--- Instead of below three lines I can write only this 1 Line)
		CounsellorDTO cObj = new CounsellorDTO();//empty object for FORM BINDING...empty fields
		model.addAttribute("counsellor",cObj);
		return "index";
	}
	@PostMapping("/login")
	public String counsellorLogin(HttpServletRequest req,CounsellorDTO counsellorDTO,Model model) {
		CounsellorDTO counsellor = cServImpl.login(counsellorDTO);
		if(counsellor == null) {
			model.addAttribute("errMsg","Invalid Credentials");
			CounsellorDTO cObj = new CounsellorDTO();//empty object for FORM BINDING...empty fields
			model.addAttribute("counsellor",cObj);
			return "index";
			
		}
		else {
			Integer counsellorId = counsellor.getCounsellorId();
			
			// Need To store Counsellor Id in httpSession obj
			HttpSession session = req.getSession(true);
			session.setAttribute("counsellorId", counsellorId);// its very important to store it in a session As later we would use it
			
			DashboardDTO dashboardDto = eServImpl.getDashboardInfo(counsellorId);
			model.addAttribute("dashboardData",dashboardDto);//sending dashboard info of that counsellor to Ui
		return "dashboard";
		}
		
	}
	// Register Functionality
	@GetMapping("/register")
	public String registerPage(Model model) {
		CounsellorDTO cObj = new CounsellorDTO();//empty object for FORM BINDING...empty fields
		model.addAttribute("counsellor",cObj);
		return "register";
	}
	@PostMapping("/registration")
	public String registerUser(Model model,@ModelAttribute("counsellor") CounsellorDTO counsellorDTO) {
		String cEmail=counsellorDTO.getEmail();
		boolean uniqueEmailCheck = cServImpl.uniqueEmailCheck(cEmail);
			if(uniqueEmailCheck) {
					boolean regUser = cServImpl.register(counsellorDTO);
					if(regUser) {
					model.addAttribute("sMsg","Counsellor Registered");
						}else {
							model.addAttribute("errMsg","Registration failed");
						}
			}else {
					model.addAttribute("errMsg","Counsellor with this Email Already Registered");
							}
		
						return "register";
	}
	@GetMapping("/dashboard")
	public String displayDashboard(HttpServletRequest req, Model model) {
		HttpSession session= req.getSession(false);
		Integer counsellorId = (Integer)session.getAttribute("counsellorId");
		DashboardDTO dashboardDto = eServImpl.getDashboardInfo(counsellorId);
		model.addAttribute("dashboardData",dashboardDto);//sending dashboard info of that counsellor to Ui
	return "dashboard";
	}
	
	
}
