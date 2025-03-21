package in.ashokit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import in.ashokit.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@GetMapping("/")
	public String index(Model model) {
		User userObj = new User();
		model.addAttribute("user", userObj);
		return "index";
	}
	
	
	@PostMapping("/login")
	public String login(HttpServletRequest req,User user, Model model) {
		String email=user.getEmail();
		String password = user.getPassword();
		if(email.equals("krmauryaaman@gmail.com") && password.equals("Qwerty@123")){
			//login validated
			HttpSession session = req.getSession(true);
			session.setAttribute("email",email);
		}else {
			// invalid login
			model.addAttribute("errMsg", "Invalid Credentials");
			return "index";
		}
		
		return "dashboard";
	}
	
	@GetMapping("/personal-details")
	@ResponseBody
	public String getPersonalDetails(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);
		String email = (String)session.getAttribute("email");
		//get user personal detail based on email
		return "personalPage"+email;// I took it as response body bcoz currently i have NOT
	}								//wrote logic for getting personal details
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest req,Model model) {
		User userObj = new User();
		model.addAttribute("user", userObj);
		
		HttpSession session = req.getSession(false);
		session.invalidate();
		return "index";// empty page dikhana is a trick... session.invalidate() ne kaam kra main toh
	}
}
