package in.ashokit.service;

import java.util.List;

import in.ashokit.bindings.DashboardDTO;
import in.ashokit.bindings.EnqFilterDTO;
import in.ashokit.bindings.EnquiryDTO;

public interface EnquiryService {
	
public DashboardDTO getDashboardInfo(Integer counsellorId);
public boolean addEnquiry(EnquiryDTO enquiryDto,Integer counsellorId);
public List<EnquiryDTO> getEnquiries(Integer counsellorId);
public List<EnquiryDTO> getEnquiries(Integer counsellorId,EnqFilterDTO filterDto);
public EnquiryDTO getEnquiryById(Integer enqId);

}
