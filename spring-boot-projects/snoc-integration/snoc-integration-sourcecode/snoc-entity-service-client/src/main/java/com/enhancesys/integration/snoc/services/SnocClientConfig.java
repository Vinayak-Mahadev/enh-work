package com.enhancesys.integration.snoc.services;

import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import cxf.com.enhancesys.entities.schema.integration.IntegrationManagement;

@Configuration
public class SnocClientConfig
{

	@Value("${client.integrationManagement.address}")
	private String address;

	@Value("${client.integrationManagement.username}")
	private String userName;

	@Value("${client.integrationManagement.password}")
	private String password;

	@Bean(name = "IntegrationManagementProxy")
	public IntegrationManagement IntegrationManagementProxy() 
	{
		JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
		jaxWsProxyFactoryBean.setServiceClass(IntegrationManagement.class);
		jaxWsProxyFactoryBean.setAddress(address);
		return (IntegrationManagement) jaxWsProxyFactoryBean.create();
	}

	@Bean
	public Client integrationManagementClientProxy() 
	{
		return ClientProxy.getClient(IntegrationManagementProxy());
	}

	@Bean
	@Primary
	public HTTPConduit integrationManagementConduit() 
	{
		HTTPConduit httpConduit = (HTTPConduit) integrationManagementClientProxy().getConduit();
		httpConduit.setAuthorization(basicAuthorization());
		return httpConduit;
	}

	@Bean
	@Primary
	public AuthorizationPolicy basicAuthorization() 
	{
		AuthorizationPolicy authorizationPolicy = new AuthorizationPolicy();
		System.out.println(userName + ":" + password + "@" + address);
		authorizationPolicy.setUserName(userName);
		authorizationPolicy.setPassword(password);
		authorizationPolicy.setAuthorizationType("Basic");
		return authorizationPolicy;
	}

}