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
import com.enhancesys.integration.snoc.services.layers.SnocServices;
import com.enhancesys.integration.snoc.services.util.IntegrationUtilManagement;
import com.enhancesys.integration.snoc.services.util.IntegrationUtilManagementImpl;

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
 * 1       Mar 2, 2020			Vinayak Mahadev
 * 	-- Base Release
 * </pre>
 * 
 * <br>
 */
@Configuration
//@EnableWs
public class SnocBeans extends WsConfigurerAdapter 
{


	@Autowired
	private Bus bus;

	@Autowired
	private MetricsProvider metricsProvider;
	

	@Autowired
	private SnocServices snocServices;

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
		EndpointImpl endpoint = new EndpointImpl(bus, new IntegrationManagementImpl(snocServices), null, null, new MetricsFeature[]{
				new MetricsFeature(metricsProvider)
		});
		endpoint.publish("/IntegrationManagement");
		return endpoint;
	}

	@Bean
	public IntegrationManagementLocal getIntegrationManagementLocal()
	{
		return new IntegrationManagementImpl(snocServices);
	}

	@Bean
	public ExternalInterfaceLocal getExternalInterfaceLocal()
	{
		return new ExternalInterfaceImpl();
	}

	@Bean
	public IntegrationUtilManagement getIntegrationUtilManagement() {
		return new IntegrationUtilManagementImpl();
	}
}
