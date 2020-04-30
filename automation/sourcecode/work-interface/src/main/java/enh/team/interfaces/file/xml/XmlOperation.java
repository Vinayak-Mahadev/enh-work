package enh.team.interfaces.file.xml;

import java.io.StringReader;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;

import enh.team.interfaces.util.CacheLoader;

public class XmlOperation {


	public static String getXmlElementValue(String xml, String tagName){
		if(xml.contains("<"+tagName+">")){

			return xml.split("<"+tagName+">")[1].split("</"+tagName+">")[0];
		}
		else{
			return "";
		}
	}



	/**
	 * <pre>
	 * <b>Algorithm</b><br>
	 * 	Formating xml as per industry standards...
	 * </pre>
	 * @author Vinayak Mahadev
	 * @param xml
	 * @return
	 */
	public static String formatXml(String xml) 
	{
		try 
		{
			final InputSource src = new InputSource(new StringReader(xml));
			final Node document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(src).getDocumentElement();
			final Boolean keepDeclaration = Boolean.valueOf(xml.startsWith("<?xml"));

			//May need this: System.setProperty(DOMImplementationRegistry.PROPERTY,"com.sun.org.apache.xerces.internal.dom.DOMImplementationSourceImpl");
			final DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
			final DOMImplementationLS impl = (DOMImplementationLS) registry.getDOMImplementation("LS");
			final LSSerializer writer = impl.createLSSerializer();

			writer.getDomConfig().setParameter("format-pretty-print", Boolean.TRUE); // Set this to true if the output needs to be beautified.
			writer.getDomConfig().setParameter("xml-declaration", keepDeclaration); // Set this to true if the declaration is needed to be outputted.

			return writer.writeToString(document);
		} 
		catch (Exception e) 
		{
			throw new RuntimeException(e);
		}
	}


	public static void testJsonXmlDataConverterLocal() 
	{
		JsonXmlDataConverterLocal jsonXmlDataConverterLocal = new JsonXmlDataConverterLocal();
		long TRANSFER_BALANCE_INTERFACE_ID = 1136l;	
		Map<String, String> attributes = CacheLoader.getTransferBalAttributes();
		String jsonRequestData = "		{\r\n" + 
				"                \"payload\": {\r\n" + 
				"                        \"currency\": \"INR\",\r\n" + 
				"                        \"trans_amt\": \"122.11\",\r\n" + 
				"                        \"destn_ref_code\": \"001\",\r\n" + 
				"                        \"operator_id\": \"OP01\",\r\n" + 
				"                        \"org_ref_code\": \"0091\",\r\n" + 
				"                        \"user_id\": \"123\",\r\n" + 
				"                        \"uname\": \"CAN01\",\r\n" + 
				"                        \"trans_dt\": \"2019-06-13T00:00:00.000\",\r\n" + 
				"                        \"pin\": \"2112\",\r\n" + 
				"                        \"acc_type\": \"\"\r\n" + 
				"                }\r\n" + 
				"        }";
		try
		{
			jsonXmlDataConverterLocal.processRequest(attributes, TRANSFER_BALANCE_INTERFACE_ID, jsonRequestData, null);;

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}




}
