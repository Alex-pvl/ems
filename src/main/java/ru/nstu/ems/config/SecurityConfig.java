package ru.nstu.ems.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

	@Autowired
	private UserConfig userConfig;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
			.authorizeHttpRequests(auth -> {
				auth.anyRequest().authenticated();
			})
			.oauth2Login(withDefaults())
			.formLogin(withDefaults())
			.build();
	}

	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder()
			.username(userConfig.getUsername())
			.password(userConfig.getPassword().toString())
			.roles("ADMIN")
			.build();
		return new InMemoryUserDetailsManager(user);
	}

}        
