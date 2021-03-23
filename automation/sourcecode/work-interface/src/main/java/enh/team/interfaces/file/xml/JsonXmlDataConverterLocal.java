package enh.team.interfaces.file.xml;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.finevm.enh.util.IWorkConstants;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;

import enh.team.interfaces.util.CacheLoader;
import enh.team.interfaces.util.MongoUtil;

/**
 * <b>Purpose:</b><br>
 * 		Implementation of JsonXmlDataConverter services..<br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * 		Enhancesys Innovations 2017<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 * Sl No   Modified Date        Author</b>
 * ==============================================
 * 1        20-12-2017		   Suresh Upparu
 *		 -- Base Release	
 * </pre>
 * 
 * <br>
 */

public class JsonXmlDataConverterLocal
{
	/**
	 * @author Suresh Upparu
	 * <b>Algorithm</b>
	 * <pre>
	 * 
	 * </pre>
	 * @param interfaces
	 * @param interfaceId
	 * @param requestData
	 * @param templateName
	 * @return String
	 * @throws Exception
	 */
	public String processRequest(Map<String, String> attributes, Long interfaceId, String requestData, String templateName) throws Exception
	{
		Long startTime = System.currentTimeMillis();
		Long endTime = null;

		System.out.println("JsonXmlDataConverter : Entry processRequest.. interfaceId : " + interfaceId + " : requestData : " + requestData);

		DocumentBuilderFactory dbFactory = null;
		DocumentBuilder dBuilder = null;
		JSONObject sourceJson = null;
		Document templateDoc = null;
		Document newDoc = null;
		Element rootElement = null;
		NodeList templateNodeList = null;
		Node templateNode = null;
		File xmlFile = null;
		XPathFactory factory = null;
		XPath xPath = null;
		Map<String, Long> kycMap = null; 
		String templateSelectAttr = null;
		String convertedRequestData = null;
		String templateFileName = null;
		String templateDir = null;
		String orgTypeValue = null;
		try
		{
			if(interfaceId == null)
			{
				System.out.println("Interface ID should not be empty..");
			}
			if(requestData == null)
			{
				System.out.println("RequestData should not be empty..");
			}

			kycMap = new HashMap<String, Long>();

			dbFactory = DocumentBuilderFactory.newInstance();
			dBuilder = dbFactory.newDocumentBuilder();

			sourceJson = (JSONObject) new JSONParser().parse(requestData);
			System.out.println("Payload : " + sourceJson.get("payload"));

			templateSelectAttr = attributes.get(ORG_TYPE_PATH_ATTR);
			if(templateSelectAttr != null && !templateSelectAttr.trim().isEmpty())
			{
				orgTypeValue = getFieldValue((BasicDBObject) JSON.parse(requestData), templateSelectAttr).toString();
				templateFileName = attributes.get(TEMPLATE_NAME_ATTR + "_" + orgTypeValue);
			}
			else
			{
				if(templateName != null)
					templateFileName = templateName;
				else
					templateFileName = attributes.get(TEMPLATE_NAME_ATTR);
			}

			if(templateFileName == null)
			{
				System.out.println("Template file does not configure for interfaceId : " + interfaceId);
				throw new Exception("Template file does not configure for interfaceId : " + interfaceId, null);
			}

			/*if(RECEIVE_TRANS_TYPE.equals(interfaces.getTransactionType()))
				templateDir = INTEGRATION_XML_RESPONSE_TEMPLATE_PATH;
			else*/
			templateDir = INTEGRATION_XML_REQUEST_TEMPLATE_PATH;

			xmlFile = new File(templateDir + templateFileName);
			System.out.println("templateFileName :"+templateFileName);
			templateDoc = dBuilder.parse(xmlFile);

			templateNodeList = templateDoc.getChildNodes();

			factory = XPathFactory.newInstance();
			xPath = factory.newXPath();

			newDoc = dBuilder.newDocument();
			rootElement = newDoc.createElement(templateDoc.getDocumentElement().getNodeName());
			newDoc.appendChild(rootElement);

			int nsLength = templateDoc.getDocumentElement().getAttributes().getLength();

			for(int i = 0; i < nsLength; i ++)
			{
				newDoc.getDocumentElement().setAttribute(templateDoc.getDocumentElement().getAttributes().item(i).getNodeName(), 
						templateDoc.getDocumentElement().getAttributes().item(i).getNodeValue());
			}

			for(int i = 0; i < templateNodeList.getLength(); i++)
			{
				templateNode = templateNodeList.item(i);

				if(templateNode.getNodeType() == Node.ELEMENT_NODE)
				{
					//					System.out.println(test.nodeToString(node));
					generateXml(templateNode, rootElement, newDoc, (JSONObject)sourceJson.get("payload"), xPath, kycMap);
				}
			}

			newDoc = validateDocumentProcess(newDoc, VALIDATE_NODE_BY_CHILD_BEFORE_PROCESS, "validateNodeByChildElemet");

			convertedRequestData = this.documentToString(newDoc);
			System.out.println("End ::  " + System.currentTimeMillis() + " -- " + convertedRequestData);
			return convertedRequestData;
		}
		catch (NullPointerException nullPointerException)
		{
			System.out.println("Error in initialising entity manager" +  nullPointerException);
		}
		catch(Exception applicationException)
		{
			System.out.println(applicationException.getMessage() +  applicationException);
			throw applicationException;
		}

		finally
		{
			endTime = System.currentTimeMillis() ; 
			dbFactory = null;
			dBuilder = null;
			sourceJson = null;
			templateDoc = null;
			newDoc = null;
			rootElement = null;
			templateNodeList = null;
			templateNode = null;
			xmlFile = null;
			factory = null;
			xPath = null;
			convertedRequestData = null;
			templateFileName = null;
			templateDir = null;
			kycMap = null; 
			templateSelectAttr = null;
			orgTypeValue = null;

			System.out.println("JsonXmlDataConverter : Exit processRequest..processingTime : " + (endTime - startTime));
			endTime = null;
			startTime = null;
		}
		return convertedRequestData;
	}

