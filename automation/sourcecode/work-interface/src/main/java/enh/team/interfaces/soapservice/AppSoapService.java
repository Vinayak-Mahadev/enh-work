package enh.team.interfaces.soapservice;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPMessage;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import enh.team.interfaces.soapservice.beans.AttachmentBean;
import enh.team.interfaces.soapservice.beans.DownloadFileBean;
import enh.team.interfaces.soapservice.beans.PropertyBean;
import enh.team.interfaces.soapservice.beans.UploadFileBean;


public class AppSoapService 
{

	public static void main(String[] args)
	{
		try 
		{

			uploadOrDownloadFilesSoapCall(SOAP_ENDPOINT_URL, USERNAME, PASSWORD, prepareUploadFilesRequest(), OUTPUT_LOC,  "UPLOAD");
			uploadOrDownloadFilesSoapCall(SOAP_ENDPOINT_URL, USERNAME, PASSWORD, prepareDownloadFilesRequest(), OUTPUT_LOC,  "DOWNLOAD");

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			System.out.println("Tk u..");	
		}
	}

	public static String prepareUploadFilesRequest() throws Exception 
	{
		Set<PropertyBean> propertyBeanSet = new HashSet<PropertyBean>();
		Set<AttachmentBean> attachmentBeanSet = new HashSet<AttachmentBean>();

		propertyBeanSet.add(new PropertyBean("Request Id", "request number from MOBI"));
		propertyBeanSet.add(new PropertyBean("Payout Id", "MOBI internal Payout id"));
		propertyBeanSet.add(new PropertyBean("Payout type", "Individual for Dealer and Group for CSO"));
		propertyBeanSet.add(new PropertyBean("Payout Name", "Subject from the IOM document created by MOBI. See attachment"));
		propertyBeanSet.add(new PropertyBean("Payout to", "Vendor Id"));

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("Request Id", "request number from MOBI");
		jsonObject.put("Payout Id", "MOBI internal Payout id");
		jsonObject.put("Payout type", "Individual for Dealer and Group for CSO");
		jsonObject.put("Payout Name", "Subject from the IOM document created by MOBI. See attachment");
		jsonObject.put("Payout to", "Vendor Id");

		propertyBeanSet.add(new PropertyBean("payload", jsonObject.toString()));

		attachmentBeanSet.add(new AttachmentBean("Attachment1.jpg", getDataHandlerFromFDS("C:\\Users\\vinayak\\Desktop\\SOAP_TEST\\Attachment1.jpg")));
		attachmentBeanSet.add(new AttachmentBean("Attachment2.jpg", getDataHandlerFromFDS("C:\\Users\\vinayak\\Desktop\\SOAP_TEST\\Attachment2.jpg")));


		UploadFileBean uploadFileBean = new UploadFileBean();
		uploadFileBean.setInterfaceId(1001l);
		uploadFileBean.setAttachments(attachmentBeanSet);
		uploadFileBean.setProperties(propertyBeanSet);

		return uploadFileBean.createRequestForInterface();
	}

	public static String prepareDownloadFilesRequest() throws Exception 
	{
		Set<PropertyBean> propertyBeanSet = new HashSet<PropertyBean>();
		Set<AttachmentBean> attachmentBeanSet = new HashSet<AttachmentBean>();

		propertyBeanSet.add(new PropertyBean("Request Id", "request number from MOBI"));
		propertyBeanSet.add(new PropertyBean("Payout Id", "MOBI internal Payout id"));
		propertyBeanSet.add(new PropertyBean("Payout type", "Individual for Dealer and Group for CSO"));
		propertyBeanSet.add(new PropertyBean("Payout Name", "Subject from the IOM document created by MOBI. See attachment"));
		propertyBeanSet.add(new PropertyBean("Payout to", "Vendor Id"));

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("Request Id", "request number from MOBI");
		jsonObject.put("Payout Id", "MOBI internal Payout id");
		jsonObject.put("Payout type", "Individual for Dealer and Group for CSO");
		jsonObject.put("Payout Name", "Subject from the IOM document created by MOBI. See attachment");
		jsonObject.put("Payout to", "Vendor Id");

		propertyBeanSet.add(new PropertyBean("payload", jsonObject.toString()));

		attachmentBeanSet.add(new AttachmentBean("Attachment1.jpg", null));
		attachmentBeanSet.add(new AttachmentBean("Attachment2.jpg", null));


		DownloadFileBean downloadFileBean = new DownloadFileBean();
		downloadFileBean.setInterfaceId(1001l);
		downloadFileBean.setAttachments(attachmentBeanSet);
		downloadFileBean.setProperties(propertyBeanSet);
		
		return downloadFileBean.createRequestForInterface();
	}

