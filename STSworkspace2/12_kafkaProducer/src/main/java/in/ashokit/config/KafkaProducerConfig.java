package in.ashokit.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import in.ashokit.constants.AppConstants;
import in.ashokit.model.Order;

@Configuration
public class KafkaProducerConfig {
	@Bean
	public ProducerFactory<String,Order> producerFactory(){
		Map<String,Object> configProps = new HashMap<>();
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConstants.HOST);// kafka server URL
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);// Key is String
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);// I want to store a JSON Value as a KAFKA Topic
		return new DefaultKafkaProducerFactory<>(configProps);
		
	}
	@Bean
	public KafkaTemplate<String,Order> KafkaTemplate(){
		return new KafkaTemplate<>(producerFactory());
	}
	
}
