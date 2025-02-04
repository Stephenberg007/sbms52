package in.ashokit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import in.ashokit.service.UserService;
import lombok.SneakyThrows;

@Configuration
//@EnableWebSecurity
public class AppSecurityConfig {
	@Autowired
	//@Lazy
	private UserService userServ;
	
	@Bean
	@SneakyThrows
	public SecurityFilterChain security(HttpSecurity http) {
		http.authorizeHttpRequests((req)->
			req.requestMatchers("/register","/login")// So that Anybody can send Login AND Register Request
											// We can write 4-5 URLs here too ',' lga kar 
				.permitAll()
				.anyRequest()
				.authenticated());
		
		return http.csrf().disable().build();
	}
	
	
	@Bean
	public BCryptPasswordEncoder pwdEncoder() {
		
		return new BCryptPasswordEncoder();// used by DaoAuthentication Provider
	}
	
	@Bean
	public DaoAuthenticationProvider authProvider() {// Responsible to get details to be used for validation
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setPasswordEncoder(pwdEncoder());//OR .setPasswordEncoder(new BCryptPasswordEncoder())
		authProvider.setUserDetailsService(userServ);
		return authProvider;	//We can create a custom Authentication Provider also
		
	}
	
	@Bean
	@SneakyThrows// Responsible to do the Validation
	public AuthenticationManager getAuthManager(AuthenticationConfiguration config) {
		return config.getAuthenticationManager();
		
	}
	
	
	
}
