package br.com.srvforo11.parkingcontroller.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.srvforo11.parkingcontroller.filter.AuthUserFilter;

@Configuration
public class FilterConfiguration {

	@Bean
	public FilterRegistrationBean<AuthUserFilter> authUserFilter(){
		FilterRegistrationBean<AuthUserFilter> filterRegistration = new FilterRegistrationBean<AuthUserFilter>();
		filterRegistration.setFilter(new AuthUserFilter());
		filterRegistration.addUrlPatterns("/ticket/*");
		return filterRegistration;
	}
}
