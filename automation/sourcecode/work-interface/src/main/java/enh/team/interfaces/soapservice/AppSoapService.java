package enh.team.interfaces.soapservice;

public class AppSoapService {

	public static void main(String[] args){

		try 
		{
			/*
			String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:api=\"http://cps.huawei.com/synccpsinterface/api_requestmgr\" xmlns:com=\"http://cps.huawei.com/synccpsinterface/common\" xmlns:req=\"http://cps.huawei.com/synccpsinterface/request\"> 	<soapenv:Header/> 	<soapenv:Body> 		<api:Request> 			<req:Header> 				<req:Version>1.0</req:Version> 				<req:CommandID>QueryOrganizationBalance</req:CommandID> 				<req:OriginatorConversationID>126339</req:OriginatorConversationID> 				<req:Caller> 					<req:CallerType>2</req:CallerType> 					<req:ThirdPartyID>POS_Broker</req:ThirdPartyID> 					<req:Password>gMMqGGrKxsE=</req:Password> 					<req:ResultURL>http://10.63.146.235:8082/mockAPIResultMgrBinding</req:ResultURL> 				</req:Caller> 				<req:KeyOwner>1</req:KeyOwner> 				<req:Timestamp>20191017145735</req:Timestamp> 			</req:Header> 			<req:Body> 				<req:Identity> 					<req:Initiator> 						<req:IdentifierType>14</req:IdentifierType> 						<req:Identifier>TAP-TMW-GN</req:Identifier> 						<req:SecurityCredential>voN2HZXSUWrGKUZVwf8byQ==</req:SecurityCredential> 					</req:Initiator> 					<req:ReceiverParty> 						<req:IdentifierType>4</req:IdentifierType> 						<req:Identifier>35467876</req:Identifier> 					</req:ReceiverParty> 				</req:Identity> 				<req:QueryOrganizationBalanceRequest> 					<req:TargetOrgList>0201;35467876</req:TargetOrgList> 					<req:AccountType>Stock Account_Normal</req:AccountType> 				</req:QueryOrganizationBalanceRequest> 			</req:Body> 		</api:Request> 	</soapenv:Body> </soapenv:Envelope>";

			System.out.println(XML.toJSONObject(xml));



			SndInterfaceResponseService service = new SndInterfaceResponseService();


			ResponseBean responseBean = service.getStaticResponse(1018l);

			System.out.println(responseBean);

			xml = getXmlElementValue(responseBean.getResponseData(), "getStaticResponse");
			xml = xml.replaceAll("&lt;", "<").replaceAll("&gt;", ">");

			System.out.println(xml);*/


			/*			FineVMBanyanService banyanService = new FineVMBanyanService();

			System.out.println(banyanService.getOrg(101l));
			System.out.println(banyanService.getOrgPro(1001l));
			System.out.println(banyanService.getOrgProAttr(1001001l));



			 */

			SndIntegrationSoupService service = new SndIntegrationSoupService();
			ResponseBean responseBean = null;
			for (long i = 1165; i <= 1181; i++) 
			{
				//responseBean = service.processFile(i);
				//responseBean = service.processReceivedFiles(i);
				responseBean = service.prepareRejectionFile(i);
				System.out.println("Service call :: " + i  + "    status  :: " + responseBean.getStatus());
				//Thread.sleep(1 * 30 * 1000);
				//Thread.sleep(1000);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
