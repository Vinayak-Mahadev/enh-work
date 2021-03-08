package com.enhancesys.integration.snoc.services.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * <b>Purpose:</b>
 * <br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * Enhancesys Innovations Private Limited<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 * Sl No   Modified Date		Author</b>
 * ==============================================
 * 1       Mar 2, 2020			Vinayak Mahadev
 * 	-- Base Release
 * </pre>
 * 
 * <br>
 */

@Configuration
@EnableAutoConfiguration
@EnableWebMvc
public class SnocSecurity extends WebSecurityConfigurerAdapter
{
	@Value("${cxf.path}")
	private String cxfPath;
	
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
		.antMatchers(cxfPath + "/ExternalInterface?wsdl").hasRole("USER")
		.antMatchers(cxfPath + "/IntegrationManagement?wsdl").hasRole("ADMIN")
		.antMatchers(cxfPath + "/**").hasRole("USER")
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