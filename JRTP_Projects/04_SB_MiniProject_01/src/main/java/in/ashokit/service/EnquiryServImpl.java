package in.ashokit.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.ashokit.bindings.DashboardDTO;
import in.ashokit.bindings.EnqFilterDTO;
import in.ashokit.bindings.EnquiryDTO;
import in.ashokit.entity.Counsellor;
import in.ashokit.entity.Enquiry;
import in.ashokit.repo.CounsellorRepo;
import in.ashokit.repo.EnquiryRepo;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EnquiryServImpl implements EnquiryService{
	@Autowired
	EnquiryRepo eRepo;
	@Autowired
	CounsellorRepo cRepo;

	@Override
	public DashboardDTO getDashboardInfo(Integer counsellorId) {
		List<Enquiry> enqList = eRepo.findByCounsellorCounsellorId(counsellorId);
			DashboardDTO dto = new DashboardDTO();
			//dto.setTotalEnqCnt(enqList.size());
			
		int openCnt = enqList.stream()
							.filter(enq->enq.getEnqStatus().equalsIgnoreCase("OPEN"))
							.collect(Collectors.toList())
							.size();	
		//dto.setOpenEnqCnt(openCnt);
		
		int enrolledCnt = enqList.stream()
				.filter(enq->enq.getEnqStatus().equalsIgnoreCase("ENROLLED"))
				.collect(Collectors.toList())
				.size();	
			//dto.setEnrollefEnqCnt(enrolledCnt);
			
			int lostEnqCnt = enqList.stream()
					.filter(enq->enq.getEnqStatus().equalsIgnoreCase("LOST"))
					.collect(Collectors.toList())
					.size();	
				//dto.setLostEnqCnt(lostEnqCnt);

				dto.setTotalEnqCnt(enqList.size());
				dto.setOpenEnqCnt(openCnt);
				dto.setEnrolledEnqCnt(enrolledCnt);
				dto.setLostEnqCnt(lostEnqCnt);
		return dto;
	}

	@Override
	public boolean addEnquiry(EnquiryDTO enquiryDto, Integer counsellorId) {
	    if (enquiryDto == null) {
	        throw new IllegalArgumentException("EnquiryDTO cannot be null");
	    }

	    Optional<Counsellor> byId = cRepo.findById(counsellorId);
	    if (byId.isPresent()) {
	        Counsellor counsellor = byId.get();

	        // Map DTO to Entity
	        Enquiry enquiry = new Enquiry();
	        BeanUtils.copyProperties(enquiryDto, enquiry); // Ensure properties match
	        enquiry.setCounsellor(counsellor);//associating that particular counsellor OBJECT who logged in, with this enquiry RECORD

	        // Save the enquiry
	        eRepo.save(enquiry);
	        return true;
	    } else {
	        // Throw an exception for better error tracking
	        throw new EntityNotFoundException("Counsellor with ID " + counsellorId + " not found");
	    }
	}


	@Override
	public List<EnquiryDTO> getEnquiries(Integer counsellorId) {
		Optional<Counsellor> byId = cRepo.findById(counsellorId);
		if(byId.isEmpty()) {
			throw new IllegalArgumentException("Invalid Counsellor Id"); 
		}
		/*List<EnquiryDTO> allEnqDtos = new ArrayList<>();
		List<Enquiry> allEnquiry = eRepo.findByCounsellorCid(counsellorId);
			for(Enquiry enquiry : allEnquiry) {
			EnquiryDTO dto = new EnquiryDTO();
			BeanUtils.copyProperties(enquiry, dto);
			allEnqDtos.add(dto);
		}*/
		List<Enquiry> allEnquiry = eRepo.findByCounsellorCounsellorId(counsellorId);
		 List<EnquiryDTO> allEnqDtos = allEnquiry.stream()
				 							.map(enquiry -> {
				 							EnquiryDTO dto = new EnquiryDTO();
				 								BeanUtils.copyProperties(enquiry, dto);
				 									return dto;
				 								})
				 								.collect(Collectors.toList());
		
		
		return allEnqDtos;
	}

	@Override
	public List<EnquiryDTO> getEnquiries(Integer counsellorId, EnqFilterDTO filterDto) {
		Enquiry enquiry = new Enquiry();
		if(filterDto.getClassMode()!=null && !filterDto.getClassMode().equals(""))
			enquiry.setClassMode(filterDto.getClassMode());
		if(filterDto.getCourse() !=null && !filterDto.getCourse().equals(""))
			enquiry.setCourse(filterDto.getCourse());
		if(filterDto.getEnqStatus() !=null && !filterDto.getEnqStatus().equals(""))
			enquiry.setEnqStatus(filterDto.getEnqStatus());
		
		Counsellor counsellor =new Counsellor();
		counsellor.setCounsellorId(counsellorId);
		enquiry.setCounsellor(counsellor); // In Enquiry class we have counsellor field and Not counsellorid field therefore we did this
		
		Example<Enquiry> example = Example.of(enquiry);//Practical Example of using Query by Example---INTERVIEW
		List<Enquiry> allEnqs = eRepo.findAll(example);
		 List<EnquiryDTO> allEnqDtos = allEnqs.stream()
					.map(enq -> {
					EnquiryDTO dto = new EnquiryDTO();
						BeanUtils.copyProperties(enq, dto);
							return dto;
						})
						.collect(Collectors.toList());


return allEnqDtos;
	}

	@Override
	public EnquiryDTO getEnquiryById(Integer enqId) {
		Optional<Enquiry> byId = eRepo.findById(enqId);
		if(byId.isEmpty())
			throw new IllegalArgumentException("ID Not Found");
		Enquiry enquiry = byId.get();
		EnquiryDTO dto = new EnquiryDTO();
		BeanUtils.copyProperties(enquiry, dto);
		return dto;
	}

}
