package enh.team.interfaces.soapservice;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import javax.ws.rs.core.MediaType;

import org.apache.tools.ant.util.Base64Converter;

import com.finevm.enh.util.PropType;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;



public class TestWebServiceRequest{

	private final PropType  webService;
	private static TestWebServiceRequest testSoapRequest = null;
	public static TestWebServiceRequest getTestSoapRequest(PropType webService){
		synchronized (TestWebServiceRequest.class) {

			if(testSoapRequest == null){
				testSoapRequest = new TestWebServiceRequest(webService);

			}

		}	
		return testSoapRequest;
	}
	private TestWebServiceRequest(PropType webService){
		this.webService = webService;
		webService = null;
	}
	
	public ResponseBean invokeService(String wsdlUrl, String reqString, String userName, String password, Long interfaceId)
	{
		ResponseBean responseBean = new ResponseBean();
		long start = System.currentTimeMillis();
		String response = null;
		try
		{

			switch(webService){
			case SOAP: 
				response = sendSoapRequest(wsdlUrl, reqString, userName, password, interfaceId);
				break;
			case REST: 
				sendRestRequest(wsdlUrl, reqString, userName, password, interfaceId);
				break;
			default:
				System.out.println("Some problem occured...");
				break;
			}

			responseBean.setTimeRequired((System.currentTimeMillis() - start));
			responseBean.setStatus("Success");
			responseBean.setResponseData(response);
			responseBean.setResponseTime(new Date());

			return responseBean;
		}
		catch(Exception exception)
		{
			responseBean.setErrorCode(1001L);
			responseBean.setErrorMessage(exception.getMessage());
			responseBean.setStatus("Error");
			responseBean.setResponseTime(new Date());
			responseBean.setTimeRequired((System.currentTimeMillis() - start));
			return responseBean; 
		}
		finally
		{
			responseBean = null;
		}
	}

	private String sendSoapRequest(String wsdlUrl, String reqString, String userName, String password, Long interfaceId) throws Exception 
	{  
		System.out.println("InterfaceId :: "+ interfaceId + " Entry sendSoapRequest..wsdlUrl: "+wsdlUrl);

		URL url = null;
		URLConnection connection = null;
		Base64Converter base64Converter = null;
		OutputStreamWriter outStreamWriter = null;
		InputStreamReader inStreamReader = null;
		StringBuilder builder = null;
		String response = null;
		String credentials = null;
		String encoding = null;
		int length = 0;

		try
		{
			if(reqString == null || reqString.trim().isEmpty())
			{
				System.out.println("Request should not be empty..");
				throw new Exception("Request should not be empty..", null); 
			}
			if(wsdlUrl == null || wsdlUrl.trim().isEmpty())
			{
				System.out.println("WSDL URL was not configured to invoke the service for interfaceID.." + interfaceId);
				throw new Exception("WSDL URL was not configured to invoke the service for interfaceID.." + interfaceId, null); 
			}
			if(userName == null || userName.trim().isEmpty())
			{
				System.out.println("Username was not configured to invoke the service for interfaceID.." + interfaceId);
				throw new Exception("Username was not configured to invoke the service for interfaceID.." + interfaceId, null); 
			}

			url = new URL(wsdlUrl);
			length = reqString.length();
			credentials = userName + ":" + password;        
			base64Converter = new Base64Converter();
			encoding = base64Converter.encode(credentials.getBytes("UTF-8"));

			connection = url.openConnection();
			connection.setRequestProperty("Authorization", String.format("Basic %s", encoding));
			connection.setDoOutput( true );  
			connection.setDoInput( true );   
			connection.setRequestProperty( "Content-Type", "text/xml; charset=utf-8" );  
			connection.setRequestProperty( "Content-Length", Integer.toString( length ) );  
			connection.setConnectTimeout(3600000);
			connection.setReadTimeout(3600000);
			connection.connect();

			outStreamWriter = new OutputStreamWriter( connection.getOutputStream() );   
			outStreamWriter.write( reqString, 0, length );  
			outStreamWriter.flush();  

			inStreamReader = new InputStreamReader( connection.getInputStream() );  
			builder = new StringBuilder();     
			int ch = inStreamReader.read();  

			while( ch != -1 )
			{  
				builder.append((char)ch);  
				ch = inStreamReader.read();  
			}  

			response = builder.toString();
			response = response.replace("&quot;", "\"");

			return response;
		}
		catch(Exception exception)
		{
//			exception.printStackTrace();
			throw exception;
		}
		finally
		{
			if(outStreamWriter!=null)
			{
				outStreamWriter.close();
			}
			if(inStreamReader!= null)
			{
				inStreamReader.close();
			}
			outStreamWriter = null;
			url = null;
			connection = null;
			base64Converter = null;
			inStreamReader = null;
			builder = null;
			response = null;
			credentials = null;
			encoding = null;


			System.out.println("InterfaceId :: " + interfaceId + " Exit sendSoapRequest..");

		}
	}     