	/**
	 * @author Suresh Upparu
	 * <b>Algorithm</b>
	 * <pre>
	 * 
	 * </pre>
	 * @param templateNode
	 * @param rootElement
	 * @param newDoc
	 * @param sourceElement
	 * @param xPath
	 */
	public void generateXml(Node templateNode, Element rootElement, Document newDoc, JSONObject sourceElement, XPath xPath, Map<String, Long> kycMap) throws Exception
	{

		System.out.println("JsonXmlDataConverter : Entry generateXml..");

		Element element = null;
		Element newRootElement = null;
		JSONArray jsonArr = null;
		Object result = null;
		DateFormat fromDateFormat = null;
		DateFormat toDateFormat = null;
		String value = null;
		String property = null;

		try
		{
			//			System.out.println("sourceElement : " + sourceElement);
			for(int j = 0; j < templateNode.getChildNodes().getLength(); j++)
			{
				if(templateNode.getChildNodes().item(j).getNodeType() == Node.ELEMENT_NODE)
				{
					element = (Element) templateNode.getChildNodes().item(j);
					if(TRUE.equalsIgnoreCase(element.getAttribute(IS_MULTIPLE)))
					{
						if(element.getAttribute(SOURCE) == null || element.getAttribute(SOURCE).trim().isEmpty())
							continue;

						//						System.out.println("isMultiple : " + element.getAttribute(IS_MULTIPLE));
						result = getFieldValue(element.getAttribute(SOURCE), sourceElement);
						if(result instanceof JSONArray)
						{
							jsonArr = (JSONArray) result;
							for(int i = 0; i < jsonArr.size(); i ++)
							{
								if(jsonArr.get(i) instanceof JSONObject)
								{
									newRootElement = newDoc.createElement(element.getNodeName());
									rootElement.appendChild(newRootElement);

									//									System.out.println("(JSONObject) jsonArr.get(i) : " + (JSONObject) jsonArr.get(i));
									generateXml(element, newRootElement, newDoc, (JSONObject) jsonArr.get(i), xPath, kycMap);
								}
							}
						}
					}
					else
					{
						//System.out.println("Element : " + element.getNodeName());
						if(element.getAttribute(SOURCE) != null && !element.getAttribute(SOURCE).trim().isEmpty())
						{
							if(element.getAttribute(SOURCE).equals("TRANS_ID_IN_CONVERTER_TEMPLATE"))
							{
								value = element.getAttribute(SOURCE);
							}
							else
							{
								System.out.println("element.getAttribute(SOURCE) : " + element.getAttribute(SOURCE));
								result = getFieldValue(element.getAttribute(SOURCE), sourceElement);
								System.out.println("Result : " + result);
								if(result instanceof String)
									value = result.toString();
								else if(result instanceof JSONObject)
								{
									if(element.getAttribute(FETCH_ORDER) != null && !element.getAttribute(FETCH_ORDER).trim().isEmpty())
									{
										value = fetchValueByOrder(element, (JSONObject) result);
									}
								}
								else if(result instanceof JSONArray)
								{
									if(element.getAttribute(OPERATION) != null && !element.getAttribute(OPERATION).trim().isEmpty() 
											&& MERGE.equalsIgnoreCase(element.getAttribute(OPERATION).trim()) 
											&& element.getAttribute(DELIMITER) != null && !element.getAttribute(DELIMITER).trim().isEmpty())
									{
										value = "";
										JSONArray tempArr = (JSONArray) result;
										for(int i = 0; i < tempArr.size(); i ++)
										{
											if(i + 1 == tempArr.size())
												value = value + tempArr.get(i);
											else 
												value = value + tempArr.get(i) + element.getAttribute(DELIMITER).trim();
										}
									}
									else if((element.getAttribute(SOURCE_TAG) == null || element.getAttribute(SOURCE_TAG).trim().isEmpty())
											&& (element.getAttribute(VALUE) == null || element.getAttribute(VALUE).trim().isEmpty())
											&& (element.getAttribute(TARGET_TAG) != null && !element.getAttribute(TARGET_TAG).trim().isEmpty()))
									{
										value = getTargetVal((JSONArray) result, element.getAttribute(TARGET_TAG));
									}
									else if(element.getAttribute(SOURCE_TAG) != null && !element.getAttribute(SOURCE_TAG).trim().isEmpty()
											&& element.getAttribute(TARGET_TAG) != null && !element.getAttribute(TARGET_TAG).trim().isEmpty() 
											&& element.getAttribute(VALUE) != null && !element.getAttribute(VALUE).trim().isEmpty())
									{
										value = getTargetValue((JSONArray) result, element.getAttribute(SOURCE_TAG), element.getAttribute(VALUE), element.getAttribute(TARGET_TAG));
									}
								}
							}
						}

						if(element.getAttribute(DEFAULT_VALUE) != null && !element.getAttribute(DEFAULT_VALUE).trim().isEmpty())
						{
							if(element.getNodeName().equalsIgnoreCase(KYC_FIELD_NAME_TAG) 
									&& kycMap.containsKey(element.getAttribute(DEFAULT_VALUE).trim()) && kycMap.get(element.getAttribute(DEFAULT_VALUE).trim()).longValue() == 0)
							{
								newRootElement = newDoc.createElement(element.getNodeName());
								rootElement.appendChild(newRootElement);
								System.out.println("newRootElement : " + newRootElement.getNodeName() + " : Parent : " + newRootElement.getParentNode().getNodeName());
								newRootElement.getParentNode().getParentNode().removeChild(newRootElement.getParentNode());
								element.getParentNode().getParentNode().removeChild(element.getParentNode());
								continue;
							}

							if(value == null || value.trim().isEmpty())
								value = element.getAttribute(DEFAULT_VALUE); 
						}
						if(element.getAttribute(REQUIRED) != null && !element.getAttribute(REQUIRED).trim().isEmpty())
						{
							if(FALSE.equals(element.getAttribute(REQUIRED)))
							{
								//								System.out.println("element.getAttribute(LOOKUP_TYPE) : " + element.getAttribute(LOOKUP_TYPE));
								if((value == null || value.trim().isEmpty()) && element.getAttribute(LOOKUP_TYPE) == null)
								{
									continue;
								}
							}
						}

						newRootElement = newDoc.createElement(element.getNodeName());
						rootElement.appendChild(newRootElement);

						if(element.getAttribute(HEADER) != null && !element.getAttribute(HEADER).trim().isEmpty() && TRUE.equalsIgnoreCase(element.getAttribute(HEADER)))
						{
							int attributeSize = element.getAttributes().getLength();
							for(int i = 0; i < attributeSize; i ++)
							{
								if(!HEADER.equalsIgnoreCase(element.getAttributes().item(i).getNodeName()))
								{
									newRootElement.setAttribute("header", "true");
									newRootElement.setAttribute(element.getAttributes().item(i).getNodeName(), element.getAttributes().item(i).getNodeValue());
								}
							}
						}

						System.out.println(element.getNodeName() + " : element.getChildNodes().getLength() : " + element.getChildNodes().getLength());
						if(element.getChildNodes().getLength() > 0)
						{
							generateXml(element, newRootElement, newDoc, sourceElement, xPath, kycMap);
						}
						else
						{
							if(element.getAttribute(LOOKUP_TYPE) != null && !element.getAttribute(LOOKUP_TYPE).trim().isEmpty())
							{
								if(element.getAttribute(ENTITY_ID) != null && !element.getAttribute(ENTITY_ID).trim().isEmpty())
									value = element.getAttribute(ENTITY_ID);

								if(value != null && !value.trim().isEmpty())
								{
									//lookup-type="3"
									if(element.getAttribute(LOOKUP_TYPE).equalsIgnoreCase(LTYPE_MONGO_MAPPING))
									{
										if(element.getAttribute(TARGET_FIELD) != null && !element.getAttribute(TARGET_FIELD).trim().isEmpty())
											value = getMongoValue(element.getAttribute(SCHEMA), element.getAttribute(COLLECTION), element.getAttribute(TYPE), element.getAttribute(TARGET_FIELD), value);
										else
											value = getMongoValue(element.getAttribute(SCHEMA), element.getAttribute(COLLECTION), element.getAttribute(TYPE), null, value);
									}
									else if(element.getAttribute(LOOKUP_TYPE).equalsIgnoreCase(LTYPE_ENTITY_MAPPING))
									{
										value = CacheLoader.getLookupEntity(Long.valueOf(element.getAttribute(ENTITY_TYPE_ID)), Long.valueOf(value), INTERFACE_APPLICATION_ID);
									}
									else if(element.getAttribute(LOOKUP_TYPE).equalsIgnoreCase(LTYPE_DATE_MAPPING))
									{
										//lookup-type="4"
										Date received = null;

										if(element.getAttribute(DATE_FROM_FORMAT) != null && !element.getAttribute(DATE_FROM_FORMAT).trim().isEmpty() 
												&& element.getAttribute(DATE_TO_FORMAT) != null && !element.getAttribute(DATE_TO_FORMAT).trim().isEmpty())
										{
											fromDateFormat = new SimpleDateFormat(element.getAttribute(DATE_FROM_FORMAT));
											toDateFormat = new SimpleDateFormat(element.getAttribute(DATE_TO_FORMAT));
											if(value != null && !value.trim().isEmpty())
											{
												received = fromDateFormat.parse(value);
												value = toDateFormat.format(received);
											}
											else
												value = toDateFormat.format(new Date());
										}
										else
										{
											if(value != null && !value.trim().isEmpty())
											{
												received = format1.parse(value);
												value = format.format(received);
											}
											else
												value = format.format(new Date());
										}
										received = null;
									}
									else if(element.getAttribute(LOOKUP_TYPE).equalsIgnoreCase(LTYPE_COMPLEX_MONGO_MAPPING))
									{
										//lookup-type="6"
										String temp = null;
										if(element.getAttribute(TARGET_FIELD) != null && !element.getAttribute(TARGET_FIELD).trim().isEmpty())
											temp = getMongoValue(element.getAttribute(SCHEMA), element.getAttribute(COLLECTION), element.getAttribute(TYPE), element.getAttribute(TARGET_FIELD), value);
										else
											temp = getMongoValue(element.getAttribute(SCHEMA), element.getAttribute(COLLECTION), element.getAttribute(TYPE), null, value);

										//										System.out.println("temp value : " + temp);
										if(temp == null || temp.trim().isEmpty())
											value = null;
										else
										{
											if(element.getAttribute(TARGET_FIELD_1) != null && !element.getAttribute(TARGET_FIELD_1).trim().isEmpty())
												value = getMongoValue(element.getAttribute(SCHEMA_1), element.getAttribute(COLLECTION_1), element.getAttribute(TYPE_1), element.getAttribute(TARGET_FIELD_1), temp);
											else
												value = getMongoValue(element.getAttribute(SCHEMA_1), element.getAttribute(COLLECTION_1), element.getAttribute(TYPE_1), null, temp);
										}
										temp = null;
									}
								}

								if(element.getAttribute(LOOKUP_TYPE).equalsIgnoreCase(LTYPE_CONDITION_MAPPING))
								{
									//lookup-type="5"
									Object temp = null;

									if(element.getAttribute(CONDITION_TAG) != null && !element.getAttribute(CONDITION_TAG).trim().isEmpty() 
											&& element.getAttribute(CONDITION_VALUE) != null && !element.getAttribute(CONDITION_VALUE).trim().isEmpty())
									{
										temp = getFieldValue(element.getAttribute(CONDITION_TAG), sourceElement);
										//										System.out.println("FieldValue : " + temp);
										if(temp != null && temp.toString().trim().equalsIgnoreCase(element.getAttribute(CONDITION_VALUE).trim()))
											element.setAttribute(REQUIRED, "true");
									}
									temp = null;
									//									System.out.println("element required : " + element.getAttribute(REQUIRED));
								}
								else if(element.getAttribute(LOOKUP_TYPE).equalsIgnoreCase(LTYPE_DATE_MAPPING))
								{
									if((value == null || value.trim().isEmpty()) && 
											(element.getAttribute(REQUIRED) != null && !element.getAttribute(REQUIRED).trim().isEmpty() 
											&& TRUE.equalsIgnoreCase(element.getAttribute(REQUIRED))))
									{
										if(element.getAttribute(DATE_TO_FORMAT) != null && !element.getAttribute(DATE_TO_FORMAT).trim().isEmpty())
										{
											toDateFormat = new SimpleDateFormat(element.getAttribute(DATE_TO_FORMAT));
											value = toDateFormat.format(new Date());
										}
										else
											value = format.format(new Date());
									}
								}

								if(element.getAttribute(SUB_LOOKUP_TYPE) != null && !element.getAttribute(SUB_LOOKUP_TYPE).trim().isEmpty())
								{
									if(element.getAttribute(SUB_LOOKUP_TYPE).equalsIgnoreCase(LTYPE_CONDITION_MAPPING))
									{
										//lookup-type="5"
										Object temp = null;

										if(element.getAttribute(CONDITION_TAG) != null && !element.getAttribute(CONDITION_TAG).trim().isEmpty() 
												&& element.getAttribute(CONDITION_VALUE) != null && !element.getAttribute(CONDITION_VALUE).trim().isEmpty())
										{
											temp = getFieldValue(element.getAttribute(CONDITION_TAG), sourceElement);
											//											System.out.println("FieldValue : " + temp);
											if(temp != null && temp.toString().trim().equalsIgnoreCase(element.getAttribute(CONDITION_VALUE).trim()))
												element.setAttribute(REQUIRED, "true");
											else
												value = null;
										}
										temp = null;
										//										System.out.println("element required : " + element.getAttribute(REQUIRED));
									}
								}
							}

							if(element.getAttribute(LETTER_CASE) != null && !element.getAttribute(LETTER_CASE).trim().isEmpty() && value != null && !value.trim().isEmpty())
							{
								if(element.getAttribute(LETTER_CASE).trim().equalsIgnoreCase(LOWER_CASE))
									value = value.toLowerCase();
								else if(element.getAttribute(LETTER_CASE).trim().equalsIgnoreCase(UPPER_CASE))
									value = value.toUpperCase();
							}

							if(element.getAttribute(APPEND_PREFIX) != null && !element.getAttribute(APPEND_PREFIX).trim().isEmpty() && value != null && !value.trim().isEmpty())
							{
								value = value + element.getAttribute(APPEND_PREFIX).trim();
							}

							if(element.getAttribute(APPEND_SUFFIX) != null && !element.getAttribute(APPEND_SUFFIX).trim().isEmpty() && value != null && !value.trim().isEmpty())
							{
								value = value + element.getAttribute(APPEND_SUFFIX).trim();
							}

							System.out.println(element.getNodeName() + " value : " + value);
							if(element.getAttribute(REQUIRED) != null && !element.getAttribute(REQUIRED).trim().isEmpty())
							{
								if(FALSE.equalsIgnoreCase(element.getAttribute(REQUIRED)))
								{
									if(value == null || value.trim().isEmpty())
									{
										continue;
									}
								}
								else if (TRUE.equalsIgnoreCase(element.getAttribute(REQUIRED)))
								{
									if(value == null || value.trim().isEmpty())
									{
										property = (element.getAttribute(VALUE) != null && !element.getAttribute(VALUE).trim().isEmpty()) ? element.getAttribute(VALUE) : element.getAttribute(SOURCE);
										System.out.println(element.getNodeName() + " is Mandatory, but " + property + " value is coming as empty.. value :: " + value);
									}
								}
							}

							System.out.println(element.getNodeName() + " : " + element.getAttribute(SOURCE) + " : " + value);
							rootElement.appendChild(newRootElement);
							newRootElement.appendChild(newDoc.createTextNode(value));
							value = "";
						}
					}
				}
			}
		}
		catch(NullPointerException nullPointerException)
		{
			System.out.println("Unable to get the value from the element : " + element.getAttribute(SOURCE) + " nullPointerException : " + nullPointerException.getMessage() +  nullPointerException);
		}
		catch(NumberFormatException numberFormatException)
		{
			System.out.println("Unable to get the value from the element : " + element.getAttribute(SOURCE) + " numberFormatException : " + numberFormatException.getMessage() +  numberFormatException);
		}
		catch(Exception applicationException)
		{
			System.out.println(applicationException.getMessage() + applicationException);
			throw applicationException;
		}

		finally
		{
			element = null;
			newRootElement = null;
			result = null;
			value = null;
			fromDateFormat = null;
			toDateFormat = null;

			System.out.println("JsonXmlDataConverter : Exit generateXml..");

		}
	}

