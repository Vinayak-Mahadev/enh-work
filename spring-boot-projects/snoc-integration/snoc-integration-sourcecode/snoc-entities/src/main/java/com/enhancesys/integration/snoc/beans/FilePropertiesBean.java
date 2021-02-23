package com.enhancesys.integration.snoc.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <b>Purpose:</b><br>
 * Declaration of the FilePropertiesBean service parameter properties..<br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * Enhancesys Innovations 2015<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 * Sl No   Modified Date        Author</b>
 * ==============================================
 * 1       11-02-2015          Ramana Rao K
 *   -- Base Release
 * 2	   27-02-2015		   Suresh Upparu
 * -- Added new properties..
 */
@XmlRootElement(name = "filePropertiesBean")
public class FilePropertiesBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String remoteHost;
	private Integer remotePort;
	private String securityPrincipal;
	private String securityCredentials;
	private String securityCredentialsPath;
	private String clientId;
	// private String clientName;
	private String remoteDir;
	private String localDir;

	private String remoteFileNameFormat;
	private String remoteFileFormat;
	private String controlFileFormat;

	private String localControlDir;
	private String remoteControlDir;

	private String localRejectedDir;
	private String remoteRejectedDir;

	private String localRejectedBackupDir;
	private String remoteRejectedControlDir;

	private String localBackupDir;
	private String remoteBackupDir;
	
	private String localFilterBackupDir;
	private String localFilterControlBackupDir;
	
	private String localControlBackupDir;
	private String remoteControlBackupDir;

	private String localRejectedControlDir;
	private String remoteRejectedBackupDir;

	private String localRejectedControlBackupDir;
	private String remoteRejectedControlBackupDir;

	private String controlFileName;
	private String fileName;
	private String fileFormat;
	private Long fileRowCount;

	private String destHost;
	private Integer destPort;
	private String destSecurityPrincipal;
	private String destSecurityCredentials;
	private String destSecurityCredentialsPath;
	private String destDir;

	private String fileHeaders;
	private String dateFormat;
	private Long recordsLimit;
	private Long fileRecordsLimit;

	private String isSFTP;

	private String dateDuration;

	private String excludedOperatorTypes;
	private String excludedOrganizationTypes;

	private String csvDelimeter;
	private String collectionName;
	private String collectionNamePattern;
	private String schemaName;
	private String dailyDumpConf;
	private String queryField;
	private String filterQuery;
	private String fileNameSequence;
	private String fileDataInsertQuery;
	private String fileDataInsertQueryParamConfig;
	private String fileDataBatchSize;
	private String validationConfigDelimeter;
	private String fieldValidationConfig;
	private String processorClass;
	private String mongoConfig;
	private String sendControlFileFlag;
	private String processName;
	private String mongoConnectionId;
	private String processorUrl;
	private String query;
	private String queryFieldConf;
	
	private String lookupConf;
	private String dateConf;
	private String fieldLookupConf;
	private Boolean crossSellingFlag;
	private Long dumpDateCount;
	
	@XmlElement(name = "files")
	private Set<FileBean> files;

	@XmlElement(name = "servers")
	private Set<ServerBean> servers;

	public String getRemoteHost()
	{
		return remoteHost;
	}

	public void setRemoteHost(String remoteHost)
	{
		this.remoteHost = remoteHost;
	}

	public Integer getRemotePort()
	{
		return remotePort;
	}

	public void setRemotePort(Integer remotePort)
	{
		this.remotePort = remotePort;
	}

	public String getSecurityPrincipal()
	{
		return securityPrincipal;
	}

	public void setSecurityPrincipal(String securityPrincipal)
	{
		this.securityPrincipal = securityPrincipal;
	}

	public String getSecurityCredentials()
	{
		return securityCredentials;
	}

	public void setSecurityCredentials(String securityCredentials)
	{
		this.securityCredentials = securityCredentials;
	}

	public String getClientId()
	{
		return clientId;
	}

	public void setClientId(String clientId)
	{
		this.clientId = clientId;
	}

	public String getRemoteDir()
	{
		return remoteDir;
	}

	public void setRemoteDir(String remoteDir)
	{
		this.remoteDir = remoteDir;
	}

	public String getLocalDir()
	{
		return localDir;
	}

	public void setLocalDir(String localDir)
	{
		this.localDir = localDir;
	}

	public String getRemoteFileNameFormat()
	{
		return remoteFileNameFormat;
	}

	public void setRemoteFileNameFormat(String remoteFileNameFormat)
	{
		this.remoteFileNameFormat = remoteFileNameFormat;
	}

	public String getRemoteFileFormat()
	{
		return remoteFileFormat;
	}

	public void setRemoteFileFormat(String remoteFileFormat)
	{
		this.remoteFileFormat = remoteFileFormat;
	}

	public String getControlFileFormat()
	{
		return controlFileFormat;
	}

	public void setControlFileFormat(String controlFileFormat)
	{
		this.controlFileFormat = controlFileFormat;
	}

	public String getLocalControlDir()
	{
		return localControlDir;
	}

	public void setLocalControlDir(String localControlDir)
	{
		this.localControlDir = localControlDir;
	}

	public String getRemoteControlDir()
	{
		return remoteControlDir;
	}

	public void setRemoteControlDir(String remoteControlDir)
	{
		this.remoteControlDir = remoteControlDir;
	}

	public String getRemoteRejectedDir()
	{
		return remoteRejectedDir;
	}

	public void setRemoteRejectedDir(String remoteRejectedDir)
	{
		this.remoteRejectedDir = remoteRejectedDir;
	}

	public String getRemoteRejectedControlDir()
	{
		return remoteRejectedControlDir;
	}

	public void setRemoteRejectedControlDir(String remoteRejectedControlDir)
	{
		this.remoteRejectedControlDir = remoteRejectedControlDir;
	}

	public String getLocalBackupDir()
	{
		return localBackupDir;
	}

	public void setLocalBackupDir(String localBackupDir)
	{
		this.localBackupDir = localBackupDir;
	}

	public String getRemoteBackupDir()
	{
		return remoteBackupDir;
	}

	public void setRemoteBackupDir(String remoteBackupDir)
	{
		this.remoteBackupDir = remoteBackupDir;
	}

	public String getLocalControlBackupDir()
	{
		return localControlBackupDir;
	}

	public void setLocalControlBackupDir(String localControlBackupDir)
	{
		this.localControlBackupDir = localControlBackupDir;
	}

	public String getRemoteControlBackupDir()
	{
		return remoteControlBackupDir;
	}

	public void setRemoteControlBackupDir(String remoteControlBackupDir)
	{
		this.remoteControlBackupDir = remoteControlBackupDir;
	}

	public String getControlFileName()
	{
		return controlFileName;
	}

	public void setControlFileName(String controlFileName)
	{
		this.controlFileName = controlFileName;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public String getFileFormat()
	{
		return fileFormat;
	}

	public void setFileFormat(String fileFormat)
	{
		this.fileFormat = fileFormat;
	}

	public Long getFileRowCount()
	{
		return fileRowCount;
	}

	public void setFileRowCount(Long fileRowCount)
	{
		this.fileRowCount = fileRowCount;
	}

	public Set<FileBean> getFiles()
	{
		if (files == null)
		{
			files = new HashSet<FileBean>();
		}

		return files;
	}

	public Set<ServerBean> getServers()
	{
		if (servers == null)
		{
			servers = new HashSet<ServerBean>();
		}

		return servers;
	}

	public String getLocalRejectedBackupDir()
	{
		return localRejectedBackupDir;
	}

	public void setLocalRejectedBackupDir(String localRejectedBackupDir)
	{
		this.localRejectedBackupDir = localRejectedBackupDir;
	}

	public String getRemoteRejectedBackupDir()
	{
		return remoteRejectedBackupDir;
	}

	public void setRemoteRejectedBackupDir(String remoteRejectedBackupDir)
	{
		this.remoteRejectedBackupDir = remoteRejectedBackupDir;
	}

	public String getLocalRejectedDir()
	{
		return localRejectedDir;
	}

	public void setLocalRejectedDir(String localRejectedDir)
	{
		this.localRejectedDir = localRejectedDir;
	}

	public String getLocalRejectedControlDir()
	{
		return localRejectedControlDir;
	}

	public void setLocalRejectedControlDir(String localRejectedControlDir)
	{
		this.localRejectedControlDir = localRejectedControlDir;
	}

	public String getLocalRejectedControlBackupDir()
	{
		return localRejectedControlBackupDir;
	}

	public void setLocalRejectedControlBackupDir(String localRejectedControlBackupDir)
	{
		this.localRejectedControlBackupDir = localRejectedControlBackupDir;
	}

	public String getRemoteRejectedControlBackupDir()
	{
		return remoteRejectedControlBackupDir;
	}

	public void setRemoteRejectedControlBackupDir(String remoteRejectedControlBackupDir)
	{
		this.remoteRejectedControlBackupDir = remoteRejectedControlBackupDir;
	}

	public String getDestHost()
	{
		return destHost;
	}

	public void setDestHost(String destHost)
	{
		this.destHost = destHost;
	}

	public Integer getDestPort()
	{
		return destPort;
	}

	public void setDestPort(Integer destPort)
	{
		this.destPort = destPort;
	}

	public String getDestSecurityPrincipal()
	{
		return destSecurityPrincipal;
	}

	public void setDestSecurityPrincipal(String destSecurityPrincipal)
	{
		this.destSecurityPrincipal = destSecurityPrincipal;
	}

	public String getDestSecurityCredentials()
	{
		return destSecurityCredentials;
	}

	public void setDestSecurityCredentials(String destSecurityCredentials)
	{
		this.destSecurityCredentials = destSecurityCredentials;
	}

	public String getDestDir()
	{
		return destDir;
	}

	public void setDestDir(String destDir)
	{
		this.destDir = destDir;
	}

	public String getFileHeaders()
	{
		return fileHeaders;
	}

	public void setFileHeaders(String fileHeaders)
	{
		this.fileHeaders = fileHeaders;
	}

	public Long getRecordsLimit()
	{
		return recordsLimit;
	}

	public void setRecordsLimit(Long recordsLimit)
	{
		this.recordsLimit = recordsLimit;
	}
	
	public Long getFileRecordsLimit()
	{
		return fileRecordsLimit;
	}

	public void setFileRecordsLimit(Long fileRecordsLimit)
	{
		this.fileRecordsLimit = fileRecordsLimit;
	}

	public String getDateFormat()
	{
		return dateFormat;
	}

	public void setDateFormat(String dateFormat)
	{
		this.dateFormat = dateFormat;
	}

	// public String getClientName() {
	// return clientName;
	// }
	//
	// public void setClientName(String clientName) {
	// this.clientName = clientName;
	// }

	public String getSecurityCredentialsPath()
	{
		return securityCredentialsPath;
	}

	public void setSecurityCredentialsPath(String securityCredentialsPath)
	{
		this.securityCredentialsPath = securityCredentialsPath;
	}

	public String getDestSecurityCredentialsPath()
	{
		return destSecurityCredentialsPath;
	}

	public void setDestSecurityCredentialsPath(String destSecurityCredentialsPath)
	{
		this.destSecurityCredentialsPath = destSecurityCredentialsPath;
	}

	public String getIsSFTP()
	{
		return isSFTP;
	}

	public void setIsSFTP(String isSFTP)
	{
		this.isSFTP = isSFTP;
	}

	public String getDateDuration()
	{
		return dateDuration;
	}

	public void setDateDuration(String dateDuration)
	{
		this.dateDuration = dateDuration;
	}

	public String getExcludedOperatorTypes()
	{
		return excludedOperatorTypes;
	}

	public void setExcludedOperatorTypes(String excludedOperatorTypes)
	{
		this.excludedOperatorTypes = excludedOperatorTypes;
	}

	public String getExcludedOrganizationTypes()
	{
		return excludedOrganizationTypes;
	}

	public void setExcludedOrganizationTypes(String excludedOrganizationTypes)
	{
		this.excludedOrganizationTypes = excludedOrganizationTypes;
	}

	public String getCsvDelimeter()
	{
		return csvDelimeter;
	}

	public void setCsvDelimeter(String csvDelimeter)
	{
		this.csvDelimeter = csvDelimeter;
	}

	public String getCollectionName()
	{
		return collectionName;
	}

	public void setCollectionName(String collectionName)
	{
		this.collectionName = collectionName;
	}
	
	public String getCollectionNamePattern()
	{
		return collectionNamePattern;
	}
	
	public void setCollectionNamePattern(String collectionNamePattern)
	{
		this.collectionNamePattern = collectionNamePattern;
	}

	public String getSchemaName()
	{
		return schemaName;
	}

	public void setSchemaName(String schemaName)
	{
		this.schemaName = schemaName;
	}

	public String getDailyDumpConf()
	{
		return dailyDumpConf;
	}

	public void setDailyDumpConf(String dailyDumpConf)
	{
		this.dailyDumpConf = dailyDumpConf;
	}

	public String getQueryField()
	{
		return queryField;
	}

	public void setQueryField(String queryField)
	{
		this.queryField = queryField;
	}

	public String getFilterQuery()
	{
		return filterQuery;
	}

	public void setFilterQuery(String filterQuery)
	{
		this.filterQuery = filterQuery;
	}

	public String getFileNameSequence()
	{
		return fileNameSequence;
	}

	public void setFileNameSequence(String fileNameSequence)
	{
		this.fileNameSequence = fileNameSequence;
	}

	public String getFileDataInsertQuery()
	{
		return fileDataInsertQuery;
	}

	public void setFileDataInsertQuery(String fileDataInsertQuery)
	{
		this.fileDataInsertQuery = fileDataInsertQuery;
	}

	public String getFileDataInsertQueryParamConfig()
	{
		return fileDataInsertQueryParamConfig;
	}

	public void setFileDataInsertQueryParamConfig(String fileDataInsertQueryParamConfig)
	{
		this.fileDataInsertQueryParamConfig = fileDataInsertQueryParamConfig;
	}

	public String getFileDataBatchSize()
	{
		return fileDataBatchSize;
	}

	public void setFileDataBatchSize(String fileDataBatchSize)
	{
		this.fileDataBatchSize = fileDataBatchSize;
	}

	public String getValidationConfigDelimeter()
	{
		return validationConfigDelimeter;
	}

	public void setValidationConfigDelimeter(String validationConfigDelimeter)
	{
		this.validationConfigDelimeter = validationConfigDelimeter;
	}

	public String getFieldValidationConfig()
	{
		return fieldValidationConfig;
	}

	public void setFieldValidationConfig(String fieldValidationConfig)
	{
		this.fieldValidationConfig = fieldValidationConfig;
	}

	public String getProcessorClass()
	{
		return processorClass;
	}

	public void setProcessorClass(String processorClass)
	{
		this.processorClass = processorClass;
	}

	public String getMongoConfig()
	{
		return mongoConfig;
	}

	public void setMongoConfig(String mongoConfig)
	{
		this.mongoConfig = mongoConfig;
	}

	public String getSendControlFileFlag()
	{
		return sendControlFileFlag;
	}

	public void setSendControlFileFlag(String sendControlFileFlag)
	{
		this.sendControlFileFlag = sendControlFileFlag;
	}
	
	public String getProcessName()
	{
		return processName;
	}

	public void setProcessName(String processName)
	{
		this.processName = processName;
	}
	
	public String getMongoConnectionId() 
	{
		return mongoConnectionId;
	}

	public void setMongoConnectionId(String mongoConnectionId) 
	{
		this.mongoConnectionId = mongoConnectionId;
	}

	public String getProcessorUrl()
	{
		return processorUrl;
	}

	public void setProcessorUrl(String processorUrl)
	{
		this.processorUrl = processorUrl;
	}

	public String getQuery() 
	{
		return query;
	}

	public void setQuery(String query) 
	{
		this.query = query;
	}
	
	public String getLookupConf() 
	{
		return lookupConf;
	}

	public void setLookupConf(String lookupConf) 
	{
		this.lookupConf = lookupConf;
	}
	
	public String getDateConf() 
	{
		return dateConf;
	}

	public void setDateConf(String dateConf) 
	{
		this.dateConf = dateConf;
	}
	
	public String getQueryFieldConf()
	{
		return queryFieldConf;
	}
	
	public void setQueryFieldConf(String queryFieldConf)
	{
		this.queryFieldConf = queryFieldConf;
	}
	
	public String getFieldLookupConf()
	{
		return fieldLookupConf;
	}
	
	public void setFieldLookupConf(String fieldLookupConf)
	{
		this.fieldLookupConf = fieldLookupConf;
	}
	
	public Boolean getCrossSellingFlag() 
	{
		return crossSellingFlag;
	}

	public void setCrossSellingFlag(Boolean crossSellingFlag)
	{
		this.crossSellingFlag = crossSellingFlag;
	}

	public String getLocalFilterBackupDir() 
	{
		return localFilterBackupDir;
	}

	public void setLocalFilterBackupDir(String localFilterBackupDir) 
	{
		this.localFilterBackupDir = localFilterBackupDir;
	}

	public String getLocalFilterControlBackupDir() 
	{
		return localFilterControlBackupDir;
	}

	public void setLocalFilterControlBackupDir(String localFilterControlBackupDir) 
	{
		this.localFilterControlBackupDir = localFilterControlBackupDir;
	}

	public Long getDumpDateCount() {
		return dumpDateCount;
	}

	public void setDumpDateCount(Long dumpDateCount) {
		this.dumpDateCount = dumpDateCount;
	}
}