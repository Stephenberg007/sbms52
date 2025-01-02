package in.ashokit.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.Entity.Contact;
import in.ashokit.repository.ContactInfo;

@Service
public class ContactService {
	@Autowired
	private ContactInfo contactRepo;
	public void saveContact() {
		Contact c1 = new Contact(1,"Niti","niti@gmail",8979914l);
		Contact c2 = new Contact(2,"Kannu","kannu@gmail",9634853l);
		Contact c3 = new Contact(3,"Farhan","farhan@gmail",74170935l);
		List<Contact> asList = Arrays.asList(c1,c2,c3);		
		
		 List<Contact> savedContact = contactRepo.saveAll(asList);
		System.out.println(savedContact);
		
	}
}
