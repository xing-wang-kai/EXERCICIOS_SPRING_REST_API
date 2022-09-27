package br.com.forum;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.forum.config.AuthenticationTokenFilter;
import br.com.forum.service.AuthService;
import br.com.forum.service.TokenService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthService AuthService;
	
	@Autowired
	private TokenService tokenService;

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		 	.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/topico")
				.permitAll()
			.antMatchers(HttpMethod.POST, "/auth/**")
				.permitAll()			
			.antMatchers(HttpMethod.POST, "/usuario/cadastra/**")
				.permitAll()
			.antMatchers(HttpMethod.GET, "/interceptor")
				.permitAll()
			.antMatchers(HttpMethod.GET, "/actuator/**")
				.permitAll()
			.anyRequest()
				.authenticated()
			.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.addFilterBefore(new AuthenticationTokenFilter(tokenService), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		auth.userDetailsService(AuthService).passwordEncoder(encoder);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
		
		}
}
