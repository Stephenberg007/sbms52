package in.ashokit.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import in.ashokit.constants.AppConstants;
import in.ashokit.service.EmailService;
import in.ashokit.service.UserServImpl;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UserController {
	@Autowired
	private UserServImpl userImpl;
	
	@Autowired
	private EmailService emailServ;
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@GetMapping("/")
	public String loginPage(Model model) {
		UserRegistrationDTO userDto = new UserRegistrationDTO();
		model.addAttribute("userRegObj", userDto);
		return AppConstants.LOGIN_PAGE;
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
				return AppConstants.RESET_PWD;
				
			} else {
				QuoteDTO quotation = userImpl.getQuotation();
				model.addAttribute("quoteObj",quotation);
				return "Dashboard";
			}
		} else {
			model.addAttribute(AppConstants.ERR_MSG, "Username or Password is Invalid");
			return AppConstants.LOGIN_PAGE;
		}

	}

	// Register Functionality-For EMPTY FORM
	@GetMapping("/register")
	public String registrationPage(UserRegistrationDTO userRegDto, Model model) {
		UserRegistrationDTO userDto = new UserRegistrationDTO();
		model.addAttribute(AppConstants.REG_DTO, userDto);// So using regDto we have done Empty Form Binding
		
		Map<Integer,String> countriesMap = userImpl.getCountries();	
		model.addAttribute("countries",countriesMap);// we are sending countries MAP also to the UI
		
		//countries,states,cities I have used in Template page with entrySet()
		
		return "RegisPag";
	}
	
	@GetMapping("/states/{countryId}")// Done for Ajax Call
	@ResponseBody
	public Map<Integer,String> getStates(@PathVariable Integer countryId){
	return userImpl.getStates(countryId);
	}
	
	@GetMapping("/cities/{stateId}")// Done For AJAX Call
	@ResponseBody
	public Map<Integer,String> getCities(@PathVariable Integer stateId){
	return userImpl.getCities(stateId);
	}


	@PostMapping("/registration")
	public String registerUser( UserRegistrationDTO userRegDto, Model model) {
		String email = userRegDto.getUserEmail();
		boolean uniqueEmail = userImpl.uniqueEmailCheck(email);
		if (uniqueEmail) {
			 userImpl.registerUser(userRegDto);
			emailServ.sendEmailWithRandomPassword(email);
			model.addAttribute("sMsg", "Registration Done.Please Login...");
	        model.addAttribute(AppConstants.REG_DTO, new UserRegistrationDTO()); // Reset the form after successful registration

		} else {
			model.addAttribute(AppConstants.ERR_MSG, "Email already Registered.Please Login..");
			model.addAttribute(AppConstants.REG_DTO, userRegDto); // Retain user input in case of error in Registration
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
		return AppConstants.RESET_PWD;
	}

	@PostMapping("/resetPassword")
	public String resetPassword(@ModelAttribute("reset") ResetPasswordDTO resetPwdDto, Model model) {
		String oldPassword = resetPwdDto.getOldPassword();
		String newPassword = resetPwdDto.getNewPassword();
		String confirmPassword = resetPwdDto.getConfirmPassword();
		UserRegistrationDTO login = userImpl.login(resetPwdDto.getEmail(),oldPassword);
		if(login !=null) {
			if(newPassword.equals(confirmPassword)) {
				 userImpl.resetPassword(resetPwdDto);
				model.addAttribute("sMsg","Password Reset");
				
			}else {
				model.addAttribute(AppConstants.ERR_MSG,"New And Confirm Password Don't Match");
			}
			
		}else {
			model.addAttribute(AppConstants.ERR_MSG,"Wrong Credentials Provided.Could Not change PASSWORD ");
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
		return AppConstants.RESET_PWD;
	}
	
	//DashBoard
	@GetMapping("/dashboard")
	public String getQuote(Model model) {
		QuoteDTO quotation = userImpl.getQuotation();
		logger.info("Recieved Quote :{}",quotation);
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
		return AppConstants.LOGIN_PAGE;
	}
	

}
