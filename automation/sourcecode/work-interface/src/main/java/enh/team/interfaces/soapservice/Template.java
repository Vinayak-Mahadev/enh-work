package enh.team.interfaces.soapservice;

public interface Template {
	
	public static final String processReceivedFiles = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:int=\"http://com/enhancesys/integration/services/interfaces/IntegrationManagement\"><soapenv:Header/><soapenv:Body><int:processReceivedFiles><interfaceId>INTERFACE_ID</interfaceId><partnerIds>0</partnerIds></int:processReceivedFiles></soapenv:Body></soapenv:Envelope>";
	public static final String processFile = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:int=\"http://com/enhancesys/integration/services/interfaces/IntegrationManagement\"><soapenv:Header/><soapenv:Body><int:processFile><interfaceIds>INTERFACE_ID</interfaceIds></int:processFile></soapenv:Body></soapenv:Envelope>";
	public static final String reProcessFileBasedOrders = "<soapenv:Envelopexmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"xmlns:int=\"http://com/enhancesys/integration/services/interfaces/IntegrationManagement\"><soapenv:Header/><soapenv:Body><int:reProcessFileBasedOrders><interfaceId>INTERFACE_ID</interfaceId><fileId>FILE_ID</fileId></int:reProcessFileBasedOrders></soapenv:Body></soapenv:Envelope>";
	public static final String pullDataToFile = "<soapenv:Envelopexmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"xmlns:int=\"http://com/enhancesys/integration/services/interfaces/IntegrationManagement\"><soapenv:Header/><soapenv:Body><int:pullDataToFile><interfaceIds>INTERFACE_ID</interfaceIds></int:pullDataToFile></soapenv:Body></soapenv:Envelope>";
	public static final String getStaticResponse = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:int=\"http://com/enhancesys/integration/response/services/interfaces/InterfaceResponseServices\"><soapenv:Header/><soapenv:Body><int:getStaticResponse><interfaceId>INTERFACE_ID</interfaceId><requestData><![CDATA[REQUEST_DATA]]></requestData></int:getStaticResponse></soapenv:Body></soapenv:Envelope>";

	public static final String getOrganizationProfileAttr = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ent=\"http://com/finevm/banyan/org/services/interfaces/EntityAdminServices\"> <soapenv:Header/> <soapenv:Body> <ent:getOrganizationProfileAttr> <atrributeId>ATRRIBUTE_ID</atrributeId> </ent:getOrganizationProfileAttr> </soapenv:Body> </soapenv:Envelope>";
	public static final String getOrganization = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ent=\"http://com/finevm/banyan/org/services/interfaces/EntityAdminServices\"> <soapenv:Header/> <soapenv:Body> <ent:getOrganization> <orgId>ORG_ID</orgId> </ent:getOrganization> </soapenv:Body> </soapenv:Envelope>";
	public static final String getOrganizationProfile = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ent=\"http://com/finevm/banyan/org/services/interfaces/EntityAdminServices\"> <soapenv:Header/><soapenv:Body><ent:getOrganizationProfile> <profileId>PROFILE_ID</profileId> </ent:getOrganizationProfile> </soapenv:Body> </soapenv:Envelope>";

	
	
}
