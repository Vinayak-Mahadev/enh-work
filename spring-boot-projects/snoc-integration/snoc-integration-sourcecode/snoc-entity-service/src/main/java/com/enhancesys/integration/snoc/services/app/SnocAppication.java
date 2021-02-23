package com.enhancesys.integration.snoc.services.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
@ComponentScan("com.enhancesys.integration.snoc.services")
@EnableAutoConfiguration
@RestController
@Configuration
public class SnocAppication 
{
	public static ApplicationContext context;

	
		public static void main(String[] args) 
		{
			context = SpringApplication.run(SnocAppication.class, args);
		}
		
		public static ApplicationContext context() {
			return context;
		}
	
		@RequestMapping({"/", "/snoc"})
		public ModelAndView welcome() 
		{
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("index");
			return modelAndView;
		}
}
