package in.ashokit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.Entity.Contact;
import in.ashokit.repository.ContactInfo;

@Service
public class ContactService {
	@Autowired
	private ContactInfo contactRepo;
	public void saveContact() {
		Contact c = new Contact();
		c.setName("3rd-Yati");
		c.setEmail("Yati@gmai.com");
		c.setPhoneno(983786031l);
		
		Contact savedContact = contactRepo.save(c);
		System.out.println(savedContact);
		
	}
}
