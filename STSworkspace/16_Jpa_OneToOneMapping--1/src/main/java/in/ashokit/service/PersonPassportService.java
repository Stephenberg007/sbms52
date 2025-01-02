package in.ashokit.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entity.Passport;
import in.ashokit.entity.Person;
import in.ashokit.repository.PassportRepo;
import in.ashokit.repository.PersonRepo;

@Service
public class PersonPassportService {
	@Autowired
	PersonRepo personRepo;
	@Autowired
	PassportRepo passportRepo;
	public void saveData() {
		Person person = new Person();
		person.setPersonName("Radhamohan");
		person.setPersonGender("Female");
		Person savedPerson = personRepo.save(person);
		
		Passport passport = new Passport();
		passport.setPassportNumber("llkjh89khyt");
		passport.setIssuedAt(LocalDate.now());
		passport.setExpiringOn(LocalDate.now().plusYears(10));
		passport.setPerson(savedPerson);
		passportRepo.save(passport);
		
	}
	public void getPassport() {
		Optional<Passport> byId = passportRepo.findById(2);
		if(byId.isPresent()) {
			System.out.println(byId);;
		}else {
			System.out.println("Record not Found");
		}
		
	}
	public void deleteRecord() {
		passportRepo.deleteById(1);
	}

}
