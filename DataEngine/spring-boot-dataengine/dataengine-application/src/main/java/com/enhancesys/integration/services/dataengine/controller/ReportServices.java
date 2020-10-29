package com.enhancesys.integration.services.dataengine.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enhancesys.integration.services.dataengine.util.DataConstants;


@RestController
@RequestMapping(DataConstants.REPORT_SERVICE_BASE_PATH)
public interface ReportServices 
{	
	
	@PostMapping(value = "/processJobData", produces = MediaType.APPLICATION_JSON_VALUE)
	public String processJobData(@RequestBody String String);
	
	@GetMapping(path="/", produces = MediaType.APPLICATION_JSON_VALUE)
	public String welcome();
	
}

