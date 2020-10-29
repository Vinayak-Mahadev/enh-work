package com.enhancesys.integration.services.dataengine;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity http) throws Exception 
    {
        http
         .csrf().disable()
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