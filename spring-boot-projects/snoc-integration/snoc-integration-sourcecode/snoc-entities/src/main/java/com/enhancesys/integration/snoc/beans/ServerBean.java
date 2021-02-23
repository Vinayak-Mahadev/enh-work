package com.enhancesys.integration.snoc.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * <b>Purpose:</b><br>
 * Declaration of the ServerBean service parameter properties..<br>
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
 * 1       18-04-2018		   Suresh Upparu
 *   -- Base Release
 */
@XmlRootElement(name = "serverBean")
public class ServerBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String remoteHost;
	private Integer remotePort;
	private String securityPrincipal;
	private String securityCredentials;
	private String securityCredentialsPath;
	private String remoteDir;
	private String remoteControlDir;
	private String sendControlFileFlag;
	
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
	
	public String getSecurityCredentialsPath() 
	{
		return securityCredentialsPath;
	}

	public void setSecurityCredentialsPath(String securityCredentialsPath) 
	{
		this.securityCredentialsPath = securityCredentialsPath;
	}

	public String getRemoteDir() 
	{
		return remoteDir;
	}

	public void setRemoteDir(String remoteDir) 
	{
		this.remoteDir = remoteDir;
	}

	public String getRemoteControlDir() 
	{
		return remoteControlDir;
	}

	public void setRemoteControlDir(String remoteControlDir) 
	{
		this.remoteControlDir = remoteControlDir;
	}
	
	public String getSendControlFileFlag()
	{
		return sendControlFileFlag;
	}

	public void setSendControlFileFlag(String sendControlFileFlag)
	{
		this.sendControlFileFlag = sendControlFileFlag;
	}
}