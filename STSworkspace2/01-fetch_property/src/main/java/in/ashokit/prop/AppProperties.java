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
@ConfigurationProperties(prefix="aman")
public class AppProperties {
	
	private Map<String,String> xyz= new HashMap<>();//name of map is very Important while writing properties in .properties file
// Initially i took 'meme' as the name of our MAP. and it was behaving abnormally. I was getting 
// properties from both the classes i.e 6 properties total. Changing the name led to Normal Behaviour
	// This suggests 'meme' must be a reserved keyword bcoz of which issues were happening in the property binding mechanism
}
