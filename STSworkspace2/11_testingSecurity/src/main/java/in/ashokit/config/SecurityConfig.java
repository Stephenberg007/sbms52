package in.ashokit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable) // ✅ Disable CSRF for APIs
            .authorizeHttpRequests(auth -> auth
               // .requestMatchers("/recieve").permitAll() // ✅ Allow access to /recieve API without AUTH so we commented it
                .anyRequest().authenticated() // 🔒 Secure other endpoints
            )
            .httpBasic(); // ✅ Enable Basic Authentication
        
        return http.build();
    }
}
