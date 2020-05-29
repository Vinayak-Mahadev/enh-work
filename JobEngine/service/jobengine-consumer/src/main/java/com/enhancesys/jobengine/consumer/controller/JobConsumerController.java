package com.enhancesys.jobengine.consumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/consumer")
public class JobConsumerController 
{

	@GetMapping("/")
	public String home() 
	{
		String responce = "Welcome to Job Consumer Service";
		try 
		{

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return responce;
	}

}
