package in.ashokit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.dto.OrderDto;
import in.ashokit.entity.Order;

public interface OrderRepo extends JpaRepository<Order,Integer> {
	public List<Order> findByEmail(String email);
	public Order findByRazorpayOrderId(String razorpayOrderId);
}
