package com.enhancesys.integration.snoc.beans;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <b>Purpose:</b>
 * <br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * Enhancesys Innovations Private Limited<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 * Sl No   Modified Date		Author</b>
 * ==============================================
 * 1       Dec 2, 2014			Parthipan Rajagopal
 * 	-- Base Release
 * </pre>
 * 
 * <br>
 */
@XmlRootElement(name="pullDataToFileBean")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pullDataToFileBean")
public class PullDataToFileBean implements Serializable
{
	private static final long serialVersionUID = -145966191202390049L;

	@XmlElement(name = "interfaceId")
	protected Long interfaceId; 
	
	@XmlElement(name = "primaryKeys")
	protected List<String> primaryKeys;
	
	@XmlElement(name = "fileHeader")
	protected String fileHeader;
	
	@XmlElement(name = "fields")
	protected List<String> fields;
	
	@XmlElement(name = "remoteHost")
	protected String remoteHost;
	
	@XmlElement(name = "remotePort")
	protected Long remotePort;
	
	@XmlElement(name = "remoteUser")
	protected String remoteUser;
	
	@XmlElement(name = "remotePassword")
	protected String remotePassword;
	
	@XmlElement(name = "remotePasswordPath")
	protected String remotePasswordPath;
	
	@XmlElement(name = "remoteDir")
	protected String remoteDir;
	
	@XmlElement(name = "remoteCtlDir")
	protected String remoteCtlDir;
	
	@XmlElement(name = "localDir")
	protected String localDir;
	
	@XmlElement(name = "localBkpDir")
	protected String localBkpDir;
	
	@XmlElement(name = "localCtlBkpDir")
	protected String localCtlBkpDir;
	
	@XmlElement(name = "localCtlDir")
	protected String localCtlDir;
	
	@XmlElement(name = "fileName")
	protected String fileName;
	
	
	@XmlElement(name = "fileNameFormat")
	protected String fileNameFormat;
	
	@XmlElement(name = "fileFormat")
	protected String fileFormat;
	
	@XmlElement(name = "fileLocation")
	protected String fileLocation;
	
	@XmlElement(name = "controlFileFormat")
	protected String controlFileFormat;
	
	@XmlElement(name = "controlFileLocation")
	protected String controlFileLocation;
	
	@XmlElement(name = "csvDelimeter")
	protected String csvDelimeter;
	
	@XmlElement(name = "file")
	protected File file;
	
	@XmlElement(name = "totalCount")
	protected Long totalCount;
	
	@XmlElement(name = "fileId")
	protected Long fileId;
	
	@XmlElement(name = "uploadedBy")
	protected String uploadedBy;

	public Long getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(Long interfaceId) {
		this.interfaceId = interfaceId;
	}

	public List<String> getPrimaryKeys() {
		return primaryKeys;
	}

	public void setPrimaryKeys(List<String> primaryKeys) {
		this.primaryKeys = primaryKeys;
	}

	public String getFileHeader() {
		return fileHeader;
	}

	public void setFileHeader(String fileHeader) {
		this.fileHeader = fileHeader;
	}

	public List<String> getFields() {
		return fields;
	}

	public void setFields(List<String> fields) {
		this.fields = fields;
	}

	public String getRemoteHost() {
		return remoteHost;
	}

	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}

	public Long getRemotePort() {
		return remotePort;
	}

	public void setRemotePort(Long remotePort) {
		this.remotePort = remotePort;
	}

	public String getRemoteUser() {
		return remoteUser;
	}

	public void setRemoteUser(String remoteUser) {
		this.remoteUser = remoteUser;
	}

	public String getRemotePassword() {
		return remotePassword;
	}

	public void setRemotePassword(String remotePassword) {
		this.remotePassword = remotePassword;
	}

	public String getRemoteDir() {
		return remoteDir;
	}

	public void setRemoteDir(String remoteDir) {
		this.remoteDir = remoteDir;
	}

	public String getRemoteCtlDir() {
		return remoteCtlDir;
	}

	public void setRemoteCtlDir(String remoteCtlDir) {
		this.remoteCtlDir = remoteCtlDir;
	}

	public String getLocalDir() {
		return localDir;
	}

	public void setLocalDir(String localDir) {
		this.localDir = localDir;
	}

	public String getLocalBkpDir() {
		return localBkpDir;
	}

	public void setLocalBkpDir(String localBkpDir) {
		this.localBkpDir = localBkpDir;
	}

	public String getLocalCtlBkpDir() {
		return localCtlBkpDir;
	}

	public void setLocalCtlBkpDir(String localCtlBkpDir) {
		this.localCtlBkpDir = localCtlBkpDir;
	}

	public String getLocalCtlDir() {
		return localCtlDir;
	}

	public void setLocalCtlDir(String localCtlDir) {
		this.localCtlDir = localCtlDir;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileNameFormat() {
		return fileNameFormat;
	}

	public void setFileNameFormat(String fileNameFormat) {
		this.fileNameFormat = fileNameFormat;
	}

	public String getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public String getControlFileFormat() {
		return controlFileFormat;
	}

	public void setControlFileFormat(String controlFileFormat) {
		this.controlFileFormat = controlFileFormat;
	}

	public String getControlFileLocation() {
		return controlFileLocation;
	}

	public void setControlFileLocation(String controlFileLocation) {
		this.controlFileLocation = controlFileLocation;
	}

	public String getCsvDelimeter() {
		return csvDelimeter;
	}

	public void setCsvDelimeter(String csvDelimeter) {
		this.csvDelimeter = csvDelimeter;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	
	public String getRemotePasswordPath() {
		return remotePasswordPath;
	}

	public void setRemotePasswordPath(String remotePasswordPath) {
		this.remotePasswordPath = remotePasswordPath;
	}

	public String getUploadedBy() {
		return uploadedBy;
	}

	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}
}

