package in.ashokit.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entity.Address;
import in.ashokit.entity.Employee;
import in.ashokit.repository.AddressRepository;
import in.ashokit.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository empRepo;
	@Autowired
	private AddressRepository addrRepo;
	
	public void GetEmployeeByCity(String city) {
		List<Address> bycity = addrRepo.findBycity(city);
		System.out.println(bycity);
	}
	
	public void getEmpBySalary(Long salary) {
		 List<Employee> bySalary = empRepo.findBySalary(salary);
		System.out.println(bySalary);
		
	}
	
	public void getEmp() {
		Optional<Employee> byId = empRepo.findById(1);
		System.out.println(byId);
		
	}
	public void addEmployee() {
		Employee employee = new Employee();
		employee.setEmpName("GurHarsh");
		employee.setSalary(35000l);
		//Employee savedEmp = empRepo.save(employee);
		
		Address address1 = new Address();
		address1.setCity("Banaras");
		address1.setState("U.P");
		address1.setType("Permanent Address");
		
		Address address2 = new Address();
		address2.setCity("Delhi");
		address2.setState("Delhi");
		address2.setType("Present Address");
		
		//setting employee to addresses
		address1.setEmployee(employee);
		address2.setEmployee(employee);
		
		//setting addresses to employee
		 List<Address> addrList = Arrays.asList(address1,address2); //
		//  addrRepo.saveAll(addrList);
		 	employee.setAddress(addrList);
		
		Employee save = empRepo.save(employee);
		
	}

}
