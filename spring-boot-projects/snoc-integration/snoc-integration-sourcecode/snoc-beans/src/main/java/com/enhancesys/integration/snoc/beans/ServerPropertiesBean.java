package com.enhancesys.integration.snoc.beans;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * <b>Purpose:</b><br>
 * Declaration of the ServerPropertiesBean service parameter properties..<br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * Enhancesys Innovations 2014<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 * Sl No   Modified Date        Author</b>
 * ==============================================
 * 1       28-10-2014          Vinayak Mahadev
 * -- Base Release
 */
@XmlRootElement(name = "serverPropertiesBean")
public class ServerPropertiesBean 
{
	private String initialContextFactory;
	private String providerUrl;
	private String urlPkgPrefixes;
	private String securityAuthentication;
	private String securityPrincipal;
	private String securityCredentials;
	private String remoteConnectionFactory;
	private String queueName;
	private String protocol;
	private String host;
	private String jmsServer;
	private String port;
	private Long waitingTime;
	
	public String getInitialContextFactory() 
	{
		return initialContextFactory;
	}
	
	public void setInitialContextFactory(String initialContextFactory) 
	{
		this.initialContextFactory = initialContextFactory;
	}
	
	public String getProviderUrl() 
	{
		return providerUrl;
	}
	
	public void setProviderUrl(String providerUrl) 
	{
		this.providerUrl = providerUrl;
	}
	
	public String getUrlPkgPrefixes() 
	{
		return urlPkgPrefixes;
	}
	
	public void setUrlPkgPrefixes(String urlPkgPrefixes) 
	{
		this.urlPkgPrefixes = urlPkgPrefixes;
	}
	
	public String getSecurityAuthentication() 
	{
		return securityAuthentication;
	}
	
	public void setSecurityAuthentication(String securityAuthentication) 
	{
		this.securityAuthentication = securityAuthentication;
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
	
	public String getRemoteConnectionFactory() 
	{
		return remoteConnectionFactory;
	}
	
	public void setRemoteConnectionFactory(String remoteConnectionFactory) 
	{
		this.remoteConnectionFactory = remoteConnectionFactory;
	}

	public String getQueueName() 
	{
		return queueName;
	}

	public void setQueueName(String queueName) 
	{
		this.queueName = queueName;
	}

	public String getProtocol() 
	{
		return protocol;
	}

	public void setProtocol(String protocol) 
	{
		this.protocol = protocol;
	}

	public String getHost() 
	{
		return host;
	}

	public void setHost(String host) 
	{
		this.host = host;
	}

	public String getJmsServer() 
	{
		return jmsServer;
	}

	public void setJmsServer(String jmsServer) 
	{
		this.jmsServer = jmsServer;
	}

	public String getPort() 
	{
		return port;
	}

	public void setPort(String port) 
	{
		this.port = port;
	}

	public Long getWaitingTime() 
	{
		return waitingTime;
	}

	public void setWaitingTime(Long waitingTime) 
	{
		this.waitingTime = waitingTime;
	}
}
