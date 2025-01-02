package in.ashokit.service;

import in.ashokit.bindings.CounsellorDTO;

public interface CounsellorService {
	
	public CounsellorDTO login(CounsellorDTO counsellorDTO);
	public boolean register(CounsellorDTO counsellorDTO);
	
	public boolean uniqueEmailCheck(String email);

}
