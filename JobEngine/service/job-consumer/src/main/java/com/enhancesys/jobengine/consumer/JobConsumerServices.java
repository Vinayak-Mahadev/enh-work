package com.enhancesys.jobengine.consumer;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Path("/consumer/")
@Produces({MediaType.APPLICATION_JSON})
public interface JobConsumerServices 
{	
	@GET
	@Path("home")
	@ResponseBody
	public String goHome(@RequestBody String request);

	@POST
	@Path("processModule")
	@ResponseBody
	public JSONObject processModule(@RequestBody String request);


}

