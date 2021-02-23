package com.enhancesys.integration.snoc.services.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableAutoConfiguration
@EnableWebMvc
public class SnocSecurity extends WebSecurityConfigurerAdapter
{
	@Value("${soap.path}")
	private String soapPath;
	
	@Autowired
	private void configureGlobal(AuthenticationManagerBuilder auth) throws Exception 
	{
		auth.inMemoryAuthentication().withUser("admin").password("{noop}admin#123").roles("ADMIN", "USER");
		auth.inMemoryAuthentication().withUser("user").password("{noop}user#123").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		http.authorizeRequests()
		.antMatchers(soapPath + "/**").hasRole("USER")
		.antMatchers(soapPath + "/ExternalInterface?wsdl").hasRole("USER")
		.antMatchers(soapPath + "/IntegrationManagement?wsdl").hasRole("ADMIN")
		.and().httpBasic()
		.and().csrf().disable()
		.authorizeRequests().anyRequest().permitAll();
	}
	//  
	//    @Autowired
	//    public void configureGlobal(AuthenticationManagerBuilder auth) 
	//            throws Exception 
	//    {
	//        auth.inMemoryAuthentication()
	//            .withUser("admin")
	//            .password("{noop}password")
	//            .roles("USER");
	//    }
}