package in.ashokit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem,Integer>{
	public List<OrderItem>findByOrderOrderId(Integer orderId);
}