	public static void uploadOrDownloadFilesSoapCall(String soapEndpointUrl, String username, String password, String request, String downloadFileLoc, String uploadORdownload) throws Exception
	{
		String RESPONSE = null;
		String REQUEST  = null;
		InputStream inputStream = null;
		SOAPMessage soapRequest = null;
		SOAPMessage soapResponse = null;
		Map<String, String> attachmentsMap = null;
		try 
		{
			inputStream = new ByteArrayInputStream(validateSoapRequest(request));
			soapRequest =  MessageFactory.newInstance().createMessage(null, inputStream);
			soapResponse = sendSoapRequest(soapRequest, soapEndpointUrl, username, password);

			attachmentsMap = writeAttachmentsAsFile(soapResponse, downloadFileLoc);
			REQUEST  = getSOAPMessageAsString(soapRequest);
			RESPONSE = getSOAPMessageAsString(soapResponse);

			System.out.println("RESPONSE Attachments :: " + attachmentsMap);
			//			System.out.println("REQUEST :: " + REQUEST);
			//			System.out.println("RESPONSE :: " + RESPONSE);
			System.out.println("REQUEST :: File written path :: " + writeIntoFile(INPUT_LOC, uploadORdownload+"_FILE_REQUEST.xml", REQUEST, false));
			System.out.println("RESPONSE :: File written path :: " + writeIntoFile(OUTPUT_LOC, uploadORdownload+"_FILE_RESPONSE.xml", RESPONSE, false));
		}
		finally 
		{
			if(inputStream != null)
				inputStream.close();
			soapRequest = null;
			soapResponse = null;
			RESPONSE = null;
			REQUEST  = null;
			inputStream = null;
			attachmentsMap = null;
		}
	}

	public static Map<String, String> writeAttachmentsAsFile(SOAPMessage soapMessage, String outputPath) throws Exception
	{
		SOAPBody soapBody = null;
		Node attachmentsNode = null;
		NodeList attachmentsNodeList = null;
		Node node = null;
		String name = null;
		String value = null;
		Map<String, String> map = new HashMap<String, String>();
		try 
		{
			soapBody = soapMessage.getSOAPBody();
			attachmentsNode = soapBody.getFirstChild().getFirstChild().getLastChild();
			attachmentsNodeList = attachmentsNode.getChildNodes();

			for (int i = 0; i < attachmentsNodeList.getLength(); i++) 
			{
				node = attachmentsNodeList.item(i);
				name = null;
				value = null;
				for(int j = 0; j < node.getChildNodes().getLength(); j++)
				{
					if(node.getChildNodes().item(j).getNodeName().equals("name"))
						name = node.getChildNodes().item(j).getTextContent();
					if(node.getChildNodes().item(j).getNodeName().equals("value"))
						value = node.getChildNodes().item(j).getTextContent();
				}

				if(value != null && !value.trim().isEmpty())
					map.put(name, writeIntoFile(outputPath, name, value, true));
			}
		}
		catch (Exception e) 
		{
			throw new  Exception("Internal :: Consuming attachments file data issue issue", e);
		}
		finally 
		{
			soapBody = null;
			attachmentsNode = null;
			attachmentsNodeList = null;
			node = null;
			name = null;
			value = null;
		}
		return map;
	}


