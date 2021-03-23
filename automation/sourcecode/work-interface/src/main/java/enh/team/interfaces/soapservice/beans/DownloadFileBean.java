package enh.team.interfaces.soapservice.beans;

import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;

@XmlRootElement(name="downloadFileRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "downloadFile")
public class DownloadFileBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@XmlElement(name = "interfaceId", required = true)
	private Long intId;

	@XmlElementWrapper(name="properties")
	@XmlElement(name="property")
	private Set<PropertyBean> props;

	@XmlElementWrapper(name="attachments")
	@XmlElement(name="attachment")
	private Set<AttachmentBean> atts;

	public Long getInterfaceId() {
		return intId;
	}

	public void setInterfaceId(Long interfaceId) {
		this.intId = interfaceId;
	}

	public Set<PropertyBean> getProperties() {
		return props;
	}

	public void setProperties(Set<PropertyBean> properties) {
		this.props = properties;
	}

	public Set<AttachmentBean> getAttachments() {
		return atts;
	}

	public void setAttachments(Set<AttachmentBean> attachments) {
		this.atts = attachments;
	}

	public String getXMLMarshal()throws Exception
	{
		String response = null;
		JAXBContext jc = null;
		Marshaller mars = null;
		StringWriter sw = null;
		try 
		{
			jc = JAXBContext.newInstance(DownloadFileBean.class);
			mars = jc.createMarshaller();
			mars.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); 
			mars.setProperty(Marshaller.JAXB_FRAGMENT, true); 
			sw = new StringWriter();
			mars.marshal(this, sw);
			response = sw.toString();
		}
		catch (Exception e) 
		{
			throw new  Exception("Internal :: DownloadFileBean XMLMarshal issue", e);
		}
		return response;
	}
	
	private static final String INTERFACE_REQ = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:int=\"http://com/enhancesys/integration/services/interfaces/IntegrationManagement\"> <soapenv:Header/> <soapenv:Body> <int:downloadFiles> DOWNLOAD_FILE_BEAN_REQUEST </int:downloadFiles> </soapenv:Body> </soapenv:Envelope>"; 
	
	public String createRequestForInterface() throws Exception
	{
		try 
		{
			return formatXml(INTERFACE_REQ.replaceAll("DOWNLOAD_FILE_BEAN_REQUEST", getXMLMarshal()));
		} 
		catch (Exception e) 
		{
			throw new  Exception("Internal :: CreateRequestForInterface issue", e);
		}
	}
	
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
}
