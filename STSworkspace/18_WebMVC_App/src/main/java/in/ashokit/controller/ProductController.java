package in.ashokit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/product")
public class ProductController {
	@GetMapping("/welcome")
	public String getWelcomeProd(Model model){
		model.addAttribute("msg","We have 100 welcome products");
		return "index";
	}
	@GetMapping("/comm")
	@ResponseBody
	public String interComm(@RequestParam("a")String Jakira,@RequestParam("b")String khana) {
		
		return Jakira +" This is best Day "+" "+khana+" Would sell today !!!";
	}
	@GetMapping("/communicate")
	public ModelAndView interCommun(@RequestParam("name")String name,@RequestParam("item")String item,ModelAndView mav) {
	
		mav.addObject("msg","Hi "+name+" We have some 800 "+item+"to sell .");
		mav.setViewName("index");
		return mav;
	}
	@GetMapping("/{name}/pathV")
	@ResponseBody
	public String getPathData(@PathVariable("name")String empName) {
		return empName+" is my user name.";
	}
	
	@GetMapping("/{name}/pathVar")
	@ResponseBody
	public String getData(@PathVariable("")String name) {
		return name+" what is my USER ID And Pwd.";
	}
	
	
	
}
