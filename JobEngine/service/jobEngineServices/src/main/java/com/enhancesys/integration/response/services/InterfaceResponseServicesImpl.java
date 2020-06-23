package com.enhancesys.integration.response.services;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.enhancesys.integration.response.services.jobengine.JobEngineServices;
import com.enhancesys.integration.response.util.InterfaceDBUtil;

/**
 * <b>Purpose:</b><br>
 * 		Implementation of IntegrationManagement interface static resonse operations..<br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * 		Enhancesys Innovations 2019<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 * Sl No   Modified Date        Author</b>
 * ==============================================
 * 1        23-08-2019		   Suresh Upparu
 * 	-- Base Release 
 * </pre>
 * 
 * <br>
 */

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@WebService(endpointInterface = "com.enhancesys.integration.response.services.jobengine.JobEngineServices", name = "JobEngineServices")
@Remote({ JobEngineServices.class })
public class InterfaceResponseServicesImpl 
{
	private static Logger log = Logger.getLogger(InterfaceResponseServicesImpl.class);

	private InterfaceDBUtil dbUtil = new InterfaceDBUtil();
	private JobEngineServicesUtil jobEngineServices = new JobEngineServicesUtil();
	
	public String getStaticResponse(final Long moduleId,final String requestData) throws Exception
	{
		String staticResponse = null;
		try
		{
			log.info("moduleId :: " + moduleId + ", Request  :: " + requestData);
			staticResponse = "Welcome to InterfaceResponseServices";
			jobEngineServices.processModule(moduleId.toString());
			
			return staticResponse;
		}
		catch(Exception exception)
		{
			log.error("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{

		}
		return staticResponse;
	}

	private static String getStaticResponse(Long moduleId, JSONObject staticResConf, String requestData, String staticResponse, String responseType)
	{
		return null;
	}

	/**
	 * <pre> This method returns element's value.. If given tagname is not there in xml it returns empty value...   </pre>
	 * @param xml
	 * @param tagName
	 * @return
	 * @author Vinayak Mahadev
	 */
	private static String getXmlElementValue(final String xml, String tagName)
	{
		String data = null;
		try
		{
			if(xml.contains("<"+tagName+">"))
			{
				data = xml.split("<"+tagName+">")[1].split("</"+tagName+">")[0];
			}
			if(data !=null && data.isEmpty())
			{

				throw new Exception("In this xml given tagName is not present or tag element  value is empty ::: Check this xml ::: "+ xml);
			}
		}
		catch (Exception exception)
		{
			log.error("The return data is empty now :: Reason ::" + exception.getMessage()+"   "+exception);
			data ="";
		}

		return data;
	}

}