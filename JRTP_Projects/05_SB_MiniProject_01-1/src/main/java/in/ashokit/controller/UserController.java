package in.ashokit.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import in.ashokit.binding.QuoteDTO;
import in.ashokit.binding.ResetPasswordDTO;
import in.ashokit.binding.UserRegistrationDTO;
import in.ashokit.service.EmailService;
import in.ashokit.service.UserServImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	private UserServImpl userImpl;
	
	@Autowired
	private EmailService emailServ;

	@GetMapping("/")
	public String loginPage(Model model) {
		UserRegistrationDTO userDto = new UserRegistrationDTO();
		model.addAttribute("userRegObj", userDto);
		return "LoginPage";
	}

	@PostMapping("/login")
	public String userLogin( @ModelAttribute("userRegObj") UserRegistrationDTO userRegDto,
			Model model) {
			//UserRegistrationDTO user = userImpl.login(userRegDto);
			UserRegistrationDTO user = userImpl.login(userRegDto.getUserEmail(),userRegDto.getUserPwd());
		if (user != null) {
			//HttpSession session = req.getSession(true);// not used 
			String email = user.getUserEmail();
			//session.setAttribute("email", email);	// not used
			if (user.getPwdUpdated().equalsIgnoreCase("No")) {
				ResetPasswordDTO resetPwd = new ResetPasswordDTO();
				//String email = (String) req.getAttribute("email");
				resetPwd.setEmail(email);
				model.addAttribute("reset", resetPwd);
				return "ResetPwd";
				
			} else {
				QuoteDTO quotation = userImpl.getQuotation();
				model.addAttribute("quoteObj",quotation);
				return "Dashboard";
			}
		} else {
			model.addAttribute("errMsg", "Username or Password is Invalid");
			return "LoginPage";
		}

	}

	// Register Functionality
	@GetMapping("/register")
	public String registrationPage(UserRegistrationDTO userRegDto, Model model) {
		UserRegistrationDTO userDto = new UserRegistrationDTO();
		model.addAttribute("regDto", userDto);
		
		Map<Integer,String> countriesMap = userImpl.getCountries();	
		model.addAttribute("countries",countriesMap);
		
		//countries,states,cities I have used in Template page with entrySet()
		
		return "RegisPag";
	}
	
	@GetMapping("/states/{countryId}")
	@ResponseBody
	public Map<Integer,String> getStates(@PathVariable Integer countryId){
	return userImpl.getStates(countryId);
	}
	
	@GetMapping("/cities/{stateId}")
	@ResponseBody
	public Map<Integer,String> getCities(@PathVariable Integer stateId){
	return userImpl.getCities(stateId);
	}


	@PostMapping("/registration")
	public String registerUser(@ModelAttribute("regDto") UserRegistrationDTO userRegDto, Model model) {
		String email = userRegDto.getUserEmail();
		boolean uniqueEmail = userImpl.uniqueEmailCheck(email);
		if (uniqueEmail) {
			boolean registerUser = userImpl.registerUser(userRegDto);
			emailServ.sendEmailWithRandomPassword(email);
			model.addAttribute("sMsg", "Registration Done.Please Login...");

		} else {
			model.addAttribute("errMsg", "Email already Registered.Please Login..");
		}
		Map<Integer,String> countriesMap = userImpl.getCountries();	
		model.addAttribute("countries",countriesMap);
		return "RegisPag";
	}
	// Reset PWD Functionality

	@GetMapping("/reset")
	public String reset( Model model) {
		ResetPasswordDTO resetPwd = new ResetPasswordDTO();
		//String email = (String) req.getAttribute("email");
		//resetPwd.setEmail(email);
		model.addAttribute("reset", resetPwd);
		return "ResetPwd";
	}

	@PostMapping("/resetPassword")
	public String resetPassword(@ModelAttribute("reset") ResetPasswordDTO resetPwdDto, Model model) {
		String oldPassword = resetPwdDto.getOldPassword();
		String newPassword = resetPwdDto.getNewPassword();
		String confirmPassword = resetPwdDto.getConfirmPassword();
		UserRegistrationDTO login = userImpl.login(resetPwdDto.getEmail(),resetPwdDto.getOldPassword());
		if(login !=null) {
			if(newPassword.equals(confirmPassword)) {
				boolean resetPwd = userImpl.resetPassword(resetPwdDto);
				model.addAttribute("sMsg","Password Reset");
				
			}else {
				model.addAttribute("errMsg","New And Confirm Password Don't Match");
			}
			
		}else {
			model.addAttribute("errMsg","Wrong Credentials Provided.Could Not change PASSWORD ");
		}
		
		/*
		 * if(!newPassword.equals(confirmPassword)) {
		 * model.addAttribute("errMsg","New Password Mismatch"); }
		 * 
		 * boolean resetPassword = userImpl.resetPassword(resetPwdDto);
		 * 
		 * if(resetPassword) { model.addAttribute("sMsg","Password Reset.Login Again");
		 * 
		 * }else { model.addAttribute("errMsg","Incorrect Password Entered"); } return
		 * "ResetPwd";
		 */
		return "ResetPwd";
	}
	
	//DashBoard
	@GetMapping("/dashboard")
	public String getQuote(Model model) {
		QuoteDTO quotation = userImpl.getQuotation();
		model.addAttribute("quoteObj",quotation);
		return "Dashboard";
	}
	
	//Logout
	@GetMapping("/logout")
	public String userLogout(HttpServletRequest req,Model model) {
		//HttpSession session = req.getSession(false);
		//session.invalidate();
		UserRegistrationDTO userDto = new UserRegistrationDTO();
		model.addAttribute("userRegObj", userDto);
		model.addAttribute("sMsg","Logged Out Successfully");
		return "LoginPage";
	}
	

}
