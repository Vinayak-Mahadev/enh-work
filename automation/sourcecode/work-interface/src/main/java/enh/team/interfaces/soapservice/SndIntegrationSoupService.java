package enh.team.interfaces.soapservice;

import com.finevm.enh.util.PropType;

public class SndIntegrationSoupService {

	private final String wsdlUrl   = "http://192.168.2.143:8080/IntegrationServices/IntegrationManagement?wsdl";
	private final String userName  = "admin";
	private final String password  = "admin";
	private TestWebServiceRequest testSoapRequest = TestWebServiceRequest.getTestSoapRequest(PropType.SOAP);
	
	public ResponseBean processReceivedFiles(Long interfaceID){
		String template = Template.processReceivedFiles.replaceAll("INTERFACE_ID", interfaceID.toString());
		
		return (testSoapRequest.invokeService(wsdlUrl, template, userName, password, interfaceID));
	}
	
	public ResponseBean processFile(Long interfaceID){
		String template = Template.processFile.replaceAll("INTERFACE_ID", interfaceID.toString());
		
		return (testSoapRequest.invokeService(wsdlUrl, template, userName, password, interfaceID));
	}

	public ResponseBean reProcessFileBasedOrders(Long interfaceID, Long fileId){
		String template = Template.reProcessFileBasedOrders.replaceAll("INTERFACE_ID", interfaceID.toString());
		template = template.replaceAll("FILE_ID", fileId.toString());
		
		return (testSoapRequest.invokeService(wsdlUrl, template, userName, password, interfaceID));
	}
	
	public ResponseBean pullDataToFile(Long interfaceID){
		String template = Template.pullDataToFile.replaceAll("INTERFACE_ID", interfaceID.toString());
		
		return (testSoapRequest.invokeService(wsdlUrl, template, userName, password, interfaceID));
	}
	
}
