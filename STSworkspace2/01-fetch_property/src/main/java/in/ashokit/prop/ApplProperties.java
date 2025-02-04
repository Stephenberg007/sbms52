package in.ashokit.prop;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
@EnableConfigurationProperties
@ConfigurationProperties(prefix="anuj")
public class ApplProperties {
	private Map<String,String> cutory= new HashMap<>();

}
