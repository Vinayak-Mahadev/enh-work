package com.enhancesys.integration.snoc.interfaces.converter;

import java.util.HashMap;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.enhancesys.integration.snoc.beans.PartyDetailsBean;
import com.enhancesys.integration.snoc.beans.ProductDetailsBean;
/**
 * <b>Purpose:</b><br>
 * Interface to provide the data retrieve operations for the converters..<br>
 * <br>
 * <br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * Enhancesys 2014<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 *  Sl No   Modified Date        Author</b>
 *  ==============================================
 *  1        23-12-2014          Suresh Upparu
 *    	-- Base Release
 * </pre>
 * 
 * <br>
 */

public interface LookupMappingLocal
{
	public  String getTargetTagValue(String sourceTag, String value, String sourceTag1, String value1, String targetTag, Document sourceElement) throws Exception;
	
	public String getValue(Node parentNode, String targetTag, Document sourceElement) throws Exception;
	
	/*public String getLookupPartyId(Long partyId, Long applicationId)throws Exception;*/
	
	public String getLookupEntity(Long entityType, Long entityId, Long applicationId) throws Exception;
	
	public String getLookupMapping(Long lookupType, String lookupName) throws Exception;
	
	public Long getLookupEntityId(Long entityType, String id1, Long applicationId) throws Exception;
	
	public String getLookupMasterEntity(Long entityType, String entityId)throws Exception;
	
	public String getLookupPartyRoleCharValue(Long partyId, Long specId, Long charId) throws Exception;
	
//	public String getLookupUnitOfMeasure(String shortName) throws Exception;
	
	public String getLookupItem(String shortName, Long versionType) throws Exception;
	
//	public String getLookupItemCode(String itemId) throws Exception;
	
	public String getLookupItemPrice(String shortName, Long roleTypeId, Long locationId) throws Exception;
	
	public List<ProductDetailsBean> getLookupItemPriceDetails(List<String> shortNames, Long formPartyRoleType, Long toPartyId, Long toPartyRoleType)throws Exception;
	
	public String getLookupItemStock(String shortName, Long roleTypeId, Long locationId) throws Exception;
	
	public void getLookupPartyDetails(List<PartyDetailsBean> partyBeans, Long applicationId) throws Exception;
	
	public List<ProductDetailsBean> getLookupItemDetails(List<String> shortNames) throws Exception;
	
	public PartyDetailsBean getLookupUserDetails(String salesOrderNumber) throws Exception;
	
	public PartyDetailsBean getLookupUserDetails(PartyDetailsBean partyDetailsBean) throws Exception;
	
	public String getProfileAttribute(String value, String attribute, Long entityType) throws Exception;
	
	public PartyDetailsBean getLookupSupplierDetails(PartyDetailsBean partyDetailsBean, Long itemCategory) throws Exception;
	
	public PartyDetailsBean getLookupParentPartyDetails(PartyDetailsBean partyDetailsBean) throws Exception;
	
//	public OrderDetailsBean getOrderDetails(String extReferenceNo, Long requisitionOrderType, Long invoiceOrderType) throws Exception;
	
	public String getInvoiceNumber(String extReferenceNo, Long requisitionOrderType, Long requiredOrderType) throws Exception;
	
	public List<PartyDetailsBean> getPartyDetailsAsList(String xml, String path1, String path2, boolean multiple) throws Exception;
	
	public HashMap<String, String> getTagValue(Object object, String path1, String path2, boolean multiple) throws Exception;
	
	public List<String> getTagValueAsList(Object object, String path1, boolean multiple) throws Exception;
	
	public List<String> getProductDetails(String xml, String path1) throws Exception;
	
	public Document convertStringToDocument(String xmlStr) throws Exception;
	
	public String convertDocumentToString(Document document) throws Exception;
	
	public void removeNode(Document document, String parent, String removeChilds, String conditionNode) throws Exception;
}