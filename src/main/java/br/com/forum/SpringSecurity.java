package br.com.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import br.com.forum.service.AuthService;

@Configuration
@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthService AuthService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		 	.csrf().disable()
			.authorizeRequests()
			.antMatchers("/**")
			.permitAll()
			.antMatchers(HttpMethod.GET, "/topicos")
			.permitAll()
			.anyRequest()
			.authenticated();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(AuthService);
	}
}
