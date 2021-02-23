package com.enhancesys.integration.snoc.services.conf;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.metrics.MetricsFeature;
import org.apache.cxf.metrics.MetricsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;

import com.enhancesys.integration.snoc.services.ExternalInterfaceImpl;
import com.enhancesys.integration.snoc.services.IntegrationManagementImpl;
import com.enhancesys.integration.snoc.services.interfaces.ExternalInterfaceLocal;
import com.enhancesys.integration.snoc.services.interfaces.IntegrationManagementLocal;

@Configuration
//@EnableWs
public class SnocBeans extends WsConfigurerAdapter 
{


	@Autowired
	private Bus bus;

	@Autowired
	private MetricsProvider metricsProvider;

	@Bean("ExternalInterface")
	public Endpoint ExternalInterfaceEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, new ExternalInterfaceImpl(), null, null, new MetricsFeature[]{
				new MetricsFeature(metricsProvider)
		});
		endpoint.publish("/ExternalInterface");
		return endpoint;
	}

	@Bean("IntegrationManagement")
	public Endpoint IntegrationManagementEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, new IntegrationManagementImpl(), null, null, new MetricsFeature[]{
				new MetricsFeature(metricsProvider)
		});
		endpoint.publish("/IntegrationManagement");
		return endpoint;
	}

	@Bean
	public IntegrationManagementLocal getIntegrationManagementLocal()
	{
		return new IntegrationManagementImpl();
	}

	@Bean
	public ExternalInterfaceLocal getExternalInterfaceLocal()
	{
		return new ExternalInterfaceImpl();
	}

}
