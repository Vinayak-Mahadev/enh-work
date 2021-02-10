package enh.team.interfaces.test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.util.Base64Converter;
import org.apache.xmlbeans.impl.soap.AttachmentPart;
import org.apache.xmlbeans.impl.soap.MessageFactory;
import org.apache.xmlbeans.impl.soap.MimeHeaders;
import org.apache.xmlbeans.impl.soap.SOAPConnection;
import org.apache.xmlbeans.impl.soap.SOAPConnectionFactory;
import org.apache.xmlbeans.impl.soap.SOAPHeader;
import org.apache.xmlbeans.impl.soap.SOAPMessage;

public class TestSndInterfaceResponseService {


	public static void main(String[] args) 
	{
		List<String> filePaths = new ArrayList<String>();
		filePaths.add("C:\\Users\\vinayak\\Desktop\\films.txt");
		filePaths.add("C:\\Users\\vinayak\\Desktop\\sample.txt");
		sendAttachmentSoapReq(filePaths);		
	}


	public static void sendAttachmentSoapReq(List<String> filePaths) 
	{
		FileReader fr = null;
		BufferedReader br = null;
		AttachmentPart attachmentPart = null;
		MessageFactory messageFactory = null;
		SOAPConnectionFactory soapConnectionFactory = null;
		SOAPConnection soapConnection = null;
		SOAPMessage responseMsg = null;
		SOAPMessage requestMessage = null;
		MimeHeaders mimeHeaders = null;
		SOAPHeader header = null;
		String wsdlUrl   = "http://50.17.26.200:8080/IntegrationServices/IntegrationManagement?wsdl";
		String userName  = "admin";
		String password  = "admin";
		String authorization = null;
		String line = "";
		String stringContent = "";
		URL url = null;
		File file = null;
		InputStream is = null;
		String bodyStr = "";
		try
		{
			messageFactory = MessageFactory.newInstance();
			requestMessage = messageFactory.createMessage();
			header = requestMessage.getSOAPHeader();
			header.detachNode();

			for (String filepath : filePaths) 
			{
				file = new File(filepath);
				System.out.println(file.getName());
				bodyStr = bodyStr + entry_key_value.replace("FILE_NAME", file.getName()).replace("FILE_TYPE", "cid:" + file.getName().length());
			}

			is = new ByteArrayInputStream(xml.replace("BODY_STR", bodyStr).getBytes());
			requestMessage = MessageFactory.newInstance().createMessage(null, is);

			for (int i = 0; i < filePaths.size(); i++) 
			{
				stringContent = "";
				attachmentPart = requestMessage.createAttachmentPart();
				file = new File(filePaths.get(i));
				fr = new FileReader(file);
				br = new BufferedReader(fr);

				while ((line = br.readLine()) != null) 
					stringContent = stringContent.concat(line + "\n");
				attachmentPart.setContent(stringContent, "text/plain");
//				attachmentPart.setContentId("<" + file.getName().length()+">");
				attachmentPart.setContentId("cid:<" + file.getName().length()+">");
				requestMessage.addAttachmentPart(attachmentPart);
				
			}

			url = new URL(wsdlUrl);
			URLConnection connection = url.openConnection();
			connection.setConnectTimeout(10000); // 10 sec
			connection.setReadTimeout(60000); // 1 min
			
			authorization = new Base64Converter().encode((userName+":"+password).getBytes("UTF-8"));
			mimeHeaders = requestMessage.getMimeHeaders();
			mimeHeaders.addHeader("Authorization", "Basic " + authorization);
			soapConnectionFactory = SOAPConnectionFactory.newInstance();
			soapConnection = soapConnectionFactory.createConnection();
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
//			requestMessage.writeTo(out);
			
//			System.out.println("requestMessage :: " + new String(out.toByteArray()));
			
			responseMsg = soapConnection.call(requestMessage, url);

			responseMsg.writeTo(out);
			//			String strMsg = new String(out.toByteArray());

			//			System.out.println("strMsg :: " + strMsg);

			System.out.println("responseMsg :: " + new String(out.toByteArray()));
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		} 
		finally {
			System.gc();
			System.out.println("GC activated");
		}
	}

	static String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:int=\"http://com/enhancesys/integration/services/interfaces/IntegrationManagement\">\r\n"
			+ "   <soapenv:Header/>\r\n"
			+ "   <soapenv:Body>\r\n"
			+ "      <int:uploadMultipleFileWithMap>\r\n"
			+ "         <dataHandlerMap>\r\n"
			+ "            <entry>\r\n"
			+ "               BODY_STR\r\n"
			+ "            </entry>\r\n"
			+ "         </dataHandlerMap>\r\n"
			+ "      </int:uploadMultipleFileWithMap>\r\n"
			+ "   </soapenv:Body>\r\n"
			+ "</soapenv:Envelope>";

	static String entry_key_value = "<key>FILE_NAME</key><value>FILE_TYPE</value>";

	
}


