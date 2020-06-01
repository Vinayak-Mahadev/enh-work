package com.enhancesys.jobengine.consumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/consumer")
public class JobConsumerController 
{

	@GetMapping("/home")
	public ModelAndView home() 
	{
		String response = "Welcome to Job Consumer Service";
		try 
		{
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return new ModelAndView("welcome", "data", response);
	}

}
