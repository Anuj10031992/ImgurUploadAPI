package com.example.synchrony.assignment.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.example.synchrony.assignment.demo.comp.AuthProvider;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;



@Configuration
@EnableWebSecurity
public class AppConfig  extends WebSecurityConfigurerAdapter{


	@Bean
	public Docket docket()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.synchrony.assignment.demo"))
				.paths(PathSelectors.any())
				.build();
	}
	@Bean
	public RestTemplate  getresttemplate()
	{
		return new RestTemplate();
	}
	
	@Bean 
	   public PasswordEncoder passwordEncoder() { 
	      return new BCryptPasswordEncoder(); 
	   } 

	@Override 
	   protected void configure(HttpSecurity http) throws Exception { 
	      http 
	      .csrf().disable();
	      http.headers().frameOptions().disable();
	      http.authenticationProvider(new AuthProvider())
	      .authorizeRequests().antMatchers("/hello","/actuator/**" )
	      .permitAll() .anyRequest().authenticated()
	      .and() 
	      .formLogin()
	      .permitAll() 
	      .and() 
	      .logout() .invalidateHttpSession(true) 
	      .clearAuthentication(true) .permitAll(); 
	   }
}
