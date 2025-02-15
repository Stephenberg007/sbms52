package in.ashokit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import in.ashokit.constants.AppConstants;
import in.ashokit.model.Order;

@Service
public class OrderService {
	@Autowired
	private KafkaTemplate <String, Order> kafkaTemplate;
	
	public String addMsg(Order order) {
		kafkaTemplate.send(AppConstants.TOPIC, order);// with this line we r publ. a msg in JSON Format(order) to Kafka-Topic
														// 
		return "Msg published to Kafka TOPIC";
	}
}
