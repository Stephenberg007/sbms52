package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import in.ashokit.binding.Quote;
import in.ashokit.service.MsgService;

@Controller
public class MsgController {
	@Autowired
	MsgService msgServ ;
	
	@GetMapping("/getquotes")
	public String displayQuotes(Model model) {
		List<Quote> allQuotes = msgServ.getAllQuotes();
		model.addAttribute("quotes",allQuotes);
		return "quotes";
	}
}
