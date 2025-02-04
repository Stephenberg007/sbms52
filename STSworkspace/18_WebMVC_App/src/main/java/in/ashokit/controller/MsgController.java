package in.ashokit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/aman")
public class MsgController {
	
	@GetMapping("/greet")
	public ModelAndView getGreetMsg(ModelAndView mav) {
		
		mav.addObject("msg","Good Evening Aman Sir");
		mav.setViewName("index");
		return mav;
	}
	@GetMapping("/demo")
	@ResponseBody
	public String demoMsg(Model model) {
		model.addAttribute("msg","Hi! Whats up");
		return "This is Aman Maurya Here";
	}
	@PostMapping("/welcome")
	public ModelAndView getWelcomeMsg() {
		
		ModelAndView mav = new ModelAndView();
		//setting response data in k-v
		mav.addObject("msg","Welcome To Aman Furnitures @ Dehradun,Uttarakhand");
		//setting view page name
		mav.setViewName("index");
		return mav;
		
	}
	// 2nd approach to develop controller class method
	@GetMapping("/greet2")
	public String getGreetmsg(Model model) {
		String txtMsg="Good Morning Dehradun!";
		model.addAttribute("msg",txtMsg);
		return "index";
		
	}
	
}
