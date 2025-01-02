package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.entity.User;
import in.ashokit.service.UserService;
import jakarta.validation.Valid;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	//open form
	@GetMapping("/form111")//I can leave it empty i.e just / then i would write localhost:1144 only in browser
	public String getFormData(Model model) {
		User userObj=new User();
		model.addAttribute("user", userObj);
		return "index";
	}
	
	// submit
	@PostMapping("/form")
	public String handleSubmit(@Valid User user,BindingResult result,Model model) {
		if(result.hasErrors()) {
			return "index";
		}
		
		 boolean savedUser = userService.saveUser(user);
		if(savedUser) {
			model.addAttribute("smsg","Record entered Successfully");
		}else {
			model.addAttribute("errmsg","Record Not entered");
		}
		return "index";
	}
	//view of table
	@GetMapping("/userssss")
	public String viewUserTable(Model model) {
		List<User> allUsers = userService.getAllUsers();
		model.addAttribute("alllllUsers", allUsers);
		return "users";
	}
	
	//My methods
	@GetMapping("/edit")
	public String editUserData(@RequestParam("uid")Integer uid,Model model) {
		User record = userService.getUserById(uid);
		model.addAttribute("user", record);
		
		return "index";
	}
	
	@GetMapping("/delete")
	public String deleteUserData(@RequestParam("uid")Integer uid , Model model) {
		boolean deleteUser = userService.deleteUserById(uid);
		if(deleteUser)
		{
			model.addAttribute("sMsg", "Record SuccessFully Deleted");
			List<User> allUsers = userService.getAllUsers();
			model.addAttribute("alllllUsers", allUsers);
			
		}
		else {
			model.addAttribute("errMsg", "Record Cannot be Deleted");
			
		}
		
		return "users";
	}
	
	
}
