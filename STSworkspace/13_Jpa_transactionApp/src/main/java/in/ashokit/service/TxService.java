package in.ashokit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.ashokit.entity.Contact;
import in.ashokit.entity.Person;
import in.ashokit.repository.ContactRepo;
import in.ashokit.repository.PersonRepo;
;

@Service
public class TxService {
	@Autowired
	PersonRepo personRepo;
	@Autowired
	ContactRepo contactRepo;
	
		@Transactional(rollbackFor=Exception.class)
		public void savePersonWithContact() {
			Person p = new Person();
			p.setAge(20);
			p.setCountry("India");
			p.setGender("Male");
			p.setName("Anuj");
			personRepo.save(p);
			
			int i=10/0;
			
			Contact c = new Contact();
			c.setEmail("krmauryaaman@gmail.com");
			c.setName("Aman");
			c.setPhoneno(8979914l);
			contactRepo.save(c);
			
			
		}
}
