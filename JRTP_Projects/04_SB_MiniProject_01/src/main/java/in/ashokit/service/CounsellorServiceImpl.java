package in.ashokit.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.bindings.CounsellorDTO;
import in.ashokit.entity.Counsellor;
import in.ashokit.repo.CounsellorRepo;

@Service
public class CounsellorServiceImpl implements CounsellorService {
	
	@Autowired
	CounsellorRepo cRepo;


	@Override
	public CounsellorDTO login(CounsellorDTO counsellorDTO) {
		Counsellor entity = cRepo.findByEmailAndPassword(counsellorDTO.getEmail(), counsellorDTO.getPwd());
		// But returnType=CounsellorDTO I need to convert it
		if(entity != null) {
		CounsellorDTO cDTO = new CounsellorDTO();
		BeanUtils.copyProperties(entity, cDTO);
	return cDTO;
	}else {
		return null;
	}
	}

	@Override
	public boolean register(CounsellorDTO counsellorDTO) {
		//String email = counsellorDTO.getEmail();
		//if(uniqueEmailCheck(email)) {		// 1 method should have only 1 functionality so i must NOT use another method inside it
			Counsellor entity = new Counsellor();
			BeanUtils.copyProperties(counsellorDTO, entity);
			Counsellor savedRecord = cRepo.save(entity);
			return savedRecord.getCid() != null;
			
		
		
	}

	@Override
	public boolean uniqueEmailCheck(String email) {
		Counsellor entityObj = cRepo.findByEmail(email);
		if( entityObj == null)
		return true;
		else
			return false;// i Can directly write return entity == null; it means the same as if,else condition
	}
	
	
	
}
