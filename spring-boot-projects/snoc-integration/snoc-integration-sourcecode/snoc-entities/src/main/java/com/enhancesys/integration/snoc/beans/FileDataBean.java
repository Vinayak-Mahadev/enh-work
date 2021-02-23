package com.enhancesys.integration.snoc.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.enhancesys.integration.snoc.entities.InterfaceFileSummary;
import com.enhancesys.integration.snoc.entities.InterfaceFileSummaryDetails;

/**
 * <b>Purpose:</b><br>
 * Declaration of the FileBean service parameter properties..<br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * Enhancesys Innovations 2018<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 * Sl No   Modified Date        Author</b>
 * ==============================================
 * 1       07-06-2018		   Satheesh Kumar
 *   -- Base Release
 */
public class FileDataBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long fileId;
	private Long interfaceId;
	private Long batchId;
	private FilePropertiesBean filePropertiesBean;
	private InterfaceFileSummary interfaceFileSummary;
	private List<InterfaceFileSummaryDetails> interfaceFileSummaryDetailsList;
	private List<String> fileData;
	private List<org.json.JSONObject> successData;
	private List<org.json.JSONObject> failureData;
	private String frequencyType ;
	private Boolean dataFlag;
	private Integer frequency;
	private Boolean crossSellingFlag;
	
	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	public Long getInterfaceId() {
		return interfaceId;
	}
	public void setInterfaceId(Long interfaceId) {
		this.interfaceId = interfaceId;
	}
	public Long getBatchId() {
		return batchId;
	}
	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}
	public List<String> getFileData() 
	{
		if(fileData == null)
		{
			fileData = new ArrayList<String>();
		}
		return fileData;
	}
	public void setFileData(List<String> fileData) 
	{
		this.fileData = fileData;
	}
	
	public InterfaceFileSummary getInterfaceFileSummary() 
	{
		return interfaceFileSummary;
	}
	public void setInterfaceFileSummary(InterfaceFileSummary interfaceFileSummary) 
	{
		this.interfaceFileSummary = interfaceFileSummary;
	}
	public List<InterfaceFileSummaryDetails> getInterfaceFileSummaryDetailsList() 
	{
		if(interfaceFileSummaryDetailsList == null)
		{
			interfaceFileSummaryDetailsList = new ArrayList<InterfaceFileSummaryDetails>();
		}
		return interfaceFileSummaryDetailsList;
	}
	public void setInterfaceFileSummaryDetailsList(List<InterfaceFileSummaryDetails> interfaceFileSummaryDetailsList) 
	{
		this.interfaceFileSummaryDetailsList = interfaceFileSummaryDetailsList;
	}
	public FilePropertiesBean getFilePropertiesBean() {
		return filePropertiesBean;
	}
	public void setFilePropertiesBean(FilePropertiesBean filePropertiesBean) {
		this.filePropertiesBean = filePropertiesBean;
	}
	public List<org.json.JSONObject> getSuccessData() {
		if(successData == null)
		{
			successData = new ArrayList<org.json.JSONObject>();
		}
		return successData;
	}
	public void setSuccessData(List<org.json.JSONObject> successData) {
		this.successData = successData;
	}
	public List<org.json.JSONObject> getFailureData() {
		if(failureData == null)
		{
			failureData = new ArrayList<org.json.JSONObject>();
		}
		return failureData;
	}
	public void setFailureData(List<org.json.JSONObject> failureData) {
		this.failureData = failureData;
	}
	
	public Boolean getDataFlag()
	{
		return this.dataFlag;
	}
	public void setDataFlag(Boolean dataFlag)
	{
		this.dataFlag = dataFlag;
	}
	public String getFrequencyType() {
		return frequencyType;
	}
	
	public void setFrequencyType(String frequencyType) {
		this.frequencyType = frequencyType;
	}
	
	public Integer getFrequency() {
		return frequency;
	}
	
	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}
	
	public Boolean getCrossSellingFlag() 
	{
		return crossSellingFlag;
	}
	
	public void setCrossSellingFlag(Boolean crossSellingFlag) 
	{
		this.crossSellingFlag = crossSellingFlag;
	}
}