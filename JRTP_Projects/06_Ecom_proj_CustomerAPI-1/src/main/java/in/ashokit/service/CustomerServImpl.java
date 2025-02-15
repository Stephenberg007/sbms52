package in.ashokit.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import in.ashokit.dto.CustomerDto;
import in.ashokit.entity.Customer;
import in.ashokit.repo.CustomerRepo;

public class CustomerServImpl implements CustomerService {
	
	private final CustomerRepo custRepo;
	
	@Autowired
	public CustomerServImpl(CustomerRepo custRepo) {
		this.custRepo=custRepo;
	}

	@Override
	public boolean saveCustomer(CustomerDto customerDto) {
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerDto,customer);// If i take a common DTO then matching variables would be mapped rest will be ignored
		Customer cust = custRepo.save(customer);
		return cust.getCustEmail() != null;
	}

}