	public static String writeIntoFile(String outputPath, String fileName, String data, boolean withDecode) throws Exception
	{
		FileOutputStream fStream = null;
		String path = outputPath + fileName;
		File file = null;
		try 
		{
			file = new File(path);
			if(file!= null && !file.isDirectory() && file.isFile()) // check file already present are not
			{
				// need to handle

			}
			fStream = new FileOutputStream(file);
			if(withDecode)
				fStream.write(Base64.decodeBase64(data));
			else
				fStream.write(data.getBytes());
			return path;
		} 
		catch (Exception e) 
		{
			throw new  Exception("Internal :: Writing file data issue issue", e);
		}
		finally 
		{
			if(fStream != null)
				fStream.close();
			file = null;
			fStream = null;
		}
	}


	@SuppressWarnings("restriction")
	public static SOAPMessage sendSoapRequest(SOAPMessage request, String soapEndpointUrl, String username, String password) throws Exception
	{
		SOAPMessage response = null;
		SOAPConnectionFactory soapConnectionFactory = null;
		SOAPConnection soapConnection = null;
		MimeHeaders mimeHeaders = null;
		String basicAuth = null;
		try 
		{
			basicAuth = new sun.misc.BASE64Encoder().encode((username+":"+password).getBytes());
			mimeHeaders = request.getMimeHeaders();
			mimeHeaders.addHeader("Authorization", "Basic " + basicAuth);

			soapConnectionFactory = SOAPConnectionFactory.newInstance();
			soapConnection = soapConnectionFactory.createConnection();
			response = soapConnection.call(request, soapEndpointUrl);

		}
		catch (Exception e) 
		{
			throw new  Exception("Internal :: SEND SOAPMessage service issue", e);
		}
		finally 
		{
			soapConnectionFactory = null;
			soapConnection = null;
			mimeHeaders = null;
			basicAuth = null;
		}
		return response;
	}


	public static String getSOAPMessageAsString(SOAPMessage message) throws Exception
	{
		String response = null;
		ByteArrayOutputStream outputStream = null;
		try 
		{
			outputStream = new ByteArrayOutputStream();
			message.writeTo(outputStream);
			response = new String(outputStream.toByteArray());
		}
		catch (Exception e) 
		{
			throw new  Exception("Internal :: SOAPMessage conversion issue", e);
		}
		return response;
	}


	public static byte[] validateSoapRequest(String str) throws Exception
	{
		try 
		{
			return str.replaceAll(System.getProperty("line.separator"), "").replaceAll("\r", "").replaceAll("\t", " ").replaceAll("  ", "").getBytes();
		}
		catch (Exception e) 
		{
			throw new  Exception("Internal :: String Validation issue", e);
		}
	}


	public static DataHandler getDataHandlerFromFDS(String path) throws Exception
	{
		DataHandler dataHandler = null;
		DataSource dataSource = null;
		try 
		{
			dataSource = new FileDataSource(path);
			dataHandler = new DataHandler(dataSource);
		}
		catch (Exception e) 
		{
			throw new  Exception("Internal :: DataHandler conversion issue", e);
		}
		finally 
		{
			dataSource = null;	
		}
		return dataHandler;
	}

	public static String USERNAME = "admin";
	public static String PASSWORD = "admin";
	public static String SOAP_ENDPOINT_URL = "http://50.17.26.200:8080/IntegrationServices/IntegrationManagement?wsdl";
	public static String OUTPUT_LOC = "C:\\Users\\vinayak\\Desktop\\SOAP_TEST\\RESPONSE\\";
	public static String INPUT_LOC = "C:\\Users\\vinayak\\Desktop\\SOAP_TEST\\";


}
