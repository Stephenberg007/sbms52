package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.StudentOrder;

public interface StudentOrderRepo extends JpaRepository<StudentOrder,Integer> {
	public StudentOrder findByRazorpayOrderId(String orderId);

}
