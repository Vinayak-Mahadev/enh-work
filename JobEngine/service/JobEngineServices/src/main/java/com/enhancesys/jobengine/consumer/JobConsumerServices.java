package com.enhancesys.jobengine.consumer;



import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;

@Path("/consumer")
@Produces ({MediaType.APPLICATION_JSON})
public interface JobConsumerServices 
{	
	@POST
	@Path("/")
	public JSONObject goHome(@RequestBody String String);
	
	
}

