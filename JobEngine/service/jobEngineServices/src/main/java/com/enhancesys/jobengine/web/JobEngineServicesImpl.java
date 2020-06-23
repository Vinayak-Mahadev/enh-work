package com.enhancesys.jobengine.web;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jws.WebService;

import org.apache.log4j.Logger;

import com.enhancesys.jobengine.serviceslayer.JobEngineServicesHelper;

/**
 * <b>Purpose:</b><br>
 * 		JobEngine invoker implementation<br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * 		Enhancesys Innovations 2020<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 * Sl No   Modified Date        Author</b>
 * ==============================================
 * 1        10-05-2010		   Vinayak Mahadev (vinay.nagaraj@enhancesys.com)
 * 	-- Base Release 
 * </pre>
 * 
 * <br>
 */

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@WebService(endpointInterface = "com.enhancesys.jobengine.web.JobEngineServices", name = "JobEngineServices")
@Remote({ JobEngineServices.class })
public class JobEngineServicesImpl 
{
	private static Logger log = Logger.getLogger(JobEngineServicesImpl.class);

	private JobEngineServicesHelper jobEngineServices = new JobEngineServicesHelper();
	

	public String getResponse(final Long moduleId, final String requestData) throws Exception
	{
		String staticResponse = null;
		try
		{
			log.info("moduleId :: " + moduleId + ", Request  :: " + requestData);
			staticResponse = "Welcome to JobEngineServices...";
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

	
	
	
	public String processModule(final Long moduleId, final String requestData) throws Exception
	{
		String staticResponse = null;
		try
		{
			log.info("moduleId :: " + moduleId + ", Request  :: " + requestData);
			staticResponse = "Welcome to JobEngineServices...";
			jobEngineServices.processModule(moduleId);
			
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


	/**
	 * <pre> This method returns element's value.. If given tagname is not there in xml it returns empty value...   </pre>
	 * @param xml
	 * @param tagName
	 * @return
	 * @author Vinayak Mahadev
	 */
	 static String getXmlElementValue(final String xml, String tagName)
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