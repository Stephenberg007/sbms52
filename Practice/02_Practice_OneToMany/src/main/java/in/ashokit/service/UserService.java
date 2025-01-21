package in.ashokit.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entity.Address;
import in.ashokit.entity.User;
import in.ashokit.repo.AddressRepo;
import in.ashokit.repo.UserRepo;
@Service
public class UserService {
	@Autowired
	AddressRepo addRepo;
	
	@Autowired
	UserRepo uRepo;

	public void curdService() {
	User user1 = new User();
	user1.setUName("Amanss");
	user1.setPhNo(989754l);
	//uRepo.save(user1);
	
	User user2 = new User();
	user2.setUName("Anujss");
	user2.setPhNo(952032l);
	//uRepo.save(user2);
	
	
	
	Address addr1 = new Address();
	addr1.setCity("Dehradunsss");
	addr1.setState("Uttarakhandss");
	addr1.setUser(user1);
	
	Address addr2 = new Address();
	addr2.setCity("Nainitalss");
	addr2.setState("Uttarakhandss");
	addr2.setUser(user1);
	//addRepo.save(addr2);
	
	List<Address> addList=Arrays.asList(addr1,addr2);
	user1.setAddress(addList);
	uRepo.save(user1);

	
}
}