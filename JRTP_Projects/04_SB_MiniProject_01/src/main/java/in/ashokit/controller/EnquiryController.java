package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.bindings.EnqFilterDTO;
import in.ashokit.bindings.EnquiryDTO;
import in.ashokit.service.EnquiryServImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {

	@Autowired
	private EnquiryServImpl enqServImp;
	
	@GetMapping("/enquiry-page")
	private String loadEnqPage(Model model) {
		EnquiryDTO enqDto = new EnquiryDTO();
		model.addAttribute("enquiry",enqDto);
		return "add-enquiry";
	}
	
	@PostMapping("/add-enquiry")
	public String addEnquiry(HttpServletRequest req,@ModelAttribute("enquiry")EnquiryDTO enquiry, Model model) {
		HttpSession session = req.getSession(false);
		Integer counsellorId =(Integer) session.getAttribute("counsellorId");
		boolean enqStatus = enqServImp.addEnquiry(enquiry, counsellorId);
		if(enqStatus)
			model.addAttribute("sMsg","Enquiry Added");
		else {
			model.addAttribute("errMsg","Enquiry Not Added");
		}
		return "add-enquiry";
	}
	
	@GetMapping("/view-enquiries")
	public String getEnquiries(HttpServletRequest req,Model model) {
		HttpSession session = req.getSession(false);
		Integer counsellorId = (Integer)session.getAttribute("counsellorId");
		List<EnquiryDTO> enqList = enqServImp.getEnquiries(counsellorId);
		model.addAttribute("enquiriesss",enqList);
		EnqFilterDTO filterDTO= new EnqFilterDTO();
		model.addAttribute("filterDTO", filterDTO);
		return "view-enquiries";
	}
	
	@GetMapping("/edit-enquiry")
	private String editEnquiry(@RequestParam("enquiry_id") Integer enqId,Model model) {
		
		EnquiryDTO enqDto = enqServImp.getEnquiryById(enqId);
		model.addAttribute("enquiry",enqDto);
		return "add-enquiry";
	}
	@PostMapping("/view-filtered")
	public String getFilteredEnquiries(HttpServletRequest req,Model model,@ModelAttribute("filterDTO")EnqFilterDTO enqfilterDto) {
		HttpSession session = req.getSession(false);
		Integer counsellorId = (Integer)session.getAttribute("counsellorId");
		List<EnquiryDTO> enqList = enqServImp.getEnquiries(counsellorId,enqfilterDto);
		model.addAttribute("enquiriesss",enqList);
		/*
		 * EnqFilterDTO filterDTO= new EnqFilterDTO(); model.addAttribute("filterDTO",
		 * filterDTO);
		 */
		
		return "view-enquiries";
	}
	
	
}
