package com.artsandcodes.eventpadi;

import com.artsandcodes.eventpadi.security.CustomCorsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EventpadiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventpadiApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	public FilterRegistrationBean corsFilterRegistration(){
		FilterRegistrationBean filterRegistrationBean =
				new FilterRegistrationBean(new CustomCorsFilter());
		filterRegistrationBean.setName("CORS Filter");
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.setOrder(0);

		return filterRegistrationBean;
	}
}