	/**
	 * @author Suresh Upparu
	 * <b>Algorithm</b>
	 * <pre>
	 * 
	 * </pre>
	 * @param from
	 * @param to
	 */
	@SuppressWarnings("unused")
	private void copyAttributes(Element from, Element to) 
	{
		System.out.println("copyAttributes : from : " + from.getNodeName() + " to : " + to.getNodeName());

		NamedNodeMap attributes = null;
		Attr node = null;

		try
		{
			attributes = from.getAttributes();
			for (int i = 0; i < attributes.getLength(); i++) 
			{
				node = (Attr) attributes.item(i);
				to.setAttributeNS(node.getNamespaceURI(), node.getName(), node.getValue());
			}
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		finally
		{
			attributes = null;
			node = null;
		}
	}

	/**
	 * @author Suresh Upparu
	 * <b>Algorithm</b>
	 * <pre>
	 * 
	 * </pre>
	 * @param document
	 * @return String
	 * @throws TransformerException
	 */
	public String documentToString(Document document) throws TransformerException 
	{
		Node node = null;
		NodeList nodeList = null; 
		Transformer transformer = null; 
		StringWriter writer = null;

		try
		{
			writer = new StringWriter();
			transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			if(document != null )
			{
				nodeList = document.getChildNodes();
				for(int i = 0; i < nodeList.getLength(); i ++)
				{
					node = nodeList.item(i);
					deleteNullNode(node);
				}

				transformer.transform(new DOMSource(document), new StreamResult(writer));
			}
			return writer.toString();
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		finally
		{
			node = null;
			nodeList = null; 
			transformer = null; 
			writer = null;
		}
		return null;
	}

	/**
	 * @author Suresh Upparu
	 * <b>Algorithm</b>
	 * <pre>
	 * 
	 * </pre>
	 * @param node
	 */
	public void deleteNullNode(Node node)
	{
		NodeList nodeList = null;

		try
		{
			nodeList = node.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i ++)
			{
				if(nodeList.item(i).getNodeType() == Node.TEXT_NODE && nodeList.item(i).getNodeValue() == null)
					nodeList.item(i).getParentNode().removeChild(nodeList.item(i));
				else
					deleteNullNode(nodeList.item(i));
			}
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		finally
		{
			nodeList = null;
		}
	}

	/**
	 * @author Suresh Upparu
	 * <b>Algorithm</b>
	 * <pre>
	 * 
	 * </pre>
	 * @param node
	 * @return String
	 * @throws TransformerException
	 */
	public String nodeToString(Node node) throws  TransformerException
	{
		Transformer transformer = null;
		StringWriter writer = null;

		try
		{
			writer = new StringWriter();
			transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(new DOMSource(node), new StreamResult(writer));

			return writer.toString();
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		finally
		{
			transformer = null;
			writer = null;
		}
		return null;
	}

	/**
	 * 
	 * @param node
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws TransformerException
	 */
	public Document nodeToDocment(Node node) throws ParserConfigurationException, SAXException, IOException, TransformerException
	{
		String str = null;

		try
		{
			str = nodeToString(node);
			return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(str)));
		}
		catch(ParserConfigurationException parserConfigurationException)
		{
			throw parserConfigurationException;
		}
		catch(SAXException saxException)
		{
			throw saxException;
		}
		catch(IOException ioException)
		{
			throw ioException;
		}
		catch(TransformerException transformerException)
		{
			throw transformerException;
		}
		finally
		{
			str = null;
		}
	}

	/**
	 * @author Suresh Upparu
	 * <b>Algorithm</b>
	 * <pre>
	 * 
	 * </pre>
	 * @param xmlStr
	 * @return Document
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public static Document convertStringToDocument(String xmlStr) throws SAXException, IOException, ParserConfigurationException 
	{
		try
		{
			return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(xmlStr))); 
		}
		catch(ParserConfigurationException parserConfigurationException)
		{
			throw parserConfigurationException;
		}
		catch(SAXException saxException)
		{
			throw saxException;
		}
		catch(IOException ioException)
		{
			throw ioException;
		}
	}

	/**
	 * @author Suresh Upparu
	 * <b>Algorithm</b>
	 * <pre>
	 * 
	 * </pre>
	 * @param jsonPath
	 * @param requestJson
	 * @return Object
	 * @throws Exception
	 */
	private Object getFieldValue(String jsonPath, JSONObject requestJson) throws Exception
	{
		JSONObject tempJson = null;
		String[] arr = null;

		try
		{
			tempJson = requestJson;
			arr = jsonPath.split("\\.");
			//			System.out.println("arr.length : " + arr.length);
			for(int i = 0; i < arr.length; i ++)
			{
				//				System.out.println("arr[i]" + arr[i] + " :: tempJson.get(arr[i]) : " + tempJson.get(arr[i]));
				if(tempJson.get(arr[i]) == null || tempJson.get(arr[i]).toString().trim().isEmpty())
				{
					return "";
				}
				if(tempJson.get(arr[i]) instanceof JSONObject)
				{
					//					System.out.println("insdie object..");
					tempJson = (JSONObject) tempJson.get(arr[i]);
					if(i == arr.length - 1)
						return tempJson;
				}
				if(tempJson.get(arr[i]) instanceof JSONArray)
				{
					//					System.out.println("inside array..");
					return (JSONArray) tempJson.get(arr[i]);
				}
				if(tempJson.get(arr[i]) instanceof String)
				{
					//					System.out.println("insdie string..");
					return (String) tempJson.get(arr[i]);
				}
				if(tempJson.get(arr[i]) instanceof Long)
				{
					//					System.out.println("insdie long.." + tempJson.get(arr[i]));
					return ((Long) tempJson.get(arr[i])).toString();
				}
				if(tempJson.get(arr[i]) instanceof Integer)
				{
					//					System.out.println("insdie integer.." + tempJson.get(arr[i]));
					return ((Integer) tempJson.get(arr[i])).toString();
				}
				if(tempJson.get(arr[i]) instanceof Double)
				{
					//					System.out.println("insdie double.." + tempJson.get(arr[i]));
					return ((Double) tempJson.get(arr[i])).toString();
				}
			}
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		finally
		{
			tempJson = null;
			arr = null;
		}
		return null;
	}

	/**
	 * @author Suresh Upparu
	 * <b>Algorithm</b>
	 * <pre>
	 * 
	 * </pre>
	 * @param jsonArr
	 * @param sourceTag
	 * @param value
	 * @param targetTag
	 * @return String
	 * @throws Exception
	 */
	private String getTargetValue(JSONArray jsonArr, String sourceTag, String value, String targetTag) throws Exception
	{
		JSONObject json = null;

		try
		{
			//			System.out.println("JSON ARR : " + jsonArr);
			for(int i = 0; i < jsonArr.size(); i ++)
			{
				if(jsonArr.get(i) instanceof JSONObject)
				{
					//					System.out.println("inside Jobject..");
					json = (JSONObject) jsonArr.get(i);
					if(json.get(sourceTag) != null && json.get(sourceTag).toString().equalsIgnoreCase(value))
					{
						if(json.get(targetTag) == null || json.get(targetTag).toString().trim().isEmpty())
							return "";
						else if(json.get(targetTag) instanceof String)
							return (String) json.get(targetTag);
						else if(json.get(targetTag) instanceof Long)
							return ((Long) json.get(targetTag)).toString();
						else if(json.get(targetTag) instanceof Integer)
							return ((Integer) json.get(targetTag)).toString();
						else if(json.get(targetTag) instanceof Double)
							return ((Double) json.get(targetTag)).toString();
					}
				}
			}
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		finally
		{
			json = null;
		}
		return null;
	}

	/**
	 * @author Suresh Upparu
	 * <b>Algorithm</b>
	 * <pre>
	 * 
	 * </pre>
	 * @param jsonArr
	 * @param targetTag
	 * @return Object
	 * @throws Exception
	 */
	private String getTargetVal(JSONArray jsonArr, String targetTag) throws Exception
	{
		JSONObject json = null;
		Object value = null;

		try
		{
			//			System.out.println("JSON ARR size : " + jsonArr.size());
			for(int i = 0; i < jsonArr.size(); i ++)
			{
				if(jsonArr.get(i) instanceof JSONObject)
				{
					json = (JSONObject) jsonArr.get(i);
					//					System.out.println("Json : " + json);
					value = json.get(targetTag);
					if(value != null && !value.toString().trim().isEmpty())
					{
						if(value instanceof String)
							return value.toString();
						else if(value instanceof Long)
							return ((Long) value).toString();
						else if(value instanceof Double)
							return ((Double) value).toString();
						else if(value instanceof Integer)
							return ((Integer) value).toString();
					}
				}
			}
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		finally
		{
			json = null;
			value = null;
		}
		return null;
	}

	/**
	 * @author Suresh Upparu
	 * <b>Algorithm</b>
	 * <pre>
	 * 
	 * </pre>
	 * @param element
	 * @param jsonObject
	 * @return String
	 * @throws Exception
	 */
	private String fetchValueByOrder(Element element, JSONObject jsonObject) throws Exception
	{
		try
		{
			System.out.println("element.getAttribute(FETCH_ORDER) : " + element.getAttribute(FETCH_ORDER));
			if(element.getAttribute(FETCH_ORDER).contains(","))
			{
				for(String field : element.getAttribute(FETCH_ORDER).split(","))
				{
					if(jsonObject.get(field) != null)
					{
						if(jsonObject.get(field) instanceof String && !jsonObject.get(field).toString().trim().isEmpty())
						{
							return jsonObject.get(field).toString();
						}
						else if(jsonObject.get(field) instanceof Long)
						{
							return ((Long) jsonObject.get(field)).toString();
						}
						else if(jsonObject.get(field) instanceof Double)
						{
							return ((Double) jsonObject.get(field)).toString();
						}
					}
				}
			}
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		return null;
	}

	/**
	 * @author Suresh Upparu
	 * <b>Algorithm</b>
	 * <pre>
	 * 
	 * </pre>
	 * @param dbName
	 * @param collectionName
	 * @param type
	 * @param targetField
	 * @param value
	 * @return String
	 * @throws Exception
	 */
	private String getMongoValue(String dbName, String collectionName, String type, String targetField, String value) throws Exception
	{
		//		System.out.println("DBName : " + dbName + " : CollectionName : " + collectionName + " : Type : " + type + " : Value : " + value);

		BasicDBObject outputObject = null;
		BasicDBObject inputObject = null;

		try
		{
			inputObject = new BasicDBObject();
			if("char".equalsIgnoreCase(type))
				inputObject.append("_id", value);
			else
				inputObject.append("_id", Long.valueOf(value));
			outputObject = mongoUtil.findOne(dbName, collectionName, inputObject);
			if(outputObject != null)
			{
				System.out.println("outputObj : " + outputObject);
				if(targetField != null)
				{
					if(outputObject.get(targetField) != null)
						return outputObject.get(targetField).toString();
				}
				else
				{
					return outputObject.getString("ref_code") != null ? outputObject.getString("ref_code") : outputObject.getString("operator_id");
				}
			}

			return null;
		}
		catch(Exception exception)
		{
			throw exception;
		}
		finally
		{
			outputObject = null;
			inputObject = null;
		}
	}

	public Document validateDocumentProcess(Document newDoc, String validateElemetName, String flagName) throws Exception
	{
		String outerArray[] = null;
		String innerObjAry[] = null;
		try
		{
			outerArray = convertStringToArrayBySeperator(validateElemetName, "!&!");

			if(outerArray != null )
			{
				for (int i = 0; i < outerArray.length; i++)
				{
					innerObjAry = convertStringToArrayBySeperator(outerArray[i].toString(), "!~!");
					if(innerObjAry.length>0)
					{
						if(flagName.equalsIgnoreCase("validateNodeByChildElemet"))
						{
							newDoc = validateNodeByChildElemet(newDoc, innerObjAry[0], innerObjAry[1], innerObjAry[2]);
						}
					}
				}
			}
			else
			{
				System.out.println("Proeprty content is null :: "+validateElemetName);
			}
			return newDoc;
		}
		catch(Exception exception)
		{
			System.out.println("exception :: Unable to validate the elements from the document ");
			throw exception;
		}
		finally
		{
			outerArray = null;
			innerObjAry = null;
		}
	}

	public static String[] convertStringToArrayBySeperator(String value, String seperator) throws Exception
	{
		String[] objAry = null;
		try
		{
			if(value != null && !value.trim().isEmpty())
			{
				if(seperator == null || seperator.trim().isEmpty())
				{
					seperator = ",";
				}
				objAry = value.split(seperator);
			}
			return objAry ;
		}
		catch(Exception exception) 
		{
			//System.out.println("Unable to split the value :: "+value+" by seperator :: "+seperator);
			System.out.println("Unable to split the value :: "+value+" by seperator :: "+seperator);
			throw exception;
		}
		finally
		{
			objAry = null;
		}

	}

	public Document validateNodeByChildElemet(Document  document, String parent, String removeChilds, String conditionNode) throws Exception
	{

		XPathFactory factory = null;
		XPath xPath = null;
		XPathExpression expression = null;
		Object result = null;
		NodeList nodeList = null;
		boolean foundTag = false;
		boolean invalidData = false;
		Node parentNode = null;
		NodeList firstLevelNodeList = null;
		Node childNode = null;
		Node innerChildNode = null;
		NodeList allChildNode = null;

		try
		{
			factory = XPathFactory.newInstance();
			xPath = factory.newXPath();
			expression = xPath.compile(parent);
			result = expression.evaluate(document, XPathConstants.NODESET);
			nodeList = (NodeList) result;
			if(nodeList != null)
			{
				for(int i = 0; i < nodeList.getLength() ; i++)
				{
					foundTag = false;
					invalidData = false;
					parentNode = nodeList.item(i);
					firstLevelNodeList = parentNode.getChildNodes();
					for(int j = 0; j < firstLevelNodeList.getLength(); j++)
					{
						childNode = firstLevelNodeList.item(j);
						innerChildNode = null;
						if(childNode != null && removeChilds.equalsIgnoreCase(childNode.getNodeName()))
						{
							//							System.out.println("---------------");
							//							System.out.println("childNode ::"+childNode.getNodeName());
							allChildNode = childNode.getChildNodes();
							if(allChildNode == null || allChildNode.getLength() == 0)
							{
								childNode.getParentNode().removeChild(childNode);
							}
							else
							{
								foundTag = false;
								invalidData = false;
								//elements are available check for validation
								//								System.out.println("Elements are available");
								innerFor: for (int k = 0; k < allChildNode.getLength(); k++)
								{
									innerChildNode = allChildNode.item(k);
									if(innerChildNode.getNodeName().equalsIgnoreCase(conditionNode))
									{
										foundTag = true;
									}
									if((innerChildNode.getNodeType() == Node.TEXT_NODE || (innerChildNode.getTextContent() == null || innerChildNode.getTextContent().trim().isEmpty())) && 
											innerChildNode.getNodeName().equalsIgnoreCase(conditionNode))
									{
										//										System.out.println("innerChildNode.getNodeType() ::"+innerChildNode.getNodeType());
										invalidData = true;
										foundTag = true;
										//										System.out.println("innerFor ::"+innerChildNode.getTextContent());
										break innerFor;
									}
								}
							}
							//							System.out.println("foundTag ::"+foundTag);
							//							System.out.println("invalidData ::"+invalidData);
							if(!foundTag)
							{
								invalidData = true;
							}
						}
						if(invalidData && innerChildNode != null)
						{
							childNode.getParentNode().removeChild(childNode);
							j--;
						}
					}
				}
			}
			return document;
		}
		catch(Exception exception)
		{
			System.out.println("Exception is raised while removing the element :"+removeChilds+" from the xml "+exception.getMessage() +  exception);
			throw exception;
		}
		finally
		{
			factory = null;
			xPath = null;
			expression = null;
			result = null;
			nodeList = null;
			foundTag = false;
			invalidData = false;
			parentNode = null;
			firstLevelNodeList = null;
			childNode = null;
			innerChildNode = null;
			allChildNode = null;
		}
	}

	private Object getFieldValue(BasicDBObject dbObject, String path) throws Exception
	{
		BasicDBObject tempObject = null;
		String[] arr = null;

		try
		{
			if(dbObject == null || (path == null || path.trim().isEmpty()))
			{
				System.out.println("DBObject : " + dbObject + " : Path : " + path);
				return null;
			}

			tempObject = dbObject;
			arr = path.split("\\.");
			//			System.out.println("arr.length : " + arr.length);
			for(int i = 0; i < arr.length; i ++)
			{
				//				System.out.println("arr[i] : " + arr[i] + " :: tempObject.get(arr[i]) : " + tempObject.get(arr[i]));
				if(tempObject.get(arr[i]) == null || tempObject.get(arr[i]).toString().trim().isEmpty())
				{
					//					System.out.println("inside null..");
					return "";
				}
				if(tempObject.get(arr[i]) instanceof BasicDBObject)
				{
					//					System.out.println("inside Object.." + tempObject.get(arr[i]));
					tempObject = (BasicDBObject) tempObject.get(arr[i]);
					if(i == arr.length - 1)
						return tempObject;
				}
				if(tempObject.get(arr[i]) instanceof BasicDBList)
				{
					//					System.out.println("inside List..");
					return tempObject.get(arr[i]);
				}
				if(tempObject.get(arr[i]) instanceof String)
				{
					//					System.out.println("inside String.." + tempObject.get(arr[i]));
					return tempObject.get(arr[i]);
				}
				if(tempObject.get(arr[i]) instanceof Long)
				{
					//					System.out.println("inside Long.." + tempObject.get(arr[i]));
					return ((Long) tempObject.get(arr[i])).toString();
				}
				if(tempObject.get(arr[i]) instanceof Integer)
				{
					//					System.out.println("insdie integer.." + tempObject.get(arr[i]));
					return ((Integer) tempObject.get(arr[i])).toString();
				}
				if(tempObject.get(arr[i]) instanceof Double)
				{
					//					System.out.println("inside Double.." + tempObject.get(arr[i]));
					return ((Double) tempObject.get(arr[i])).toString();
				}
			}
			return null;
		}
		catch(Exception exception)
		{
			System.out.println("Unhandled Exception :: " + exception.getMessage() + exception);
		}
		finally
		{
			tempObject = null;
			arr = null;
		}
		return null;
	}
	private final String ORG_TYPE_PATH_ATTR="Org Type Path";
	private final String SOURCE = "source";
	private final String TRUE = "true";
	private final String FALSE = "false";
	private final String REQUIRED = "required";
	private final String IS_MULTIPLE = "isMultiple";
	private final String DEFAULT_VALUE = "defaultValue";
	private final String HEADER = "header";
	private final String LOOKUP_TYPE = "lookup-type";
	private final String SOURCE_TAG = "source-tag";
	private final String VALUE = "value";
	private final String TARGET_TAG = "target-tag";
	private final String FETCH_ORDER = "fetch-order";
	private final String SCHEMA = "schema";
	private final String COLLECTION = "collection";
	private final String ENTITY_TYPE_ID = "lookup-entity-type-id";
	private final String TYPE="type";
	private final String TARGET_FIELD = "target-field";
	private final String DATE_FROM_FORMAT = "date-from-format";
	private final String DATE_TO_FORMAT = "date-to-format";
	private final String CONDITION_TAG = "condition-tag";
	private final String CONDITION_VALUE = "condition-value";
	private final String SUB_LOOKUP_TYPE = "sub-lookup-type";
	private final String LETTER_CASE = "letter-case";
	private final String LOWER_CASE = "lower";
	private final String UPPER_CASE = "upper";
	private final String APPEND_PREFIX = "append-prefix";
	private final String APPEND_SUFFIX = "append-suffix";
	private final String SCHEMA_1 = "schema-1";
	private final String COLLECTION_1 = "collection-1";
	private final String TYPE_1 = "type-1";
	private final String TARGET_FIELD_1 = "target-field-1";
	private final String ENTITY_ID = "entity-id";
	private final String OPERATION = "operation";
	private final String DELIMITER = "delimiter";
	private final String MERGE = "merge";
	private final Long INTERFACE_APPLICATION_ID=301l;
	public SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
	public SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	private final String VALIDATE_NODE_BY_CHILD_BEFORE_PROCESS = "//Request/Body/CreateChildOrgRequest/SimpleKYCUpdateData!~!req:AddField!~!com:KYCValue!&!//Request/Body/CreateTopOrgRequest/SimpleKYCUpdateData!~!req:AddField!~!com:KYCValue!&!//Request/Body/UpdateOrganizationKYCRequest/SimpleKYCUpdateData!~!req:AddField!~!com:KYCValue!&!//Request/Body/CreateOrgOperatorRequest/SimpleKYCUpdateData!~!req:AddField!~!com:KYCValue!&!//Request/Body/UpdateOrgOperatorKYCRequest/SimpleKYCUpdateData!~!req:AddField!~!com:KYCValue";
	private final String LTYPE_ENTITY_MAPPING="1";
	@SuppressWarnings("unused")
	private final String LTYPE_LOOKUP_MAPPING="2";
	private final String LTYPE_MONGO_MAPPING="3";
	private final String LTYPE_DATE_MAPPING="4";
	private final String LTYPE_CONDITION_MAPPING="5";
	private final String LTYPE_COMPLEX_MONGO_MAPPING="6";
	private final String KYC_FIELD_NAME_TAG="com:KYCName";
	private final String INTEGRATION_XML_REQUEST_TEMPLATE_PATH = IWorkConstants.INTEGRATION_XML_REQUEST_TEMPLATE_PATH;
	private final String TEMPLATE_NAME_ATTR = "Template Name";
	private MongoUtil mongoUtil = new MongoUtil();
}