	private String sendRestRequest(String wsdlUrl, String reqString, String userName, String password, Long interfaceId) throws Exception 
	{
		System.out.println("Entry sendRestRequest..");

		ClientResponse response = null;
		WebResource webResource = null;

		try
		{
			if(reqString == null || reqString.trim().isEmpty())
			{
				System.out.println("Request should not be empty..");
				throw new Exception("Request should not be empty..", null); 
			}
			if(wsdlUrl == null || wsdlUrl.trim().isEmpty())
			{
				System.out.println("WSDL URL was not configured to invoke the service for interfaceID.." + interfaceId);
				throw new Exception("WSDL URL was not configured to invoke the service for interfaceID.." + interfaceId, null); 
			}
			if(userName == null || userName.trim().isEmpty())
			{
				System.out.println("Username was not configured to invoke the service for interfaceID.." + interfaceId);
				throw new Exception("Username was not configured to invoke the service for interfaceID.." + interfaceId, null); 
			}

			webResource = Client.create().resource(wsdlUrl);
			webResource.addFilter(new HTTPBasicAuthFilter(userName, password));
			response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, reqString);

			System.out.println("[response] " + response);

			if (response.getStatus() != 200)
			{
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			return response.getEntity(String.class);
		}
		catch(RuntimeException runtimeException)
		{

			throw runtimeException;
		}
		catch (Exception exception)
		{
			throw exception;
		}
		finally
		{
			response = null;
			webResource = null;


			System.out.println("Exit sendRestRequest..");

		}
	}

	public String sendSoapRequestWithAttachments(String wsdlUrl, String reqString, String userName, String password, Long interfaceId) throws Exception 
	{  
		System.out.println("InterfaceId :: " + interfaceId + " Entry sendSoapRequest :: wsdlUrl: " + wsdlUrl);

		URL url = null;
		URLConnection connection = null;
		Base64Converter base64Converter = null;
		OutputStreamWriter outStreamWriter = null;
		InputStreamReader inStreamReader = null;
		StringBuilder builder = null;
		String response = null;
		String credentials = null;
		String encoding = null;
		int length = 0;

		try
		{
			if(reqString == null || reqString.trim().isEmpty())
			{
				System.out.println("Request should not be empty..");
				throw new Exception("Request should not be empty..", null); 
			}
			if(wsdlUrl == null || wsdlUrl.trim().isEmpty())
			{
				System.out.println("WSDL URL was not configured to invoke the service for interfaceID.." + interfaceId);
				throw new Exception("WSDL URL was not configured to invoke the service for interfaceID.." + interfaceId, null); 
			}
			if(userName == null || userName.trim().isEmpty())
			{
				System.out.println("Username was not configured to invoke the service for interfaceID.." + interfaceId);
				throw new Exception("Username was not configured to invoke the service for interfaceID.." + interfaceId, null); 
			}

			url = new URL(wsdlUrl);
			length = reqString.length();
			credentials = userName + ":" + password;        
			base64Converter = new Base64Converter();
			encoding = base64Converter.encode(credentials.getBytes("UTF-8"));

			connection = url.openConnection();
			connection.setRequestProperty("Authorization", String.format("Basic %s", encoding));
			connection.setDoOutput( true );  
			connection.setDoInput( true );   
			connection.setRequestProperty( "Content-Type", "text/xml; charset=utf-8" );  
			connection.setRequestProperty( "Content-Length", Integer.toString( length ) );  
			connection.setConnectTimeout(3600000);
			connection.setReadTimeout(3600000);
			connection.connect();

			
			outStreamWriter = new OutputStreamWriter( connection.getOutputStream() );   
			outStreamWriter.write( reqString, 0, length );  
			outStreamWriter.flush();  

			inStreamReader = new InputStreamReader( connection.getInputStream() );  
			builder = new StringBuilder();     
			int ch = inStreamReader.read();  

			while( ch != -1 )
			{  
				builder.append((char)ch);  
				ch = inStreamReader.read();  
			}  

			response = builder.toString();
			response = response.replace("&quot;", "\"");

			return response;
		}
		catch(Exception exception)
		{
//			exception.printStackTrace();
			throw exception;
		}
		finally
		{
			if(outStreamWriter!=null)
			{
				outStreamWriter.close();
			}
			if(inStreamReader!= null)
			{
				inStreamReader.close();
			}
			outStreamWriter = null;
			url = null;
			connection = null;
			base64Converter = null;
			inStreamReader = null;
			builder = null;
			response = null;
			credentials = null;
			encoding = null;


			System.out.println("InterfaceId :: " + interfaceId + " Exit sendSoapRequest..");

		}
	}     

}
