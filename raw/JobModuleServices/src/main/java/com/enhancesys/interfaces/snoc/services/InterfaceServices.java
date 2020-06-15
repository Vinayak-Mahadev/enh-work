package com.enhancesys.interfaces.snoc.services;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.RequestBody;

@Path("/interface/")
@Produces ({MediaType.APPLICATION_JSON})
public interface InterfaceServices 
{	
	@POST
	@Path("getInterfaces")
	public String getInterfaces(@RequestBody String String);
	
	@POST
	@Path("getInterfacesById")
	public String getInterfaceByModuleId(@RequestBody String String);
	
	@POST
	@Path("getInterfaceFileDetails")
	public String getInterfaceFileDetails(@RequestBody String String);
	
	@POST
	@Path("getFileSummary")
	public String getInterfaceFileSummary(@RequestBody String String);
	
	@POST
	@Path("downlaodFileSummary")
	public String downlaodFileSummary(@RequestBody String String);
	
	@POST
	@Path("uploadInterfaceFile")
	public String uploadInterfaceFile(@RequestBody String String);
	
	@POST
	@Path("getOnlineInterfaceSummaries")
	public String getOnlineInterfaceSummaries(@RequestBody String String);
	
	@POST
	@Path("getRequestAndResponseDataByTransactionId")
	public String getRequestAndResponseDataByTransactionId(@RequestBody String String);
	
	@POST
	@Path("getOnlineInterfaceListWrapper")
	public String getOnlineInterfaceListWrapper(@RequestBody String String);
	
	@POST
	@Path("reprocessInterfaceSummary")
	public String reprocessInterfaceSummary(@RequestBody String String);
	
	@POST
	@Path("downlaodInterfaceReports")
	public String downlaodInterfaceReports(@RequestBody String String);
	
	@POST
	@Path("getInterfacesByInterfaceIds")
	public String getInterfacesByInterfaceIds(@RequestBody String String);
	
	@POST
	@Path("getKycSyncAttrByInterfaceId")
	public String getKycSyncAttrByInterfaceId(@RequestBody String String);
	
	@POST
	@Path("updateKycSyncAttributes")
	public String updateKycSyncAttributes(@RequestBody String String);
	
	@POST
	@Path("getInterfaceDetails")
	public String getInterfaceDetails(@RequestBody String String);
}

